package com.ezone.ezonista.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PermissionInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			if(uri.startsWith("/post") && uri.startsWith("/user/profile")) {
				response.sendRedirect("/user/signin_view");
				return false;
			}
		}
		else {
			if(uri.startsWith("/user/singin_view") && !uri.startsWith("/user/signup_view")) {
				response.sendRedirect("/post/timeline");
				return false;
			}
		}
		
		return true;
		
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse resposne,
			Object handler,
			ModelAndView modelAndView) {
		
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception ex) {
		
	}

}
