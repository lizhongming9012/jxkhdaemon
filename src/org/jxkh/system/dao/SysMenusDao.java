package org.jxkh.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jxkh.security.dto.SysMenuResourceDto;
import org.jxkh.system.model.SysMenus;
import org.jxkh.util.TreeNode;

public interface SysMenusDao {

	public List<SysMenuResourceDto> getUserMenuResourcesString(String auth);
	public List<SysMenuResourceDto> getMenuBranchById(String menuId);
	public List<SysMenus> getChildByParentId(String parentId);
	public List<String> getParentId();
	public String getMenuResource(String menuId);
	public void insertNode(SysMenus sysMenus);
	public void deleteNode(String menuId);
	public void updateNode(SysMenus sysMenus);
	public  List<String> getMenuLeafByUserAccount(String userAccount);
	public List<SysMenuResourceDto> getMenusByUserAccount(@Param("userAccount") String userAccount,@Param("ismobile") String ismobile);
	
	public List<TreeNode> getChildrenByParentId(String parentId);
}
