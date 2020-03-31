第一部分：平台搭建：
在MyEclipse中安装插件 
	1、安装svn插件
	2、安装Extjs编辑插件spket
	3、调整applicationContext.xml、db.properties中数据库的数据源地址、密码
	
第二部分：开发步骤
一、前台开发：(js-->jsp)
1、利用Extjs 的MVC 模式，创建：
   利用“org.jxkh.util.ModelMappingJs”工具类，创建js文件。
    将String classFullName = java model类，然后运行这个java文件，在
    d:/mapping/js 生产相应的文件。将生产的文件，拷贝到myEclipse 工程文件jxgl
    WEB-INF/resource/js/jxgl的目录下，然后进行修改。
    主控文件——》view目录下的文件-》store目录下的文件-->controller目录下的文件 
    （model 目录下的文件一般不需要修改，除了更改了表结构）
  注意： \rs\js\jxgl\xslr\JxglCwhs.js中的appFolder : 
   	'res/js/jxgl/jxglxslr'的路径需做修改，对应文件路径修改\rs\js\jxgl\xslr\，否则前台会报404

2、创建jsp文件，引入Extjs的主控js文件。
  复制一个jsp文件，然后更名，引入对应的Extjs对应的主控文件，
  放在WEB-INF/admin/jxgl 对应目录下。

二、后台开发：（controller-->service-->dao-->xml）
 1、在org.jxkh.jxgl.controller 包下创建相应controller文件，
 然后将d:/mapping/java下的文件内容复制，进行修改，
 然后逐层的生产相应的方法 --》org.jxkh.jxgl.service--》org.jxkh.jxgl.dao
 --->org.jxkh.mybatis.config.mappers.jxgl (编写对应的sql语句)


三、登陆平台创建菜单

先建资源---》建权限--》建菜单， 最后重启tomcat服务器，
登陆平台就可以进行调试

第三部分：开发常见问题：
(一) Extjs
  1、前台转后台日期（spring mvc），报400错误。(从前台往后台传日期)
  解决方法 在java bean 中日期类型加注解例如：
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date skssqz;
    即可。
    后台传前台页面日期型处理：
 在js 的model的字段 配置 convert:convertToDate，
 并定义函数
 function convertToDate(v, record) {        
		var dt = new Date(v);
        return Ext.Date.format(dt, "Y-m-d");
        }
 2、combox设置默认值：通过配置监听，在afterRender事件中初始化
  例如：listeners : {
			afterRender : function(combo) {
				combo.setValue('N');// 初始值为列表最后一个
			}
		}
		
 3、实现combobox 动态模糊匹配，在定义的combobox中，添加监听方法：
	triggerAction : 'all',// 用all表示把下拉框列表框的列表值全部显示出来
	selectOnFocus : true,
	// 动态模糊匹配
	listeners : {
		beforequery : function(e) {
			var combo = e.combo;
			if (!e.forceAll) {
				var input = e.query;
				// 检索的正则
				var regExp = new RegExp(".*" + input + ".*");
				// 执行检索
				combo.store.filterBy(function(record, id) {
					// 得到每个record的项目名称值
					var text = record.get(combo.displayField);
					return regExp.test(text);
				});
				combo.expand();
				return false;
			}
		}
	}
 4、在textfield中配置正则表示式进行输入项的校验：
	regex:/^-?(?!0+(\.0*)?$)\d+(\.\d{1,3})?$/,  //非0整数或3位小数
	regexText:'不能为零'
 5、get方式传非ASCII码参数主要是中文参数乱码（原因是浏览器进行url编码时是按照某一字符集进行的），解决方法：
  		前台：传递前，首先领用函数进行编码：encodeURI('中文参数');
  		后台：接受后，对其进行解码：URLDecoder.decode('中文参数',"utf-8")
 6、修改时没有正确修改，将Ext.getCmp中的form修改为GridEdit中的id解决方法：
	updateGrid : function() {                                                     
		var iGrid = this.getJxglCwhsGrid();                                             
		var irecord = iGrid.getSelectionModel().getSelection()[0];                  
		if ((irecord)) {                                                            
			            this.addGrid();	                                              
			            Ext.getCmp('此处修改为GridEdit中的id').loadRecord(irecord);                   
				     }                                                                  
	}
7、HTTP 500错误。自定义ComboBox后，ExtJS错误提示 Cannot read property 'on' of undefined。 
	解决方案：  在Controller层添加要用的store。
	Ext.define('XXX', {
 		extend : 'Ext.app.Controller',
 		stores : [ 'JxglCwhs','CsCombo' ]
 		})
8、通过ajax，直接在自定义的按钮中发起请求，完成对应功能，
例如：{
			xtype : 'button',
			width : 80,
			text : '计算费用系数',
			tooltip : '计算',
			listeners : {
				click : function() {// 点击后发送Ajax请求，调用后台controller方法，该Ajax无需传参
					Ext.Ajax.request({
						method : 'POST',
						url : 'admin/jxgl/jxglywfy/comJxglYwfy',
						contentType : 'application/json;charset=utf-8',
						success : function() {
							Ext.MessageBox.alert("成功！","费用系数计算成功，请再次查询，查看结果!");
						},
						failure : function() {
							Ext.MessageBox.alert("错误!","请重新登录该模块后重试");
						}
					});
				}
			},
		}
9、增加导出按钮：在前台定义exportbutton，后台controller定义导出字段
10、extjs4 进行查询操作化后，页面自动跳至查询结果后第一页的方法：
（1）在XXXXGrid中，
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		id : 'pagingTool',// 为pagingtoolbar赋上一个ID
		store : 'JxglCwhs',
		dock : 'bottom',
		displayInfo : true
	}		
（2）在XXXXController中，
	queryGztz : function() {
		…
		…
		// 在点击查询按钮后的事件里进行pagingtoolbar跳转到第一页
		var pagingTool = Ext.getCmp("pagingTool");
		pagingTool.moveFirst();
	}
11、grid的选择模式："SINGLE", "SIMPLE", and "MULTI".三种，默认是"MULTI"。
例如：selModel : Ext.create('Ext.selection.CheckboxModel', {
		mode:'SINGLE',
		listeners : {
			selectionchange : function(sm, selections) {
				Ext.getCmp('removeButton').setDisabled(selections.length === 0);
				Ext.getCmp('updateButton').setDisabled(selections.length !== 1);
			}
		}
	})	
	
(二)spring：
1、spring 中使用事物，利用spring提供的事物模板来实现：
  TransactionTemplate tt=new TransactionTemplate(transactionManager);
	  tt.execute(new TransactionCallbackWithoutResult(){	
	  protected void doInTransactionWithoutResult(TransactionStatus status) {
                // 进行数据库操作的代码。。。
 })
 
(三) 数据库-mybatis
1、oracle 中的序列是用来生产主键，序列调用：
一种方法是在触发器中调用 SELECT  sequence-name.nextval INTO :NEW.ID FROM DUAL;
另一种方法直接在insert语句中调用sequence-name.nextval
建议使用后者,不采用触发器方式的插入主键
2、插入数据表一个记录时，表主键返回页面的方式，在mybatis中调用：
<selectKey keyProperty="id" resultType="String" order ="BEFORE">
    select sys_guid() from dual    </selectKey>
3、在mapper.xml中直接模糊查询：用'%${xm}%'代替#{xm}：and XM LIKE '%${xm}%'
4、直接将sql语句传入后台的方法：
创建一个SqlVo类，用了set方法传入sql语句，通过controller传入mapper.xml
<update id="comJxglYwfy" parameterType="org.jxkh.jxgl.model.SqlVo">
		${sql}    </update>
5、判断当前数据表里面有没有存在重复数据，如果没有就插入，如果有就更新或不插入
	MERGE INTO [target-table] A USING [source-table sql] B ON([conditional expression] and [...]...)  
	WHEN MATCHED THEN  [UPDATE sql]  
	WHEN NOT MATCHED THEN  [INSERT sql] 
6、在所有记录最下方增加一行合计数，使用 UNION ALL。（xml中，记录方法使用UNION ALL，返回条数，结果需+1，否则前台无合计行）
	SELECT t.xm,t.ssyf,t.tc,t.syjz,t.xyjz,t.dytc
	FROM JXGL_LRB T WHERE [ ]
	UNION ALL
	SELECT '合计' xm,null ssyf,null tc,sum(v.syjz) syjz,sum(v.xyjz) xyjz,sum(v.dytc) dytc
	FROM JXGL_LRB V WHERE [ ]
（四）Myeclipse错误
1、错误：The import XXX cannot be resolved，import类的都没问题，但就是报错。
	解决方法：选择project --> clean后，OK。如果还不行，删掉全部import，然后按alt+/提示，逐个导入    
