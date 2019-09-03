package org.jxkh.security.dao;

import java.util.List;

import org.jxkh.security.model.SysAuthoritiesResources;
import org.jxkh.system.dto.SysAuthorityResourceDto;

public interface SysAuthoritiesResourcesDao {

	public List<String> getSysResourcesString(String auth);

	public void deleteSysAuthoritiesResources(String authorityId);

	public void insertSysAuthoritiesResources(
			SysAuthorityResourceDto sysAuthorityResourceDto);
}
