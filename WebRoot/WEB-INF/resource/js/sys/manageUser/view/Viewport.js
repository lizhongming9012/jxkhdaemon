Ext.define('ManageUser.view.Viewport', {
			extend : 'Ext.container.Viewport',
			layout : {
				type : 'fit',
				align : 'stretch'
			},
			border : 0,
			requires : ['ManageUser.view.DepartTree',
					'ManageUser.view.UserGrid', 'ManageUser.view.GridEdit',
					'ManageUser.view.AuthGridEdit','ManageUser.view.UserOrderGrid','ManageUser.view.TreePicker','ManageUser.view.ResourceComboBox'
			],
			initComponent : function() {
				this.items = {
					layout : 'border',
					items : [{
								width : 280,
								region : 'west',
								split : true,
								collapsible : true,
								animCollapse : true,
								collapsed : false,
								margins : '0 0 0 5',
								minWidth : 175,
								autoScroll : true,
								items : {
									xtype : 'departtree',
									id : 'departtree'
								}
							}, {
								region : 'center', 
								deferredRender : false,
								id : 'ipanel',
								dockedItems : [{
											xtype : 'toolbar',
											dock : 'top',
											id : 'itbar',
											items : [{
														xtype : 'button',
														text : '增加',
														action : 'add'
													}, {
														xtype : 'button',
														text : '修改',
														action : 'edit'
													}, {
														xtype : 'button',
														text : '删除',
														action : 'delete'
													}, {
														xtype : 'button',
														text : '修改权限',
														action : 'authedit'
													}, {
														xtype : 'button',
														text : '人员查找',
														action : 'findUser'
													}, {
														xtype : 'button',
														text : '人员排序',
														action : 'order'
													}, {
														xtype : 'button',
														text : '修改密码',
														action : 'changePwd'
													}]
										}],
								autoScroll : true,
								items : [{
											xtype : 'usergrid',

											id : 'usergrid'
										}]
							}]
				};

				this.callParent();
			}
		});