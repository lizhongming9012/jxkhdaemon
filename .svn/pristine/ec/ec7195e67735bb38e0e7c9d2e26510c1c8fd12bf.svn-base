/**
 * 通用导出button需要配置两个参数，一个是url，调用后台导出的url; 另一个是formid，是查询form的id,用于收集查询的条件
 * 如果没有form，则需要配置value
 */
Ext.define('Jxgl.button.ExportButton', {
			extend : 'Ext.button.Button',
			alias : 'widget.exportbutton',
			text : '导出',
			width : 60,
			formid : '',
			url : '',
			value:'',
			handler : function(me) {
				//me.el.mask('导出中...');
				var hiddenForm = Ext.create('Ext.form.Panel', {
							standardSubmit : true,
							url : me.url,
							hidden : true,
							baseParams :(me.value=='')? Ext.getCmp(me.formid).getValues():me.value
						});
				hiddenForm.getForm().submit(/*{
							success : function(form, action) {
								me.el.unmask();
							},
							 failure: function(form, action) {
							 	me.el.unmask();
							 	Ext.Msg.alert('导出失败！');
							 }
							 
						}*/);
			}
		})
