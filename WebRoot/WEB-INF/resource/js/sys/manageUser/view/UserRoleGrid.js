
Ext.define('ManageUser.view.UserRoleGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.userrolegrid',
			store:'UserRoleGrid',
			viewConfig : {enableTextSelection:true},//可选择
			border : 0,
				
		columns: 
			{ defaults:{resizable:false},items:
			[
				{text:'用户角色',dataIndex:'roleDesc',flex:1}
		       
		   	]
			}
		})