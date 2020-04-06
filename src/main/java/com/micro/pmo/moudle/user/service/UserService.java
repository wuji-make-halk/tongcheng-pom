package com.micro.pmo.moudle.user.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.vo.LoginUser;
import com.micro.pmo.moudle.user.vo.UserPassword;
import com.micro.pmo.moudle.user.vo.UserQueryVo;

/**
 * 后台用户Service
 */
public interface UserService{
	
	/***用户登录**/
	public User userLogin(LoginUser loginUser);
	
	/***
	 * 添加区域经理
	 * @param user
	 */
	public void addRegionUser(User user);
	
	/***
	 * 修改密码
	 * @param userPassword
	 */
	public void updateUserPassword(UserPassword userPassword);
	/***
	 * 注销用户
	 * @param userId
	 */
	public void logOutUser(Integer userId);
	/***
	 * 后台用户列表
	 */
	public PageInfo<User> userList(UserQueryVo userQueryVo);
	/***
	 * 获取用户详情
	 * @param userId
	 * @return
	 */
	public User userInfo(Integer userId);
	/***
	 * 获取所有登录名
	 * @return
	 */
	public List<String> userLoginNames();
	
	/***
	 * 退出
	 */
	public void loginOutUser();
	
	/***
	 * 修改用户信息
	 * @param user
	 */
	public void updateUser(User user);
}
