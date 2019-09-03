Ext.define('Gztzview.view.GztzGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.gztzgrid',
	store : 'GztzGrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择

	border : 0,
	iconCls : 'icon-grid',
	margin : '0 0 20 0',
	title : '文档列表',
	frame : true,
	columns : {
		items : [ {
			text : '文档标题',
			dataIndex : 'xxbt',
			width : 200,
			autoScroll : true,
			locked : true
		}, {
			text : '发布人',
			dataIndex : 'fbrMc',
			width : 100
		}, {
			text : '发布时间',
			dataIndex : 'fbsj',
			width : 100
		} ]
	},
	columnLines : true
})