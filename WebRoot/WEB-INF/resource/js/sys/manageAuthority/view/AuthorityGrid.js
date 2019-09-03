Ext.define('ManageAuthority.view.AuthorityGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.authoritygrid',
	store : 'AuthorityGrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择
	border : 0,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'AuthorityGrid',
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : {
		items : [ {
			text : '权限名称',
			dataIndex : 'authorityName',
			flex : 3
		}, {
			text : '权限描述',
			dataIndex : 'authorityDesc',
			flex : 1
		}, {
			text : '资源名称',
			dataIndex : 'resourceId',
			flex : 1
		}, {
			text : '非移动平台',
			dataIndex : 'issys',
			flex : 1
		}, {
			text : '资源URI',
			dataIndex : 'resourceString',
			flex : 3
		} ]
	}
})