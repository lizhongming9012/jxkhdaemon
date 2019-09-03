Ext.define('Gztz.view.GztzGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.gztzgrid',
	store : 'GztzGrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择

	border : 0,
	iconCls : 'icon-grid',
	margin : '0 0 20 0',
	// title : '信息发布',
	frame : true,
	selModel : Ext.create('Ext.selection.CheckboxModel',
			{
				listeners : {
					selectionchange : function(sm, selections) {
						Ext.getCmp('removeButton').setDisabled(
								selections.length === 0);
						Ext.getCmp('updateButton').setDisabled(
								selections.length !== 1);
					}
				}
			}),
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'GztzGrid',
		dock : 'bottom',
		displayInfo : true
	}, {
		xtype : 'toolbar',
		items : [ {
			id : 'addButton',
			text : '录入',
			tooltip : '录入',
			action : 'add',
			iconCls : 'add'
		}, '-', {
			id : 'removeButton',
			text : '删除',
			tooltip : '删除',
			action : 'delete',
			iconCls : 'remove',
			disabled : true
		}, '-', {
			id : 'updateButton',
			text : '修改',
			tooltip : '修改',
			action : 'update',
			iconCls : 'update',
			disabled : true
		} ]
	} ],
	columns : {
		items : [ {
			text : '文档标题',
			dataIndex : 'xxbt',
			flex : 1
		}, {
			text : '发布人',
			dataIndex : 'fbrMc',
			width : 100
		}, {
			text : '发布时间',
			dataIndex : 'fbsj',
			width : 200
		} ]
	},
	columnLines : true
})