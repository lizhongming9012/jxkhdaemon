Ext.application({
	name : 'ManageMenu',
	autoCreateViewport : true,
	models : [ 'MenuTree', 'ModuleTreePicker' ],
	appFolder : 'res/js/sys/manageMenu',
	stores : [ 'MenuTree', 'ModuleTreePicker' ],
	controllers : [ 'MenuController' ]
});