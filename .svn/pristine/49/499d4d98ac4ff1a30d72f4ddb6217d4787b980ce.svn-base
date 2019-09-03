Ext.define('Gztzview.view.GztzViewCx', {
	extend : 'Ext.form.Panel',
	alias : 'widget.gztzviewcx',
	requires : [ 'Ext.form.Panel' ],
	padding : '5 5 0 5',
	border : false,
	id : 'gztzViewCx',
	title : '查询条件',
	style : 'background-color: #fff;',
	defaults : {
		labelAlign : 'right'
	},
	items : [ {
		layout : 'hbox',
		defaults : {
			labelWidth : 80,
			labelAlign : 'right'
		},
		items : [ {
			xtype : 'textfield',
			name : 'xxbt',
			id : 'xxbt',
			msgTarget : 'side',
			fieldLabel : '标题',
			enableKeyEvents : true,
			activeError : ''
		}, {
			xtype : 'textfield',
			name : 'content',
			id : 'content',
			width : 300,
			msgTarget : 'side',
			fieldLabel : '内容'
		} ]
	}, {
		layout : 'hbox',
		defaults : {
			labelWidth : 80,
			labelAlign : 'right'
		},
		items : [ {
			xtype : 'datefield',
			fieldLabel : '起日期',
			name : 'ksrq',
			id : 'ksrq',
			msgTarget : 'side',
			format : 'Y-m-d'
		// defaults to today
		}, {
			xtype : 'datefield',
			fieldLabel : '止日期',
			name : 'jzrq',
			id : 'jzrq',
			msgTarget : 'side',
			format : 'Y-m-d'
		// defaults to today
		}, {
			xtype : 'button',
			id : 'queryId',
			text : '查询',
			width : 60

		}, {
			xtype : 'button',
			id : 'resetId',
			text : '重置',
			width : 60
		} ]
	} ]
});
