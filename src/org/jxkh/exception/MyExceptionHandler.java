package org.jxkh.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {  
	  
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,  
            Exception ex) {  
        Map<String, Object> model = new HashMap<String, Object>();         
        
        model.put("ex", ex);
       
  
          
        // 根据不同错误转向不同页面  
        if(ex instanceof BusinessException) {  
            return new ModelAndView("admin/error/error-business", model);  
        }else if(ex instanceof ParameterException) {  
            return new ModelAndView("admin/error/error-parameter", model);  
        } else {  
            return new ModelAndView("admin/error/error", model);  
        }  
    }  
}  

  
