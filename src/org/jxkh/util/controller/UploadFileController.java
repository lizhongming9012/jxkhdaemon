package org.jxkh.util.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.jxkh.security.CustomUserDetails;
import org.jxkh.util.ConfigInfo;


@Controller
public class UploadFileController {
	@Autowired
	private ConfigInfo configInfo;

	@RequestMapping(value = "/admin/util/uploadFile")
	public String uploadFile(ModelMap model) {
		return "/admin/util/uploadFile";
	}

	@RequestMapping(value = "/admin/util/fileManagerJson")
	public String fileManagerJson(ModelMap model) {
		return "/admin/util/fileManagerJson";
	}
	
	@RequestMapping(value = "/util/fileManagerJson")
	public String wttbfileManagerJson(ModelMap model) {
		return "/admin/util/fileManagerJson";
	}
	//
	@RequestMapping(value = "/util/chatfileManagerJson")
	public String chatfileManagerJson(ModelMap model) {
		return "/admin/util/chatfileManagerJson";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/portal/common/admin/util/uploadJson")
	public String uploadJson1(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "imgFile") MultipartFile... files)
			throws FileUploadException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ configInfo.getRealPhyPathForImage();

		// 文件保存目录URL
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		String saveUrl = baseUrlPath + configInfo.getHtmlUrlForImage();

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,mht,pdf,pptx");

		// 最大文件大小
		long maxSize = 50000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				return obj.toJSONString();
			}
		}

		return "/admin/util/uploadJson";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/util/uploadJson")
	public String uploadJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "imgFile") MultipartFile... files)
			throws FileUploadException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ configInfo.getRealPhyPathForImage();

		// 文件保存目录URL
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		String saveUrl = baseUrlPath + configInfo.getHtmlUrlForImage();

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,mht,pdf,pptx");

		// 最大文件大小
		long maxSize = 50000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				return obj.toJSONString();
			}
		}
		return "/admin/util/uploadJson";
	}

	/**
	 * 手机图片上传
	 * 
	 * @throws FileUploadException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/mobile/ywgl/util/uploadJson")
	public void uploadImgFile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "attachment") MultipartFile... files) {
		JSONObject obj = new JSONObject();
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/portal/image/ywgl";
		File syxxDirFile = new File(savePath);
		if (!syxxDirFile.exists()) {
			syxxDirFile.mkdirs();
		}
		long maxSize = 100000000;// 100M大小
		response.setContentType("text/html; charset=UTF-8");
		File uploadDir = new File(savePath);
		try {
			// 文件不存在
			if (!ServletFileUpload.isMultipartContent(request)) {
				response.setContentType("text/html");
				response.getWriter().write(getError("请选择文件。"));
			} else
			// 检查目录

			if (!uploadDir.isDirectory()) {
				response.setContentType("text/html");
				response.getWriter().write(getError("上传目录不存在。"));
			} else
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				response.setContentType("text/html");
				response.getWriter().write(getError("上传目录没有写权限。"));
			} else {
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}

				// System.out.println(savePath + "/" + fileName);

				for (MultipartFile f : files) {
					String originName = f.getOriginalFilename();
					String fileOName = URLEncoder.encode(originName, "utf-8");
					long fileSize = f.getSize();
					if (!f.isEmpty()) {
						// 检查文件大小
						if (fileSize > maxSize) {
							response.setContentType("text/html");
							response.getWriter().write(getError("上传文件大小超过限制。"));
						} else {
							// 文件保存已原文件名保存

							File uploadedFile = new File(savePath, fileOName);
							f.transferTo(uploadedFile);
							obj.put("success", true);
							obj.put("attchName", fileOName);
							obj.put("displayAttachName",
									f.getOriginalFilename());

							response.setContentType("text/html");
							response.getWriter().write(obj.toJSONString());

						}
					}
				}
			}
		} catch (Exception e) {
			response.setContentType("text/html");
			try {
				response.getWriter().write(getError(e.getMessage()));
			} catch (IOException ie) {
				// TODO Auto-generated catch block
				ie.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/util/uploadJson")
	public String wttbupload(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "imgFile") MultipartFile... files)
			throws FileUploadException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ configInfo.getRealPhyPathForImage();

		// 文件保存目录URL
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		String saveUrl = baseUrlPath + configInfo.getHtmlUrlForImage();

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,mht,pdf,pptx");

		// 最大文件大小
		long maxSize = 50000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				return obj.toJSONString();
			}
		}

		return "/admin/util/uploadJson";
	}
	//聊天消息图片上传
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/util/chatuploadJson")
	public String chatupload(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "imgFile") MultipartFile... files)
			throws FileUploadException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/portal/chat/";

		// 文件保存目录URL
		String baseUrlPath = request.getScheme() + "://"
				+ request.getServerName() + request.getContextPath();
		if (request.getServerPort() != 80) {
			baseUrlPath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + request.getContextPath();
		}
		String saveUrl = baseUrlPath +"/res/repository/portal/chat/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,mht,pdf,pptx");

		// 最大文件大小
		long maxSize = 50000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				return obj.toJSONString();
			}
		}

		return "/admin/util/uploadJson";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/util/uploadTopicPicJson")
	public String uploadTopicPicJson(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "tPic") MultipartFile... files)
			throws FileUploadException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ configInfo.getRealPhyPathForImage();

		// 文件保存目录URL
		String saveUrl = configInfo.getHtmlUrlForImage();

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");

		if (!ServletFileUpload.isMultipartContent(request)) {
			return getError("请选择文件。");
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			return getError("上传目录不存在。");
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			return getError("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			return getError("目录名不正确。");
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		for (MultipartFile f : files) {
			String fileName = f.getOriginalFilename();
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					return getError("上传文件大小超过限制。");
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					return getError("上传文件扩展名是不允许的扩展名。\n只允许"
							+ extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					return getError("上传文件失败。");
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				return obj.toJSONString();
			}
		}

		return "/admin/util/uploadJson";
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/util/uploadAttachmentFile")
	public void uploadAttachmentFile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "attachment") MultipartFile... files)
			throws FileUploadException, IOException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/mail/";

		// 最大文件大小
		long maxSize = 100000000;//100M大小
		response.setContentType("text/html; charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.setContentType("text/html");
			response.getWriter().write(getError("请选择文件。"));
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			response.setContentType("text/html");
			response.getWriter().write(getError("上传目录不存在。"));
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			response.setContentType("text/html");
			response.getWriter().write(getError("上传目录没有写权限。"));
		}
		// 获取用户id,用id为名称创建目录
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String dirName = user.getUserAccount().toLowerCase();

		// 创建文件夹
		savePath += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		
		for (MultipartFile f : files) {
			 String originName=f.getOriginalFilename();
			 String suffix=originName.lastIndexOf(".")>0?originName.substring(originName.lastIndexOf(".")):"";
			 int sep =originName.lastIndexOf(".")>0?originName.lastIndexOf("."):originName.length();
			 String preffix =originName.substring(0,sep);
			 preffix=preffix.length()>24?preffix.substring(0, 24):preffix;
			 String dealName =originName.lastIndexOf(".")>0?preffix + ".."+ new Random().nextInt(1000) +suffix:preffix + "_"+ new Random().nextInt(1000);
			 dealName = dealName.replaceAll(" ", "%20");
			String fileName = URLEncoder.encode(dealName,"utf-8");
			fileName =fileName.replaceAll("%20", " ");
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					response.setContentType("text/html");
					response.getWriter().write(getError("上传文件大小超过限制。"));
				}

				// 文件保存已原文件名保存
				try {
					File uploadedFile = new File(savePath, fileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					response.setContentType("text/html");
					response.getWriter().write(getError("上传文件失败。"));
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("success", true);
				obj.put("attchName", fileName);
				obj.put("displayAttachName", f.getOriginalFilename());
				response.setContentType("text/html");
				response.getWriter().write(obj.toJSONString());
			}
		}

		
	}
/**
 * 邮件附件下载
 * @param request
 * @param response
 * @param model
 * @param filName
 * @param ext
 * @param account
 * @return
 * @throws UnsupportedEncodingException
 */
	@RequestMapping(value = "/admin/util/downloadAttachmentFile/{fileName}.{fileExt}")
	@ResponseBody
	public FileSystemResource downloadAttachmentFile(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @PathVariable("fileName") String filName,
			@PathVariable("fileExt") String ext,String account)
			throws UnsupportedEncodingException {
	    filName =filName.replaceAll(" ", "%20"); 
	    filName = URLEncoder.encode(filName,"utf-8");
	    filName = filName.replaceAll("%20", " ");
	    String extStr="." + ext;
		String realFile = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/mail/"
				+ account
				+ "/"
				+ filName+extStr;

		return new FileSystemResource(new File(realFile));
	}
	@RequestMapping(value = "/admin/util/downloadNoExtAttachmentFile/{fileName}")
	@ResponseBody
	public FileSystemResource downloadNoExtAttachmentFile(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @PathVariable("fileName") String filName,String account)
					throws UnsupportedEncodingException {
		filName =filName.replaceAll(" ", "%20"); 
		filName = URLEncoder.encode(filName,"utf-8");
		filName = filName.replaceAll("%20", " ");
		String realFile = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/mail/"
				+ account
				+ "/"
				+ filName;
		
		return new FileSystemResource(new File(realFile));
	}
	
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
  /**
   * 通用文件上传 存放目录：common，子目录为用户账号名
   * @param request
   * @param response
   * @param model
   * @param files
   * @throws FileUploadException
   * @throws IOException
   */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/admin/util/commonUploadFile")
	public void commonUploadFile(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value = "attachment") MultipartFile... files)
			throws FileUploadException, IOException {
		// 文件保存目录路径
		String savePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/WEB-INF/resource/repository/common/";
		// 最大文件大小
		long maxSize = 100000000;//100M大小
		response.setContentType("text/html; charset=UTF-8");
		if (!ServletFileUpload.isMultipartContent(request)) {
			response.setContentType("text/html");
			response.getWriter().write(getError("请选择文件。"));
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			response.setContentType("text/html");
			response.getWriter().write(getError("上传目录不存在。"));
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			response.setContentType("text/html");
			response.getWriter().write(getError("上传目录没有写权限。"));
		}
		/*// 获取用户id,用id为名称创建目录
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String dirName = user.getUserAccount().toLowerCase();
*/
		// 以当前年月为 名称创建目录
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMM");
        String dirName = sdf2.format(new Date());
		// 创建文件夹
		savePath += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String preffix = sdf.format(new Date());
       
		for (MultipartFile f : files) {
			 String suffix=f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf("."));
			 suffix=f.getOriginalFilename().length()>15?f.getOriginalFilename().substring(0, 15)+suffix:f.getOriginalFilename();
			String fileName = URLEncoder.encode(preffix+suffix,
					"UTF-8");//
			long fileSize = f.getSize();
			if (!f.isEmpty()) {
				// 检查文件大小
				if (fileSize > maxSize) {
					response.setContentType("text/html");
					response.getWriter().write(getError("上传文件大小超过限制。"));
				}

				// 文件保存已原文件名保存
				try {
					File uploadedFile = new File(savePath, fileName);
					f.transferTo(uploadedFile);
				} catch (Exception e) {
					e.printStackTrace();
					response.setContentType("text/html");
					response.getWriter().write(getError("上传文件失败。"));
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("success", true);
				obj.put("attchName", dirName + "/"+fileName);
				obj.put("displayAttachName", f.getOriginalFilename());
				response.setContentType("text/html");
				response.getWriter().write(obj.toJSONString());
			}
		}

		
	}
	/**
	 * 公用下载文件相应方法
	 * @param request
	 * @param response
	 * @param model
	 * @param filName //格式  账号/编码后的文件名：
	 *    例如：admin/2015-05-27-15-01-242E4%B9%A6%EF%BC%88%E7%A8%BD.doc
	 * @return
	 * @throws UnsupportedEncodingException
	 */
		@RequestMapping(value = "/admin/util/commonDownloadFile/{lastdic}/{fileName}.{fileExt}")
		@ResponseBody
		public FileSystemResource commonDownloadFile(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@PathVariable("fileName") String filName,@PathVariable("lastdic") String lastdic,
				@PathVariable("fileExt") String ext)
				throws UnsupportedEncodingException {
			// 获取用户id,用id为名称创建目录
			String realFile = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/WEB-INF/resource/repository/common/"
					+  lastdic+"/"+ URLEncoder.encode(filName, "UTF-8")+ "." + ext;

			return new FileSystemResource(new File(realFile));
		}

		/**
		 * 外网照片上传：common，子目录为用户账号名
		 * @param request
		 * @param response
		 * @param model
		 * @param files
		 * @throws FileUploadException
		 * @throws IOException
		 * 2015.8.3
		 */
		@RequestMapping(value = "local/imageUpload")
		public void localImageUpload(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,String lastDir,String fileName,
				@RequestParam(value = "imageFile") MultipartFile... files)
						throws FileUploadException, IOException {
			System.out.println("upload image arrive.......");
			String savePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/WEB-INF/resource/repository/common/"+lastDir;
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			
			for (MultipartFile f : files) {
				//2、检查文件大小	
				if (!f.isEmpty()) {
					// 文件保存已原文件名保存
					try {
						File uploadedFile = new File(savePath, fileName);
						f.transferTo(uploadedFile);
					
						//将文件更新到ptfpfxfk_xiecha 表中
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		@SuppressWarnings("unchecked")
		@ResponseBody
		@RequestMapping(value = "/admin/util/uploadZtFile")
		public void uploadZtFile(HttpServletRequest request,
				HttpServletResponse response, ModelMap model,
				@RequestParam(value = "htmlUrl") MultipartFile... files)
				throws FileUploadException, IOException {
			// 文件保存目录路径
			String savePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/WEB-INF/resource/repository/zt/";

			// 最大文件大小
			long maxSize = 100000000;//100M大小
			response.setContentType("text/html; charset=UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				response.setContentType("text/html");
				response.getWriter().write(getError("请选择文件。"));
			}
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				response.setContentType("text/html");
				response.getWriter().write(getError("上传目录不存在。"));
			}
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				response.setContentType("text/html");
				response.getWriter().write(getError("上传目录没有写权限。"));
			}
			// 获取用户id,用id为名称创建目录
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			String dirName = user.getUserAccount().toLowerCase();

			// 创建文件夹
			savePath += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			
			for (MultipartFile f : files) {
				 String originName=f.getOriginalFilename();				 
				 originName = originName.replaceAll(" ", "%20");
				String fileName = URLEncoder.encode(originName,"utf-8");
				fileName =fileName.replaceAll("%20", " ");
				long fileSize = f.getSize();
				if (!f.isEmpty()) {
					// 检查文件大小
					if (fileSize > maxSize) {
						response.setContentType("text/html");
						response.getWriter().write(getError("上传文件大小超过限制。"));
					}

					// 文件保存已原文件名保存
					try {
						File uploadedFile = new File(savePath, fileName);
						f.transferTo(uploadedFile);
					} catch (Exception e) {
						response.setContentType("text/html");
						response.getWriter().write(getError("上传文件失败。"));
					}

					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("success", true);
					obj.put("attchName", fileName);
					obj.put("displayAttachName", f.getOriginalFilename());
					response.setContentType("text/html");
					response.getWriter().write(obj.toJSONString());
				}
			}

			
		}
		
		/**
		 * 公用下载文件相应方法
		 * @param request
		 * @param response
		 * @param model
		 * @param filName //格式  账号/编码后的文件名：
		 *    例如：admin/2015-05-27-15-01-242E4%B9%A6%EF%BC%88%E7%A8%BD.doc
		 * @return
		 * @throws UnsupportedEncodingException
		 */
			@RequestMapping(value = "/util/ztDownloadFile/{lastdic}/{fileName}.{fileExt}")
			@ResponseBody
			public FileSystemResource ztDownloadFile(HttpServletRequest request,
					HttpServletResponse response, ModelMap model,
					@PathVariable("fileName") String filName,@PathVariable("lastdic") String lastdic,
					@PathVariable("fileExt") String ext)
					throws UnsupportedEncodingException {
				
				filName =filName.replaceAll(" ", "%20"); 
				filName = URLEncoder.encode(filName,"utf-8");
				filName = filName.replaceAll("%20", " ");
				
				// 获取用户id,用id为名称创建目录
				String realFile = request.getSession().getServletContext()
						.getRealPath("/")
						+ "/WEB-INF/resource/repository/zt/"
						+  lastdic+"/"+ filName+ "." + ext;

				return new FileSystemResource(new File(realFile));
			}
}
