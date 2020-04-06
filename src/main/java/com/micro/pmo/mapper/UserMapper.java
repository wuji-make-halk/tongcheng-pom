package com.micro.pmo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.vo.UserQueryVo;

/**
 * 后台用户Mapper接口
 */
public interface UserMapper{
	
	/***通过登录名找用户**/
	public User getUserByLoginName(@Param("loginName")String loginName);
	
	/**添加区域经理***/
	public int insertUser(User user);
	
	/**修改区域经理信息***/
	public int updateUser(User user);
	 
	/**修改密码***/
	@Update("UPDATE sys_user SET password = #{password} WHERE user_id = #{userId}")
	public int updateUserPassword(@Param("userId")Integer userId,@Param("password")String password);
	
	/**注销后台用户**/
	@Update("UPDATE sys_user SET user_status = #{userStatus} WHERE user_id = #{userId}")
	public int updateUserStatus(@Param("userId")Integer userId,@Param("userStatus")Integer userStatus);
	/**后台用户列表**/
	public List<User> getUserList(UserQueryVo userQueryVo);
	/**获取用户信息**/
	public User getUserById(@Param("userId")Integer userId);
	/**获取所有登录用户名***/
	public List<String> getLoginNames(@Param("userStatus")Integer userStatus);
}
