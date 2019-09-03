Ext.define('ManageMenu.view.MenuEdit', {
	extend : 'Ext.window.Window',
	alias : 'widget.menuedit',
	requires : [ 'Ext.form.Panel', 'ManageMenu.view.ModuleTreePicker',
			'ManageMenu.view.AuthorityComboBox' ],
	title : '菜单维护',
	layout : 'fit',
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
				name : 'text',
				width : '80%',
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '菜单名称'
			}, {
				xtype : 'textfield',
				name : 'description',
				width : '80%',
				fieldLabel : '菜单描述'
			}, {
				xtype : 'moduletreepicker',
				name : 'moduleId',
				itemId : 'droptree',
				width : '80%',
				// allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '所属模块'
			}, {
				xtype : 'authoritycombobox',
				name : 'authorityId',
				itemId : 'auth-combobox',
				width : '80%',
				// allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '权限'
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
