package org.jxkh.util;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;
/** 
 * 自定义LOG4J启动类 
 * cas打成WAR包部署到weblogic上因找不到log4j.xml文件而出错 
 * @author zz 
 */  
public class Log4jInit extends HttpServlet {  
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void init() throws ServletException {  
  String file = getInitParameter("log4j");  
  System.out.println("...........log4j start");  
	  if(null != file) {  
	   Properties ps=new Properties();  
	    try {  
	    ps.load(getServletContext().getResourceAsStream(file));  
	    } catch (IOException e) {  
	    e.printStackTrace();  
	    }  
	    PropertyConfigurator.configure(ps);  
	  }  
	 }  
	}  

