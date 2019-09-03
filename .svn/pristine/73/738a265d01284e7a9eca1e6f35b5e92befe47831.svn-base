Ext.define('ManageUser.controller.UserController', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'userGrid',
				selector : 'usergrid'
			}, {
				ref : 'gridEdit',
				selector : 'gridedit'
			}, {
				ref : 'departTree',
				selector : 'departtree'
			}, {
				ref : 'authGridEdit',
				selector : 'authgridedit'
			}, {
				ref : 'roleGrid',
				selector : 'rolegrid'
			}, {
				ref : 'userRoleGrid',
				selector : 'userrolegrid'
			}, {
				ref : 'userOrderGrid',
				selector : 'userordergrid'
			}, {

				ref : 'treePicker',
				selector : 'treepicker'
			}],

	stores : ['DepartTree', 'UserGrid', 'RoleGrid', 'UserRoleGrid',
			'ResourceComboBox'],
	userDept : '',
	init : function() {
		// if(mailUserList==1){
		// Ext.getCmp('itbar').setVisible(false);
		// }else{
		// Ext.getCmp('tbarchoose').setVisible(false);
		// };
		this.getUserRoleGridStore().on('write', this.afterSaveUserRole);
		this.getUserGridStore().on('write', this.afterSaveUser);
		this.control({
					'departtree' : {
						beforeitemexpand : this.onItemExpand,
						beforeitemclick : this.onBeforeItemClick,
						itemclick : this.onClickTree
					},

					'viewport panel toolbar button[action=add]' : {
						click : this.addGrid
					},
					'viewport panel toolbar button[action=edit]' : {
						click : this.editGrid
					},
					'viewport panel toolbar button[action=delete]' : {
						click : this.deleteGrid
					},

					'viewport panel toolbar button[action=authedit]' : {
						click : this.autheditGrid
					},
					'viewport panel toolbar button[action=choose]' : {
						click : this.chooseUserMailList
					},
					'viewport panel toolbar button[action=rtn]' : {
						click : this.closePage
					},
					'viewport panel toolbar button[action=changePwd]' : {
						click : this.onChangePwd
					},
					'gridedit button[action=save]' : {
						click : this.saveGrid
					},
					'authgridedit  #addRoleId' : {
						click : this.addRole
					},

					'authgridedit   #removeRoleId' : {
						click : this.removeRole
					},
					'authgridedit  button[action=saveUserRole]' : {
						click : this.saveUserRole
					},
					'viewport panel toolbar button[action=order]' : {// 人员排序
						click : this.userOrder
					},
					'viewport panel toolbar button[action=findUser]' : {// 人员排序
						click : this.findUser
					}
				})
	},
	afterSaveUserRole : function() {
		Ext.Msg.alert('保存提示', '保存成功！')
	},
	afterSaveUser : function() {
		Ext.Msg.alert('保存提示', '保存成功！')
	},
	addGrid : function() {
		var edit = Ext.create('ManageUser.view.GridEdit').show();
		var treeNode = this.getDepartTree().getSelectionModel().getSelection()[0];
		// Ext.Msg.alert("ddd");
		Ext.ComponentQuery.query("#userDeptId")[0].setValue(treeNode.get("id"));
		Ext.ComponentQuery.query("#userDeptId")[0].setReadOnly(true);
	},
	editGrid : function() {
		var iGrid = this.getUserGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var edit = Ext.create('ManageUser.view.GridEdit');
			edit.show();
			edit.down('form').down('textfield[name=userPassword]')
					.setDisabled(true);// 用户修改时不显示口令，口令有专门模块修改
			edit.down('form').down('textfield[name=userPassword]')
					.setVisible(false);
			edit.down('form').loadRecord(irecord);
			edit.down('form').down('textfield').setDisabled(true);
			Ext.getCmp("treepicker").value = irecord.get('userDept');
			Ext.getCmp("treepicker").setRawValue(irecord.get('userDesktop'));
		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}
	},
	deleteGrid : function() {
		var iGridStore = this.getUserGridStore();
		var iGrid = this.getUserGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		Ext.Msg.confirm('提示', '是否确认删除？', function(choice) {
					if (choice == 'yes') {
						if (irecord) {
							iGridStore.remove(irecord);
						} else {
							Ext.Msg.alert('请选择要删除的记录!');
						}
					}
				});

	},
	saveGrid : function(button) {
		var win = button.up('window'), form = win.down('form'), record = form
				.getRecord(), values = form.getValues();
		var me =this;
		if (form.isValid()) {
			if (record) {
				delete record.data.userPassword;
				record.set(values);
				win.close();
			} else {// add
				// 添加用户
				Ext.Ajax.request({
							url : 'admin/sys/userinfoifexist',
							params : {
								userId : values.userId,
								userAccount:values.userAccount
							},
							success : function(response) {
								var text = response.responseText;
								if (text == "true") {
									Ext.Msg.alert("提示", "用户名或ID已存在，请重新修改");
								}else{
									me.getUserGridStore().insert(0, values);
									win.close();
								}
							}
						});
			}
			
		}
	},
	autheditGrid : function() {

		var iGrid = this.getUserGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var edit = Ext.create('ManageUser.view.AuthGridEdit');
			edit.show();
			var userRolestore = this.getUserRoleGridStore();
			userRolestore.loadData([], false)
			userRolestore.getProxy().setExtraParam("userId",
					irecord.get("userId"));
			userRolestore.load();
			var rolestore = this.getRoleGridStore();
			rolestore.userRolestore
			rolestore.getProxy().setExtraParam("userId", irecord.get("userId"));
			rolestore.load();

			Ext.ComponentQuery.query("#userId")[0].setValue(irecord
					.get("userId"));
		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}

	},
	addRole : function() {

		var iGrid = this.getRoleGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		var iGridStore = iGrid.getStore();
		if (irecord) {
			var userRolestore = this.getUserRoleGridStore();
			var values = {
				userId : Ext.ComponentQuery.query("#userId")[0].getValue(),// Ext.getDom("userId").value,
				roleId : irecord.get("roleId"),
				roleDesc : irecord.get("roleDesc")
			};
			userRolestore.insert(0, values);
			iGridStore.remove(irecord);
			iGrid.getSelectionModel().select(0);

		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}
	},
	removeRole : function() {
		var iGrid = this.getUserRoleGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var userRolestore = this.getUserRoleGridStore();
			userRolestore.remove(irecord);
			this.getRoleGrid().getStore().insert(0, irecord);
			iGrid.getSelectionModel().select(0);

		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}
	}

	,
	onItemExpand : function(node, optd) {
		var store = this.getDepartTreeStore();
		var mProxy = store.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	},
	onBeforeItemClick : function(viewtree, record, item, index, e, eOpts) {

		e.stopEvent();
	},

	onClickTree : function(viewtree, record, item, index, e, eOpts) {
		// if (record.isLeaf()) {

		var store = this.getUserGridStore();
		var mProxy = store.getProxy();
		// 清空store
		store.loadData([], false);
		this.userDept = record.get("id");
		mProxy.setExtraParam("userDept", record.get("id"));
		store.load();
	},
	saveUserRole : function() {

		var roleRightStore = this.getUserRoleGridStore();
		if (roleRightStore.getNewRecords().length == 1) {
			roleRightStore.getProxy().api.create = 'admin/sys/addSingleUserRole';
		} else {
			roleRightStore.getProxy().api.create = 'admin/sys/addUserRole';
		}
		if (roleRightStore.getRemovedRecords().length == 1) {
			roleRightStore.getProxy().api.destroy = 'admin/sys/removeSingleUserRole';
		} else {
			roleRightStore.getProxy().api.destroy = 'admin/sys/removeUserRole';
		}
		roleRightStore.sync();
		// this.getUserRoleGridStore().sync();

	},
	chooseUserMailList : function() {
		var iGrid = this.getUserGrid();
		var irecords = iGrid.getSelectionModel().getSelection();
		var names = [];
		Ext.each(irecords, function(irecord) {
					name.push(irecord.get('userName') + '<'
							+ irecord.get('userAccount').toLowerCase()
							+ '@ytgs.gov.cn>');
				});
		window.opener.document.getElementById('mailUsers').value = name
				.join(',');
		window.close();
	},
	closePage : function() {
		window.close();
	},
	userOrder : function() {
		var win = Ext.create('Ext.window.Window', {
					width : 400,
					height : 300,
					layout : 'fit',
					items : [{
								xtype : 'userordergrid'
							}]
				});
		// var grid = Ext.create('ManageUser.view.UserOrderGrid');
		// win.add(grid);
		win.show();

	},
	findUser : function() {
		var me = this;
		var win = Ext.create('Ext.window.Window', {
					width : 400,
					height : 200,
					layout : 'fit',
					items : [{
						xtype : 'form',
						url : 'admin/sys/manageUserQueryByUser',
						items : [{
									xtype : 'textfield',
									fieldLabel : '姓名',
									name : 'userName'
								}, {
									xtype : 'textfield',
									fieldLabel : '登录账号',
									name : 'userAccount'
								}],
						buttons : [{
							text : '查询',
							formBind : true, // only enabled once the
							// form is
							// valid
							disabled : true,
							handler : function() {
								var form = this.up('form').getForm();
								if (form.isValid()) {
									var store = me.getUserGridStore();
									var mProxy = store.getProxy();
									// 清空store
									store.loadData([], false);
									var values = form.getValues();
									mProxy.setExtraParam("userDept", '');
									mProxy.setExtraParam("userName",
											values.userName);
									mProxy.setExtraParam("userAccount",
											values.userAccount);
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
						}]

					}]
				});
		win.show();

	},
	onChangePwd : function() {
		var iGrid = this.getUserGrid();
		var userAccount;
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			userAccount = irecord.get('userAccount');
		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
			return;
		}
		var dlg = new Ext.create('Ext.window.Window', {
			title : '修改密码',
			layout : 'fit',
			width : 380,
			height : 160,
			items : {
				xtype : 'form',
				id : 'pwdForm',

				layout : 'vbox',
				items : [{
							xtype : 'textfield',

							name : 'newpass1',
							id : 'newpass1',
							width : 260,
							inputType : 'password',
							labelWidth : 95,
							labelAlign : 'right',
							allowBlank : false,
							msgTarget : 'side',
							fieldLabel : '新密码'
						}, {
							xtype : 'textfield',
							name : 'newpass2',
							id : 'newpass2',
							width : 260,
							inputType : 'password',
							labelWidth : 95,
							labelAlign : 'right',
							allowBlank : false,
							msgTarget : 'side',
							fieldLabel : '确认新密码'
						}, {
							xtype : 'textfield',
							name : 'userAccount',
							hidden : true,
							value : userAccount
						}],
				buttons : [{
					text : '确认',
					name : 'subbutton',
					action : 'modifyPwd',
					handler : function(btn) {
						var vpwd1 = Ext.getCmp('newpass1').value;
						var vpwd2 = Ext.getCmp('newpass2').value;
						var vForm = Ext.getCmp('pwdForm');
						if (vForm.getForm().isValid()) {
							if (vpwd1 == vpwd2) {
								vForm.el.mask('保存中...');
								vForm.getForm().submit({
									clientValidation : true,
									url : 'admin/sys/desktop/modifyPwd',
									success : function(form, action) {

										if (action.result.success) {
											Ext.Msg.alert('提示', "修改成功");
											dlg.close();
										} else {
											Ext.Msg.alert('提示', "修改失败");
											vForm.el.unmask();
										}

									},
									failure : function(form, action) {
										switch (action.failureType) {
											case Ext.form.action.Action.CLIENT_INVALID :
												Ext.Msg
														.alert('Failure',
																'Form fields may not be submitted with invalid values');
												break;
											case Ext.form.action.Action.CONNECT_FAILURE :
												Ext.Msg
														.alert('Failure',
																'Ajax communication failed');
												break;
											case Ext.form.action.Action.SERVER_INVALID :
												Ext.Msg.alert('Failure',
														action.result.msg);

										}
										vForm.el.unmask();
									}
								});

							} else {
								Ext.Msg.alert('两次输入不一致，请检查!');
							}
						}
					}
				}]
			}
		});
		dlg.show();
	}
});
