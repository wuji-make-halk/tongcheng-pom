package com.micro.pmo.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CacheUtils;
import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.JwtUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.user.utils.UserUtils;

/***
 * 后台管理用户校验
 * @author rao.bo
 *
 */
@Service
@Configuration
public class LoginAndminInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(LoginAndminInterceptor.class);
	
	@Value("${web.auth.timeout.ms}")
	private int authTimeout;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//如果是需要放行的url就不处理日志
		String uri = request.getRequestURI();
		
		String method = request.getMethod();
		String addr = CommonUtils.getIpAddr(request);
		
		 // 从 http 请求头中取出 token
	    String token = request.getHeader(UserUtils.userToken);
	
	    //如果注解的值为true就进行token校验
	
	    if (token == null) {
	        throw new PmoException(ResultState.TOKEN_ERROR);
	    }
	    try {
	        JwtUtils.parseJWT(token);
	    } catch (Exception ex){
	        throw new PmoException(ResultState.TOKEN_ERROR);
	    }
	    //处理登录用户
	    Object user = CacheUtils.get(CacheUtils.USER_CACHE, CacheUtils.USER_CACHE_ID + token);
	    if(user == null){
	    	 throw new PmoException(ResultState.LOGIN_INVALID);
	    }
		// 打印日志
		if (log.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder("clientAddr=").append(addr).append(", method=").append(method).append(", url=").append(uri);
			log.debug("收到用户请求: {}", sb.toString());
		}
		
	return true;

		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		/*do nothing*/
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		/*do nothing*/
	}

}
