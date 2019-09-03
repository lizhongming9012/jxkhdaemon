Ext.define('Gztzview.controller.GztzviewController', {
	extend : 'Ext.app.Controller',

	refs : [ {
		ref : 'gztzviewGrid',
		selector : 'gztzviewgrid'
	}, {
		ref : 'gztzviewqueryGrid',
		selector : 'gztzviewquerygrid'
	}, {
		ref : 'gztzView',
		selector : 'gztzview'
	}, {
		ref : 'gztzViewCx',
		selector : 'gztzviewform'
	} ],

	stores : [ 'GztzviewGrid', 'GztzviewqueryGrid' ],
	init : function() {
		// this.getGztzviewGridStore().load();
		this.getGztzviewqueryGridStore().load();
		this.control({
			'viewport panel toolbar button[action=querygztz]' : {
				click : this.querygztz
			},
			'viewport panel toolbar button[action=querygztzcx]' : {
				click : this.querygztz
			},
			'#queryId' : { // 响应查询按钮
				click : this.queryGztz
			},
			'#resetId' : { // 响应重置按钮
				click : this.resetGztz
			}
		})
	},
	viewgztz : function() {
		var pal = Ext.create('Gztzview.view.GztzView');
		pal.width = document.body.clientWidth / 2 * 1.2;
		pal.height = document.body.clientHeight / 2 * 1.2;
		pal.show();
	},
	querygztz : function() {
		Ext.getCmp('viewform').setVisible(true);
		Ext.getCmp('ipanelcx').setVisible(true);
		Ext.getCmp('ipanel').setVisible(false);
	},
	queryGztz : function() {
		values = Ext.getCmp('viewform').getValues();
		var iproxy = this.getGztzviewqueryGridStore().getProxy();
		iproxy.extraParams = values;
		this.getGztzviewqueryGridStore().load();
	},
	resetGztz : function() {
		Ext.getCmp('viewform').getForm().reset();
	}
});
