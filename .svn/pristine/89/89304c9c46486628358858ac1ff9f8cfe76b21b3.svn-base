Ext.define('ManageUser.store.UserGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageUser.model.UserGrid',
	model: 'ManageUser.model.UserGrid',
	autoSync : true,
    proxy : {
         type: 'ajax',
         //url : 'admin/sys/manageUserQuery',
         api: {
	    	create  : 'admin/sys/manageUserSave',
	    	read    : 'admin/sys/manageUserQuery',
		    update  : 'admin/sys/manageUserSave',
		    destroy : 'admin/sys/manageUserDelete'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount'
    		
         }
//         writer: {
//             type : 'json',
//    		 idProperty      : 'UserId'
//         }
     }
})
