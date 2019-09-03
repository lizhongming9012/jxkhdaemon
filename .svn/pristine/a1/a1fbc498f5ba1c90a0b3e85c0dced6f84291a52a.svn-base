package org.jxkh.system.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.jxkh.security.dao.SysAuthoritiesDao;
import org.jxkh.security.dao.SysAuthoritiesResourcesDao;
import org.jxkh.security.dao.SysRolesAuthoritiesDao;
import org.jxkh.security.dao.SysRolesDao;
import org.jxkh.security.dao.SysUsersDao;
import org.jxkh.security.dto.SysMenuResourceDto;
import org.jxkh.security.model.SysAuthorities;
import org.jxkh.security.model.SysResources;
import org.jxkh.security.model.SysRoles;
import org.jxkh.security.model.SysRolesAuthorities;
import org.jxkh.security.model.SysUsers;
import org.jxkh.system.dao.SysDeptDao;
import org.jxkh.system.dao.SysMenusDao;
import org.jxkh.system.dao.SysModulesDao;
import org.jxkh.system.dao.SysResourcesDao;
import org.jxkh.system.dao.SysUserShortCutsDao;
import org.jxkh.system.dto.SysAuthorityResourceDto;
import org.jxkh.system.model.SysDept;
import org.jxkh.system.model.SysMenus;
import org.jxkh.system.model.SysModules;
import org.jxkh.util.TreeNode;
@Service
public class SystemDaoService {

	@Autowired
	public SysMenusDao sysMenusDao;
	@Autowired
	public SysUsersDao sysUsersDao;
	@Autowired
	public SysModulesDao sysModulesDao;
	@Autowired
	public SysResourcesDao sysResourcesDao;
	@Autowired
	public SysAuthoritiesDao sysAuthoritiesDao;
	@Autowired
	public SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;
	@Autowired
	public SysRolesAuthoritiesDao sysRolesAuthoritiesDao;
	@Autowired
	public SysRolesDao sysRolesDao;
	@Autowired
	public SysUserShortCutsDao sysUserShortCutsDao;

	@Autowired
	public SysDeptDao sysDeptDao;
	public Map<String, SysMenuResourceDto> imap = new HashMap<String, SysMenuResourceDto>(
			0);

	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysMenus> getChildByParentId(String parentId) {
		return sysMenusDao.getChildByParentId(parentId);
	}

	/**
	 * 获取所有父亲节点的代码
	 * 
	 * @return
	 */
	public List<String> getParentId() {
		return sysMenusDao.getParentId();
	}

	/**
	 * 根据权限名称获取菜单值对象SysMenuResourceDto的以菜单ID为key的map，
	 * 
	 * @param authName
	 */
	public void getUserMenusByAuth(String authName) {

		List<SysMenuResourceDto> menuResourceList = sysMenusDao
				.getUserMenuResourcesString(authName);
		for (SysMenuResourceDto smr : menuResourceList) {
			imap.put(smr.getMenuId(), smr);
		}
	}

	/**
	 * 获取每个子节点的到根的所有节点路径
	 */
	public void getUserMenuBranch() {
		// 值引用怎么搞？？？
		Object[] iKeySet = null;
		iKeySet = imap.keySet().toArray();
		for (int i = 0; i < iKeySet.length; i++) {
			String menuId = (String) iKeySet[i];
			List<SysMenuResourceDto> menuResourceList = sysMenusDao
					.getMenuBranchById(menuId);
			for (SysMenuResourceDto smr : menuResourceList) {
				if (!imap.containsKey(smr.getMenuId())) {
					imap.put(smr.getMenuId(), smr);
				}
			}
		}
	}

	/**
	 * 填充所有权限对应的资源及父的imap对象
	 */
	@SuppressWarnings("unchecked")
	public void setAuthMenuMap() {
		UserDetails userDetails = (SysUsers) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Collection<GrantedAuthority> auths = (Collection<GrantedAuthority>) userDetails
				.getAuthorities();
		imap=new HashMap<String, SysMenuResourceDto>(0);//重置imap
		for (GrantedAuthority au : auths) {
			this.getUserMenusByAuth(au.getAuthority());
		}
		this.getUserMenuBranch();

	}

	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysModules> getModuleChildByParentId(String parentId) {
		return sysModulesDao.getChildByParentId(parentId);
	}

	/**
	 * 获取所有父亲节点的代码
	 * 
	 * @return
	 */
	public List<String> getModuleParentId() {
		return sysModulesDao.getParentId();
	}

	/**
	 * 根据menuId获取url
	 * 
	 * @param menuId
	 * @return
	 */
	public String getMenuResource(String menuId) {
		return sysMenusDao.getMenuResource(menuId);
	}

	/**
	 * 删除模块节点
	 * 
	 * @param moduleId
	 */
	public void deleteModuleNode(String moduleId) {
		sysModulesDao.deleteNode(moduleId);
	}

	/**
	 * 增加模块节点
	 * 
	 * @param sysModules
	 */
	public void insertModuleNode(SysModules sysModules) {
		sysModulesDao.insertNode(sysModules);
	}

	/**
	 * 修改模块节点
	 * 
	 * @param sysModules
	 */
	public void updateModuleNode(SysModules sysModules) {
		sysModulesDao.updateNode(sysModules);
	}

	/**
	 * 根据moduleId获取资源列表
	 * 
	 * @param moudleId
	 * @return
	 */
	public List<SysResources> getSysResourcesByModuleId(String moudleId) {
		return sysResourcesDao.selectSysResourcesByModuleId(moudleId);
	}

	/**
	 * 删除资源节点
	 * 
	 * @param resourceId
	 */
	public void deleteResource(String resourceId) {
		sysResourcesDao.deleteSysResources(resourceId);
	}

	/**
	 * 增加资源节点
	 * 
	 * @param sysResources
	 */
	public void insertResource(SysResources sysResources) {
		sysResourcesDao.insertSysResources(sysResources);
	}

	/**
	 * 修改资源节点
	 * 
	 * @param sysResources
	 */
	public void updateResource(SysResources sysResources) {
		sysResourcesDao.updateSysResources(sysResources);
	}

	/**
	 * 是否存在该resources
	 * 
	 * @param resourceId
	 * @return
	 */
	public String isExistResource(String resourceId) {
		return sysResourcesDao.selectSysResourcesById(resourceId);
	}

	/**
	 * 增加权限记录
	 * 
	 * @param sysAuthorities
	 */
	public void addAuthority(SysAuthorityResourceDto sysAuthorityResourceDto) {
		sysAuthoritiesDao.insertSysAuthorities(sysAuthorityResourceDto);
	}

	/**
	 * 修改权限信息
	 * 
	 * @param sysAuthorities
	 */
	public void editAuthority(SysAuthorityResourceDto sysAuthorityResourceDto) {
		sysAuthoritiesDao.updateSysAuthorities(sysAuthorityResourceDto);
	}

	/**
	 * 删除权限
	 * 
	 * @param authorityId
	 */
	public void removeAuthority(String authorityId) {
		sysAuthoritiesDao.deleteSysAuthorities(authorityId);
	}

	/**
	 * 通过moduleId获取权限及资源信息
	 * 
	 * @param moduleId
	 * @return
	 */
	public List<SysAuthorityResourceDto> getSysAuthoritiesByModule(
			String moduleId) {
		return sysAuthoritiesDao.selectSysAuthoritiesByModule(moduleId);
	}

	/**
	 * 删除资源权限关联表中记录
	 * 
	 * @param authorityId
	 */
	public void removeSysAuthoritiesResources(String authorityId) {
		sysAuthoritiesResourcesDao.deleteSysAuthoritiesResources(authorityId);
	}

	/**
	 * 增加权限资源关联表记录
	 * 
	 * @param sysAuthorityResourceDto
	 */
	public void addSysAuthoritiesResources(
			SysAuthorityResourceDto sysAuthorityResourceDto) {
		sysAuthoritiesResourcesDao
				.insertSysAuthoritiesResources(sysAuthorityResourceDto);
	}

	public String isExistAuthority(String authorityId) {
		return sysAuthoritiesDao.selectSysAuthorities(authorityId);
	}

	/**
	 * 增加菜单项
	 * 
	 * @param sysMenus
	 */
	public void addMenuNode(SysMenus sysMenus) {
		sysMenusDao.insertNode(sysMenus);
	}

	/**
	 * 删除菜单项
	 * 
	 * @param menuId
	 */
	public void removeMenuNode(String menuId) {
		sysMenusDao.deleteNode(menuId);
	}

	/**
	 * 修改菜单项
	 * 
	 * @param sysMenus
	 */
	public void editMenuNode(SysMenus sysMenus) {
		sysMenusDao.updateNode(sysMenus);
	}
	/**
	 * 根据账号获取所有菜单的叶子节点
	 * @param userAccount
	 * @return
	 */
   public List<String> getMenuLeafByUserAccount(String userAccount)
   {
       return sysMenusDao.getMenuLeafByUserAccount(userAccount);
   }
   public List<TreeNode> getUserShortCuts(String userAccount){
	   return sysUserShortCutsDao.getUserShortCuts(userAccount);
   }
   /**
    * 更具菜单id获取其所有祖先节点
    * @param menuId
    * @return
    */
   public List<SysMenuResourceDto> getMenuBranchById(String menuId)
   {
	   return sysMenusDao.getMenuBranchById(menuId);
   }
   public List<TreeNode> getChildrenByParentId(String parentId)
   {
	   return sysMenusDao.getChildrenByParentId(parentId);
   }
   public List<TreeNode> getChildrenByParentId(String parentId,List<TreeNode> list)
   {
	   return sysMenusDao.getChildrenByParentId(parentId);
   }
	/**
	 * 插入角色权限
	 * 
	 * @param sysRolesAuthorities
	 */
	public void addSysRolesAuthorities(SysRolesAuthorities sysRolesAuthorities) {
		sysRolesAuthoritiesDao.insertSysRolesAuthorities(sysRolesAuthorities);
	}
	public List<SysRoles> getAllRoles(SysRoles sysrole)
	{
		return sysRolesDao.selectAllRoles(sysrole);
	}

	public String isExistRole(String roleName) {
		// TODO Auto-generated method stub
		return sysRolesDao.selectRoleById(roleName);
	}

	public void insertRole(SysRoles sysRole) {
		// TODO Auto-generated method stub
		sysRolesDao.insertSysRoles(sysRole);
		
	}

	public void updateRole(SysRoles sysRole) {
		// TODO Auto-generated method stub
		sysRolesDao.updateSysRoles(sysRole);
	}

	public void delRole(String roleId) {
		// TODO Auto-generated method stub
		sysRolesDao.deleteSysRoles(roleId);
	}
	/** 部门维护 增删改
	 * 
	 */
	/**
	 * 通过parentId获取它的儿子，不包括孙子及后代
	 * 
	 * @param parentId
	 * @return
	 */
	public List<SysDept> getDeptChildByParentId(String parentId) {
		return sysDeptDao.getDeptChildByParentId(parentId);
	}

	/**
	 * 获取所有父亲节点的代码
	 * 
	 * @return
	 */
	public List<String> getDeptParentId() {
		return sysDeptDao.getDeptParentId();
	}
	/**
	 * 删除部门
	 * 
	 * @return
	 */
	public void removeDeptNode(String id){
	   sysDeptDao.deleteSysDept(id);
	}
	/**
	 * 增加部门
	 * 
	 * @return
	 */
	public void addDeptNode(SysDept sysDept){
	   sysDeptDao.insertSysDept(sysDept);
	}
     /**
      * 编辑部门
      * 
      * @return
      */
	public void editDeptNode(SysDept sysDept){
		 sysDeptDao.updateSysDept(sysDept);
		 }
	/**
	 * 获取所有部门Id的代码
	 * 
	 * @return
	 */
	public List<String> getDeptId() {
		return sysDeptDao.getDeptId();
	}
	/**
	 * 校验部门Id的代码
	 * 
	 * @return
	 */
	public String isExistDept(String deptId) {
		// TODO Auto-generated method stub
		return sysDeptDao.selectDeptById(deptId);
	}

	public List<SysMenuResourceDto> getMenusByUserAccount(String userAccount,String ismobile) {
		return sysMenusDao.getMenusByUserAccount(userAccount,ismobile);
	}

	public SysUsers getUserByAccount(String userAccount) {
		return sysUsersDao.getUserByAccount(userAccount);
		
	}

	public int getAllRolesCount(SysRoles sysRole) {
		// TODO Auto-generated method stub
		return sysRolesDao.selectAllRolesCount(sysRole);
	}
	//为多个用户分配单一角色
	public void insertRoleToUser(String roleId,String userId)
	{
		sysRolesDao.insertRoleToUser(roleId,userId);
	}
	
}
