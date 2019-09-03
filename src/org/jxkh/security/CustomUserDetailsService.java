
package org.jxkh.security;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.jxkh.security.model.SysUsers;
import org.jxkh.security.model.SysUsersRoles;
import org.jxkh.security.service.SecurityDaoService;


/**
 *该类的主要作用是为Spring Security提供一个经过用户认证后的UserDetails。
 *该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
//	private static final Log log = LogFactory.getLog(SysUsersDao.class);
	@Autowired
	private SecurityDaoService securityDaoService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
				
		//得到用户的权限
		auths = loadUserAuthoritiesByName( username );
		
		//根据用户名取得一个SysUsers对象，以获取该用户的其他信息。
		SysUsers user = securityDaoService.findByUserAccount( username );
			//最后一个参数是用来放用户权限的
		return new SysUsers( user.getUserId(), user.getUserAccount(), user.getUserName(),
				 user.getUserPassword(),user.getUserDesc(), "Y", user.getUserMobile(),"N",
				 user.getUserDuty(), user.getUserDept(), user.getSubSystem(), 
				 new HashSet<SysUsersRoles>(0), true, true, true, auths);
	}
	public List<GrantedAuthority> loadUserAuthoritiesByName(String username) {

		try {

			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

			List<String> query1 = securityDaoService.loadUserAuthorities(username);
//将权限全部加入到GrantedAuthority集合中
			for (String roleName : query1) {
				GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
				auths.add(authority);
			}

			return auths;

		} catch (RuntimeException re) {
			//log.error("find by authorities by username failed.", re);
			throw re;
		}
	}		
	
}
