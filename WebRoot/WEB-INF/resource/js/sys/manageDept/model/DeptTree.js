
Ext.define('ManageDept.model.DeptTree', {
	extend: 'Ext.data.Model',
	fields: [
	    { name : 'id', type: 'string' },
	    { name : 'deptId', type: 'string' },
	    { name : 'text', type: 'string' },
        { name : 'deptName', type: 'string' },
        { name : 'deptDesc' , type: 'string' },
        { name : 'deptstandalone', type: 'string' },
        { name : 'deptsort', type: 'string' },
        { name : 'parentId', type: 'string'},
        { name : 'deptDis', type: 'string'}
        
        
	],
	 validations: [
        {type: 'presence', name: 'deptName'},
        {type: 'length',   name: 'deptId', min: 11,max:11}
        
    ]
	}
);