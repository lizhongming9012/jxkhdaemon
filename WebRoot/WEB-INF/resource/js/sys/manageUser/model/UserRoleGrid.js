
Ext.define('ManageUser.model.UserRoleGrid', {
	extend: 'Ext.data.Model',
	idProperty:'id',
	fields: [
	 	{ name : 'id'	  },
	 	{ name : 'userId'	  },
	 	{ name : 'roleId'	  },
	    { name : 'roleName'	  },
	    { name : 'roleDesc'   },
	    { name : 'enabled'	  }
        
        
        
	 ]
  }
);