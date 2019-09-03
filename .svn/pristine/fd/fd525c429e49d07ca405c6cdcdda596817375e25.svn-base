package org.jxkh.security.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.jxkh.security.CustomUserDetails;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysDept;
@Controller
public class SecurityController {

	@Autowired
    public SecurityDaoService securityDaoService;
	@Autowired
	public SessionRegistry sessionRegistry;
	
    @RequestMapping(value = "/admin/login")  
    public String login(ModelMap model) {  
    	return "/admin/login";  
    }  
    @RequestMapping(value = "/admin/logfail")  
    public String logfail(ModelMap model) {  
    	return "/admin/logfail";  
    }  
    
    @RequestMapping(value = "/admin/sessionTimeout")  
    public String sessionTimeout(ModelMap model) {  
    	return "/admin/sessionTimeout";  
    }
	
    @RequestMapping(value = "/admin/opendesktop")  
    public String opendesktop(ModelMap model) { 
    	
    	return "/admin/opendesktop";  
    }
    
	@RequestMapping(value = "/admin/index")  
    public String mainSpace(ModelMap model){  
		CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.put("userName", user.getUserName());
    	return "/admin/index";  
    } 
	@RequestMapping(value = "/ywgl/lmgl")  
    public String Lmgl(ModelMap model,HttpServletRequest  request){  
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		model.put("userName", user.getUserName());
		
		return "/admin/ywgl/back/lmgl"; 
		
	}
	@RequestMapping(value = "/sys/desktop")  
    public String desktop(ModelMap model,HttpServletRequest  request){  		
		try {
			String userAgent=request.getHeader("user-agent");
			if(userAgent.indexOf("Mobile")!=-1){
				return "redirect:/admin/mobile/menu";
			}
			CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String deptId=user.getUserDept();
			SysDept userDeparts = securityDaoService.getDeparts(deptId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//securityDaoService.updateUserStatus(user.getUserId(),"YES");
			//String ols = securityDaoService.onlineUsers();
			model.put("userName", user.getUserName());
			model.put("userAccount", user.getUserAccount());
			model.put("deptName", userDeparts.getDeptName());
			model.put("deptId", userDeparts.getDeptId());
			model.put("sysDate", sdf.format(new Date()));
			model.put("onlineUsers", sessionRegistry.getAllPrincipals().size());
			//统计桌面提醒事项数目
			  //当天发通知条数
	
			
		
			return "/admin/desktop";
		} catch (Exception e) {
			return "/admin/login";
		}  
    } 
	
	@RequestMapping(value = "/sys/logout")  
	public String logout(ModelMap model,HttpServletRequest request){  		
		try {
			CustomUserDetails user=(CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			//securityDaoService.updateUserStatus(user.getUserId(),"NO");
			String baseUrlPath = request.getScheme() + "://"
					+ request.getServerName() + request.getContextPath();
			if (request.getServerPort() != 80) {
				baseUrlPath = request.getScheme() + "://" + request.getServerName()
						+ ":" + request.getServerPort() + request.getContextPath();
			}
			return "redirect:"+baseUrlPath+"/j_spring_security_logout";
		} catch (Exception e) {
			
			return "/admin/login";
		}  
	} 
	
}
