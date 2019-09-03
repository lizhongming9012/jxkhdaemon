Ext.define('Sys.store.SysTree', {
	extend : 'Ext.data.TreeStore',
	requires : 'Sys.model.TreeNode',
	model : 'Sys.model.TreeNode',
	proxy : {
		type : 'ajax',
		async : false,
		url : 'admin/desktop/startTree',
		extraParams : {
			id : '00000000000'
		},
		reader : {
			type : 'json'
		}
	},
	root : {
		text : '绩效管理系统',
		id : '00000000000',
		expanded : true
	},
	autoLoad : true
});