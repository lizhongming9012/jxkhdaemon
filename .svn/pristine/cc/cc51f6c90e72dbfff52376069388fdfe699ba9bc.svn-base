package org.jxkh.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class ModelMappingJs {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String classFullName = "org.jxkh.jxgl.model.JxglFpsend";
		String className = classFullName.substring(classFullName
				.lastIndexOf('.') + 1);
		String tempStr = classFullName.substring(8, // 截取第8位，即org.jxkh.到model之间的package名称
				classFullName.indexOf("model"));
		String reqPathPrefix = "";
		String[] strs = tempStr.split("\\.");
		for (String str : strs) {
			reqPathPrefix += "/" + str + "/" + className.toLowerCase() + "/";
		}
		try {
			// 加载model类
			Class<?> modelClass = Class.forName(classFullName);
			// 获取model的属性
			Field[] fields = modelClass.getDeclaredFields();
			String[] props = new String[fields.length];// model属性字符串
			int i = 0;
			for (Field field : fields) {

				props[i] = field.getName();
				i++;
			}
			// 创建映射目录
			File directory = initDirectory();
			// 生成Javascript文件
			genJavascriptFile(className, reqPathPrefix, props, directory);
			genJavaFile(className, reqPathPrefix, props);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void genJavaFile(String className, String reqPathPrefix,
			String[] props) throws IOException {
		// 生成js 主文件串（首先生成内容字符串，然后将字符串写到文件中）
		File directory = new File("d:/mapping/java");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		String curdStr = genCurdStr(className, reqPathPrefix);

		// 将字符串写到主控(js)
		String fullFileName = directory.getParent() + "/java/" + className
				+ ".java";
		writerToFile(fullFileName, curdStr);
	}

	private static String genCurdStr(String className, String reqPathPrefix) {
		StringBuffer content = new StringBuffer();
		String className2 = className.substring(0, 1).toLowerCase()
				+ className.substring(1);
		// 增加
		content.append("//增加一条记录                                                     \r\n");
		content.append("@RequestMapping(value = \"admin" + reqPathPrefix
				+ "add" + className + "\")                     \r\n");
		content.append("	@ResponseBody                                                         \r\n");
		content.append("	public Map<String, Object> Save" + className
				+ "(@RequestBody " + className + "  " + className2 + ")   \r\n");
		content.append("			throws Exception {                                                \r\n");
		content.append("		Map<String, Object> resultMap = new HashMap<String, Object>();      \r\n");
		content.append("		try {                                                               \r\n");
		content.append("			" + className2 + "Service.add" + className + "("
				+ className2 + ");                            \r\n");
		content.append("			resultMap.put(\"success\", true);                                   \r\n");
		content.append("			resultMap.put(\"records\", " + className2
				+ ");//添加后返回带ID的对象给前台   \r\n");
		content.append("		} catch (Exception e) {                                             \r\n");
		content.append("			e.printStackTrace();                                              \r\n");
		content.append("			resultMap.put(\"success\", false);                                  \r\n");
		content.append("		}                                                                   \r\n");
		content.append("		return resultMap;                                                   \r\n");
		content.append("	}                                                                     \r\n");
		// 删除 -一条
		content.append("//删除一条记录                                                      \r\n");
		content.append("	@RequestMapping(value = \"admin" + reqPathPrefix
				+ "del" + className + "\")                   \r\n");
		content.append("	@ResponseBody                                                           \r\n");
		content.append("	public Map<String, Object> del" + className
				+ "(@RequestBody " + className + "  " + className2 + ")   \r\n");
		content.append("			throws Exception {                                                  \r\n");
		content.append("		Map<String, Object> resultMap = new HashMap<String, Object>();        \r\n");
		content.append("		try {                                                                 \r\n");
		content.append("			" + className2 + "Service.del" + className + "("
				+ className2 + ".getId());                 \r\n");
		content.append("				resultMap.put(\"success\", true);                                   \r\n");
		content.append("		} catch (Exception e) {                                               \r\n");
		content.append("			e.printStackTrace();                                                \r\n");
		content.append("			resultMap.put(\"success\", false);                                    \r\n");
		content.append("                                                                          \r\n");
		content.append("		}                                                                     \r\n");
		content.append("		return resultMap;                                                     \r\n");
		content.append("                                                                          \r\n");
		content.append("	}                                                                       \r\n");
		// 删除 -多条
		content.append("//删除多条记录                                                   \r\n");
		content.append("	@RequestMapping(value =\"admin" + reqPathPrefix
				+ "del" + className + "List\")           \r\n");
		content.append("	@ResponseBody                                                       \r\n");
		content.append("	public Map<String, Object> del" + className
				+ "List(                          \r\n");
		content.append("			@RequestBody List<" + className + "> " + className2
				+ "List) throws Exception {  \r\n");
		content.append("		Map<String, Object> resultMap = new HashMap<String, Object>();    \r\n");
		content.append("		try {                                                             \r\n");
		content.append("			for (" + className + " " + className2 + ":"
				+ className2 + "List) {                      \r\n");
		content.append("                                                                      \r\n");
		content.append("				" + className2 + "Service.del" + className + "("
				+ className2 + ".getId());           \r\n");
		content.append("				}                                                             \r\n");
		content.append("			resultMap.put(\"success\", true);                                 \r\n");
		content.append("		} catch (Exception e) {                                           \r\n");
		content.append("			e.printStackTrace();                                            \r\n");
		content.append("			resultMap.put(\"success\", false);                                \r\n");
		content.append("                                                                      \r\n");
		content.append("		}                                                                 \r\n");
		content.append("		return resultMap;                                                 \r\n");
		content.append("	}                                                                   \r\n");
		// 修改
		content.append("//修改一条记录                                                      \r\n");
		content.append("@RequestMapping(value = \"admin" + reqPathPrefix
				+ "update" + className + "\")                      \r\n");
		content.append("	@ResponseBody                                                            \r\n");
		content.append("	public Map<String, Object> update" + className
				+ "(@RequestBody " + className + "  " + className2 + ")   \r\n");
		content.append("			throws Exception {                                                   \r\n");
		content.append("		Map<String, Object> resultMap = new HashMap<String, Object>();         \r\n");
		content.append("		try {                                                                  \r\n");
		content.append("		" + className2 + "Service.update" + className + "("
				+ className2 + ");                              \r\n");
		content.append("			resultMap.put(\"success\", true);                                      \r\n");
		content.append("		} catch (Exception e) {                                                \r\n");
		content.append("			e.printStackTrace();                                                 \r\n");
		content.append("			resultMap.put(\"success\", false);                                     \r\n");
		content.append("                                                                           \r\n");
		content.append("		}                                                                      \r\n");
		content.append("		return resultMap;                                                      \r\n");
		content.append("                                                                           \r\n");
		content.append("	}                                                                        \r\n");
		// 查询
		content.append("//查询                                                    \r\n");
		content.append("//	通知明细查询                                            \r\n");
		content.append("	@RequestMapping(value = \"admin" + reqPathPrefix
				+ "query" + className + "\")                          \r\n");
		content.append("	@ResponseBody                                                                 \r\n");
		content.append("	public Map<String, Object> query" + className
				+ "(@RequestBody " + className + "  " + className2
				+ " )                      \r\n");
		content.append("			throws Exception {                                                        \r\n");
		content.append("		Map<String, Object> resultMap = new HashMap<String, Object>();              \r\n");
		content.append("		List<" + className + "> resultList = new ArrayList<"
				+ className + ">();                      \r\n");
		content.append("		try {                                                                       \r\n");
		content.append("			int count=" + className2 + "Service.get" + className
				+ "Count(" + className2 + ");                  \r\n");
		content.append("			resultMap.put(\"totalCount\",count);                                        \r\n");
		content.append("			resultList =" + className2 + "Service.get"
				+ className + "ByPage(" + className2 + ");               \r\n");
		content.append("			resultMap.put(\"records\", resultList);                                     \r\n");
		content.append("                                                                                \r\n");
		content.append("		} catch (Exception e) {                                                     \r\n");
		content.append("			e.printStackTrace();                                                      \r\n");
		content.append("		}                                                                           \r\n");
		content.append("                                                                                \r\n");
		content.append("		return resultMap;                                                           \r\n");
		content.append("	}                                                                             \r\n");
		return content.toString();
	}

	public static void genJavascriptFile(String className,
			String reqPathPrefix, String[] props, File directory)
			throws IOException {
		// 生成js 主文件串（首先生成内容字符串，然后将字符串写到文件中）
		String mainStr = genMainStr(className, reqPathPrefix);
		// 生成js model串（首先生成内容字符串，然后将字符串写到文件中）
		String modelStr = genModelStr(className, props);
		// 生成 js store串
		String storeStr = genStoreStr(className, reqPathPrefix);
		// 生成 js view-gride串
		String gridViewStr = genGridViewStr(className, props);
		String gridEditStr = genGridEditStr(className, props);
		String cxFormStr = genCxFormStr(className, props);
		String viewPortStr = genViewPortStr(className, props);
		// 生成 js controller串
		String controllerStr = genControllerStr(className, reqPathPrefix);
		// 将字符串写到主控(js)
		String fullFileName = directory.getParent() + "/" + className + ".js";
		writerToFile(fullFileName, mainStr);
		// 将字符串写到文本文件model(js)
		fullFileName = directory.getParent() + "/model/" + className + ".js";
		writerToFile(fullFileName, modelStr);

		// 将字符串写到文本文件store(js)
		fullFileName = directory.getParent() + "/store/" + className + ".js";
		writerToFile(fullFileName, storeStr);
		// 将字符串写到文本文件gridview(js)
		fullFileName = directory.getParent() + "/view/" + className + "Grid.js";
		writerToFile(fullFileName, gridViewStr);
		fullFileName = directory.getParent() + "/view/GridEdit.js";
		writerToFile(fullFileName, gridEditStr);
		fullFileName = directory.getParent() + "/view/Viewport.js";
		writerToFile(fullFileName, viewPortStr);
		fullFileName = directory.getParent() + "/view/CxForm.js";
		writerToFile(fullFileName, cxFormStr);
		// 将字符串写到文本文件controller(js)
		fullFileName = directory.getParent() + "/controller/" + className
				+ "Controller.js";
		writerToFile(fullFileName, controllerStr);
	}

	private static String genControllerStr(String className,
			String reqPathPrefix) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className + ".controller." + className
				+ "Controller', {                                  \r\n");
		content.append("	extend : 'Ext.app.Controller',                                                \r\n");
		content.append("                                                                                \r\n");
		content.append("	refs : [{                                                                     \r\n");
		content.append("				ref : '"
				+ className
				+ "Grid',                                                       \r\n");
		content.append("				selector : '"
				+ className.toLowerCase()
				+ "grid'                                                   \r\n");
		content.append("			}, {                                                                      \r\n");
		content.append("				ref : 'gridEdit',                                                       \r\n");
		content.append("				selector : 'gridedit'                                                   \r\n");
		content.append("			}],                                                                       \r\n");
		content.append("                                                                                \r\n");
		content.append("	stores : ['" + className
				+ "'],                                        \r\n");
		content.append("	catId:0,                                                                      \r\n");
		content.append("	init : function() {                                                           \r\n");
		content.append("		var istore = this.get" + className
				+ "Store();                                       \r\n");
		content.append("		istore.on('write', function() {// store写回后台成功时，回调函数             \r\n");
		content.append("					Ext.Msg.alert('保存成功！')                                           \r\n");
		content.append("				});                                                                     \r\n");
		content.append("		this.control({                                                              \r\n");
		content.append("					'viewport panel toolbar button[action=add]' : {                       \r\n");
		content.append("						click : this.addGrid                                                \r\n");
		content.append("					},                                                                    \r\n");
		content.append("					'viewport panel toolbar button[action=update]' : {                    \r\n");
		content.append("						click : this.updateGrid                                             \r\n");
		content.append("					},\r\n");
		content.append("					'viewport panel toolbar button[action=delete]' : {                    \r\n");
		content.append("						click : this.deleteGrid                                             \r\n");
		content.append("					},                                                                    \r\n");
		content.append("					'gridedit button[action=save]' : {                                    \r\n");
		content.append("						click : this.saveGrid                                               \r\n");
		content.append("					},                                                                    \r\n");
		content.append("					'gridedit button[action=close]' : {                                   \r\n");
		content.append("						click : this.reloadGrid                                             \r\n");
		content.append("					},                                                                    \r\n");
		content.append("					'#queryId' : { // 响应查询按钮                                        \r\n");
		content.append("						click : this.queryGztz                                              \r\n");
		content.append("					},                                                                    \r\n");
		content.append("					'#resetId' : { // 响应重置按钮                                        \r\n");
		content.append("						click : this.resetGztz                                              \r\n");
		content.append("					}                                                                     \r\n");
		content.append("				})                                                                      \r\n");
		content.append("	},                                                                            \r\n");
		content.append("	queryGztz : function() {                                                      \r\n");
		content.append("		values = Ext.getCmp('cxform').getValues();                              \r\n");
		content.append("		var iproxy = this.get" + className
				+ "Store().getProxy();                            \r\n");
		content.append("		iproxy.extraParams = values;                                                \r\n");
		content.append("		this.get"
				+ className
				+ "Store().load();                                             \r\n");
		content.append("	},                                                                            \r\n");
		content.append("	resetGztz : function() {                                                      \r\n");
		content.append("		Ext.getCmp('cxform').getForm().reset();                                 \r\n");
		content.append("	},                                                                            \r\n");
		content.append("	addGrid : function() {                                                        \r\n");
		content.append("		  var pal = Ext.create('" + className
				+ ".view.GridEdit');                               \r\n");
		content.append("		  pal.width = document.body.clientWidth / 2 * 1.2;                          \r\n");
		content.append("		  pal.height = document.body.clientHeight / 2 * 1.2;                        \r\n");
		content.append("		  pal.show();                                                               \r\n");
		content.append("		                                                                            \r\n");
		content.append("	},                                                                            \r\n");
		content.append("	updateGrid : function() {                                                     \r\n");
		content.append("		var iGrid = this.get" + className
				+ "Grid();                                             \r\n");
		content.append("		var irecord = iGrid.getSelectionModel().getSelection()[0];                  \r\n");
		content.append("		if ((irecord)) {                                                            \r\n");
		content.append("			            this.addGrid();	                                              \r\n");
		content.append("			            Ext.getCmp('"
				+ className.substring(0, 1).toLowerCase()
				+ className.substring(1)
				+ "Form').loadRecord(irecord);                   \r\n");
		content.append("				     }                                                                  \r\n");
		content.append("	},                                                                            \r\n");
		content.append("	deleteGrid : function() {                                                     \r\n");
		content.append("		var grid = this.get" + className
				+ "Grid();                                              \r\n");
		content.append("		var gridStore = this.get" + className
				+ "Store();                                    \r\n");
		content.append("		var records = grid.getSelectionModel().getSelection();                      \r\n");
		content.append("		if (records.length > 1) {                                                   \r\n");
		content.append("			gridStore.getProxy().api.destroy = 'admin"
				+ reqPathPrefix + "del" + className + "List';      \r\n");
		content.append("		} else {                                                                    \r\n");
		content.append("			gridStore.getProxy().api.destroy = 'admin"
				+ reqPathPrefix + "del" + className + "';          \r\n");
		content.append("		}                                                                           \r\n");
		content.append("		Ext.Array.each(records, function(record, index, countriesItSelf) {          \r\n");
		content.append("					gridStore.remove(record);                                             \r\n");
		content.append("				});                                                                     \r\n");
		content.append("		gridStore.sync();                                                           \r\n");
		content.append("	},                                                                            \r\n");
		content.append("                                                                                \r\n");
		content.append("	saveGrid : function(button) {                                                 \r\n");
		content.append("		var win = button.up('window'), form = win.down('form'), record = form       \r\n");
		content.append("				.getRecord(), values = form.getValues();                                \r\n");
		content.append("		if (record) {// 修改                                                        \r\n");
		content.append("			record.set(values);                                                       \r\n");
		content.append("		var store=	this.get" + className
				+ "Store();	                                  \r\n");
		content.append("			win.close();                                                              \r\n");
		content.append("		} else {                                                                    \r\n");
		content.append("	var store=	this.get"
				+ className
				+ "Store();store.insert(0, values);                                \r\n");
		content.append("			store.sync();                                           \r\n");
		content.append("		}                                                                           \r\n");
		content.append("		win.close();                                                                \r\n");
		content.append("	},                                                                            \r\n");
		content.append("	reloadGrid :function(button){                                                 \r\n");
		content.append("		var store = this.get" + className
				+ "Store();                                        \r\n");
		content.append("		var mProxy = store.getProxy();                                              \r\n");
		content.append("		//清空store                                                                 \r\n");
		content.append("		store.loadData([],false);                                                   \r\n");
		content.append("		mProxy.setExtraParam('id', this.catId);                                     \r\n");
		content.append("		store.load();                                                               \r\n");
		content.append("		button.up('window').close();                                                \r\n");
		content.append("	}                                                                             \r\n");
		content.append("                                                                                \r\n");
		content.append("});                                                                             \r\n");

		return content.toString();
	}

	private static String genCxFormStr(String className, String[] props) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className
				+ ".view.CxForm', {      \r\n");
		content.append("			extend : 'Ext.form.Panel',          \r\n");
		content.append("			alias : 'widget.cxform',          \r\n");
		content.append("			requires : ['Ext.form.Panel'],      \r\n");
		content.append("			padding : '5 5 0 5',                \r\n");
		content.append("			border : false,                     \r\n");
		content.append("			id : 'cxform',                  \r\n");
		content.append("			title : '查询条件',                 \r\n");
		content.append("			style : 'background-color: #fff;',  \r\n");
		content.append("			defaults : {                        \r\n");
		content.append("				labelAlign : 'right'              \r\n");
		content.append("			},                                  \r\n");
		content.append("			items : [{                          \r\n");
		content.append("						layout : 'hbox',              \r\n");
		content.append("						defaults : {                  \r\n");
		content.append("							labelWidth : 80,            \r\n");
		content.append("							labelAlign : 'right'        \r\n");
		content.append("						},                            \r\n");
		content.append("						items : [{                    \r\n");
		content.append("									xtype : 'textfield',    \r\n");
		content.append("									name : 'xxbt',          \r\n");
		content.append("									id : 'xxbt',            \r\n");
		content.append("									msgTarget : 'side',     \r\n");
		content.append("									fieldLabel : '标题',    \r\n");
		content.append("									enableKeyEvents : true, \r\n");
		content.append("									activeError : ''        \r\n");
		content.append("								}, {                      \r\n");
		content.append("									xtype : 'textfield',    \r\n");
		content.append("									name : 'content',       \r\n");
		content.append("									id : 'content',         \r\n");
		content.append("									width : 300,            \r\n");
		content.append("									msgTarget : 'side',     \r\n");
		content.append("									fieldLabel : '内容'     \r\n");
		content.append("								}, {                      \r\n");
		content.append("									xtype : 'button',       \r\n");
		content.append("									id : 'queryId',         \r\n");
		content.append("									text : '查询',          \r\n");
		content.append("									width : 60              \r\n");
		content.append("                                          \r\n");
		content.append("								}, {                      \r\n");
		content.append("									xtype : 'button',       \r\n");
		content.append("									id : 'resetId',         \r\n");
		content.append("									text : '重置',          \r\n");
		content.append("									width : 60              \r\n");
		content.append("								}]                        \r\n");
		content.append("			           }]                       \r\n");
		content.append("		});                                   \r\n");
		return content.toString();
	}

	private static String genViewPortStr(String className, String[] props) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className + ".view.Viewport', {   \r\n");
		content.append("    extend: 'Ext.container.Viewport',\r\n");
		content.append("    layout: {                        \r\n");
		content.append("               type: 'fit',          \r\n");
		content.append("               align: 'stretch'      \r\n");
		content.append("             },                     \r\n ");
		content.append("    border : 0,                     \r\n ");
		content.append("    requires: [                     \r\n ");
		content.append("        '" + className + ".view." + className
				+ "Grid',        \r\n");
		content.append("        '" + className + ".view.CxForm',      \r\n");
		content.append("        '" + className + ".view.GridEdit'         \r\n");
		content.append("                                     \r\n");
		content.append("    ],                              \r\n ");
		content.append("    initComponent : function() {     \r\n");
		content.append("				this.items = {              \r\n ");
		content.append("					layout : {                 \r\n");
		content.append("						type : 'vbox',           \r\n");
		content.append("						align : 'stretch'        \r\n");
		content.append("					},                         \r\n");
		content.append("					items : [{                 \r\n");
		content.append("								xtype : 'cxform'   \r\n");
		content.append("							}, {                   \r\n");
		content.append("								xtype : '" + className.toLowerCase()
				+ "grid',  \r\n");
		content.append("								flex : 1,            \r\n");
		content.append("								id : 'ipanel'        \r\n");
		content.append("							}]                     \r\n");
		content.append("				};                           \r\n");
		content.append("				this.callParent();           \r\n");
		content.append("			}                              \r\n");
		content.append("		});                              \r\n");
		return content.toString();
	}

	private static void writerToFile(String fullFileName, String content)
			throws IOException {
		FileWriter writerToMain = new FileWriter(fullFileName);
		writerToMain.write(content);
		writerToMain.close();
	}

	private static String genGridEditStr(String className, String[] props) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className
				+ ".view.GridEdit', {              \r\n");
		content.append("			extend : 'Ext.window.Window',             \r\n");
		content.append("			alias : 'widget.gridedit',                \r\n");
		content.append("                                                \r\n");
		content.append("			requires : ['Ext.form.Panel'],            \r\n");
		content.append("                                                \r\n");
		content.append("			title : 'title',                       \r\n");
		content.append("			layout : 'fit',                           \r\n");
		content.append("			maximizable : true,                       \r\n");
		content.append("			minimizable : true,                       \r\n");
		content.append("                                                \r\n");
		content.append("			initComponent : function() {              \r\n");
		content.append("				this.items = [{                         \r\n");
		content.append("							xtype : 'form',                   \r\n");
		content.append("							id : '"
				+ className.substring(0, 1).toLowerCase()
				+ className.substring(1) + "Form',             \r\n");
		content.append("							padding : '5 5 0 5',              \r\n");
		content.append("							border : false,                   \r\n");
		content.append("							style : 'background-color: #fff;',\r\n");
		content.append("                            defaultType: 'textfield',                    \r\n");
		content.append("							items : [                        \r\n");

		for (String prop : props) {
			if ("id".equals(prop) || "limit".equals(prop)
					|| "page".equals(prop)) {
				continue;
			}
			content.append("{ 	name :'" + prop + "',\r\n id : '" + prop
					+ "',\r\n fieldLabel : '" + prop + "',\r\n");
			content.append(" 		allowBlank : false, \r\n msgTarget : 'side' },\r\n");
		}
		content.deleteCharAt(content.length() - 3);// 删除倒数第三个字符，也就是最后一个逗号

		content.append("									]                            \r\n");
		content.append("						}];                                 \r\n");
		content.append("                                                \r\n");
		content.append("				this.buttons = [{                       \r\n");
		content.append("							text : '保存',                    \r\n");
		content.append("							action : 'save'                   \r\n");
		content.append("						},                                  \r\n");
		content.append("                                                \r\n");
		content.append("						{                                   \r\n");
		content.append("							text : '取消',                    \r\n");
		content.append("							scope : this,                     \r\n");
		content.append("							handler : this.close              \r\n");
		content.append("						}];                                 \r\n");
		content.append("                                                \r\n");
		content.append("				this.callParent(arguments);             \r\n");
		content.append("			}                                         \r\n");
		content.append("		});                                         \r\n");
		return content.toString();
	}

	private static String genGridViewStr(String className, String[] props) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className + ".view." + className
				+ "Grid', {\r\n");
		content.append("extend : 'Ext.grid.Panel',\r\n");
		content.append("alias : 'widget." + className.toLowerCase()
				+ "grid',\r\n");
		content.append("store : '" + className + "',\r\n");
		content.append("	viewConfig : {\r\n enableTextSelection : true\r\n},\r\n border : 0,\r\n  iconCls : 'icon-grid',\r\n");
		content.append("	margin : '0 0 20 0',\r\n title : '数据列表',\r\nframe : true,\r\n");
		content.append("	selModel : Ext.create('Ext.selection.CheckboxModel', {\r\n");
		content.append("	listeners : { \r\n");
		content.append("	selectionchange : function(sm, selections) {\r\n");
		content.append("	Ext.getCmp('removeButton').setDisabled(selections.length === 0);\r\n");
		content.append("Ext.getCmp('updateButton').setDisabled(selections.length !== 1);\r\n");
		content.append("}\r\n}\r\n }),\r\n");
		content.append("dockedItems : [{                    \r\n");
		content.append("				xtype : 'pagingtoolbar',    \r\n");
		content.append("				store : '" + className + "',        \r\n ");
		content.append("				dock : 'bottom',           \r\n ");
		content.append("				displayInfo : true          \r\n");
		content.append("			}, {                          \r\n");
		content.append("				xtype : 'toolbar',          \r\n");
		content.append("				items : [{                  \r\n");
		content.append("							id : 'addButton',    \r\n ");
		content.append("							text : '录入',          \r\n");
		content.append("							tooltip : '录入',      \r\n ");
		content.append("							action : 'add',       \r\n");
		content.append("							iconCls : 'add'       \r\n");
		content.append("						}, '-', {               \r\n");
		content.append("							id : 'removeButton',  \r\n");
		content.append("							text : '删除',          \r\n");
		content.append("							tooltip : '删除',       \r\n");
		content.append("							action : 'delete',    \r\n");
		content.append("							iconCls : 'remove',   \r\n");
		content.append("							disabled : true       \r\n");
		content.append("						}, '-', {               \r\n");
		content.append("							id : 'updateButton',  \r\n");
		content.append("							text : '修改',          \r\n");
		content.append("							tooltip : '修改',       \r\n");
		content.append("							action : 'update',    \r\n");
		content.append("							iconCls : 'update',   \r\n");
		content.append("							disabled : true       \r\n");
		content.append("						}]                      \r\n");
		content.append("			}],                           \r\n");
		content.append("	columns : {                       \r\n");
		content.append("		items : [  {xtype: 'rownumberer',text:'序号'},                  ");
		for (String prop : props) {
			if ("id".equals(prop) || "limit".equals(prop)
					|| "page".equals(prop)) {
				continue;
			}
			content.append("{ 	text :'" + prop + "',\r\n dataIndex : '" + prop
					+ "'},\r\n");
		}

		content.deleteCharAt(content.length() - 3);// 删除倒数第三个字符，也就是最后一个逗号
		content.append("	]},                                \r\n");
		content.append("	columnLines : true                \r\n");
		content.append("})                                  ");

		return content.toString();
	}

	private static String genMainStr(String className, String reqPathPrefix) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.application({ \r\n");
		content.append("name :'" + className + "',\r\n");
		content.append("	autoCreateViewport : true,\r\n");
		content.append("	models : ['" + className + "'],\r\n");
		content.append("	appFolder : 'res/js"
				+ reqPathPrefix.substring(0, reqPathPrefix.lastIndexOf("/"))
				+ "',\r\n");
		content.append("	stores : ['" + className + "'],\r\n");
		content.append("	controllers : ['" + className + "Controller']\r\n");
		content.append("});");
		return content.toString();
	}

	private static File initDirectory() {
		File directory = new File("d:/mapping/js/model");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		directory = new File("d:/mapping/js/store");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		directory = new File("d:/mapping/js/view");
		if (!directory.exists()) {
			directory.mkdirs();
		}
		directory = new File("d:/mapping/js/controller");
		if (!directory.exists()) {
			directory.mkdirs();
		}

		return directory;
	}

	private static String genStoreStr(String className, String reqPathPrefix) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className + ".store." + className
				+ "', {\r\n");
		content.append("extend : 'Ext.data.Store',\r\n");
		content.append("requires : '" + className + ".model." + className
				+ "',\r\n");// 第三行
		content.append("model : '" + className + ".model." + className
				+ "',\r\n");// 第四行
		content.append("autoSync : true,\r\n");// 第五行
		content.append("proxy : {\r\n");
		content.append("type : 'ajax',\r\n");
		content.append("api : {\r\n");
		content.append("create : ' admin" + reqPathPrefix + "add" + className
				+ "',\r\n");
		content.append("destroy : 'admin" + reqPathPrefix + "del" + className
				+ "',\r\n");
		content.append("update : 'admin" + reqPathPrefix + "update" + className
				+ "',\r\n");
		content.append("read : 'admin" + reqPathPrefix + "query" + className
				+ "'\r\n");
		content.append("},\r\n");
		content.append("reader : {\r\n type : 'json',\r\n root : 'records',\r\n");
		content.append("totalProperty : 'totalCount'\r\n");
		content.append("},\r\n");
		content.append("writer : {\r\n type : 'json',\r\n idProperty : 'id'\r\n	}\r\n}\r\n})");
		return content.toString();
	}

	public static String genModelStr(String className, String[] props) {
		StringBuffer content = new StringBuffer();
		content.append("Ext.define('" + className + ".model." + className
				+ "', {\r\n");
		content.append("extend : 'Ext.data.Model',\r\n");
		content.append("fields : [\r\n");
		for (String prop : props) {
			content.append("{ name:'" + prop + "'},\r\n");
		}
		content.deleteCharAt(content.length() - 3);// 删除倒数第三个字符，也就是最后一个逗号
		content.append("]\r\n");
		content.append("});");
		return content.toString();
	}

}
