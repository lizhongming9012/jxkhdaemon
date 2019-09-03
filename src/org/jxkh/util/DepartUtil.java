package org.jxkh.util;

import org.jxkh.system.model.SysDept;

public class DepartUtil {
	public static String trimZero(String str) {
		String s = "";
		if ("0".equals(str.substring(str.length() - 1))) {
			s = trimZero(str.substring(0, str.length() - 1));
		} else {
			s = str;
		}
		return s;
	}
/**
 * 获取权限机构范围，根据sysDept 返回字符串 格式为：13706%;Deptstandalone:机构还是部门
 * 
 * @param userDeparts
 * @return
 */
	public static String deptStr(SysDept userDeparts ) {
		if("Y".equals(userDeparts.getDeptstandalone())){
			return deptStrBydeptId(userDeparts.getId());
		}else{
			return deptStrBydeptId(userDeparts.getParentId());
		}
	}
	/**
	 * 获取权限机构范围,用于后台SQL语句查询条件用
	 * @param deptstandalone:机构还是部门:Y为机构,N是部门
	 * @param deptId：部门id即代码
	 * @param parentDeptId：父部门id即代码的上级代码
	 * @return 返回字符串 格式为：13706%；
	 */
	public static String getAuthDeptForSql(String deptstandalone, String deptId,String parentDeptId ) {
		if("Y".equals(deptstandalone)){
			return deptStrBydeptId(deptId);
		}else{
			return deptStrBydeptId(parentDeptId);
		}
	}
	public static String deptStrBydeptId(String deptId) {
		String deptStr = "";
		String deptTrimZero = "";
		if (deptId.lastIndexOf("%") == -1) {
			if ("0000".equals(deptId.substring(7,11))) {
				if ("0000".equals(deptId.substring(7,11))) {
					deptTrimZero = deptId.substring(0,7);
				}
				if ("000000".equals(deptId.substring(5,11))){
					deptTrimZero = deptId.substring(0,5);
				}
				if ("00000000".equals(deptId.substring(3,11))) {
					deptTrimZero = deptId.substring(0,3);
				}
				deptStr = deptTrimZero + "%";
			}else {
				deptTrimZero = trimZero(deptId.substring(0));
				deptStr = deptTrimZero + "%";
			}			
		} else {
			deptStr = deptId;
		}
		return deptStr;
	}
	/**
	 * @param deptstandalone:机构还是部门:Y为机构,N是部门
	 * @param deptId:部门ID,
	 * @param parentDeptId:父部门ID
	 */
	public static String getAuthDeptId(String deptstandalone, String deptId,String parentDeptId ) {
		if("Y".equals(deptstandalone)){
			return deptId;
		}else{
			return parentDeptId;
		}
	}
	
	public static String swjgDmStr(String swjgDm) {
		String deptStr = "";
		String deptTrimZero = "";
		if ("00".equals(swjgDm.substring(9,11))) {
			deptTrimZero = swjgDm.substring(0,9);
		}
		if ("0000".equals(swjgDm.substring(7,11))) {
			deptTrimZero = swjgDm.substring(0,7);
		}
		if ("000000".equals(swjgDm.substring(5,11))){
			deptTrimZero = swjgDm.substring(0,5);
		}
		if ("00000000".equals(swjgDm.substring(3,11))) {
			deptTrimZero = swjgDm.substring(0,3);
		}
		deptStr = deptTrimZero + "%";
			
		return deptStr;
	}
}
