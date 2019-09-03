package org.jxkh.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jxkh.common.dao.AppDao;
import org.jxkh.security.dao.SysAuthoritiesDao;
import org.jxkh.security.dao.SysAuthoritiesResourcesDao;
import org.jxkh.security.dao.SysRolesAuthoritiesDao;
import org.jxkh.security.dao.SysRolesDao;
import org.jxkh.security.dao.SysUsersDao;
import org.jxkh.security.dao.SysUsersRolesDao;
import org.jxkh.security.model.SysAuthorities;
import org.jxkh.security.model.SysResources;
import org.jxkh.security.model.SysRoles;
import org.jxkh.security.model.SysRolesAuthorities;
import org.jxkh.security.model.SysUsers;
import org.jxkh.security.model.SysUsersRoles;
import org.jxkh.system.dao.SysDepartsDao;
import org.jxkh.system.dao.SysDeptDao;
import org.jxkh.system.dao.SysResourcesDao;
import org.jxkh.system.model.SysDeparts;
import org.jxkh.system.model.SysDept;

@Service
public class SecurityDaoService {

	@Autowired
	public SysAuthoritiesDao sysAuthoritiesDao;
	@Autowired
	public SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;
	@Autowired
	public SysResourcesDao sysResourcesDao;
	@Autowired
	public SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	@Autowired
	public SysUsersDao sysUsersDao;
	@Autowired
	public SysDepartsDao sysDepartsDao;
	@Autowired
	public SysDeptDao sysDeptDao;
	@Autowired
	public SysRolesDao sysRolesDao;
	@Autowired
	public SysUsersRolesDao sysUsersRolesDao;
	@Autowired
	public AppDao appDao;

	/**
	 * 获取全部权限名
	 * 
	 * @return
	 */
	public List<String> getSysAuthoritiesNames() {
		return sysAuthoritiesDao.selectSysAuthoritiesNames();
	}

	/**
	 * 根据账号获取用户对象
	 * 
	 * @param userName
	 * @return
	 */
	public SysUsers findByUserAccount(String userName) {
		return sysUsersDao.findByUserAccount(userName);
	}

	/**
	 * 获取用户账号为'userName'的全部权限名
	 * 
	 * @param userName
	 * @return
	 */
	public List<String> loadUserAuthorities(String userName) {
		return sysUsersDao.loadUserAuthorities(userName);
	}

	/**
	 * 获取权限名为auth的资源url
	 * 
	 * @param auth
	 * @return
	 */
	public List<String> getSysResourcesString(String auth) {
		return sysAuthoritiesResourcesDao.getSysResourcesString(auth);
	}

	// 用户基本信息操作
	public List<SysUsers> selectUsers(String userId) {
		return sysUsersDao.selectUsers(userId);
	}

	public void deleteUsers(String userId) {
		sysUsersDao.deleteUsers(userId);
		// sysUsersDao.deleteMailUser(userId);
	}

	public void insertUsers(SysUsers sysUsers) {
		// // 添加邮件用户
		// if (sysUsersDao.mailUserExit(sysUsers.getUserId()) == 0) {
		// sysUsersDao.insertMailUser(sysUsers);
		// }
		// 添加平台用户
		sysUsersDao.insertUsers(sysUsers);
	}

	public void updateUsers(SysUsers sysUsers) {
		sysUsersDao.updateUsers(sysUsers);
	}

	public List<SysUsers> getAllUsers(SysUsers sysUsers) {
		return sysUsersDao.selectAllUsers(sysUsers);
	}

	// 全部角色检出，SYS_ROLES表
	public List<SysRoles> getAllRoles(String userId) {
		return sysRolesDao.selectAllRolesByUserId(userId);
	}

	// 按用户ID检出用户角色SYS_USERS_ROLES和SYS_ROLES表
	public List<SysUsersRoles> getUsersRolesByUserId(String userId) {

		return sysUsersRolesDao.selectSysUsersRolesByUserId(userId);
	}

	public void insertUsersRoles(SysUsersRoles sysUsersRoles) {
		sysUsersRolesDao.insertSysUsersRoles(sysUsersRoles);
	}

	public void deleteUsersRoles(int id) {
		sysUsersRolesDao.deleteSysUsersRoles(id);
	}

	public String isExistUserRole() {
		return "y";
	}

	// 部门树
	public List<SysDeparts> getDepartChildByParentId(String parentId) {
		return sysDepartsDao.getChildByParentId(parentId);
	}

	public SysDept getDeparts(String deptId) {
		return sysDeptDao.getDeparts(deptId);
	}

	public List<String> getDepartParentId() {
		return sysDepartsDao.getParentId();
	}

	public List<SysAuthorities> getAllAuthority(String roleId) {

		return sysAuthoritiesDao.getAllAuthority(roleId);
	}

	public List<SysRolesAuthorities> getRoleAuthorityByroleId(String roleId) {

		return sysRolesAuthoritiesDao.getRoleAuthorityByroleId(roleId);
	}

	public void deleteRoleAuthority(int id) {

		sysRolesAuthoritiesDao.deleteRoleAuthority(id);
	}

	public void insertRoleAuthority(SysRolesAuthorities roleAuthority) {
		sysRolesAuthoritiesDao.insertSysRolesAuthorities(roleAuthority);

	}

	public List<SysAuthorities> getAllApp(String roleId) {
		return appDao.getAllAppByRoleId(roleId);
	}

	public void insertRoleApp(SysRolesAuthorities roleAuthority) {
		appDao.insertRoleApp(roleAuthority);
	}

	public void deleteRoleApp(int id) {
		appDao.deleteRoleApp(id);
	}

	public List<Integer> getAppList(String userId) {
		// TODO Auto-generated method stub
		return appDao.getAppList(userId);
	}

	public void deleteRoleAppByRoleAndAppId(String roleId, String appId) {

		appDao.deleteRoleAppByRoleAndAppId(roleId, appId);
	}

	public void updateUserStatus(String userId, String status) {

		sysUsersDao.updateUserStatus(userId, status);
	}

	public void updateUser(SysUsers users) {
		// 修改邮件的用户密码
		// sysUsersDao.updateMaileUserPwd(users);

		sysUsersDao.updateSysUser(users);
	}

	public String onlineUsers() {

		return sysUsersDao.onlineUsers();
	}

	public int getAllUsersCount(SysUsers sysUsers) {
		// TODO Auto-generated method stub
		return sysUsersDao.selectAllUsersCount(sysUsers);
	}

	public SysUsers checkLoginYwgl(String userAccount, String md5Pass) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("userAccount", userAccount);
		map.put("md5Pass", md5Pass);
		return sysUsersDao.checkLoginYwgl(map);
	}

	public SysUsers getUserInfobymobile(String userAccount) {
		return sysUsersDao.getUserInfobymobile(userAccount);
	}

	public int userifexist(String userId) {
		return sysUsersDao.userifexist(userId);
	}

	public int useraccountifexist(String userAccount) {
		return sysUsersDao.useraccountifexist(userAccount);
	}

	public int userinfoifexist(String userId, String userAccount) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userAccount", userAccount);
		return sysUsersDao.userinfoifexist(map);
	}

}
