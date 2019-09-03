package org.jxkh.system.tree;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jxkh.security.dto.SysMenuResourceDto;
import org.jxkh.system.service.SystemDaoService;
import org.jxkh.util.TreeNode;

@Component
public class UserMenusTree {

	@Autowired
	private SystemDaoService systemDaoService ;

	private List<SysMenuResourceDto> menuTreeNode;

	public List<TreeNode> getTreeNode(){

		List<TreeNode> treeNodeArray = null;
		List<String> parentIdList = systemDaoService.getParentId();
		treeNodeArray = new ArrayList<TreeNode>();

		for (SysMenuResourceDto sysMenusResouceDto : this.menuTreeNode) {

			TreeNode treeNode = new TreeNode();
			treeNode.setId(sysMenusResouceDto.getMenuId());
			treeNode.setText(sysMenusResouceDto.getDisplay());
			treeNode.setDescription(sysMenusResouceDto.getDescription());
			if (sysMenusResouceDto.getParentId() == null) {
				continue;
			} else {
				treeNode.setParentId(sysMenusResouceDto.getParentId());
			}
			if (parentIdList.contains(sysMenusResouceDto.getMenuId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else //
			{
				treeNode.setHref("admin/menuTreeNodeRedirect?id="
						+ sysMenusResouceDto.getMenuId());

				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}

		return treeNodeArray;

	}

	public List<SysMenuResourceDto> getMenuTreeNode() {
		return menuTreeNode;
	}

	public void setMenuTreeNode(List<SysMenuResourceDto> menuTreeNode) {
		this.menuTreeNode = menuTreeNode;
	}
	//递归生成树json串
	public String genTreeJson(TreeNode parentNode) {
		StringBuffer jsonStr = new StringBuffer();
		if ("folder".equals(parentNode.getCls())) {
			List<TreeNode> children = this.getTreeNodeByParentId(parentNode.getId());
			jsonStr.append("{\"id\":\"" + parentNode.getId() + "\",\"text\":\""
					+ parentNode.getText() + "\",\"leaf\":false,\"cls\":\"folder\",\"children\":[");
			for (TreeNode child : children) {
				StringBuffer jsonStrTemp = new StringBuffer();
				jsonStrTemp.append(genTreeJson(child));
				jsonStrTemp.append(",");
				jsonStr.append(jsonStrTemp.toString());
			}			
			jsonStr= ",".equals(jsonStr.substring(jsonStr.length()-1))?jsonStr.deleteCharAt(jsonStr.length()-1):jsonStr;
			jsonStr.append("]}");
		} else {
			jsonStr.append("{\"id\":\"" + parentNode.getId() + "\",\"text\":\""
					+ parentNode.getText() + "\",\"leaf\":true,\"cls\":\"file\",\"href\":\""
					+ parentNode.getHref() + "\"}");
		}
		return jsonStr.toString();
	}

	private List<TreeNode> getTreeNodeByParentId(String id){
		List<TreeNode> myTreeNodes = new ArrayList<TreeNode>();
		for(TreeNode node :this.getTreeNode()){
			if(id.equals(node.getParentId())){
				myTreeNodes.add(node);
			}
		}
		return myTreeNodes;
	}

}
