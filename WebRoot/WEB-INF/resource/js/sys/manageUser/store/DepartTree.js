Ext.define('ManageUser.store.DepartTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageUser.model.DepartTree',
	model: 'ManageUser.model.DepartTree',
    proxy: {
         type: 'ajax',
         url: 'admin/sys/UserDeparmentQuery',
         extraParams :{id:authorityDeptId}, 
         reader: {
             type: 'json',
             root: 'records'
         }
     },
    root: {
        text: authorityDeptName,
        id:  authorityDeptId,
        expanded: false
    }
})