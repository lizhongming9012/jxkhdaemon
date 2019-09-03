package org.jxkh.system.tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysDeparts;
import org.jxkh.system.model.SysDept;
import org.jxkh.system.service.SystemDaoService;
import org.jxkh.util.DeptTreeNode;
import org.jxkh.util.TreeNode;

@Component
public class SysDeptTree {

	private String parentId;
	@Autowired
	private SystemDaoService systemDaoService;
	@Autowired
	private SecurityDaoService securityDaoService;
	private static ObjectMapper objectMapper = new ObjectMapper();

	public ArrayList<TreeNode> getJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {

		ArrayList<TreeNode> treeNodeArray = null;

		List<SysDept> childList = systemDaoService
				.getDeptChildByParentId(parentId);

		List<String> parentIdList = systemDaoService.getParentId();

		treeNodeArray = new ArrayList<TreeNode>();
		systemDaoService.setAuthMenuMap();
		for (SysDept sysDept : childList) {
			if (systemDaoService.imap.containsKey(sysDept.getId())) {
				TreeNode treeNode = new TreeNode();
				treeNode.setId(sysDept.getId());
				treeNode.setText(sysDept.getDeptDis());
				treeNode.setDescription(sysDept.getDeptDesc());

				if (parentIdList.contains(sysDept.getId())) // ���ڵ�
				{
					treeNode.setCls("folder");
					treeNode.setLeaf(false);
					treeNode.setExpandable(true);
				} else // �ӽڵ�
				{
					treeNode.setHref("admin/menuTreeNodeRedirect?id="
							+ sysDept.getId());
					// treeNode.setHrefTarget("rightFrame");
					treeNode.setCls("file");
					treeNode.setLeaf(true);
					treeNode.setExpandable(false);
				}
				treeNodeArray.add(treeNode);
			}
		}

		return treeNodeArray;

	}

	public String getTreeJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {
		ArrayList<TreeNode> treeNodeArray = null;
		List<SysDeparts> childList = securityDaoService
				.getDepartChildByParentId(parentId);
		List<String> parentIdList = securityDaoService.getDepartParentId();
		treeNodeArray = new ArrayList<TreeNode>();
		for (SysDeparts sysDepart : childList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(sysDepart.getId());
			treeNode.setText(sysDepart.getDeptName());
			treeNode.setDescription(sysDepart.getDeptDesc());
			if (parentIdList.contains(sysDepart.getId())) {
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

	// 取得所有Id
	public String getIdtring() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<String> allIdList = systemDaoService.getDeptId();
		String allId = "";
		for (int i = 0; i < allIdList.size(); i++) {
			allId = allId + allIdList.get(i);
		}
		return allId;

	}

	public String getPID() {
		return parentId;
	}

	public void setPID(String pid) {
		parentId = pid;
	}

	public ArrayList<DeptTreeNode> getDeptFullJSONString()
			throws JsonGenerationException, JsonMappingException, IOException {

		ArrayList<DeptTreeNode> treeNodeArray = null;

		List<SysDept> childList = systemDaoService
				.getDeptChildByParentId(parentId);

		List<String> parentIdList = systemDaoService.getDeptParentId();

		treeNodeArray = new ArrayList<DeptTreeNode>();
		systemDaoService.setAuthMenuMap();
		for (SysDept sysDept : childList) {

			DeptTreeNode treeNode = new DeptTreeNode();
			treeNode.setId(sysDept.getId());
			treeNode.setText(sysDept.getDeptDis());
			treeNode.setDescription(sysDept.getDeptDesc());
			treeNode.setDeptDesc(sysDept.getDeptDesc());
			treeNode.setDeptId(sysDept.getId());
			treeNode.setDeptName(sysDept.getDeptName());
			treeNode.setDeptstandalone(sysDept.getDeptstandalone());
			treeNode.setDeptsort(sysDept.getDeptsort());
			if (parentIdList.contains(sysDept.getId())) // ���ڵ�
			{
				treeNode.setCls("folder");
				treeNode.setLeaf(false);
				treeNode.setExpandable(true);
			} else // �ӽڵ�
			{
				// treeNode.setHref("admin/menuTreeNodeRedirect?id="
				// + sysDept.getId());
				treeNode.setCls("file");
				treeNode.setLeaf(true);
				treeNode.setExpandable(false);
			}
			treeNodeArray.add(treeNode);

		}

		return treeNodeArray;

	}

	public String getHyTreeJSONString() throws JsonGenerationException,
			JsonMappingException, IOException {
		ArrayList<TreeNode> treeNodeArray = null;
		List<SysDeparts> childList = securityDaoService
				.getDepartChildByParentId(parentId);
		List<String> parentIdList = securityDaoService.getDepartParentId();
		treeNodeArray = new ArrayList<TreeNode>();
		for (SysDeparts sysDepart : childList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(sysDepart.getId());
			treeNode.setText(sysDepart.getDeptName());
			treeNode.setDescription(sysDepart.getDeptDesc());
			if (parentIdList.contains(sysDepart.getId())) {
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

}
