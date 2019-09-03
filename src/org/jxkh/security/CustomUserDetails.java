/*
 * @(#) CustomUserDetails.java  2011-4-13 下午01:44:14
 *
 * Copyright 2011 by Sparta 
 */

package org.jxkh.security;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.jxkh.security.model.SysUsersRoles;

/**
 *实现了UserDetails，扩展几项信息，比如getSubSystem()方法等 ljg 13/11/13。
 */
public interface CustomUserDetails extends UserDetails {

	//用户id
	public String getUserId();

	//用户账户
	public String getUserAccount();

	//用户名
	public String getUserName();

	//用户密码
	public String getUserPassword();

	//用户描述或简介
	public String getUserDesc();

	//用户是否能用
	//public String getEnabled();

	//是否超级用户
	public String getIssys();
	
	//所属的单位
	public String getUserDept();

	//用户职位
	public String getUserDuty();

	//用户分管的子系统
	public String getSubSystem();
	
	//用户相对应的角色集
	public Set<SysUsersRoles> getSysUsersRoleses();
	
	//权限
	public String getAuth();
	
	//电话号码
	public String getUserMobile();
}
