Ext.define('Gztzview.view.GztzView', {
	extend : 'Ext.window.Window',
	alias : 'widget.gztzview',

	requires : [ 'Ext.form.Panel' ],

	title : '通知浏览',
	layout : 'fit',
	autoScroll : true,
	width : 500,
	height : 800,
	// maximizable:true,
	// minimizable:true,

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			id : 'gztzviewForm',
			style : 'background-color: #fff;',

			items : [ {
				xtype : 'panel',
				width : 670,
				height : 400,
				autoScroll : true,
				align : 'stretch',
				id : 'gztzview',
				bodyStyle : {
					background : '#ffc',
					// background: 'url(res/images/back.jpg) center top
					// no-repeat',
					padding : '10px'
				}
			} ]
		} ];
		this.callParent(arguments);
	}
});
