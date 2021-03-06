package supsvc.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

// This Servlet Filter enables the app to allow all CORS requests during prototyping.
// Swiped from https://github.com/BernhardWenzel/spring-data-rest-knockout-bookmarks 
// Thanks Bernhard.
// TODO: LOCK IT DOWN BEFORE PROD

@Component
public class CORSFilter implements Filter 
{
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin" , "*"										);
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, OPTIONS, DELETE"	);
		response.setHeader("Access-Control-Max-Age"		 , "3600"									);
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with"						);
		response.setHeader("Access-Control-Allow-Headers", "Content-Type"							);
		chain.doFilter(req, res);
	}
	
	public void init(FilterConfig filterConfig) 
	{
		
	}
	
	public void destroy() 
	{
		
	}

}
