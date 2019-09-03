package org.jxkh.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysDept;
import org.jxkh.system.service.SystemDaoService;
import org.jxkh.system.tree.SysDeptTree;
import org.jxkh.util.DeptTreeNode;
import org.jxkh.util.MenuTreeNode;
import org.jxkh.util.TreeNode;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.mail.handlers.message_rfc822;

@Controller
public class SysDeptController {

	@Autowired
	public SysDeptTree jsonTree;
	@Autowired
	public SecurityDaoService securityDaoService;
	@Autowired
	public SystemDaoService systemDaoService;
	@Autowired
	public SysDept sysDept;
	
	//public ObjectMapper om = new ObjectMapper();	

	
	@RequestMapping(value = "admin/dept/insertNode")
	@ResponseBody
	public Map<String, Object> insertNode(@RequestBody SysDept sysDept,
			                             ModelMap model) {
		
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			sysDept.setId(sysDept.getDeptId());
//			if(systemDaoService.isExistDept(sysDept.getDeptId())!= null){
//				imsg.put("success", "false");
//				imsg.put("message", "已存在部门!");
//			}else{
			systemDaoService.addDeptNode(sysDept);
			imsg.put("success", "true");
			imsg.put("dept",sysDept);
//			}
		} catch (Exception e) {
			imsg.put("success", "false");
			imsg.put("message", "请检查录入数据");
			e.printStackTrace();
		}
		return imsg;
	}
	@RequestMapping(value = "admin/dept/deptTree")
	@ResponseBody
	public Map<String, Object> acFullTree(@RequestParam("id") String id, ModelMap model)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		ArrayList<DeptTreeNode> jsonString = jsonTree.getDeptFullJSONString();
		Map<String,Object> imMap =new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("dept", jsonString);
		return imMap;
	}
	@RequestMapping(value = "admin/dept/deptTreeId")
	@ResponseBody
	public Map<String, Object> fullTreeId()
		throws JsonProcessingException, IOException{
		String idString = jsonTree.getIdtring();
		Map<String,Object> imMap =new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("deptId", idString);
		return imMap;
	}
	@RequestMapping(value = "admin/dept/deleteNode")
	@ResponseBody
	public Map<String, String> deleteNode(@RequestParam("id") String id, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.removeDeptNode(id);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");			
			e.printStackTrace();
			
		}
		
		
		return imsg;
	}
	@RequestMapping(value = "admin/dept/updateNode")
	@ResponseBody
	public Map<String, String> updateNode(@RequestBody SysDept sysDept,
									               ModelMap model) {
				
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			sysDept.setId(sysDept.getId());
			systemDaoService.editDeptNode(sysDept);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		return imsg;
	}
	@RequestMapping(value = "admin/dept/checkdept")
	@ResponseBody
	public Map<String, Object> checkDept(@RequestParam("textValue") String checkId,
			                             ModelMap model) {
		
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			if(systemDaoService.isExistDept(checkId)!= null){
				imsg.put("success", "false");
				imsg.put("message", "已存在部门!");
			}else{
				imsg.put("success", "true");
				imsg.put("dept",sysDept);
			}
		} catch (Exception e) {
			imsg.put("success", "false");
			imsg.put("message", "请检查录入数据");
			e.printStackTrace();
		}
		return imsg;
	}
}
