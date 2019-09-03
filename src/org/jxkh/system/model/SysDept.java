package org.jxkh.system.model;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SysDept implements java.io.Serializable{

	private String deptName;
	private String deptDis;	
	private String deptDesc;
	private String text;//对应前台ExtJS 树的'text'属性
	private String id;
	private String deptId;//为了使用ExtJs id,id=departId实际是id表示部门的id，但是前台适应ExtJs机制，又重复定义了一个deptId,
	
	public final String getDeptId() {
		return deptId;
	}
	public final void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	private String deptsort;
	private String parentId;
	private String deptstandalone;//机构还是部门
	private boolean leaf;//对应前台ExtJS树的'leaf'属性
	
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public final String getDeptstandalone() {
		return deptstandalone;
	}
	public final void setDeptstandalone(String deptstandalone) {
		this.deptstandalone = deptstandalone;
	}
	public final String getDeptsort() {
		return deptsort;
	}
	public final void setDeptsort(String deptsort) {
		this.deptsort = deptsort;
	}
	public final String getParentId() {
		return parentId;
	}
	public final void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDeptDis() {
		return deptDis;
	}
	public void setDeptDis(String deptDis) {
		this.deptDis = deptDis;
	}
	
}
