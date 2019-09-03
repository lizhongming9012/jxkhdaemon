Ext.define('ManageUser.view.ResourceComboBox',{
        extend :'Ext.form.field.ComboBox',
        alias:'widget.resourcecombobox',
        fieldLabel: '性别',
        displayField: 'sexText',
        valueField: 'userSex',
        width: 500,
        labelWidth: 100,
        forceSelection: true,
        store: 'ResourceComboBox',
        queryParam: 'q',
        queryMode: 'local',
        forceSelection:true
})
