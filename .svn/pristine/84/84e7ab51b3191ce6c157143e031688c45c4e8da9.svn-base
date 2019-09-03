package org.jxkh.system.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.jxkh.security.CustomUserDetails;
import org.jxkh.security.service.SecurityDaoService;
import org.jxkh.system.model.SysDept;
import org.jxkh.system.model.SysDesktop;
import org.jxkh.system.service.SystemDaoService;

@Controller
public class SysDesktopController {

	@Autowired
	public SystemDaoService systemDaoService;

	@Autowired
	public SecurityDaoService securityDaoService;


	@RequestMapping(value = "admin/deskNotice")
	public String deskNotice(ModelMap model) {
		return "/admin/deskNotice";
	}


	@RequestMapping(value = "admin/zqrl")
	public String zqrl(ModelMap model) {
		return "/admin/zqrl";
	}

	@RequestMapping(value = "admin/xzjx/gztz/gztz")
	public String gztz(ModelMap model) {
		return "/admin/xzjx/gztz/gztz";
	}

	@RequestMapping(value = "admin/analyzer")
	public String analyzer(ModelMap model) {
		return "/admin/analyzer";
	}

	@RequestMapping(value = "/admin/desktop/shortcuts")
	@ResponseBody
	public Map<String, Object> getDeskShortCuts(
			@RequestParam("userAccount") String userAccount,
			HttpServletRequest request) throws JsonProcessingException,
			IOException {
		// List<Notice> artList = null;
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		SysDesktop sysDesktop = new SysDesktop();
		// 桌面通知准备

		// 初始化值班时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String signDate = sdf.format(new Date());// 签到日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 1);
		String schedualeDate = sdf.format(cal.getTime());// 排班时间
		String deptId = user.getUserDept();
		if ("137060017".equals(deptId.substring(0, 9))) {
			deptId = "13706001700";
		}

		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		sysDesktop.setBasePath(basePath);
		Map<String, Object> imMap = new HashMap<String, Object>();
		imMap.put("success", "true");
		imMap.put("shortcuts", sysDesktop);
		return imMap;
	}

	@RequestMapping(value = "admin/desktop/noticeQuery")
	public String noticeQuery(ModelMap model) {

		return "/admin/desktop/noticeQuery";
	}

	@RequestMapping(value = "admin/desktop/todoMore")
	public String todoMore(ModelMap model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String deptId = user.getUserDept();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SysDept userDeparts = securityDaoService.getDeparts(deptId);
		model.put("userName", user.getUserName());// 用户名
		model.put("userAccount", user.getUserAccount());
		model.put("userDeptId", user.getUserDept());
		model.put("userDeptName", userDeparts.getDeptName());
		model.put("sysDate", sdf.format(new Date()));
		return "/admin/workflow/todo";
	}

	@RequestMapping(value = "admin/desktop/noticeMore")
	public String noticeMore(ModelMap model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String deptId = user.getUserDept();
		String deptStandId = deptId.substring(0, 7) + "0000";
		SysDept userStandDeparts = securityDaoService.getDeparts(deptStandId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SysDept userDeparts = securityDaoService.getDeparts(deptId);
		model.put("userName", user.getUserName());// 用户名
		model.put("userAccount", user.getUserAccount());
		model.put("userDeptId", user.getUserDept());
		model.put("userDeptName", userDeparts.getDeptName());
		model.put("sysDate", sdf.format(new Date()));
		model.put("userDeptStandId", userStandDeparts.getId());
		model.put("userDeptStandName", userStandDeparts.getDeptName());
		return "/admin/xzjx/gztz/view";
	}

}
