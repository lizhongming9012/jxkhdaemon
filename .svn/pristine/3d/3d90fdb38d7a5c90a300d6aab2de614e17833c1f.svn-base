Ext
		.onReady(function() {

			Ext.define('Roles', {
				extend : 'Ext.data.Model',
				fields : [ {
					name : 'roleId'
				}, {
					name : 'roleName'
				}, {
					name : 'roleDesc'
				} ],
				idProperty : 'roleId'
			});
			var varStore = Ext.create('Ext.data.Store', {

				model : 'Roles',
				autoSync : true,
				proxy : {
					type : 'ajax',
					api : {
						create : 'admin/sys/manageRoleSave',
						read : 'admin/sys/manageRoleQuery',
						update : 'admin/sys/manageRoleSave',
						destroy : 'admin/sys/manageRoleDelete'
					},
					reader : {
						type : 'json',
						root : 'records',
						totalProperty : 'totalCount',
						idProperty : 'roleId'
					}
				}
			});
			var saveRole = function(button) {
				var win = button.up('window'), form = win.down('form'), record = form
						.getRecord(), values = form.getValues();
				if (form.isValid()) {
					if (record) {// 修改
						record.set(values);
					} else {// 新增
						varStore.insert(0, values);
					}
					win.close();
				}

			}
			// 定义角色编辑窗口
			Ext.define('RoleWin', {
				extend : 'Ext.window.Window',
				title : '角色维护',
				layout : 'fit',
				width : 380,
				id : '1515',
				items : [ {
					xtype : 'form',
					padding : '5 5 0 5',
					border : false,
					style : 'background-color: #fff;',

					items : [ {
						xtype : 'textfield',
						name : 'roleName',
						width : '80%',
						allowBlank : false,
						value : 'ROLE_',
						regexText : '以ROLE_起始',
						msgTarget : 'side',
						regex : /^(ROLE_)\w+/,
						fieldLabel : '角色代码'
					}, {
						xtype : 'textfield',
						name : 'roleDesc',
						width : '80%',
						fieldLabel : '角色名称'
					} ]
				} ],

				buttons : [ {
					text : '保存',
					formBind : true,
					handler : saveRole
				}, {
					text : '取消',
					handler : function() {
						this.up('window').close();
					}
				} ]
			});
			// 以下3个函数是角色管理中的增删改实现函数
			var onAddRole = function() {

				var win = new RoleWin();
				win.show();

			};
			var onUpdateRole = function() {
				var iGrid = Ext.getCmp('roleGrid');
				var irecord = iGrid.getSelectionModel().getSelection()[0];
				if (irecord) {
					var editWin = new RoleWin();
					editWin.show();
					editWin.down('form').loadRecord(irecord);
					editWin.down('form').down('textfield').setDisabled(true);
				} else {
					Ext.Msg.alert('请选择要编辑的记录!');
				}

			};
			// 查询
			var findUser = function() {
				var me = this;
				var win = Ext.create('Ext.window.Window', {
					width : 400,
					height : 200,
					layout : 'fit',
					items : [ {
						xtype : 'form',
						// url : 'admin/sys/manageUserQueryByUser',
						items : [ {
							xtype : 'textfield',
							fieldLabel : '角色代码',
							name : 'roleName'
						}, {
							xtype : 'textfield',
							fieldLabel : '角色名称',
							name : 'roleDesc'
						} ],
						buttons : [ {
							text : '查询',
							formBind : true, // only enabled once the
							// form is
							// valid
							disabled : true,
							handler : function() {
								var form = this.up('form').getForm();
								if (form.isValid()) {
									var store = varStore;
									var mProxy = store.getProxy();
									// 清空store
									store.loadData([], false);
									var values = form.getValues();
									var rolename = "%" + values.roleName + "%";
									var roledes = "%" + values.roleDesc + "%";
									mProxy.setExtraParam("roleId", '');
									mProxy.setExtraParam("roleName", rolename);
									mProxy.setExtraParam("roleDesc", roledes);
									store.load(function() {
										mProxy.extraParams = {};// 清空代理参数
										win.close();
									});
								}
							}
						}, {
							text : '重置',
							handler : function() {
								this.up('form').getForm().reset();
							}
						} ]

					} ]
				});
				win.show();

			}
			//
			var onDelRole = function() {
				var iGrid = Ext.getCmp('roleGrid');
				var irecord = iGrid.getSelectionModel().getSelection()[0];
				Ext.Msg.confirm('角色', '是否确认删除？', function(choice) {
					if (choice == 'yes') {
						if (irecord) {
							varStore.remove(irecord);
						} else {
							Ext.Msg.alert('请选择要删除的记录!');
						}
					}
				});
			};
			varStore.load();
			// 定义两个store,一个是系统全部权限，另一个是角色权限
			var allRightStore = new Ext.data.Store({
				fields : [ {
					name : 'authorityId'
				}, {
					name : 'authorityDesc'
				} ],
				autoSync : false,
				proxy : {
					type : 'ajax',
					api : {
						read : 'admin/sys/manageRoleAuthorityQuery'
					},
					reader : {
						type : 'json',
						root : 'records',
						totalProperty : 'totalCount'

					}

				}

			});
			// 应用模块的权限例如：网站栏目
			var allAppRightStore = new Ext.data.TreeStore({
				fields : [ {
					name : 'id',
					type : 'string'
				}, {
					name : 'text',
					type : 'string'
				} ],
				autoSync : false,
				proxy : {
					type : 'ajax',
					url : 'portal/moduleTree',
					extraParams : {
						id : '1'
					},
					reader : {
						type : 'json'
					}
				},
				root : {
					text : '栏目',
					id : '1',
					expanded : false
				}

			});
			var roleRightStore = new Ext.data.Store({
				fields : [ {
					name : 'id'
				}, {
					name : 'roleId'
				}, {
					name : 'authorityId'
				}, {
					name : 'authorityDesc'
				} ],
				autoSync : false,
				proxy : {
					type : 'ajax',
					api : {
						read : 'admin/sys/manageRoleAuthorityQueryByRoleId',
						create : 'admin/sys/addRoleAuthority',
						destroy : 'admin/sys/removeRoleAuthority'
					},
					reader : {
						type : 'json',
						root : 'records',
						totalProperty : 'totalCount'

					}
				},
				listeners : {
					write : function() {
						Ext.Msg.alert('修改提示', '保存成功！')
					}
				}
			})
			// 定义departtree的store显示部门
			var DepartTree = new Ext.data.TreeStore({
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
					text : '烟台XXXXX',
					id : '13706000000',
					expanded : false
				}
			});
			// 定义部门成员store
			var userStore = new Ext.data.Store({
				idProperty : 'id',
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
				}

				],
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
			// 方法
			var onRoleItemExpand = function(node, optd) {
				var store = DepartTree;
				var mProxy = store.getProxy();
				mProxy.setExtraParam("id", node.get("id"));

			};
			var onRoleBeforeItemClick = function(viewtree, record, item, index,
					e, eOpts) {

				e.stopEvent();
			};
			var onRoleClickTree = function(viewtree, record, item, index, e,
					eOpts) {
				// if (record.isLeaf()) {

				var store = userStore;
				var mProxy = store.getProxy();
				// 清空store
				store.loadData([], false);
				this.userDept = record.get("id");
				mProxy.setExtraParam("userDept", record.get("id"));
				store.load();
			};
			var distribute = function() {
				var iGrid = Ext.getCmp('roleGrid');
				var irecord = iGrid.getSelectionModel().getSelection()[0];
				var id = irecord.get("roleId");
				// Ext.Msg.alert(irecord.get("roleId"));
				var irecords = Ext.getCmp('usergrid').getSelectionModel()
						.getSelection();
				// roleRightStore.getProxy().api.create =
				// 'admin/sys/addSingleRoleAuthority';
				var records = '';
				for ( var i = 0; i < irecords.length; i++) {
					if (i != irecords.length - 1) {
						records += irecords[i].get('userId') + ",";
					} else {
						records += irecords[i].get('userId')
					}
				}

				Ext.Ajax.request({
					url : 'admin/sys/roleToUser',
					method : 'GET',
					params : {
						roleId : id,
						userId : records
					},
					headers : {
						'Content-Type' : 'application/json'
					},
					success : function(response) {
						var text = Ext.decode(response.responseText);
						if (text.success == 'true') {
							Ext.Msg.alert('提示', '操作成功');
						}
					}
				});

			}
			// Ext.Msg.alert(ss[1].get('userId'));

			// }
			// 定义方法
			var distributeRole = function() {
				var iGrid = Ext.getCmp('roleGrid');
				var irecord = iGrid.getSelectionModel().getSelection()[0];
				if (irecord) {
					// RoleToUserWin.show();

					var win = new RoleToUserWin();
					win.show();
				} else {
					Ext.Msg.alert('请选择要编辑的记录!');
				}

			}
			// 定义一个弹出窗口用于角色分配
			Ext.define('RoleToUserWin', {
				extend : 'Ext.window.Window',
				// var RoleToUserWin=new Ext.window.Window({
				id : 'RolesWin',
				title : '角色维护',
				layout : 'fit',
				width : 600,
				height : 300,
				items : {
					layout : 'border',
					items : [ {
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
							xtype : 'treepanel',
							store : DepartTree,
							// id : 'departtree',
							listeners : {
								beforeitemexpand : onRoleItemExpand,
								beforeitemclick : onRoleBeforeItemClick,
								itemclick : onRoleClickTree
							}
						}
					}, {
						region : 'center',
						deferredRender : false,
						// id : 'ipanels',
						dockedItems : [ {
							xtype : 'toolbar',
							dock : 'bottom',
							// id : 'itbars',
							items : [ {
								xtype : 'button',
								text : '分配',
								handler : distribute

							} ]
						} ],
						autoScroll : true,
						items : {
							xtype : 'grid',
							store : userStore,
							id : 'usergrid',
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
									width : 50,
									xtype : 'rownumberer'
								}, {
									text : '姓名',
									dataIndex : 'userName',
									flex : 1
								}, {
									text : '用户ID',
									dataIndex : 'userId',
									flex : 2,
									renderer : function(value) {
										if (value == 'null') {
											return '';
										}
										return value;
									}
								} ]
							}
						}
					} ]
				}
			});

			// 定义一个弹出窗口，用于角色权限分配操作
			// 添加权限
			var addAuthority = function() {

				var iSysGrid = Ext.getCmp('allAuthorityGrid');
				var iAllAppGrid = Ext.getCmp('allAppGrid');
				var iSysrecord = iSysGrid.getSelectionModel().getSelection()[0];
				var iApprecord = iAllAppGrid.getSelectionModel().getSelection()[0];
				if (iSysrecord) {
					var irecord = iSysrecord;
					var values = {
						roleId : Ext.getCmp('hiddenRoleId').getValue(),// Ext.getDom("userId").value,
						authorityId : irecord.get("authorityId"),
						authorityDesc : irecord.get("authorityDesc")
					};
					roleRightStore.insert(0, values);
					allRightStore.remove(irecord);
					// iSysGrid.getSelectionModel().select(0);

				} else if (iApprecord) {
					if (iApprecord.isLeaf()) {
						var irecord = iApprecord;
						var values = {
							roleId : Ext.getCmp('hiddenRoleId').getValue(),// Ext.getDom("userId").value,
							authorityId : irecord.get("id"),
							authorityDesc : irecord.get("text")
						};
						if (roleRightStore.query('authorityId', irecord
								.get("id")).length == 0)
							roleRightStore.insert(0, values);
					}
					// iSysGrid.getSelectionModel().select(0);
					else {
						Ext.Msg.alert('权限添加警告', '一次只能添加一个权限!');
					}

				}

				else {
					Ext.Msg.alert('请选择要添加的权限!');
				}
			};
			var removeAuthority = function() {
				var iGrid = Ext.getCmp('removeAuthorityGrid');
				var irecord = iGrid.getSelectionModel().getSelection()[0];
				if (irecord) {

					roleRightStore.remove(irecord);
					if (isNaN(irecord.get('authorityId')))
						allRightStore.insert(0, irecord);
					iGrid.getSelectionModel().select(0);

				} else {
					Ext.Msg.alert('请选择要移除的权限!');
				}
			}
			// 定义树的相应方法3个
			var onItemExpand = function(node, optd) {

				var mProxy = allAppRightStore.getProxy();
				mProxy.setExtraParam("id", node.get("id"));

			};
			var onBeforeItemClick = function(viewtree, record, item, index, e,
					eOpts) {

				e.stopEvent();
			};

			var onClickTree = function(viewtree, record, item, index, e, eOpts) {

				/*
				 * if (record.isLeaf()) { var store = allAppRightStore var
				 * mProxy = store.getProxy(); //清空store
				 * store.loadData([],false); mProxy.setExtraParam("id",
				 * record.get("id")); //后台调用leaf参数,根据leaf调用不同的方法。true
				 * 为叶子节点。只显示出本节点的记录。 mProxy.setExtraParam("leaf", "true");
				 * store.load(); } else { var store = allAppRightStore var
				 * mProxy = store.getProxy(); //清空store
				 * store.loadData([],false); mProxy.setExtraParam("id",
				 * record.get("id")); //后台调用leaf参数,根据leaf调用不同的方法。false
				 * 为非叶子节点。显示出本节点下的所有节点记录。 mProxy.setExtraParam("leaf", "false");
				 * store.load(); }
				 */
			};
			// -----------------------
			var roleRightWin = new Ext.window.Window(
					{

						title : '权限维护',
						layout : 'fit',
						width : 760,
						height : 400,
						closable : false,
						maximizable : true,
						modal : true,
						items : [ {
							xtype : 'panel',
							padding : '5 5 0 5',
							border : false,
							style : 'background-color: #fff;',
							layout : {
								type : 'hbox',
								align : 'stretch'
							},
							items : [ {
								xtype : 'panel',
								id : 'rightPanel',
								width : '40%',
								layout : {
									// layout-specific configs go here
									type : 'accordion',
									titleCollapse : false,
									animate : true,
									activeOnTop : true
								},
								items : [ {
									title : '系统权限',
									xtype : 'grid',
									store : allRightStore,
									hideHeaders : true,
									border : 0,
									columns : {
										defaults : {
											resizable : false
										},
										items : [ {
											text : '系统权限',
											dataIndex : 'authorityDesc',
											flex : 1
										}

										]
									},

									name : 'haveRole',
									id : 'allAuthorityGrid',
									width : '100%',
									allowBlank : false,
									msgTarget : 'side',
									fieldLabel : '全部权限'
								}, {
									xtype : 'treepanel',
									title : '网站权限',
									store : allAppRightStore,
									hideHeaders : true,
									border : 0,
									id : 'allAppGrid',
									listeners : {
										beforeitemexpand : onItemExpand,
										beforeitemclick : onBeforeItemClick,
										itemclick : onClickTree
									}
								} ]
							}, {
								width : '20%',
								itemId : "panelId",
								layout : 'vbox',
								items : [ {
									xtype : 'container',
									height : 100
								}, {
									xtype : 'hiddenfield',
									name : 'roleId',
									id : 'hiddenRoleId'

								}, {
									xtype : 'button',
									action : 'addRole',
									itemId : 'addRoleId',
									height : 20,
									width : 60,
									text : '-->',
									handler : addAuthority
								}, {
									xtype : 'container',
									height : 50
								}, {
									xtype : 'button',
									height : 20,
									action : 'removeRole',
									itemId : 'removeRoleId',
									width : 60,
									text : '<--',
									handler : removeAuthority
								} ]

							}, {
								xtype : 'grid',
								store : roleRightStore,
								columns : {
									defaults : {
										resizable : false
									},
									items : [ {
										text : '角色权限',
										dataIndex : 'authorityDesc',
										flex : 1
									}

									]
								},
								id : 'removeAuthorityGrid',
								name : 'allRole',
								width : '40%',
								allowBlank : false,
								msgTarget : 'side',
								fieldLabel : '角色权限'
							}

							]
						} ],
						buttons : [

								{
									text : '保存',
									scope : this,
									handler : function() {
										if (roleRightStore.getNewRecords().length == 1)
											roleRightStore.getProxy().api.create = 'admin/sys/addSingleRoleAuthority';
										else
											roleRightStore.getProxy().api.create = 'admin/sys/addRoleAuthority';
										if (roleRightStore.getRemovedRecords().length == 1)
											roleRightStore.getProxy().api.destroy = 'admin/sys/removeSingleRoleAuthority';
										else
											roleRightStore.getProxy().api.destroy = 'admin/sys/removeRoleAuthority';
										roleRightStore.sync();
									}
								}, {
									text : '关闭',
									scope : this,
									handler : function() {
										roleRightWin.hide()
									}
								} ]

					});

			// 定义角色管理的窗口
			var ipanel = Ext
					.create(
							'Ext.panel.Panel',
							{
								id : 'rolePanel',
								// height : '100%',
								width : '100%',
								autoScroll : true,
								// layout:'fit',
								region : 'center',
								defaults : {
									bodyPadding : 10
								},
								items : [ {
									bodyPadding : 0,
									xtype : 'grid',
									id : 'roleGrid',
									viewConfig : {
										enableTextSelection : true,
										// 可选择
										stripeRows : true
									},
									hideCollapseTool : true,
									columnLines : true,
									dockedItems : [ {
										xtype : 'toolbar',
										dock : 'top',
										id : 'itbar',
										items : [ {
											xtype : 'button',
											text : '增加',
											handler : onAddRole
										}, {
											xtype : 'button',
											text : '删除',
											handler : onDelRole
										}, {
											xtype : 'button',
											text : '修改',
											handler : onUpdateRole
										}, {
											xtype : 'button',
											text : '刷新',
											handler : function() {
												varStore.load();
											}
										}, {// 新增
											xtype : 'button',
											text : '查询',
											handler : findUser
										}, {
											xtype : 'button',
											text : '分配角色',
											handler : distributeRole
										} ]

									}, {
										xtype : 'pagingtoolbar',
										store : varStore,
										dock : 'bottom',
										displayInfo : true
									} ],
									store : varStore,
									columns : [
											{
												text : '序号',
												dataIndex : 'xh',
												width : 50,
												xtype : 'rownumberer'
											},
											{
												text : '角色代码',
												flex : 2,
												sortable : false,
												dataIndex : 'roleName'
											},
											{
												text : '角色名称',
												flex : 4,
												sortable : true,
												dataIndex : 'roleDesc'
											},
											{
												xtype : 'actioncolumn',
												text : '权限分配',
												flex : 1,
												items : [ {
													icon : 'res/ext-4.2.1/resources/ext-theme-access/images/grid/hmenu-lock.gif',
													tooltip : 'Edit',
													handler : function(grid,
															rowIndex, colIndex) {
														var rec = grid
																.getStore()
																.getAt(rowIndex);
														var roleIdVar = rec
																.get('roleId');
														roleRightWin.show();
														allRightStore.loadData(
																[], false);// 清空store
														allRightStore
																.getProxy()
																.setExtraParam(
																		'roleId',
																		roleIdVar);
														allRightStore.load();
														roleRightStore
																.loadData([],
																		false)// 清空store
														roleRightStore
																.getProxy()
																.setExtraParam(
																		'roleId',
																		roleIdVar);
														roleRightStore.load();
														Ext
																.getCmp(
																		'hiddenRoleId')
																.setValue(
																		roleIdVar);

													}
												} ]
											} ]
								} ],
								listeners : [ {
									destory : function() {
										roleRightWin.close();
									}
								}

								]

							});
			var viewPort = Ext.create('Ext.container.Viewport', {});
			viewPort.add(ipanel);
		});
