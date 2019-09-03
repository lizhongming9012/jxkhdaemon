Ext.define('ManageMenu.view.Viewport', {
	extend : 'Ext.container.Viewport',
	layout : {
		type : 'fit',
		align : 'stretch'
	},
	border : 0,
	requires : [ 'ManageMenu.view.MenuTree', 'ManageMenu.view.MenuEdit' ],
	initComponent : function() {
		this.items = {
			dockedItems : [ {
				xtype : 'toolbar',
				dock : 'top',
				id : 'itbar',
				items : [ {
					xtype : 'button',
					text : '增加',
					action : 'add'
				}, {
					xtype : 'button',
					text : '修改',
					action : 'edit'
				}, {
					xtype : 'button',
					text : '删除',
					action : 'delete'
				} ]
			} ],
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				width : 250,
				xtype : 'menutree',
				id : 'menu-tree'
			} ]
		};

		this.callParent();
	}
});