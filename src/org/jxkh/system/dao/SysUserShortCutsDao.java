package org.jxkh.system.dao;

import java.util.List;

import org.jxkh.security.dto.SysMenuResourceDto;
import org.jxkh.system.model.SysMenus;
import org.jxkh.util.TreeNode;

public interface SysUserShortCutsDao {

	public List<TreeNode> getUserShortCuts(String userAccount);
}
