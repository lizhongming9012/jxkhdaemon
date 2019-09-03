/*
 * @(#) MyInvocationSecurityMetadataSourceService.java  2011-3-23 锟斤拷锟斤拷02:58:29
 *
 * Copyright 2011 by Sparta 
 */

package org.jxkh.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.jxkh.security.service.SecurityDaoService;


/**
 * 锟斤拷锟斤拷牡牡胤锟斤拷锟斤拷锟斤拷锟斤拷峁┠筹拷锟斤拷锟皆达拷锟接︼拷锟饺拷薅锟斤拷澹拷锟絞etAttributes锟斤拷锟斤拷锟斤拷锟截的斤拷锟?锟斤拷锟斤拷锟节筹拷始锟斤拷时锟斤拷应锟斤拷取锟斤拷锟斤拷锟斤拷锟斤拷源锟斤拷锟斤拷锟接︼拷锟缴拷亩锟斤拷濉?
 * 
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	@Autowired
	private SecurityDaoService securityDaoService;
	private AntPathRequestMatcher urlMatcher;
	//resourceMap是一个地址为主键，值为权限的集合对象，为了避免用户每请求一次都要去数据库读取资源，所有设置为静态的
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
//
//	public CustomInvocationSecurityMetadataSourceService() {
//		loadResourceDefine();
//	}

	@SuppressWarnings("unused")
	//将所有的权限，和地址都放到resourceMap中
	private void loadResourceDefine() {
		// 加载所有权限
		
		List<String> query = securityDaoService.getSysAuthoritiesNames();
		System.out.println("--------------"+query.size());

		/*
		 * 加载所有权限的所有url
		 * sparta
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		for (String auth : query) {
			//ca为访问权限
			ConfigAttribute ca = new SecurityConfig(auth);
			//query1得到所有权限的资源
			List<String> query1 = securityDaoService.getSysResourcesString(auth);
			for (String res : query1) {
				String url = res;//资源就是url地址
				//判断资源文件权限的对应关系，如果已经存在相关资源的url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
				if (resourceMap.containsKey(url)) {

					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);//将url地址赋予ca中的权限
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}

			}

		}

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	// 在url请求时调用，获取所访问url所对应的所有权限
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		//获取用户请求的url地址
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		//resourceMap保存了loadResourceDefine加载的所有数据
		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			//取出resourceMap中的url地址
			String resURL = ite.next();
			//判断url地址，如果请求的url地址在数据库中含有，那么将返回resourceMap中对应的权限集合，然后跳转到CustomAccessDecisionManager里的decide方法，再判断权限
			urlMatcher = new AntPathRequestMatcher(resURL);
			if (urlMatcher.matches(request)) {
				return resourceMap.get(resURL);//返回对应url地址的权限
			}
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {

		return true;
	}

}
