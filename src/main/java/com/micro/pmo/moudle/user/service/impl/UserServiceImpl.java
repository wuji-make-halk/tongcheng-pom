package com.micro.pmo.moudle.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.JwtUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.commons.utils.UUIDUtils;
import com.micro.pmo.mapper.UserMapper;
import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.service.UserService;
import com.micro.pmo.moudle.user.utils.UserUtils;
import com.micro.pmo.moudle.user.vo.LoginUser;
import com.micro.pmo.moudle.user.vo.UserPassword;
import com.micro.pmo.moudle.user.vo.UserQueryVo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Value("${qiniuyun.img_url}")
	private String defaultImgUrl;
	
	
	@Override
	public User userLogin(LoginUser loginUser) {
		User user = userMapper.getUserByLoginName(loginUser.getLoginName());
		//未找到用户
		if(user == null){
			throw new PmoException(ResultState.NOT_FIND_USER);
		}
		//账号冻结
		if(user.getUserStatus() == 1){
			throw new PmoException(ResultState.USER_FREEZING);
		}
		String password = UUIDUtils.MD5(loginUser.getPassword());
		//判断密码
		if(!StringUtils.equals(password, user.getPassword())){
			throw new PmoException(ResultState.USER_LOGIN_FAIL);
		}
		//都校验通过加入缓存，生成pcToken
		//验证通过后生成
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("id", user.getUserId());
		//时间设置
		String userToken = JwtUtils.createJWT(1000*60*60*60, claims);
		user.setPassword(null);
		user.setUserToken(userToken);
		CacheUtils.put(CacheUtils.USER_CACHE, CacheUtils.USER_CACHE_ID + userToken, user);
		return user;
	}


	@Override
	public void addRegionUser(User user) {
		//判断登录名
		User oldUser = userMapper.getUserByLoginName(user.getLoginName());
		if(oldUser != null){
			throw new PmoException(ResultState.USER_LOGIN_NAME_ERROR);
		}
		//头像问题
		if(StringUtils.isBlank(user.getUserHeadImg())){
			String userDefault = defaultImgUrl + "userDefault.jpg";
			user.setUserHeadImg(userDefault);
		}
		//创建默认密码
		String password = UUIDUtils.MD5(User.defPassword);
		//其它默认值
		User admin = UserUtils.getUser();
		user.setCreator(admin.getCreator());
		user.setCreateTime(new Date());
		user.setUserType(1);
		user.setUserStatus(0);
		user.setPassword(password);
		userCity(user);
		userMapper.insertUser(user);
	}


	@Override
	public void updateUserPassword(UserPassword userPassword) {
		User user = UserUtils.getUser();
		User oldUser = userMapper.getUserById(user.getUserId());
		String oldPassword = UUIDUtils.MD5(userPassword.getOldPassword());
		//密码判断
		if(!StringUtils.equals(oldUser.getPassword(), oldPassword)){
			throw new PmoException(ResultState.USER_PASSWORD_FAIL);
		}
		String nowPassword = UUIDUtils.MD5(userPassword.getNewPassword());
		userMapper.updateUserPassword(user.getUserId(), nowPassword);
	}


	@Override
	public void logOutUser(Integer userId) {
		 User user = UserUtils.getUser();
		 if(user.getUserId() == userId || user.getUserType() != 0){
			 throw new PmoException(ResultState.USER_DEL_FAIL);
		 }
		userMapper.updateUserStatus(userId, 1);
	}


	

	@Override
	public User userInfo(Integer userId) {
		User user = userMapper.getUserById(userId);
		user.setPassword(null);
		return user;
	}


	@Override
	public List<String> userLoginNames() {
		
		return userMapper.getLoginNames(0);
	}


	@Override
	public void loginOutUser() {
		//如果没登录则不进行操作
		if (!UserUtils.isLogin()) {
			return;
		}
		//清除缓存
		User user = UserUtils.getUser();
		CacheUtils.remove(CacheUtils.USER_CACHE, CacheUtils.USER_CACHE_ID + user.getUserToken());
	}


	@Override
	public PageInfo<User> userList(UserQueryVo userQueryVo) {
		if(userQueryVo.getPageNumKey() == null){
			userQueryVo.setPageNumKey(1);
		}
		if(userQueryVo.getPageSizeKey() == null){
			userQueryVo.setPageSizeKey(20);
		}
		PageHelper.startPage(userQueryVo.getPageNumKey(), userQueryVo.getPageSizeKey());
		userQueryVo.setUserType(1);
		userQueryVo.setUserStatus(0);
		List<User> users = userMapper.getUserList(userQueryVo);
		PageInfo<User> info = new PageInfo<>(users);
		return info;
	}


	@Override
	public void updateUser(User user) {
		if(user.getUserId() == null){
			throw new PmoException(ResultState.PARAM_ERROR);
		}
		UserQueryVo userQueryVo = new UserQueryVo();
		userQueryVo.setUserStatus(0);
		List<User> users = userMapper.getUserList(userQueryVo);
		for (User oldUser : users) {
			//判断登录名
			if(oldUser.getUserId() != user.getUserId() && StringUtils.equals(user.getLoginName(), oldUser.getLoginName())){
				throw new PmoException(ResultState.USER_LOGIN_NAME_ERROR);
			}
		}
		userCity(user);
		userMapper.updateUser(user);
		
	}
	/***
	 * 处理市
	 * @param user
	 */
	private void userCity(User user){
		String city = user.getCity();
		if(StringUtils.isBlank(city)){
			return ;
		}
		if(StringUtils.equals(city, "all")){
			return ;
		}
		int index = city.lastIndexOf("市");
		if(index < 0){
			return ;
		}
		city = city.substring(0, index);
		user.setCity(city);
	}
}
