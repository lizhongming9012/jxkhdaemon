//返回4个数据，即：机构名称、代码和用户ID、姓名，
//因此需要在引用的组件上，设置4个接受的id 分别是 swjgMc,swjgDm,userId,userName
//目前有关bug,即如果只选用户，不选机构的话，会出问题。页面没有响应。
//Ext.onReady(function() {
// 1.定义用户列表
//1.1 定义用户store
var userStore = Ext.create('Ext.data.Store', {

	fields : [ {
		name : 'id'
	}, {
		name : 'userId'
	}, {
		name : 'userAccount'
	}, {
		name : 'userName'
	}, {
		name : 'userPassword'
	}, {
		name : 'userDesc'
	}, {
		name : 'userDept'
	} ],
	autoSync : false,
	autoSync : true,
	proxy : {
		type : 'ajax',
		api : {
			read : 'admin/sys/manageUserQuery'
		},
		reader : {
			type : 'json',
			root : 'records',
			totalProperty : 'totalCount'

		}
	}
});
// 1.2定义用户grid
Ext.define('Jxgl.swjguser.UserGrid', {
	extend : 'Ext.grid.Panel',
	store : userStore,
	alias : 'widget.usergrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择
	selModel : {
		injectCheckbox : 0,
		mode : "SIMPLE",
		checkOnly : true
	},
	selType : "checkboxmodel",
	border : 0,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : userStore,
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : {
		defaults : {
			resizable : false
		},
		items : [ {
			text : '序号',
			dataIndex : 'xh',
			width : 30,
			xtype : 'rownumberer'
		}, {
			text : '姓名',
			dataIndex : 'userName',
			flex : 1,
			width : 100
		}, {
			text : '用户代码',
			dataIndex : 'userId',
			flex : 1,
			width : 100
		}, {
			text : '部门代码',
			dataIndex : 'userDept',
			flex : 1,
			width : 100
		} ]
	}
})
// 2、定义机构树
var departStore = Ext.create('Ext.data.TreeStore', {
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'text',
		type : 'string'
	} ],
	proxy : {
		type : 'ajax',
		url : 'admin/sys/UserDeparmentQuery',
		extraParams : {
			id : '13706000000'
		},
		reader : {
			type : 'json',
			root : 'records'
		}
	},
	root : {
		text : '烟台XXXXXX',
		id : '13706000000',
		expanded : false
	}
})
Ext.define('Jxgl.swjguser.DepartTree', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.departtree',
	store : departStore,
	border : 0,
	listeners : {
		beforeitemexpand : function(node, optd) {
			var mProxy = departStore.getProxy();
			mProxy.setExtraParam("id", node.get("id"));
		},
		beforeitemclick : function(viewtree, record, item, index, e, eOpts) {
			e.stopEvent();
		},
		itemclick : function(viewtree, record, item, index, e, eOpts) {
			var mProxy = userStore.getProxy();
			// 清空store
			userStore.loadData([], false);
			this.userDept = record.get("id");
			mProxy.setExtraParam("userDept", record.get("id"));
			userStore.load();
		}

	}
});
// 3、定义总控窗口
Ext
		.define(
				"Jxgl.swjguser.SwjgUser",
				{
					extend : "Ext.window.Window",
					alias : 'widget.swjguser',
					autoShow : true,
					width : 740,
					height : 400,
					layout : {
						type : 'fit',
						align : 'stretch'
					},
					border : 0,
					initComponent : function() {
						this.items = {
							layout : 'border',
							items : [
									{
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
											xtype : 'departtree'
										}
									},
									{
										region : 'center',
										deferredRender : false,
										dockedItems : [ {
											xtype : 'toolbar',
											dock : 'bottom',
											items : [
													{
														xtype : 'button',
														text : '添加',
														scope : this,
														handler : function(btn) {
															// 获取所有数据
															var userGrid = btn
																	.up(
																			'window')
																	.down(
																			'usergrid');
															var departTree = btn
																	.up(
																			'window')
																	.down(
																			'departtree');

															var irecord = userGrid
																	.getSelectionModel()
																	.getSelection();
															var dcord = departTree
																	.getSelectionModel()
																	.getSelection();

															Ext
																	.getCmp(
																			'swjgDmId')
																	.setValue(
																			irecord[0]
																					.get('userDept'));
															Ext
																	.getCmp(
																			'swjgMcId')
																	.setValue(
																			dcord[0]
																					.get('text'));
															Ext
																	.getCmp(
																			'userIdId')
																	.setValue(
																			irecord[0]
																					.get('userId'));
															Ext
																	.getCmp(
																			'userNameId')
																	.setValue(
																			irecord[0]
																					.get('userName'));
															this.close();
														}
													},
													{
														xtype : 'button',
														text : '取消',
														scope : this,
														handler : function(btn) {
															this.close()
														}
													} ]
										} ],
										autoScroll : true,
										items : [ {
											xtype : 'usergrid'
										} ]
									} ]
						};

						this.callParent();
					}
				})
// })
