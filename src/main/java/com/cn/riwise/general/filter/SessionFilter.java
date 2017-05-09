package com.cn.riwise.general.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 过滤器
 * @author Administrator
 *
 */
public class SessionFilter implements Filter {

	public void destroy() {

	}
    /**
     * 将 inStr 转为 UTF-8 的编码形式
     * 
     * @param inStr 输入字符串
     * @return UTF - 8 的编码形式的字符串
     * @throws UnsupportedEncodingException
     */
    private String toUTF(String inStr) throws UnsupportedEncodingException {
        String outStr = "";
        if (inStr != null) {
            outStr = new String(inStr.getBytes("iso-8859-1"), "UTF-8");
        }
        return outStr;
    }

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 获得请求的方式 (1.post or 2.get), 根据不同请求方式进行不同处理
        String method = req.getMethod();
        // 1. 以 post 方式提交的请求 , 直接设置编码为 UTF-8
        if (method.equalsIgnoreCase("post")) {
            try {
            	req.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // 2. 以 get 方式提交的请求
        else {
            // 取出客户提交的参数集
            Enumeration<String> paramNames = request.getParameterNames();
            // 遍历参数集取出每个参数的名称及值
            while (paramNames.hasMoreElements()) {
                String name = paramNames.nextElement(); // 取出参数名称
                String values[] = request.getParameterValues(name); // 根据参数名称取出其值
                // 如果参数值集不为空
                if (values != null) {
                    // 遍历参数值集
                    for (int i = 0; i < values.length; i++) {
                        try {
                            // 回圈依次将每个值调用 toUTF(values[i]) 方法转换参数值的字元编码
                            String vlustr = toUTF(values[i]);
                            values[i] = vlustr;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    // 将该值以属性的形式藏在 request
                    request.setAttribute(name, values);
                }
            }

        }
		HttpSession session = req.getSession();
		String StoreId=(String) session.getAttribute("StoreId");
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8"); 
		String name=req.getContextPath();
		if(StoreId == null){
			String[] excludeFiles = {"/Store_riwise_cn/login.jsp","/login",".css",".jpg",".png",".gif",".bmp"};
			String requestUri = req.getRequestURI();
			boolean flag = false;
			for (String file : excludeFiles) {
				if(requestUri.endsWith(file)){
					flag = true;
					break;
				}
			}
			if(flag){
				chain.doFilter(request, response);
			}else{
				
				PrintWriter out = res.getWriter();
				out.println("<script>alert('您没有长时间操作电脑，请重新登录!');top.location.href='"+name+"/login.jsp';</script>");
				out.close();
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
