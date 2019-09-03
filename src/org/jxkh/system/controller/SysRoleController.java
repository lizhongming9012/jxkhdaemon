package org.jxkh.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.model.SysAuthorities;
import org.jxkh.security.model.SysRoles;
import org.jxkh.security.model.SysRolesAuthorities;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.service.SystemDaoService;
import org.jxkh.util.FillBeanProperyAsNUllForMybatis;
import org.codehaus.jackson.map.ObjectMapper;

@Controller
public class SysRoleController {
	
	@Autowired
	SystemDaoService systemService;
	@Autowired
	SecurityDaoService securityDaoService;
	public ObjectMapper om = new ObjectMapper();
	@RequestMapping(value = "/admin/manageRole")	
	public String manageRole(ModelMap model)
			throws JsonProcessingException, IOException {
		
		return "admin/sys/manageRole";
	}
	@RequestMapping(value = "/admin/sys/manageRoleQuery")
	@ResponseBody
	public String getRole(SysRoles sysrole) throws IOException {
		
		List<SysRoles> sysRoleList =null;
		FillBeanProperyAsNUllForMybatis.fillNull(sysrole);
		sysRoleList=systemService.getAllRoles(sysrole);
		int totalCount=systemService.getAllRolesCount(sysrole);
			
		String jsonString ="{ \"totalCount\":"+totalCount+", \"records\":[";
		for(SysRoles sysRole:sysRoleList){
			jsonString+="{\"roleId\":\""+sysRole.getRoleId()+"\",";
			jsonString+="\"roleName\":\""+sysRole.getRoleName()+"\",";
			jsonString+="\"roleDesc\":\""+sysRole.getRoleDesc()+"\"},";			
			
		}
		jsonString=sysRoleList.size()!=0?jsonString.substring(0, jsonString.length()-1):jsonString;
		jsonString+="]}";
		return jsonString;
	}
	@RequestMapping(value = "/admin/sys/manageRoleSave")
	@ResponseBody
	public Map<String, Object> saveRole(
			@RequestBody SysRoles sysRole, ModelMap model) {
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			String selectString=systemService.isExistRole(sysRole.getRoleName());
			if(selectString!=null){
				systemService.updateRole(sysRole);
			}else{
				sysRole.setRoleId(sysRole.getRoleName());
				systemService.insertRole(sysRole);
				imsg.put("records", sysRole);
			}
			
			imsg.put("success", "true");
			
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	@RequestMapping(value = "/admin/sys/manageRoleDelete")
	@ResponseBody
	public Map<String, String> deleteResource(@RequestBody SysRoles sysRole, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemService.delRole(sysRole.getRoleId());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}
	//用户管理中调用
	@RequestMapping(value = "/admin/sys/rolesAllQuery")
	@ResponseBody
	public String getSysRolesAll(@RequestParam("userId") String userId) throws IOException {
		
		List<SysRoles> sysRolesList =null;
		sysRolesList = securityDaoService.getAllRoles(userId);
			
		String jsonString ="{ \"totalCount\":"+sysRolesList.size()+", \"records\":[";
		for(SysRoles sysRole:sysRolesList){
			jsonString+="{\"roleId\":\""+sysRole.getRoleId()+"\",";
			jsonString+="\"roleName\":\""+sysRole.getRoleName()+"\",";
			jsonString+="\"roleDesc\":\""+sysRole.getRoleDesc()+"\",";
			jsonString+="\"enabled\":\""+sysRole.getEnabled()+"\",";
			jsonString+="\"issys\":\""+sysRole.getIssys()+"\",";
			
			jsonString+="\"module\":\""+sysRole.getModule()+"\"},";
			
		}
		jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
		jsonString+="]}";
		return jsonString;
	}
	//角色管理中调用
		@RequestMapping(value = "/admin/sys/manageRoleAuthorityQuery")
		@ResponseBody
		public String getSysAuthorityAll(@RequestParam("roleId") String roleId) throws IOException {
			
			List<SysAuthorities> sysAuthorityList =null;
			sysAuthorityList = securityDaoService.getAllAuthority(roleId);
				
			String jsonString ="{ \"totalCount\":"+sysAuthorityList.size()+", \"records\":[";
			for(SysAuthorities sysAuthority:sysAuthorityList){
				jsonString+="{\"authorityId\":\""+sysAuthority.getAuthorityId()+"\",";	
				jsonString+="\"authorityDesc\":\""+sysAuthority.getAuthorityDesc()+"\"},";			
			}
			
			jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
			jsonString+="]}";		
			return jsonString;
		}
		//角色管理中调用
		@RequestMapping(value = "/admin/sys/manageRoleAppQuery")
		@ResponseBody
		public String getAppAll(@RequestParam("roleId") String roleId) throws IOException {
			
			List<SysAuthorities> sysAuthorityList =null;
			sysAuthorityList = securityDaoService.getAllApp(roleId);
			
			String jsonString ="{ \"totalCount\":"+sysAuthorityList.size()+", \"records\":[";
			for(SysAuthorities sysAuthority:sysAuthorityList){
				jsonString+="{\"authorityId\":\""+sysAuthority.getAuthorityId()+"\",";	
				jsonString+="\"authorityDesc\":\""+sysAuthority.getAuthorityDesc()+"\"},";			
			}
			jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
			jsonString+="]}";		
			return jsonString;
		}
		@RequestMapping(value = "admin/sys/manageRoleAuthorityQueryByRoleId")
		@ResponseBody
		public String getRoleAuthorityByRoleIdJsonString(@RequestParam("roleId") String roleId) throws IOException {
			
			List<SysRolesAuthorities> sysRoleAuthorityList =null;
			sysRoleAuthorityList=securityDaoService.getRoleAuthorityByroleId(roleId);
				
			String jsonString ="{ \"totalCount\":"+sysRoleAuthorityList.size()+", \"records\":[";
			for(SysRolesAuthorities roleAuthority:sysRoleAuthorityList){
				jsonString+="{\"id\":\""+roleAuthority.getId()+"\",";
				jsonString+="\"authorityId\":\""+roleAuthority.getAuthorityId()+"\",";
				jsonString+="\"roleId\":\""+roleAuthority.getRoleId()+"\",";
				jsonString+="\"authorityDesc\":\""+roleAuthority.getAuthorityDesc()+"\"},";
			}
			//jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
			jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
			jsonString+="]}";
			return jsonString;
		}	
		@RequestMapping(value = "/admin/sys/removeRoleAuthority")
		@ResponseBody
		public Map<String, String> deleteRoleAuthority(@RequestBody  List<SysRolesAuthorities> roleAuthorityList) {
			Map<String, String> imsg = new HashMap<String, String>();
			try {
				for(  SysRolesAuthorities roleAuthority: roleAuthorityList){
				//根据authorityId判断插入sys_role_app还是sys_roles_authorities
				String authorityId=roleAuthority.getAuthorityId();
				if(authorityId.matches("[0-9]*"))
					securityDaoService.deleteRoleApp((int)roleAuthority.getId());	
				else				
				securityDaoService.deleteRoleAuthority((int)roleAuthority.getId());
				}
				imsg.put("success", "true");
			} catch (Exception e) {
				imsg.put("success", "false");
				e.printStackTrace();
			}
			
			
			return imsg;
		}
		@RequestMapping(value = "/admin/sys/removeSingleRoleAuthority")
		@ResponseBody
		public Map<String, String> deleteSingleRoleAuthority(@RequestBody  SysRolesAuthorities roleAuthority) {
			Map<String, String> imsg = new HashMap<String, String>();
			try {
			
					//根据authorityId判断插入sys_role_app还是sys_roles_authorities
					String authorityId=roleAuthority.getAuthorityId();
					if(authorityId.matches("[0-9]*"))
						securityDaoService.deleteRoleApp((int)roleAuthority.getId());	
					else				
						securityDaoService.deleteRoleAuthority((int)roleAuthority.getId());
			
				imsg.put("success", "true");
			} catch (Exception e) {
				imsg.put("success", "false");
				e.printStackTrace();
			}
			
			
			return imsg;
		}
		

		@RequestMapping(value = "/admin/sys/addRoleAuthority")
		@ResponseBody
		public Map<String, Object> saveRoleAuthority(
				@RequestBody List<SysRolesAuthorities> roleAuthorityList) {
			Map<String, Object> imsg = new HashMap<String, Object>();
			List<SysRolesAuthorities> list=new ArrayList<SysRolesAuthorities>();
			try {
				for(  SysRolesAuthorities roleAuthority: roleAuthorityList){
				SysRolesAuthorities temp=roleAuthority;
				//根据authorityId判断插入sys_role_app还是sys_roles_authorities
				String authorityId=roleAuthority.getAuthorityId();
				if(authorityId.matches("[0-9]*"))
					securityDaoService.insertRoleApp(roleAuthority);	
				else				
				securityDaoService.insertRoleAuthority(roleAuthority);
				temp.setId(roleAuthority.getId());
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
		@RequestMapping(value = "/admin/sys/addSingleRoleAuthority")
		@ResponseBody
		public Map<String, Object> saveSingleRoleAuthority(
				@RequestBody SysRolesAuthorities roleAuthority) {
			Map<String, Object> imsg = new HashMap<String, Object>();		
			try {
				
					SysRolesAuthorities temp=roleAuthority;
					//根据authorityId判断插入sys_role_app还是sys_roles_authorities
					String authorityId=roleAuthority.getAuthorityId();
					if(authorityId.matches("[0-9]*"))
						securityDaoService.insertRoleApp(roleAuthority);	
					else				
						securityDaoService.insertRoleAuthority(roleAuthority);
					temp.setId(roleAuthority.getId());
					
			
				
				imsg.put("records", temp);
				imsg.put("success", "true");
			} catch (Exception e) {
				imsg.put("success", "false");
				e.printStackTrace();
			}
			
			return imsg;
		}
		//新加代码接受前台传过来的角色id和用户id
		@RequestMapping(value = "/admin/sys/roleToUser")
		@ResponseBody
		public Map<String, Object> saveRoleToUser(HttpServletRequest request,HttpServletResponse response
				) {
			Map<String, Object> imsg = new HashMap<String, Object>();		
			try {
				String roleId=request.getParameter("roleId");
				String userId[]=request.getParameter("userId").split(",");
				for(int i=0;i<userId.length;i++){
					systemService.insertRoleToUser(roleId, userId[i]);
				}
				
				imsg.put("success", "true");
			} catch (Exception e) {
			imsg.put("success", "false");
				e.printStackTrace();
			}
			
			return imsg;
		}
}
