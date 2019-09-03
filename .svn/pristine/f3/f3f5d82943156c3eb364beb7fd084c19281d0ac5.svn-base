package org.jxkh.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.model.SysResources;
import org.jxkh.security.model.SysUsersRoles;
import org.jxkh.security.service.SecurityDaoService;


@Controller
public class SysUserRoleController {

	@Autowired
	public SecurityDaoService systemDaoService;
	@Autowired
	public SysUsersRoles sysUsersRoles;
		
	@RequestMapping(value = "admin/sys/manageUserRoleQueryByUserId")
	@ResponseBody
	public String getUserRolesByUserId(@RequestParam("userId") String userId) throws IOException {
		
		List<SysUsersRoles> sysUsersRolesList =null;
		sysUsersRolesList=systemDaoService.getUsersRolesByUserId(userId);
			
		String jsonString ="{ \"totalCount\":"+sysUsersRolesList.size()+", \"records\":[";
		for(SysUsersRoles sysUsersRole:sysUsersRolesList){
			jsonString+="{\"id\":\""+sysUsersRole.getId()+"\",";
			jsonString+="\"userId\":\""+sysUsersRole.getUserId()+"\",";
			jsonString+="\"roleId\":\""+sysUsersRole.getRoleId()+"\",";
			jsonString+="\"roleDesc\":\""+sysUsersRole.getRoleDesc()+"\",";
			jsonString+="\"roleName\":\""+sysUsersRole.getRoleName()+"\"},";
		}
		jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
		jsonString+="]}";
		return jsonString;
	}
	
	@RequestMapping(value = "/admin/sys/removeUserRole")
	@ResponseBody
	public Map<String, String> deleteResource(@RequestBody List<SysUsersRoles> sysUsersRolesList) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			for(SysUsersRoles sysUsersRoles:sysUsersRolesList)
			{systemDaoService.deleteUsersRoles((int)sysUsersRoles.getId());}
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}
	@RequestMapping(value = "/admin/sys/removeSingleUserRole")
	@ResponseBody
	public Map<String, String> deleteSingleResource(@RequestBody SysUsersRoles sysUsersRoles) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
		
			systemDaoService.deleteUsersRoles((int)sysUsersRoles.getId());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}

	@RequestMapping(value = "/admin/sys/addUserRole")
	@ResponseBody
	public Map<String, Object> saveUserRole(
			@RequestBody List<SysUsersRoles> sysUsersRolesList) {
		Map<String, Object> imsg = new HashMap<String, Object>();
		List<SysUsersRoles> list=new ArrayList<SysUsersRoles>();
		try {
			for(SysUsersRoles sysUsersRoles:sysUsersRolesList)
			{SysUsersRoles temp=sysUsersRoles;
			
				systemDaoService.insertUsersRoles(sysUsersRoles);
			temp.setId(sysUsersRoles.getId());	
			list.add(temp);
			}
		
			imsg.put("records", list);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	@RequestMapping(value = "/admin/sys/addSingleUserRole")
	@ResponseBody
	public Map<String, Object> saveSingleUserRole(
			@RequestBody SysUsersRoles sysUsersRoles) {
		Map<String, Object> imsg = new HashMap<String, Object>();		
		try {
			SysUsersRoles temp=sysUsersRoles;			
			systemDaoService.insertUsersRoles(sysUsersRoles);
			temp.setId(sysUsersRoles.getId());				
			
			
			imsg.put("records", temp);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		return imsg;
	}
	
}

