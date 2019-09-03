package org.jxkh.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.jxkh.common.model.Base;
import org.jxkh.security.CustomUserDetails;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysDept;

public class InitQueryParams {
	//前台的查询参数进行预处理;对空值或空字符串进行预处理
	public static void setQueryParams(Base base,
			SecurityDaoService securityDaoService) {
		// 设置查询的权限机构，如果没有选择机构，则默认为根据当前用户的部门信息
		String zgswskfjDm = base.getZgswskfjDm();
		if ("undefined".equals(zgswskfjDm) || zgswskfjDm == null) {// 页面如果选择机构则返回undefined
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String deptId = user.getUserDept();
			SysDept userDeparts = securityDaoService.getDeparts(deptId);
			base.setZgswskfjDm(DepartUtil.deptStr(userDeparts));// 设置查询的权限机构，根据当前用户的部门信息
		} else {
			base.setZgswskfjDm(DepartUtil.swjgDmStr(zgswskfjDm));
		}
		// 对空值进行处理
		FillBeanProperyAsNUllForMybatis.fillNull(base);
	}
}
