Ext.define('Gztz.store.GztzGrid', {
	extend : 'Ext.data.Store',
	requires : 'Gztz.model.GztzGrid',
	model : 'Gztz.model.GztzGrid',
	autoSync : false,
	proxy : {
		type : 'ajax',
		api : {
			create : 'admin/xzjx/gztz/saveGztz',
			read : 'admin/xzjx/gztz/gztzquery',
			update : 'admin/xzjx/gztz/gztzupdate',
			destroy : 'admin/xzjx/gztz/gztzdelete'
		},
		reader : {
			type : 'json',
			root : 'records',
			totalProperty : 'totalCount'// 后台需返回，记录总数，分页用
		},
		writer : {
			type : 'json',
			idProperty : 'id'
		}
	}
})
