Ext.define('ManageUser.view.GridEdit', {
	extend : 'Ext.window.Window',
	alias : 'widget.gridedit',

	requires : [ 'Ext.form.Panel' ],

	title : '用户维护',
	layout : 'fit',
	autoShow : true,
	width : 600,

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff;',

			items : [ {
				xtype : 'textfield',
				name : 'userId',
				width : 300,
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '用户Id',
				listeners : {
					blur : function(me) {
						if (me.value != null) {
							Ext.Ajax.request({
								url : 'admin/sys/userifexist',
								params : {
									value : me.value
								},
								success : function(response) {
									var text = response.responseText;
									if (text == "true") {
										Ext.Msg.alert("提示", "用户ID已存在");
									}
								}
							});
						}

					}
				}
			}, {
				xtype : 'textfield',
				name : 'userAccount',
				width : 300,
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '登陆账号',
				listeners : {
					blur : function(me) {
						if (me.value != null) {
							Ext.Ajax.request({
								url : 'admin/sys/useraccountifexist',
								params : {
									value : me.value
								},
								success : function(response) {
									var text = response.responseText;
									if (text == "true") {
										Ext.Msg.alert("提示", "用户名已存在");
									}
								}
							});
						}

					}
				}
			}, {
				xtype : 'textfield',
				name : 'userName',
				allowBlank : false,
				width : 300,
				fieldLabel : '姓名'
			}
			// , {
			// xtype : 'radiogroup',
			// fieldLabel : '用户属性',
			// columns : 2,
			// width : '80%',
			// vertical : true,
			// items : [{
			// boxLabel : '内部',
			// name : 'userType',
			// padding : '0 0 0 120',
			// inputValue : '内',
			// width : 75,
			// checked : true
			// }, {
			// boxLabel : '外部',
			// name : 'userType',
			// width : 75,
			// inputValue : '外'
			// }]
			// }
			, {
				xtype : 'radiogroup',
				fieldLabel : '是否有效',
				width : 300,
				columns : 2,
				vertical : true,
				items : [ {
					boxLabel : '是',
					name : 'isValid',
					padding : '0 0 0 120',
					width : 75,
					margins : '0 0 0 30',
					inputValue : 'Y',
					checked : true
				}, {
					boxLabel : '否',
					width : 75,
					name : 'isValid',
					inputValue : 'N'
				} ]
			}, {
				xtype : 'resourcecombobox',
				name : 'userSex',
				allowBlank : false,
				width : 300,
				fieldLabel : '性别'
			}, {
				xtype : 'textfield',
				name : 'userPassword',
				width : 300,
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '口令'
			}, {
				xtype : 'textfield',
				name : 'userDesc',
				width :400,
				msgTarget : 'side',
				fieldLabel : '用户描述'
			}, {
				xtype : 'numberfield',
				name : 'userMobile',
				hideTrigger : true,
				width : 400,
				msgTarget : 'side',
				maxLength : 11,
				minLength : 11,
				fieldLabel : '移动电话'
			}, {
				// xtype: 'textfield',
				xtype : 'treepicker',
				id : 'treepicker',
				displayField : 'text',
				// value:'id',
				rootVisible : false,
				store : Ext.create('Ext.data.TreeStore', {
					fields : [ {
						name : 'id',
						type : 'string'
					}, {
						name : 'text',
						type : 'string'
					} ],
					autoLoad : true,
					proxy : {
						type : 'ajax',
						url : 'admin/sys/UserDeparmentQueryBynode',
						extraParams : {
							id : depId
						},
						reader : {
							type : 'json',
							root : 'records'
						}
					},
					root : {
						text : authorityDeptName,
						id : depId,
						expanded : false
					}
				}),
				name : 'userDept',
				itemId : 'userDeptId',
				width : 500,
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '部门'
			} ]
		} ];

		this.buttons = [ {
			text : '保存',
			action : 'save'
		}, {
			text : '取消',
			scope : this,
			handler : this.close
		} ];

		this.callParent(arguments);
	}
});
