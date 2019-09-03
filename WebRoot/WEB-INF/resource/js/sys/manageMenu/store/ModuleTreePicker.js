Ext.define('ManageMenu.store.ModuleTreePicker', {
    extend: 'Ext.data.TreeStore',
    model: 'ManageMenu.model.ModuleTreePicker',
    autoLoad : true,
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
        text: '所有模块',
        id:  '0000',
        expanded: true
    }

});