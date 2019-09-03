Ext.define('Jxgl.jxglcombobox.TcComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.tccombobox',
	fieldLabel : '请选择...',
	displayField : 'xs',
	valueField : 'xs',
	store : Ext.create('Ext.data.Store', {
		proxy : {
			type : 'ajax',
			startParam : 'startIndex',
			limitParam : 'maxResults',
			// 通过下拉框提示提成比例
			url : 'admin/jxgl/cs/queryTc',
			reader : {
				type : 'json',
				root : 'records'
			},
			extraParams : {
				limit : 100
			}
		},
		fields : [ {
			name : 'xs'
		} ]
	}),
	queryMode : 'remote',// 表示远程模式加载数据，要想远程过滤数据必须设置为remote
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
})
