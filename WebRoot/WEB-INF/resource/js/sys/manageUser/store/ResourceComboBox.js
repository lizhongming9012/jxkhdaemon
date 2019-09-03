Ext.define('ManageUser.store.ResourceComboBox',{
      extend:'Ext.data.Store',
     fields: ['userSex', 'sexText'],
    data : [
        {"userSex":"1", "sexText":"男"},
        {"userSex":"2", "sexText":"女"}
    ]
})