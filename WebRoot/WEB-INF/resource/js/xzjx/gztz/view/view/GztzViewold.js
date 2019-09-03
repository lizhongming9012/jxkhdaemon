Ext.define('Gztzview.view.GztzView', {
	extend : 'Ext.window.Window',
	alias : 'widget.gztzview',
	id : 'gztzview',
	title : '文档内容',
	closable : true,
	floating : true,
	width : '100%',
	height : '80%',
	id : 'gztzviewPage',
	layout : {
		type : 'fit',
		align : 'stretch'
	},
	html : '',
	mask : '加载中...'
});
