package org.jxkh.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxkh.security.model.SysRoles;

public interface SysRolesDao {
	public SysRoles selectSysRoles(int roleId);

	public void insertSysRoles(SysRoles sysRoles);

	public void updateSysRoles(SysRoles sysRoles);

	public void deleteSysRoles(String roleId);

	public List<SysRoles> selectAllRolesByUserId(String userId);

	public List<SysRoles> selectAllRoles(SysRoles sysrole);

	public String selectRoleById(String roleName);

	public int selectAllRolesCount(SysRoles sysrole);

	public void insertRoleToUser(@Param("roleId") String roleId,
			@Param("userId") String userId);
}
