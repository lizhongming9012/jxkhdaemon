package org.jxkh.common.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.jxkh.dqyglxt.model.TreeDqyModel;
//import org.jxkh.dqyglxt.model.TreeDqyNode;
import org.jxkh.security.service.SecurityDaoService;


@Component
public class TreeUtil {

	private int parentId;
	private String parStrId;
	private String tabName;
	@Autowired
	private TreeService treeService;
	@Autowired
	private SecurityDaoService securityDaoService;
	private static ObjectMapper objectMapper = new ObjectMapper();

	public String getTreeJSONString(String userId)
			throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<TreeNode> treeNodeArray = null;
		List<TreeModel> childList = treeService.getChildByParentId(tabName,
				parentId);
		treeNodeArray = new ArrayList<TreeNode>();
		List<Integer> parentIdList = treeService.getParentId(tabName);
		List<Integer> appIdList = securityDaoService.getAppList(userId);
		for (TreeModel treeModel : childList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(treeModel.getId());
			treeNode.setText(treeModel.getText());
			if (parentIdList.contains(treeModel.getId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else {
				if (!appIdList.contains(treeModel.getId()))
					continue;
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}
		return objectMapper.writeValueAsString(treeNodeArray);
	}

	public List<TreeNode> getTreeNodeList(String userId)
			throws JsonGenerationException, JsonMappingException, IOException {
		ArrayList<TreeNode> treeNodeArray = null;
		List<TreeModel> childList = treeService.getChildWithHerfByParentId(
				tabName, parentId);
		treeNodeArray = new ArrayList<TreeNode>();
		List<Integer> parentIdList = treeService.getParentId(tabName);
		List<Integer> appIdList = securityDaoService.getAppList(userId);
		for (TreeModel treeModel : childList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(treeModel.getId());
			treeNode.setText(treeModel.getText());
			if (parentIdList.contains(treeModel.getId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else {
				if (!appIdList.contains(treeModel.getId()))
					continue;
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setHref(treeModel.getHref());
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}
		return treeNodeArray;
	}

	public List<TreeNode> getTreeNodeList() throws JsonGenerationException,
			JsonMappingException, IOException {
		ArrayList<TreeNode> treeNodeArray = null;
		List<TreeModel> childList = treeService
				.getChildWithHerfByTableNameParentId(tabName, parentId);
		treeNodeArray = new ArrayList<TreeNode>();
		List<Integer> parentIdList = treeService
				.getParentIdByTableName(tabName);
		for (TreeModel treeModel : childList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(treeModel.getId());
			treeNode.setText(treeModel.getText());
			if (parentIdList.contains(treeModel.getId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else {
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setHref(treeModel.getHref());
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}
		return treeNodeArray;
	}

	public String getCheckTreeJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {
		ArrayList<CheckTreeNode> treeNodeArray = null;
		List<TreeModel> childList = treeService.getChildByParentId(tabName,
				parentId);
		List<Integer> parentIdList = treeService.getParentId(tabName);
		treeNodeArray = new ArrayList<CheckTreeNode>();
		for (TreeModel treeModel : childList) {
			CheckTreeNode treeNode = new CheckTreeNode();
			treeNode.setId(treeModel.getId());
			treeNode.setText(treeModel.getText());
			if (parentIdList.contains(treeModel.getId())) {
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else {
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}
		return objectMapper.writeValueAsString(treeNodeArray);
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	/*  大企业  */
//	public List<TreeDqyNode> getDqyTreeNodeList(String v)
//			throws JsonGenerationException, JsonMappingException, IOException {
//		ArrayList<TreeDqyNode> treeNodeArray = null;
//		List<TreeDqyModel> childList = treeService
//				.getDqyChildWithHerfByParentId(tabName, v);
//		List<TreeDqyModel> childListI = new ArrayList<TreeDqyModel>();
//		for (TreeDqyModel dtm : childList) {
//			dtm.setHref("admin/dqyglxt/common/editContent");
//			childListI.add(dtm);
//		}
//
//		treeNodeArray = new ArrayList<TreeDqyNode>();
//
//		for (TreeDqyModel treeModel : childListI) {
//			TreeDqyNode treeNode = new TreeDqyNode();
//			treeNode.setId(treeModel.getId());
//			treeNode.setText(treeModel.getText());
//
//			treeNode.setCls("file");
//			treeNode.setLeaf(true);
//			treeNode.setHref(treeModel.getHref());
//			treeNode.setExpandable(false);
//
//			treeNodeArray.add(treeNode);
//
//		}
//		return treeNodeArray;
//	}

	public String getParStrId() {
		return parStrId;
	}

	public void setParStrId(String parStrId) {
		this.parStrId = parStrId;
	}

}
