Ext.define('ManageMenu.store.AuthorityComboBox',{
      extend:'Ext.data.Store',
        proxy: {
            type: 'ajax',
            startParam: 'startIndex',
            limitParam: 'maxResults',
            url: 'admin/sys/manageAuthorityQuery',
            reader: {
                type: 'json',
                totalProperty: 'totalCount',
                root: 'records'
            }
        },
        fields: [{
            name: 'authorityId'
        }, {
            name: 'authorityDesc'
        }]
})