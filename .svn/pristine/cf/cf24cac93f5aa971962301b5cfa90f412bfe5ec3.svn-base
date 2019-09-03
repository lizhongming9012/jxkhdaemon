Ext.define('Gztzview.view.Viewport', {
	extend : 'Ext.container.Viewport',
	layout : {
		type : 'fit',
		align : 'stretch'
	},
	border : 0,
	requires : [ 'Gztzview.view.GztzviewGrid', 'Gztzview.view.GztzViewCx',
			'Gztzview.view.GztzviewqueryGrid' ],
	initComponent : function() {
		this.items = {
			layout : {
				type : 'vbox',
				align : 'stretch'
			},
			items : [ {
				xtype : 'gztzviewcx',
				id : 'viewform'
			}, {
				xtype : 'gztzviewgrid',
				flex : 1,
				id : 'ipanel',
				hidden : true
			}, {
				xtype : 'gztzviewquerygrid',
				flex : 1,
				id : 'ipanelcx'
			} ]
		};
		this.callParent();
	}
});