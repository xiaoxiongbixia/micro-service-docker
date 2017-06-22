package com.microservice.base.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 拦截器简单测试
 * @author Administrator
 * 与zuul在同一个服务里的功能，如果通过/zuul/服务名来访问的话，就一定会走拦截器，如果直接访问的话就不走拦截器了。
 * 而从eureka上集成的微服务的话，也可以通过/zuul/+路径来访问，奇怪呢
 *
 */
@Component
public class LoginFilter extends ZuulFilter{

	@Value("${variable.filter-enable}")
	private Boolean filterEnable;
	
	@Override
	public Object run() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		HttpSession session = request.getSession();
		if(request.getRequestURI().contains("login")){
			if(null!=request.getParameter("user"))
				session.setAttribute("user", "username");
		}else if(request.getRequestURI().contains("logout")){
			session.removeAttribute("user");
		}else{
			Object o = session.getAttribute("user");
			if(o==null){
				try {
					RequestContext.getCurrentContext().getResponse().sendRedirect("/zuul/login");
				} catch (IOException e) {
				}
			}
		}
			
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return filterEnable;
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
