package com.cn.filter;

import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  

public class SetCharacterEncodingFilter implements Filter {  
	  
   
    public void destroy() {  
    }  
  
    /* 执行过滤操作 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
          
    	 request.setCharacterEncoding("utf-8");  
    	 response.setCharacterEncoding("utf-8");
         chain.doFilter(request, response);  
    }  
  
    /* 初始化过滤器 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
     */  
    public void init(FilterConfig filterConfig) throws ServletException {  
        
    }  
}  
