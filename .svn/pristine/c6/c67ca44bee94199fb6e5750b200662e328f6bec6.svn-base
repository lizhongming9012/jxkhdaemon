Ext.define('Gztz.view.Viewport', {
	extend : 'Ext.container.Viewport',
	layout : {
		type : 'fit',
		align : 'stretch'
	},
	border : 0,
	requires : [ 'Gztz.view.GztzGrid', 'Gztz.view.GztzFormCx',
			'Gztz.view.GridEdit'

	],
	initComponent : function() {
		this.items = {
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'gztzform'
			}, {
				xtype : 'gztzgrid',
				flex : 1,
				id : 'ipanel'
			} ]
		};
		this.callParent();
	}
});