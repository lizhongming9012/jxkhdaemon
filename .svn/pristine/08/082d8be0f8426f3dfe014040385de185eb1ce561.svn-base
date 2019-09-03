
Ext.define('ManageResource.view.ResourceGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.resourcegrid',
			store:'ResourceGrid',
			viewConfig : {enableTextSelection:true},//可选择
			border : 0,
			dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: 'ResourceGrid',  
		        dock: 'bottom',
		        displayInfo: true
		    }],
			columns: 
			{ defaults:{resizable:false},items:
			[
		        { text: '资源名称',  dataIndex: 'resourceName',flex : 1 },
		        { text: '资源描述', dataIndex: 'resourceDesc',flex: 3},
		        { text: '资源URI', dataIndex: 'resourceString' , flex: 2 }
		   	]
			}
		})