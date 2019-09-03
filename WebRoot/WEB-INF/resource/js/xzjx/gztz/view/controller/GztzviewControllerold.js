Ext.define('Gztzview.controller.GztzviewController', {
	extend : 'Ext.app.Controller',

	refs : [ {
		ref : 'gztzviewGrid',
		selector : 'gztzviewgrid'
	}, {
		ref : 'gztzView',
		selector : 'gztzview'
	} ],

	stores : [ 'GztzviewGrid' ],
	catId : 0,
	init : function() {
		// var iproxy = this.getGztzviewGridStore().getProxy();
		this.getGztzviewGridStore().reload();
		this.control({
			'gztzviewgrid' : {
				itemclick : this.onClick
			},
			'viewport panel toolbar button[action=querygztz]' : {
				click : this.querygztz
			}
		})
	},
	onClick : function() {

		var iGrid = this.getGztzviewGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		var id = irecord.get('id');
		var href = 'admin/xzjx/gztz/gztzviewhtml';
		var pal = Ext.create('Gztzview.view.GztzView');
		pal.width = document.body.clientWidth / 2 * 1.2;
		pal.height = document.body.clientHeight / 2 * 1.2;
		pal.html = '<iframe id="gztzviewhh"  name="gztzviewhhname" src="'
				+ href + '?id=' + id
				+ '" width=100% height=100% frameBorder="0"></iframe>';
		pal.show();
	}

});
