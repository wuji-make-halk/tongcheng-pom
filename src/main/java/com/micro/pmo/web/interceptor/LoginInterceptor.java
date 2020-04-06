package com.micro.pmo.web.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.micro.pmo.commons.exception.PmoException;
import com.micro.pmo.commons.utils.CommonUtils;
import com.micro.pmo.commons.utils.JwtUtils;
import com.micro.pmo.commons.utils.ResultState;
import com.micro.pmo.moudle.customer.utils.CusUtils;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//如果是需要放行的url就不处理日志
		String uri = request.getRequestURI();
		
		String method = request.getMethod();
		String addr = CommonUtils.getIpAddr(request);
		
		 // 从 http 请求头中取出 token
        String token = request.getHeader(CusUtils.cusToken);

        //如果注解的值为true就进行token校验
        if (StringUtils.isBlank(token)) {
            throw new PmoException(ResultState.TOKEN_ERROR);
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception ex){
            throw new PmoException(ResultState.TOKEN_ERROR);
        }
		// 打印日志
		if (log.isInfoEnabled()) {
			StringBuilder sb = new StringBuilder("clientAddr=").append(addr)
					.append(", method=").append(method)
					.append(", url=").append(uri)
					.append(", params=").append(getParams(request));
			log.debug("收到用户请求: {}", sb.toString());
		}
		return true;
	}

	public String getParams(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
	 
		Enumeration<String> e = request.getParameterNames();
		
		if (e.hasMoreElements()) {
			
			while (e.hasMoreElements()) {
				String name = e.nextElement();
				String[] values = request.getParameterValues(name);
				if (values.length == 1) {
					map.put(name, values[0]);

				} else {
					StringBuilder sb = new StringBuilder();
					sb.append("{");
					for (int i = 0; i < values.length; i++) {
						if (i > 0)
							sb.append(",");
						sb.append(values[i]);
					}
					sb.append("}");

					map.put(name, sb.toString());
				}

			}

		}
		return JSONObject.toJSONString(map);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// do nothing
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		//如果是需要放行的url就不处理日志
		/*String uri = request.getRequestURI();
		if(passUrls.contains(uri)){
			return;
		}*/

	}

}
