Ext.define('ManageDept.store.DeptTree',{
    extend: 'Ext.data.TreeStore',
	requires: 'ManageDept.model.DeptTree',
	model: 'ManageDept.model.DeptTree',
	autoSync : true,
    proxy: {
         type: 'ajax',
         api :{
         	create  : 'admin/dept/insertNode',
	    	read    : 'admin/dept/deptTree',
		    update  : 'admin/dept/updateNode',
		    destroy : 'admin/dept/deleteNode'
         },
         extraParams :{id:authorityDeptId}, 
         reader: {
             type: 'json',
             root: 'dept'
         }
     },
    root: {
        text: authorityDeptName,
        id:  authorityDeptId ,
        expanded: false
    }
})