Ext.define('ManageAuthority.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
               type: 'fit',
               align: 'stretch'
             },
    border : 0,
    requires: [
        'ManageAuthority.view.ModuleTree',
        'ManageAuthority.view.AuthorityGrid',
        'ManageAuthority.view.GridEdit'

    ],    
    initComponent: function() {
        this.items = { 
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: 		
			 [{
                width: 250,
                xtype: 'moduletree',
                id: 'module-tree'
            }, {
            	xtype : 'splitter',
            	border: 1,
            	width : 5,
				collapsible : true,
				collapseTarget : 'prev'
            },
            	{
            	xtype: 'panel',
            	flex : 1,
            	id : 'ipanel',
            	dockedItems: [{
			        xtype: 'toolbar',
			        dock: 'top',
			        id : 'itbar',
	                items : [{	xtype : 'button',
								text : '增加',
								action: 'add'
							}, {
								xtype : 'button',
								text : '修改',
								action: 'edit'
							}, {
								xtype : 'button',
								text : '删除',
								action: 'delete'
							}]
		    	  }],
		    	layout: {
	                type: 'fit',
	                align: 'stretch'
                },
            	items:[{
                xtype: 'authoritygrid'
            	}]
             }]
        };
        
        this.callParent();
    }
});