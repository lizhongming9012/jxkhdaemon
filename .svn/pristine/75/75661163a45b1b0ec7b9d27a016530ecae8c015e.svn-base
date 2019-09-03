Ext.define('ManageUser.store.UserRoleGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageUser.model.UserRoleGrid',
	model: 'ManageUser.model.UserRoleGrid',
	autoSync : false,
    proxy : {
         type: 'ajax',
         //url : 'admin/sys/manageUserQuery',
         api: {
	    	read    : 'admin/sys/manageUserRoleQueryByUserId',
	    	create:'admin/sys/addUserRole',
	    	destroy:'admin/sys/removeUserRole'
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
