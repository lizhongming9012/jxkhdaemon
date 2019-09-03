package org.jxkh.system.dto;

import org.springframework.stereotype.Component;

@Component
public class SysAuthorityResourceDto {

	private String authorityId;
	private String resourceId;
	private String authorityName;
    private String resourceString;
    private String resourceName;
    private String authorityDesc;
    private String issys;
    private String module;
    
	public String getIssys() {
		return issys;
	}
	public void setIssys(String issys) {
		this.issys = issys;
	}
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getResourceString() {
		return resourceString;
	}
	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}
	public String getAuthorityDesc() {
		return authorityDesc;
	}
	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
   
}
