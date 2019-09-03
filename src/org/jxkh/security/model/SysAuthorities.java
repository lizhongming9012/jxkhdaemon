package org.jxkh.security.model;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
public class SysAuthorities implements java.io.Serializable {

	private String authorityId;
	private String authorityName;
	private String authorityDesc;
	private String enabled;
	private String issys;
	private String module;

	public SysAuthorities() {
	}

	public SysAuthorities(String authorityId) {
		this.authorityId = authorityId;
	}

	public SysAuthorities(String authorityId, String authorityName,
			String authorityDesc, String enabled, String issys, String module) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
		this.enabled = enabled;
		this.issys = issys;
		this.module = module;
	}

	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return this.authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
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

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

}
