function convertNull(v, record) {
	
	if (v =='null') {
		return '';
	}
}
Ext.define('ManageUser.model.UserGrid', {
	extend: 'Ext.data.Model',
	idProperty:'id',
	fields: [
		{ name : 'id'	  },
	 	{ name : 'userId'	  },
	    { name : 'userAccount'	  },
        { name : 'userName'   },
        { name : 'userPassword'   },
        { name : 'userDesc' ,convert:convertNull  },
        { name : 'userDept' },
        { name : 'userMobile' },
        { name : 'userDesktop' },
        { name : 'userOrder' },
        { name : 'userSex' },
        { name : 'userType' },
        { name : 'isValid' }
        
	 ]
  }
);