Ext.define('Gztzview.view.GztzviewGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.gztzviewgrid',
	store : 'GztzviewGrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择
	border : 0,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'GztzviewGrid',
		dock : 'bottom',
		displayInfo : true
	}, {
		xtype : 'toolbar',
		items : [ {
			id : 'queryButton',
			text : '查询',
			tooltip : '查询',
			action : 'querygztz',
			iconCls : 'query'
		} ]
	} ],
	columns : {
		defaults : {
			resizable : false
		},
		items : [ {
			text : '标题',
			dataIndex : 'xxbt',
			flex : 3
		}, {
			text : '发布部门',
			dataIndex : 'fbrDeptMc',
			flex : 3
		}, {
			text : '发布时间',
			dataIndex : 'fbsj',
			flex : 1
		} ]
	}
})