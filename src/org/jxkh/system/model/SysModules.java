package org.jxkh.system.model;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SysModules implements java.io.Serializable{

	private String moduleId;
	private String parentId;
	private String moduleName;
	private String dispindex;
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDispindex() {
		return dispindex;
	}
	public void setDispindex(String dispindex) {
		this.dispindex = dispindex;
	}
	
	
	
}
