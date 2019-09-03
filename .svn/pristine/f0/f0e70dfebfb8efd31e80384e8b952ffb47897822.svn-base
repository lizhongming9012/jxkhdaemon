package org.jxkh.security.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SysResources implements java.io.Serializable {

	private String resourceId;
	private String resourceName;
	private String resourceDesc;
	private String resourceType;
	private String resourceString;
	private String priority;
	private String id;

	private String enabled;
	private String issys;

	private String module;
	private Set pubAuthoritiesResourceses = new HashSet(0);

	public SysResources() {
	}

	public SysResources(String resourceId) {
		this.resourceId = resourceId;
	}

	public SysResources(String resourceId, String resourceName,
			String resourceDesc, String resourceType, String resourceString,
			String priority, String enabled, String issys, String module,
			Set pubAuthoritiesResourceses) {
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceDesc = resourceDesc;
		this.resourceType = resourceType;
		this.resourceString = resourceString;
		this.priority = priority;
		this.enabled = enabled;
		this.issys = issys;
		this.module = module;
		this.pubAuthoritiesResourceses = pubAuthoritiesResourceses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceDesc() {
		return this.resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceString() {
		return this.resourceString;
	}

	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getIssys() {
		return issys;
	}

	public void setIssys(String issys) {
		this.issys = issys;
	}

	public Set getPubAuthoritiesResourceses() {
		return pubAuthoritiesResourceses;
	}

	public void setPubAuthoritiesResourceses(Set pubAuthoritiesResourceses) {
		this.pubAuthoritiesResourceses = pubAuthoritiesResourceses;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Set getSysAuthoritiesResourceses() {
		return this.pubAuthoritiesResourceses;
	}

	public void setSysAuthoritiesResourceses(Set pubAuthoritiesResourceses) {
		this.pubAuthoritiesResourceses = pubAuthoritiesResourceses;
	}

}
