package org.jxkh.system.dao;

import java.util.List;
import java.util.Map;

import org.jxkh.system.model.YdptKey;

public interface YdptSerialDao {

	public List<YdptKey> getYdptSerialList(YdptKey ydptBean);
	
	public void insertYdptKey(YdptKey ydptBean);
	
	public void deleteYdptSerial(String id);
	
	public YdptKey getYdptKeyById(String id);
	
	public void updateYdptKey(YdptKey ydptBean);
	
	public void updatePhoneSerial(YdptKey ydptBean);
	
	public int serialPhoneByNum(String telephone);
	
	public int serialPhoneByKey(Map<String, String> map);
	
	public int getUserIfExit(Map<String, String> map);
	
	public int getYdptSerialListCount(YdptKey ydptBean);
}
