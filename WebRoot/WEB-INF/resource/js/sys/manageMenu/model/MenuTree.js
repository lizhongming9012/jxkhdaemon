Ext.define('ManageMenu.model.MenuTree', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'text',
		type : 'string'
	}, {
		name : 'description',
		type : 'string'
	}, {
		name : 'authorityId',
		type : 'string'
	}, {
		name : 'parentId',
		type : 'string'
	}

	]
});