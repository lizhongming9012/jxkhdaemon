/**
 * 上传文件通用组件，目前只上传一个文件，数据表只保存文件存储的最后一级目录和生成的文件名，上传的文件的原始名不保存。 文件可以重复上传，上传覆盖上次的文件
 * 引用时格式为：xtype：'uploadfile',name:''
 * ,如果想通过id来获取值，则通过getCmp('fileName')来获取该组件，并且fileName是写死的
 * 上传文件保存的目录/WEB-INF/resource/repository/common/+动态生成的年月/截取15位文件名
 * 例如：/WEB-INF/resource/repository/common/201505/2015-05-29-14-19-23%E7%B3%BB%E7%BB%9F%E5%BD%93%E5%89%8D%E5%AD%98%E5%9C%A8%E9%97%AE%E9%A2%982015042.doc
 */
Ext.define('Jxgl.uploadfile.UploadFile', {
	extend : 'Ext.form.Panel',
	xtype : 'uploadfile',
	name : '',// 用于调用时赋值
	fieldLabel : '文件',
	items : [{
		xtype : 'form',
		layout : 'hbox',
		border : false,
		items : [{
					xtype : 'filefield',
					name : 'attachment',
					width : '80%',
					msgTarget : 'side',
					// fieldLabel : '文件',
					fieldLabel : '',
					labelAlign : 'right',
					border : false,
					buttonText : '浏览',
					listeners:{
					beforerender:function(me,epts){
						me.fieldLabel=this.up('uploadfile').fieldLabel;
					}
					}
				}, {
					xtype : 'button',
					text : '上传',
					id : 'upload',
					name : 'fileButton',
					handler : function(button) {
						button.up('form').up('uploadfile').onUploadFile(button);
					}
				}]

	}, {
		xtype : 'textfield',
		name : '',// 用户引用该组件时，动态指定名称（和model的属性向对应）
		id : 'fileName',
		width : '100%',
		readOnly : true,
		hidden : true
	}, {
		xtype : 'textfield',
		name : 'displayAttachName',
		id : 'displayAttachName',
		width : '100%',
		readOnly : true
			/*
			 * border : false, fieldStyle : { borderStyle : 'none' }
			 */
		}],
	onUploadFile : function(oButton) {
		// 动态指定名称（和model的属性向对应）和该form组件名称相同，
		Ext.getCmp('fileName').name = this.name;
		var form = oButton.up('form').getForm();
		var uniForm = oButton.up('form').up('uploadfile');
		var me = this;
		if (form.isValid()) {
			form.submit({
						url : 'admin/util/commonUploadFile',
						waitMsg : '上传中...',
						success : function(fp, o) {
							Ext.Msg.alert("上传成功!");
							uniForm.down('textfield[name=' + me.name + ']')
									.setValue(o.result.attchName);
							uniForm.down('textfield[name=displayAttachName]')
									.setValue(o.result.displayAttachName);
						},
						failure : function(fp, o) {
							Ext.Msg.alert(o.result.message);
						}
					});
		}
	}
});
