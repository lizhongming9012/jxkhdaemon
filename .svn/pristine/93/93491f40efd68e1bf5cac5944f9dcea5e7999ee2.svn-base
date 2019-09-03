package org.jxkh.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jxkh.system.dao.YdptSerialDao;
import org.jxkh.system.model.YdptKey;

@Service
public class YdptSerialService {
	
	@Autowired
	public YdptSerialDao ydptSerialDao;
	
	public List<YdptKey> getYdptSerialList(YdptKey ydptBean){
		return ydptSerialDao.getYdptSerialList(ydptBean);
	}
	
	public int getYdptSerialListCount(YdptKey ydptBean){
		return ydptSerialDao.getYdptSerialListCount(ydptBean);
	}
	
	public void insertYdptKey(YdptKey ydptBean){
		ydptSerialDao.insertYdptKey(ydptBean);
	}
	
	public void deleteYdptSerial(String id){
		ydptSerialDao.deleteYdptSerial(id);
	}

	public YdptKey getYdptKeyById(String id){
		return ydptSerialDao.getYdptKeyById(id);
	}
	
	public void updateYdptKey(YdptKey ydptBean){
		ydptSerialDao.updateYdptKey(ydptBean);
	}
	
	/****************手机********************/
	public void updatePhoneSerial(YdptKey ydptBean){
		ydptSerialDao.updatePhoneSerial(ydptBean);
	}
	
	public int serialPhoneByNum(String telephone){
		return ydptSerialDao.serialPhoneByNum(telephone);
	}
	
	public int serialPhoneByKey(String deviceId,String telekey){
		Map<String,String> map = new HashMap<String, String>();
		map.put("deviceId", deviceId);
		map.put("telekey", telekey);
 		return ydptSerialDao.serialPhoneByKey(map);
	}
	
	public int getUserIfExit(String userName,String userDept){
		Map<String,String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("userDept", userDept);
 		return ydptSerialDao.getUserIfExit(map);
	}
}
