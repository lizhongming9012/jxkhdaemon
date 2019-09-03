Ext.define('ManageAuthority.store.ModuleTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageAuthority.model.ModuleTree',
	model: 'ManageAuthority.model.ModuleTree',
    proxy: {
         type: 'ajax',
         url: 'admin/moduleTree',
         extraParams :{id:'0000'}, 
         reader: {
             type: 'json',
             root: 'menu'
         }
     },
    root: {
        text: '模块',
        id:  '0000',
        expanded: false
    }
})