package org.jxkh.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysModules;
import org.jxkh.system.service.SystemDaoService;
import org.jxkh.system.tree.SysModulesTree;
import org.codehaus.jackson.map.ObjectMapper;

@Controller
public class SysModuleController {

	@Autowired
	public SysModulesTree jsonTree;
	@Autowired
	public SecurityDaoService securityDaoService;
	@Autowired
	public SystemDaoService systemDaoService;
	@Autowired
	public SysModules sysModules;
	public ObjectMapper om = new ObjectMapper();

	@RequestMapping(value = "/admin/checkModuleTree")
	@ResponseBody
	public String checkModuleTree(@RequestParam("id") String id, ModelMap model)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		String jsonString = jsonTree.getCheckTreeJSONString();
		return jsonString;
	}
	@RequestMapping(value = "/admin/moduleTree")
	@ResponseBody
	public String moduleTree(@RequestParam("id") String id, ModelMap model)
			throws JsonProcessingException, IOException {
		jsonTree.setPID(id);
		String jsonString = jsonTree.getTreeJSONString();
		return jsonString;
	}
	
	@RequestMapping(value = "/admin/modules/deleteNode")
	@ResponseBody
	public Map<String, String> deleteNode(@RequestParam("id") String moduleId, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.deleteModuleNode(moduleId);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}
	@RequestMapping(value = "/admin/modules/insertNode")
	@ResponseBody
	public Map<String, String> insertNode(@RequestParam("pid") String parentId, @RequestParam("newName") String moduleName,ModelMap model) {
		sysModules.setParentId(parentId);
		sysModules.setModuleName(moduleName);
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.insertModuleNode(sysModules);
			
			imsg.put("success", "true");
			imsg.put("nodeId",sysModules.getModuleId());
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}
	@RequestMapping(value = "/admin/modules/updateNode")
	@ResponseBody
	public Map<String, String> updateNode(@RequestParam("id") String moduleId, @RequestParam("newName") String moduleName,ModelMap model) {
		sysModules.setModuleId(moduleId);
		sysModules.setModuleName(moduleName);
		
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.updateModuleNode(sysModules);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		return imsg;
	}

	@RequestMapping(value = "/admin/sys/manageModules")
	public String manageModules()
	{
		return "admin/sys/manageModules";
	}
}
