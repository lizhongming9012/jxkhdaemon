Ext.define('ManageAuthority.store.ResourceComboBox',{
      extend:'Ext.data.Store',
        proxy: {
            type: 'ajax',
            startParam: 'startIndex',
            limitParam: 'maxResults',
            url: 'admin/sys/manageResourceQuery',
            reader: {
                type: 'json',
                totalProperty: 'totalCount',
                root: 'records'
            }
        },
        fields: [{
            name: 'resourceString'
//            mapping: function(raw) {
//                var result = raw.volumeInfo.title;
//                if (raw.volumeInfo.subtitle) {
//                    result = result + ' - ' + raw.volumeInfo.subtitle;
//                }
//                return result;
//            }
        }, {
            name: 'resourceId'
        }]
})