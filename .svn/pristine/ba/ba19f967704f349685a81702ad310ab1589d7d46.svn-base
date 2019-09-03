package org.jxkh.system.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.CustomUserDetails;
import org.jxkh.security.dto.SysMenuResourceDto;
import org.jxkh.security.model.SysRolesAuthorities;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysDept;
import org.jxkh.system.model.SysMenus;
import org.jxkh.system.service.SystemDaoService;
import org.jxkh.system.tree.SysMenusTree;
import org.jxkh.system.tree.UserMenusTree;
import org.jxkh.util.DepartUtil;
import org.jxkh.util.MenuTreeNode;
import org.jxkh.util.TreeNode;

import org.codehaus.jackson.map.ObjectMapper;

@Controller
public class SysMenuController {

	@Autowired
	public SysMenusTree jsonTree;
	@Autowired
	public UserMenusTree userMenusTree;
	@Autowired
	public SecurityDaoService securityDaoService;
	@Autowired
	public SystemDaoService systemDaoService;
	@Autowired
	public SysMenus sysMenus;
	@Autowired
	public SysRolesAuthorities sysRolesAuthorities;

	public ObjectMapper om = new ObjectMapper();

	@RequestMapping(value = "/admin/menuTree")
	@ResponseBody
	public Map<String, Object> acTree(@RequestParam("id") String id,
			ModelMap model) throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		ArrayList<TreeNode> treeNodeList = jsonTree.getJSONString();
		Map<String, Object> imMap = new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("menu", treeNodeList);
		return imMap;
	}

	@RequestMapping(value = "/admin/desktop/startTree")
	@ResponseBody
	public String startTree(@RequestParam("id") String id, ModelMap model)
			throws JsonProcessingException, IOException {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String userAccount = user.getUserAccount();
		List<SysMenuResourceDto> menuTreeNode = systemDaoService.getMenusByUserAccount(userAccount,"Y");
		userMenusTree.setMenuTreeNode(menuTreeNode);
		TreeNode rootNode = new TreeNode();
		rootNode.setId("00000000000");
		rootNode.setCls("folder");
		rootNode.setText("菜单");
		String jsonString = userMenusTree.genTreeJson(rootNode);
		Map<String, Object> imMap = new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("menu", jsonString);
		return jsonString;// imMap;
	}
	@RequestMapping(value = "/admin/mobile/menu")
	@ResponseBody
	public String mobileMenu(ModelMap model){  
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String userAccount = user.getUserAccount();
		List<SysMenuResourceDto> menuTreeNode = systemDaoService
				.getMenusByUserAccount(userAccount,"N");
		userMenusTree.setMenuTreeNode(menuTreeNode);
		TreeNode rootNode = new TreeNode();
		rootNode.setId("00000000000");
		rootNode.setCls("folder");
		rootNode.setText("菜单");
		String jsonString = userMenusTree.genTreeJson(rootNode);
		Map<String, Object> imMap = new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("menu", jsonString);
		return jsonString;//   
	}
	

	@RequestMapping(value = "/admin/menuTreeNodeRedirect")
	public String menuTreeNodeRedirect(@RequestParam("id") String id,
			ModelMap model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String deptId = user.getUserDept();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String deptStandId = deptId.substring(0, 7) + "0000";
		SysDept userDeparts = securityDaoService.getDeparts(deptId);
		// 获取权限机构代码格式为：13706000000
		String authorityDeptId="";
		String authorityDeptName  ="";
		if("Y".equals(userDeparts.getDeptstandalone())){
			authorityDeptId=userDeparts.getId();
			authorityDeptName=userDeparts.getDeptName();
		}else{
			authorityDeptId=userDeparts.getParentId();
			authorityDeptName=securityDaoService.getDeparts(authorityDeptId).getDeptName();
		}	
		SysDept userStandDeparts = securityDaoService.getDeparts(deptStandId);
		model.put("userName", user.getUserName());//用户名
		model.put("userAccount", user.getUserAccount());//用户登录账号
		model.put("userDeptId", user.getUserDept());//用户所在部门ID
		model.put("userParentDeptId", userDeparts.getParentId());//用户父部门ID
		model.put("userDeptName", userDeparts.getDeptName());//用户所在部门名称
		model.put("deptstandalone", userDeparts.getDeptstandalone());//用户所在部门是否是独立机构
		model.put("sysDate", sdf.format(new Date()));
		model.put("sysTime", sdf2.format(new Date()));
		model.put("userDeptStandId", userStandDeparts.getId());//用户所在机构ID
		model.put("userDeptStandName", userStandDeparts.getDeptName());//用户所在机构名称
		model.put("authorityDeptId", authorityDeptId);//权限机构代码
		model.put("authorityDeptName",authorityDeptName);//权限机构名称
		model.put("telePhone",user.getUserMobile());//权限机构名称
		
		@SuppressWarnings("unchecked")
		Set<GrantedAuthority> sysAuthorities = (Set<GrantedAuthority>) user
				.getAuthorities();
		
		if (sysAuthorities.toString().contains("AUTH_XZJX_RCGL_WS")) {// 日程权限
			model.put("auth","科室");//权限
		}else{
			model.put("auth","个人");//权限
		}
		
		//-------
		String resUrl = systemDaoService.getMenuResource(id);
		
		if (resUrl.indexOf("http://") == -1) {
			if(resUrl.indexOf("?")==-1){
				return resUrl;
			}else{
				String[]param=resUrl.substring(resUrl.indexOf("?")+1).split("&");
				for(int i=0;i<param.length;i++){
					model.put(param[i].substring(0, param[i].indexOf("=")), param[i].substring( param[i].indexOf("=")+1));
				}
				return resUrl.substring(0, resUrl.indexOf("?"));
			}
		} else {//http：// 重定向跳转
			/*resUrl += "?userName=" + user.getUserName() + "&userAccount="
					+ user.getUserAccount() + "&userDeptId="
					+ user.getUserDept() + "&userDeptName="
					+ userDeparts.getDeptName() + "&sysDate="
					+ sdf.format(new Date()) + "&userDeptStandId="
					+ userStandDeparts.getId() + "&userDeptStandName="
					+ userStandDeparts.getDeptName();*/
			return "redirect:"+resUrl;
		}
	}

	@RequestMapping(value = "/admin/menu/insertNode")
	@ResponseBody
	public Map<String, Object> insertNode(@RequestBody SysMenus sysMenus,
			ModelMap model) {

		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			SysMenus tmpSysMenus = sysMenus;
			systemDaoService.addMenuNode(sysMenus);
			// 暂时在此插入角色权限表（正式将在角色维护当中实现）
			
			  sysRolesAuthorities.setRoleId("ROLE_PLATFORMADMIN1");
			  sysRolesAuthorities.setAuthorityId(tmpSysMenus.getAuthorityId());
			  systemDaoService.addSysRolesAuthorities(sysRolesAuthorities);
			 
			// 后台生成Id传到前台ExtJs MVC框架接收
			tmpSysMenus.setId(sysMenus.getMenuId());
			imsg.put("success", "true");
			imsg.put("menu", tmpSysMenus);
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}

	@RequestMapping(value = "/admin/menu/menuTree")
	@ResponseBody
	public Map<String, Object> acFullTree(@RequestParam("id") String id,
			ModelMap model) throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		ArrayList<MenuTreeNode> jsonString = jsonTree.getFullJSONString();
		Map<String, Object> imMap = new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("menu", jsonString);

		return imMap;
	}

	@RequestMapping(value = "/admin/menu/deleteNode")
	@ResponseBody
	public Map<String, String> deleteNode(@RequestParam("id") String menuId,
			ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.removeMenuNode(menuId);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}

	@RequestMapping(value = "/admin/menu/updateNode")
	@ResponseBody
	public Map<String, String> updateNode(@RequestBody SysMenus sysMenus,
			ModelMap model) {

		Map<String, String> imsg = new HashMap<String, String>();
		try {
			sysMenus.setMenuId(sysMenus.getId());
			systemDaoService.editMenuNode(sysMenus);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
}
