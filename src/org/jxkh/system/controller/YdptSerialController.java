package org.jxkh.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.jxkh.security.CustomUserDetails;
import org.jxkh.system.model.YdptKey;
import org.jxkh.system.service.YdptSerialService;
import org.jxkh.util.FillBeanProperyAsNUllForMybatis;

/**
 * 移动平台授权管理
 * @author ICE
 *
 */
@Controller
public class YdptSerialController {
	
	@Autowired
	public YdptSerialService ydptSerialService;
	
	@RequestMapping(value = "/admin/ydpt/ydptkey/getYdptSerialList")
	@ResponseBody
	public Map<String, Object> getYdptSerialList(YdptKey ydptBean) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<YdptKey> ydptList= ydptSerialService.getYdptSerialList(ydptBean);
			resultMap.put("records", ydptList);
			int count=ydptSerialService.getYdptSerialListCount(ydptBean);
			resultMap.put("totalCount",count);
			resultMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);

		}
		return resultMap;
	}
	
	@RequestMapping(value = "/admin/ydpt/ydptkey/deleteYdptSerial")
	@ResponseBody
	public Map<String, String> deleteYdptSerial(@RequestBody YdptKey ydptBean,
			ModelMap model) {
		Map<String, String> imsg = new HashMap<String, String>();
		try {
			ydptSerialService.deleteYdptSerial(ydptBean.getId());
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}

		return imsg;
	}
	
	@RequestMapping(value = "/admin/ydpt/ydptkey/ydptKeySave")
	@ResponseBody
	public Map<String, Object> saveYdptKey(@RequestBody YdptKey ydptBean,
			ModelMap model) {
		FillBeanProperyAsNUllForMybatis.fillNull(ydptBean);
		
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			
			ydptSerialService.insertYdptKey(ydptBean);
			
			YdptKey ydptBack = ydptSerialService.getYdptKeyById(ydptBean.getId());

			imsg.put("records", ydptBack);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}
	
	@RequestMapping(value = "/admin/ydpt/ydptkey/ydptKeyUpdate")
	@ResponseBody
	public Map<String, Object> updateYdptKey(@RequestBody YdptKey ydptBean,
			ModelMap model) {
		FillBeanProperyAsNUllForMybatis.fillNull(ydptBean);
		
		Map<String, Object> imsg = new HashMap<String, Object>();
		try {
			
			ydptSerialService.updateYdptKey(ydptBean);
			
			YdptKey ydptBack = ydptSerialService.getYdptKeyById(ydptBean.getId());

			imsg.put("records", ydptBack);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}
	
	
	/****************************************手机移动端接口**************************************/
	
	@RequestMapping(value = "/ydpt/telephone/updatePhoneSerial")
	@ResponseBody
	public void updatePhoneSerial(@RequestBody YdptKey ydptBean) {
		FillBeanProperyAsNUllForMybatis.fillNull(ydptBean);
		try {			
			ydptSerialService.updatePhoneSerial(ydptBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/ydpt/telephone/serialPhoneByNum")
	@ResponseBody
	public Map<String, Object> serialPhoneByNum(String telephone) {
		Map<String, Object> imsg = new HashMap<String, Object>();		
		try {			
			int serialNum = ydptSerialService.serialPhoneByNum(telephone);
			imsg.put("serial", serialNum);
			imsg.put("success", "true");
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}
	
	@RequestMapping(value = "/ydpt/telephone/serialPhoneByKey")
	@ResponseBody
	public Map<String, Object> serialPhoneByKey(String teleKey,String deviceId) {
		Map<String, Object> imsg = new HashMap<String, Object>();	
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		try {		
			int serialNum = ydptSerialService.serialPhoneByKey(deviceId,teleKey);
			if(serialNum>0){
				imsg.put("serial", serialNum);
				imsg.put("success", "true");
			}else{
				if(ydptSerialService.getUserIfExit(user.getUserName(),user.getUserDept())>0){
					imsg.put("success", "false");
				}else{
					YdptKey ydptBean = new YdptKey();
					ydptBean.setUserName(user.getUserName());
					ydptBean.setUserSex("0");
					ydptBean.setUserDep(user.getUserDept());
					ydptBean.setKeyState("0");
					ydptBean.setTelephone(deviceId);
					ydptBean.setPhoneSerial(teleKey);
					
					ydptSerialService.insertYdptKey(ydptBean);
					imsg.put("serial", serialNum);
					imsg.put("success", "true");
				}
			}
			
		} catch (Exception e) {
			imsg.put("success", "false");
			e.printStackTrace();
		}
		return imsg;
	}
}
