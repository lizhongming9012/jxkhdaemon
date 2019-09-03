package org.jxkh.common.tree;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TreeDao {

	public List<TreeModel> getChildByParentId(@Param("tabName") String tabName, @Param("parentId") int parentId);
	public List<TreeModel> getChildWithHerfByParentId(@Param("tabName") String tabName, @Param("parentId") int parentId);
	public List<Integer> getParentId(@Param("tabName") String tabName);
	public List<TreeModel> getChildWithHerfByTableNameParentId(@Param("tabName") String tabName, @Param("parentId") int parentId);
	public List<Integer> getParentIdByTableName(@Param("tabName") String tabName);
	
	
	public List<Integer> getYwParentId(@Param("tabName") String tabName);
	
	public List<Integer> getYwParentIdV(@Param("tabName") String tabName,@Param("v") String v);

}
