package com.qf.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

 
public class BaseController {
	
	 
 
	/**
	 * 把浏览器参数转化放到Map集合中
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected Map<String, Object> getParam(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String method = request.getMethod();
        Enumeration<?> keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if(key!=null){
            	if (key instanceof String) {
            	    String value = request.getParameter(key.toString());
            	    if("GET".equals(method)){//前台encodeURIComponent('我们');转码后到后台还是ISO-8859-1，所以还需要转码
            	         try {
							value =new String(value.getBytes("ISO-8859-1"),"UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}    
            	    }
            		paramMap.put(key.toString(), value);
				}
            } 
        }
        return paramMap;
    }

	protected void flushResponse(HttpServletResponse response, String responseContent) {
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("GBK");
			// 针对ajax中页面编码为GBK的情况，一定要加上以下两句
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/html;charset=UTF-8");
			writer = response.getWriter();
			if (responseContent==null || "".equals(responseContent) || "null".equals(responseContent)) {
				writer.write("");
			} else {
				writer.write(responseContent);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
	
	
	/**
	 * 返回列表分页的下角页码
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView getPageNumberInfo(int total,int startIndex,int pageSize ,ModelAndView result) {
		//Math.ceil整数则为该整数，Math.ceil小数则为靠近大的整数
        int current =  (int) Math.ceil((startIndex + 1.0) / pageSize);
		result.addObject("start", startIndex);
		result.addObject("limit", pageSize);
		result.addObject("total", total);
		result.addObject("current", current);
		if(total > 0) {
			int page =  (int) Math.ceil(total/ pageSize);
			double totald= total;
			if(totald/pageSize>total/pageSize){
				page =page +1;
			}
			result.addObject("page", page);
			int startPage = 0;
			int endPage = 0;
			if (page<8) {
				startPage = 1;
				endPage = page;
			} else {
				if (current<5) {
					startPage = 1;
					endPage = page>6?6:page; 
				} else if (page-current<6) {
					startPage = page-5;
					endPage = page;
				} else {
					startPage = current - 2;
					endPage = current + 2;
				}
			}
			result.addObject("startPage", startPage);
			result.addObject("endPage", endPage);
		} else {
			result.addObject("page", 0);
		}
		return result;
	}
	
	
	
	/**
	 * 获取登录模块session对象
	 * 
	 * @return
	 */
	protected  HttpSession getSession(){ 
		HttpSession session =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession(); 
		String id=session.getId();
	 
		//从登录模块获取session信息
		if(session != null && session.getServletContext().getContext("/logon") != null 
				&& session.getServletContext().getContext("/logon").getAttribute("sessionId_" + session.getId()) != null) {
			 
			String sessionId = session.getId();
			 
			session = (HttpSession) session.getServletContext().getContext("/logon").getAttribute("sessionId_" + sessionId);
		}
		
		return session;
	}
	
	/**
	 * 获取登录模块session中的sessionInfo
	 * 
	 * @return
	 */
	protected  Map<String, Object> getSessionInfo(){ 
		HttpSession session = this.getSession(); 
		Map<String, Object> sMap = null;
		 
		try {
			if(session.getAttribute("sessionInfo") != null) {
				 sMap = (Map<String, Object>) session.getAttribute("sessionInfo");
			 }
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return sMap;
	}
	

}
