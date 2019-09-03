Ext.application({
	name : 'Gztz',
	autoCreateViewport : true,
	models : [ 'GztzGrid' ],
	appFolder : 'res/js/xzjx/gztz/edit',
	stores : [ 'GztzGrid' ],
	controllers : [ 'GztzController' ]
});