Ext.define('ManageUser.store.RoleGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageUser.model.RoleGrid',
	model: 'ManageUser.model.RoleGrid',
	autoSync : false,
    proxy : {
         type: 'ajax',        
         api: {
	    	read    : 'admin/sys/rolesAllQuery'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount'
    		
         }

     }
})
