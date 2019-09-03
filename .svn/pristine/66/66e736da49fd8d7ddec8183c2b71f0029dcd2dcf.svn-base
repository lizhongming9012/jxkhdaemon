Ext.define('ManageResource.store.ResourceGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageResource.model.ResourceGrid',
	model: 'ManageResource.model.ResourceGrid',
	autoSync : true,
    proxy : {
         type: 'ajax',
         //url : 'admin/sys/manageResourceQuery',
         api: {
	    	create  : 'admin/sys/manageResourceSave',
	    	read    : 'admin/sys/manageResourceQuery',
		    update  : 'admin/sys/manageResourceSave',
		    destroy : 'admin/sys/manageResourceDelete'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount',
    		 idProperty      : 'resourceId'
         },
         writer: {
             type : 'json',
    		 idProperty      : 'resourceId'
         }
     }
})
