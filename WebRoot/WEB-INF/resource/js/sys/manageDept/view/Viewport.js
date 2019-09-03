Ext.define('ManageDept.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: {
               type: 'fit',
               align: 'stretch'
             },
    border : 0,
    requires: [
        'ManageDept.view.DeptEdit',
        'ManageDept.view.DeptView',
        'ManageDept.view.DeptTree'
    ],    
    initComponent: function() {
        this.items = { 
        	       high: '80%',
             autoScroll: true,
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
							}, {
								xtype : 'button',
								text : '查看',
								action: 'view'
							}]
		    	  }],
            layout: {            	
                type: 'vbox',
                align: 'stretch'
            },
            items: 		
			 [{
                width: 250,
                xtype: 'depttree',
                id: 'dept-tree'
             }]
        };
        
        this.callParent();
    }
});