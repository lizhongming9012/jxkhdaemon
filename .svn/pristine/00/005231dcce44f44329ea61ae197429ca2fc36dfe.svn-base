Ext.define('ManageAuthority.view.GridEdit', {
	extend : 'Ext.window.Window',
	alias : 'widget.gridedit',

	requires : [ 'Ext.form.Panel', 'ManageAuthority.view.ResourceComboBox' ],

	title : '权限维护',
	layout : 'fit',
	autoShow : true,
	width : 600,

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff;',
			defaults : {
				labelWidth : 80,
				labelAlign : 'right'
			},
			items : [ {
				xtype : 'textfield',
				name : 'authorityName',
				width : '80%',
				allowBlank : false,
				msgTarget : 'side',
				regex : /^AUTH_/,
				fieldLabel : '标识'
			}, {
				xtype : 'textfield',
				name : 'authorityDesc',
				width : '80%',
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '名称'
			}, {
				xtype : 'resourcecombobox',
				name : 'resourceId',
				allowBlank : false,
				msgTarget : 'side',
				width : '80%',
				fieldLabel : '资源ID'
			}, {
				xtype : 'combobox',
				store : {
					fields : [ 'dm', 'mc' ],
					data : [ {
						"dm" : "Y",
						"mc" : "是"
					}, {
						"dm" : "N",
						"mc" : "否"
					}

					]
				},
				name : 'issys',
				queryMode : 'local',
				displayField : 'mc',
				valueField : 'dm',
				value : 'Y',
				width : '80%',
				fieldLabel : '非移动平台'
			} ]
		} ];

		this.buttons = [ {
			text : '保存',
			action : 'save'
		}, {
			text : '取消',
			scope : this,
			handler : this.close
		} ];

		this.callParent(arguments);
	}
});
