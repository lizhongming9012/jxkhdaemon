Ext.define('ManageResource.store.ModuleTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageResource.model.ModuleTree',
	model: 'ManageResource.model.ModuleTree',
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