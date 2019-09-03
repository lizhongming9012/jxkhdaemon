Ext.define('ManageMenu.store.MenuTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageMenu.model.MenuTree',
	model: 'ManageMenu.model.MenuTree',
	autoSync : true,
    proxy: {
         type: 'ajax',
         api :{
         	create  : 'admin/menu/insertNode',
	    	read    : 'admin/menu/menuTree',
		    update  : 'admin/menu/updateNode',
		    destroy : 'admin/menu/deleteNode'
         },
         extraParams :{id:'00000000000'}, 
         reader: {
             type: 'json',
             root: 'menu'
         }
     },
    root: {
        text: '菜单',
        id:  '00000000000',
        expanded: true
    }
})