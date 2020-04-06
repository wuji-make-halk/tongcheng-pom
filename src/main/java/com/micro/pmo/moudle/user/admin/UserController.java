package com.micro.pmo.moudle.user.admin;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.micro.pmo.commons.utils.Result;
import com.micro.pmo.moudle.user.entity.User;
import com.micro.pmo.moudle.user.service.UserService;
import com.micro.pmo.moudle.user.utils.UserUtils;
import com.micro.pmo.moudle.user.vo.LoginUser;
import com.micro.pmo.moudle.user.vo.UserPassword;
import com.micro.pmo.moudle.user.vo.UserQueryVo;

@Validated
@RestController
@RequestMapping("api-pc/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@PostMapping()
	public Result addUser(@Valid @RequestBody User user){
		userService.addRegionUser(user);
		return Result.success();
	}
	
	/***
	 * 获取所有登录名
	 * @return
	 */
	@GetMapping("name-list")
	public Result loginNameList(){
		List<String> names = userService.userLoginNames();
		return Result.success(names);
	}
	
	/***
	 * 登录
	 * @param phone
	 * @return
	 */
	@PostMapping("login")
	public Result userLogin(@Valid @RequestBody LoginUser loginUser){
		User user = userService.userLogin(loginUser);
		return Result.success(user);
	}
	/**
	 * 获取
	 * @param request
	 * @return
	 */
	@GetMapping("info")
	public Result userInfo(){
		User user = UserUtils.getUser();
		return Result.success(user);
	}
	/**
	 * 用户列表
	 * @param userQueryVo
	 * @return
	 */
	@GetMapping("page")
	public Result userList(UserQueryVo userQueryVo){
		PageInfo<User> info = userService.userList(userQueryVo);
		return Result.success(info);
	}
	/***
	 * 修改密码
	 * @param password
	 * @return
	 */
	@PutMapping("modify-password")
	public Result modifyPassword(@Valid @RequestBody UserPassword userPassword){
		userService.updateUserPassword(userPassword);
		return Result.success();
	}
	/***
	 * 注销用户
	 * @param userId
	 * @return
	 */
	@DeleteMapping("del")
	public Result logOutUser(@NotNull(message = "用户id不能为空") Integer userId){
		userService.logOutUser(userId);
		return Result.success();
	}
	/***
	 * 用户退出
	 * @return
	 */
	@PostMapping("login-out")
	public Result loginOut(){
		userService.loginOutUser();
		return Result.success();
	}
	
	/***
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@PutMapping("update")
	public Result updateUser(@Valid @RequestBody User user){
		userService.updateUser(user);
		return Result.success();
	}
}
