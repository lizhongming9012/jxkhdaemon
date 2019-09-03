Ext.define('ManageAuthority.store.AuthorityGrid',{
    extend: 'Ext.data.Store',
	requires: 'ManageAuthority.model.AuthorityGrid',
	model: 'ManageAuthority.model.AuthorityGrid',
	autoSync : true,
    proxy : {
         type: 'ajax',
         api: {
	    	create  : 'admin/sys/manageAuthoritySave',
	    	read    : 'admin/sys/manageAuthorityQuery',
		    update  : 'admin/sys/manageAuthoritySave',
		    destroy : 'admin/sys/manageAuthorityDelete'
		},
         reader: {
             type : 'json',
             root : 'records',
    		 totalProperty   : 'totalCount',
    		 idProperty      : 'authorityId'
         },
         writer: {
             type : 'json',
    		 idProperty      : 'authorityId'
         }
     }
})
