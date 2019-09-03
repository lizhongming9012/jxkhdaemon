package org.jxkh.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.CustomUserDetails;
import org.jxkh.security.model.SysUsers;
import org.jxkh.security.service.SecurityDaoService;

import org.jxkh.system.tree.SysDeptTree;
import org.jxkh.util.FillBeanProperyAsNUllForMybatis;
import org.jxkh.util.Md5Encoder;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

@Controller
public class SysUserController {

	@Autowired
	public SecurityDaoService securityDaoService;
	@Autowired
	public SysUsers sysUsers;
	@Autowired
	public SysDeptTree jsonTree;
	public ObjectMapper om = new ObjectMapper();
	

	@RequestMapping(value = "/admin/sys/manageUserQuery")
	@ResponseBody
	public String getSysUsersByUserId(SysUsers sysUsers) throws IOException {

		List<SysUsers> sysUsersList = null;
		FillBeanProperyAsNUllForMybatis.fillNull(sysUsers);
		// 模糊查询时只能查找本部门：根据部门代码、用户名和账号为空判断是来自于不输入任何条件来查询用户
		if (sysUsers.getUserDept() == null && sysUsers.getUserAccount() == null
				&& sysUsers.getUserName() == null) {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String dept = user.getUserDept();
			sysUsers.setUserDept(dept);
		}
		if (sysUsers.getUserSex() != null
				&& "137060017".equals(sysUsers.getUserDept().substring(0, 9))) {
			sysUsers.setLastLoginIp("13706001700");
			sysUsers.setUserDept(null);
			sysUsers.setLimit(50);

		}

		sysUsersList = securityDaoService.getAllUsers(sysUsers);
		int totalCount = securityDaoService.getAllUsersCount(sysUsers);

		String jsonString = "{ \"totalCount\":" + totalCount
				+ ", \"records\":[";
		for (SysUsers sysUser : sysUsersList) {
			// String sex=sysUser.getUserSex().equals("1")?"男":"女";
			jsonString += "{\"userId\":\"" + sysUser.getUserId() + "\",";
			jsonString += "\"userAccount\":\"" + sysUser.getUserAccount()
					+ "\",";
			jsonString += "\"userName\":\"" + sysUser.getUserName() + "\",";
			jsonString += "\"userPassword\":\"" + sysUser.getUserPassword()
					+ "\",";
			jsonString += "\"userDesc\":\"" + sysUser.getUserDesc() + "\",";
			jsonString += "\"userSex\":\"" + sysUser.getUserSex() + "\",";

			jsonString += "\"issys\":\"" + sysUser.getIssys() + "\",";
			jsonString += "\"userDept\":\"" + sysUser.getUserDept() + "\",";
			jsonString += "\"userType\":\"" + sysUser.getUserType() + "\",";
			jsonString += "\"isValid\":\"" + sysUser.getIsValid() + "\",";
			jsonString += "\"userDuty\":\"" + sysUser.getUserDuty() + "\",";
			jsonString += "\"userMobile\":\"" + sysUser.getUserMobile() + "\",";
			jsonString += "\"userOrder\":\"" + sysUser.getUserOrder() + "\",";
			jsonString += "\"userDesktop\":\"" + sysUser.getUserDesktop()
					+ "\",";
			jsonString += "\"userSex\":\"" + sysUser.getUserSex() + "\",";
			jsonString += "\"subSystem\":\"" + sysUser.getSubSystem() + "\"},";

		}
		if (sysUsersList.size() != 0) {
			jsonString = ","
					.equals(jsonString.substring(jsonString.length() - 1)) ? jsonString
					.substring(0, jsonString.length() - 1) : jsonString;
		}
		jsonString += "]}";
		return jsonString;
	}

	@RequestMapping(value = "/admin/sys/manageUserDelete")
	@ResponseBody
	public Map<String, String> deleteUser(@RequestBody SysUsers sysUsers,
			ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			securityDaoService.deleteUsers(sysUsers.getUserId());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}

	@RequestMapping(value = "/admin/sys/manageUserSave")
	@ResponseBody
	public Map<String, Object> saveUser(@RequestBody SysUsers sysUsers,
			ModelMap model) {
		FillBeanProperyAsNUllForMybatis.fillNull(sysUsers);
		SysUsers tempUser = sysUsers;
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			int selectString = securityDaoService.selectUsers(
					sysUsers.getUserId()).size();
			if (0 < selectString) {
				securityDaoService.updateUsers(sysUsers);
				
			} else {
				securityDaoService.insertUsers(sysUsers);
				tempUser.setId(sysUsers.getId());
			}

			imsg.put("success", "true");
			imsg.put("records", tempUser);

		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}

	@RequestMapping(value = "/admin/sys/UserDeparmentQuery")
	@ResponseBody
	public String moduleTree(@RequestParam("id") String id)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		String jsonString = jsonTree.getTreeJSONString();
		return jsonString;
	}
	
	@RequestMapping(value = "/ywgl/sys/UserDeparmentQuery")
	@ResponseBody
	public String txlTree(@RequestParam("id") String id)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		String jsonString = jsonTree.getTreeJSONString();
		return jsonString;
	}

	@RequestMapping(value = "/admin/sys/UserDeparmentQueryBynode")
	@ResponseBody
	public String UserDeparmentQueryBynode(String node)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(node);
		String jsonString = jsonTree.getTreeJSONString();
		return jsonString;
	}

	@RequestMapping(value = "/admin/sys/desktop/modifyPwd")
	@ResponseBody
	public Map<String, Object> modifyPwd(String newpass1, String userAccount) {
		Map<String, Object> iMap = new HashMap<String, Object>();
		SysUsers user = (SysUsers) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		SysUsers sysUsers = new SysUsers();
		if (userAccount == null) {
			sysUsers.setUserAccount(user.getUserAccount());
		} else {
			sysUsers.setUserAccount(userAccount);
		}
		String md5Pass = Md5Encoder.md5(newpass1);
		sysUsers.setUserPassword(newpass1);
		user.setUserPassword(md5Pass);
		try {
			securityDaoService.updateUser(sysUsers);
			iMap.put("success", true);
		} catch (Exception e) {
			iMap.put("success", false);
			e.printStackTrace();
		}
		return iMap;
	}
	
	@RequestMapping(value = "/admin/sys/userifexist")
	@ResponseBody
	public boolean userifexist(@RequestParam("value") String userId) {
		int usernum = securityDaoService.userifexist(userId);
		if(usernum > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value = "/admin/sys/useraccountifexist")
	@ResponseBody
	public boolean useraccountifexist(@RequestParam("value") String userAccount) {
		int usernum = securityDaoService.useraccountifexist(userAccount);
		if(usernum > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value = "/admin/sys/userinfoifexist")
	@ResponseBody
	public boolean userinfoifexist(@RequestParam("userId") String userId,@RequestParam("userAccount") String userAccount) {
		int usernum = securityDaoService.userinfoifexist(userId,userAccount);
		if(usernum > 0){
			return true;
		}else{
			return false;
		}
	}
	
	
}
