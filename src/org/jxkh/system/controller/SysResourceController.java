package org.jxkh.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.model.SysResources;
import org.jxkh.system.service.SystemDaoService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.json.simple.JSONArray;

@Controller
public class SysResourceController {

	@Autowired
	public SystemDaoService systemDaoService;
	@Autowired
	public SysResources sysResources;
	public ObjectMapper om = new ObjectMapper();
		
	@RequestMapping(value = "/admin/sys/manageResourceQuery")
	@ResponseBody
	public String getSysResourcesByModuleId(@RequestParam("moduleId") String moduleId, ModelMap model) throws IOException {
		
		List<SysResources> sysResourcesList =null;
		sysResourcesList=systemDaoService.getSysResourcesByModuleId(moduleId);
			
		String jsonString ="{ \"totalCount\":"+sysResourcesList.size()+", \"records\":[";
		for(SysResources sysRes:sysResourcesList){
			jsonString+="{\"resourceId\":\""+sysRes.getResourceId()+"\",";
			jsonString+="\"resourceName\":\""+sysRes.getResourceName()+"\",";
			jsonString+="\"resourceDesc\":\""+sysRes.getResourceDesc()+"\",";
			jsonString+="\"resourceString\":\""+sysRes.getResourceString()+"\",";
			jsonString+="\"resourceType\":\""+sysRes.getResourceType()+"\",";
			jsonString+="\"module\":\""+moduleId+"\"},";
		}
		jsonString=",".equals(jsonString.substring(jsonString.length()-1))?jsonString.substring(0, jsonString.length()-1):jsonString;
		jsonString+="]}";
		return jsonString;
	}
	@RequestMapping(value = "/admin/sys/manageResourceDelete")
	@ResponseBody
	public Map<String, String> deleteResource(@RequestBody SysResources sysResources, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.deleteResource(sysResources.getResourceName());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}	

	@RequestMapping(value = "/admin/sys/manageResourceSave")
	@ResponseBody
	public Map<String, String> saveResource(
			@RequestBody SysResources sysResources, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			String selectString=systemDaoService.isExistResource(sysResources.getResourceName());
			if(selectString!=null){
				systemDaoService.updateResource(sysResources);
			}else{
				systemDaoService.insertResource(sysResources);
			}
			
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	
}
