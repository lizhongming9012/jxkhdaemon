package org.jxkh.system.dao;

import java.util.List;

import org.jxkh.system.model.SysDeparts;
import org.jxkh.system.model.SysDept;

public interface SysDeptDao  {

	public List<SysDept> getDeptChildByParentId(String parentId);
	public List<String> getDeptParentId();
	public List<String> getDeptId();
	public List<SysDept> getDeptBranchById(String id);
	public void deleteSysDept(String id);
	public void insertSysDept(SysDept sysDept);
	public void updateSysDept(SysDept sysDept);
	public String selectDeptById(String deptId);
	public SysDept getDeparts(String deptId);
	public List<SysDept> getParentDepart(String deptId);	

}
