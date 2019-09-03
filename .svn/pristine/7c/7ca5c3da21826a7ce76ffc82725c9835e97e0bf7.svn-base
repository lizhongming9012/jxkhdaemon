Ext.define('ManageUser.view.AuthGridEdit', {
    extend: 'Ext.window.Window',
    alias : 'widget.authgridedit',

    requires: ['Ext.form.Panel','ManageUser.view.UserGrid','ManageUser.view.RoleGrid','ManageUser.view.UserRoleGrid'],

    title : '权限维护',
    layout: 'fit',
    autoShow: true,
    width: 380,
    height:400,

    initComponent: function() {
        this.items = [
            {
                xtype: 'panel',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',
               layout: {
                     type:'hbox',
                     align: 'stretch'
                    
                    },
                items: [
                	{
                        xtype: 'rolegrid',
                        name : 'haveRole',
                        width : '40%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '全部角色'
                    },
                    {
                    width:'20%',
                    itemId:"panelId",
                    	items:[
                    		{xtype:'container',
                    	height:100
                    	},{
                    		xtype: 'hiddenfield',
                            name : 'userId',
                            itemId:'userId'
                      
                        
                    	},
                    			{
                    			xtype : 'button',
                    			action:'addRole',
                    			itemId:'addRoleId',
                    			height : 20,
                    			width : 60,
                    			text : '-->'
                    	},
                    	{xtype:'container',
                    	height:50
                    	},{
                    		   	xtype : 'button',
                    			height : 20,
                    			action:'removeRole',
                    			itemId:'removeRoleId',
                    			width : 60,
                    			text : '<--'
                    	}]
                    		   
                    },
                    {
                        xtype: 'userrolegrid',
                        name : 'allRole',
                        width : '40%',
                        allowBlank : false,
                        msgTarget : 'side',
                        fieldLabel: '用户角色'
                    }
                    
                ]
            }
        ];

        this.buttons = [
            {
                text: '保存',
                scope: this,
                action: 'saveUserRole'
            },
             
            {
                text: '关闭',
                scope: this,
                handler: this.close
            }
        ];

        this.callParent(arguments);
    }
});
