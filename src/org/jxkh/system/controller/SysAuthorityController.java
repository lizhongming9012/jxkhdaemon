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
import org.jxkh.system.dto.SysAuthorityResourceDto;
import org.jxkh.system.service.SystemDaoService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.json.simple.JSONArray;

@Controller
public class SysAuthorityController {

	@Autowired
	public SystemDaoService systemDaoService;
	@Autowired
	public SysAuthorityResourceDto sysAuthorityResourceDto;
	public ObjectMapper om = new ObjectMapper();
		
	@RequestMapping(value = "/admin/sys/manageAuthorityQuery")
	@ResponseBody
	public String getSysAuthorityResourcesByModuleId(@RequestParam("moduleId") String moduleId, ModelMap model) throws IOException {
		
		List<SysAuthorityResourceDto> sysAuthorityResourceDtoList =null;
		sysAuthorityResourceDtoList=systemDaoService.getSysAuthoritiesByModule(moduleId);
			
		String jsonString ="{ \"totalCount\":"+sysAuthorityResourceDtoList.size()+", \"records\":[";
		for(SysAuthorityResourceDto sysAuthRes:sysAuthorityResourceDtoList){
			jsonString+="{\"authorityId\":\""+sysAuthRes.getAuthorityId()+"\",";
			jsonString+="\"authorityName\":\""+sysAuthRes.getAuthorityName()+"\",";
			jsonString+="\"authorityDesc\":\""+sysAuthRes.getAuthorityDesc()+"\",";
			jsonString+="\"resourceId\":\""+sysAuthRes.getResourceId()+"\",";
			jsonString+="\"issys\":\""+sysAuthRes.getIssys()+"\",";
			jsonString+="\"resourceString\":\""+sysAuthRes.getResourceString()+"\",";
			jsonString+="\"module\":\""+moduleId+"\"},";
		}
		jsonString=sysAuthorityResourceDtoList.size()!=0?jsonString.substring(0, jsonString.length()-1):jsonString;
		jsonString+="]}";
		return jsonString;
	}
	@RequestMapping(value = "/admin/sys/manageAuthorityDelete")
	@ResponseBody
	public Map<String, String> deleteAuthorityResource(@RequestBody SysAuthorityResourceDto sysAuthorityResourceDto, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			systemDaoService.removeSysAuthoritiesResources(sysAuthorityResourceDto.getAuthorityId());
			systemDaoService.removeAuthority(sysAuthorityResourceDto.getAuthorityId());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		
		
		return imsg;
	}

	@RequestMapping(value = "/admin/sys/manageAuthoritySave")
	@ResponseBody
	public Map<String, String> saveAuthority(
			@RequestBody SysAuthorityResourceDto sysAuthorityResourceDto, ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {sysAuthorityResourceDto.setAuthorityId(sysAuthorityResourceDto.getAuthorityName());
		  sysAuthorityResourceDto.setResourceName(sysAuthorityResourceDto.getResourceId());
			String selectString=systemDaoService.isExistAuthority(sysAuthorityResourceDto.getAuthorityId());
			if(selectString!=null){
				systemDaoService.editAuthority(sysAuthorityResourceDto);
			}else{
				systemDaoService.addAuthority(sysAuthorityResourceDto);
				systemDaoService.addSysAuthoritiesResources(sysAuthorityResourceDto);
			}
			
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	
}
