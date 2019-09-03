Ext.define('Gztzview.view.GztzviewqueryGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.gztzviewquerygrid',
	store : 'GztzviewqueryGrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择
	border : 0,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'GztzviewqueryGrid',
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : {
		defaults : {
			resizable : false
		},
		items : [
				{
					text : '标题',
					dataIndex : 'xxbt',
					flex : 1,
					renderer : function(value, md, rd) {
						return '<a href="' + basePath
								+ 'admin/desktop/noticePage?id=' + rd.get("id")
								+ '" target=_blank>' + value + '</a>';
					}
				}, {
					text : '发布部门',
					dataIndex : 'fbrDeptMc',
					width : 200
				}, {
					text : '发布时间',
					dataIndex : 'fbsj',
					width : 150
				} ]
	}
})