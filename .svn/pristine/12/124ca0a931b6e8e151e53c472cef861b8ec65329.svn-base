Ext.define('ManageResource.view.GridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.gridedit',

    requires: ['Ext.form.Panel'],

    title : '资源维护',
    layout: 'fit',
    autoShow: true,
    width: 600,

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',
                 defaults:{
                	labelWidth:80,
                	 labelAlign:'right'
                },
                items: [
                    {
                        xtype: 'textfield',
                        name : 'resourceName',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        regex :/\w+/,
                        fieldLabel: '名称'
                    },
                    {
                        xtype: 'textfield',
                        name : 'resourceDesc',
                        width : '80%',
                        fieldLabel: '描述'
                    },
                    {
                        xtype: 'textfield',
                        name : 'resourceString',
                        width : '80%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '资源URI'
                    }
                ]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                action: 'save'
            },
            {
                text: '取消',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});
