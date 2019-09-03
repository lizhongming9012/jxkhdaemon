Ext.define('ManageDept.controller.DeptController', {
			extend : 'Ext.app.Controller',
			requires : [

			'ManageDept.model.DeptTree'],
			refs : [{
						ref : 'deptTree',
						selector : 'depttree'
					}, {
						ref : 'deptEdit',
						selector : 'deptedit'
					}, {
						ref : 'deptView',
						selector : 'deptview'
					}],

			stores : ['DeptTree'],

			init : function() {
				// this.getDeptTreeStore().on({write:this.onStoreWrite,scope:
				// this});//回调函数
				this.control({

							'depttree' : {
								beforeitemexpand : this.onItemExpand,
								beforeitemclick : this.onBeforeItemClick
							},
							'viewport toolbar button[action=add]' : {
								click : this.addDept
							},
							'viewport toolbar button[action=edit]' : {
								click : this.editDept
							},
							'viewport  toolbar button[action=delete]' : {
								click : this.deleteDept
							},
							'viewport  toolbar button[action=view]' : {
								click : this.viewdept
							},
							'deptedit button[action=save]' : {
								click : this.saveDept
							},
							'deptedit textfield[name=deptId]' : {
								blur : this.checkDept
							}

						})
			},
			onStoreWrite : function() {

			},
			addDept : function() {
				var treePanel = this.getDeptTree();
				/*
				 * if (treePanel.getSelectionModel().getSelection().length > 1) {
				 * Ext.msg.alert('不能选中多个节点同时操作!'); return false; }
				 */
				var selNode = treePanel.getSelectionModel().getSelection()[0];
				if (selNode) {
					var nodeEditor = Ext.create('ManageDept.view.DeptEdit');
					nodeEditor.show();

				} else {
					Ext.Msg.alert('请选择欲插入的节点!');
				}
			},
			editDept : function() {

				var treePanel = this.getDeptTree();
				var me = this;
				var selNode = treePanel.getSelectionModel().getSelection()[0];
				if (selNode) {
					var nodeEditor = Ext.create('ManageDept.view.DeptEdit');
					nodeEditor.show();
					nodeEditor.down('form').loadRecord(selNode);
					nodeEditor.down('form').getComponent('deptId')
							.setDisabled(true);
				} else {
					Ext.Msg.alert('请选择欲插入的节点!');
				}
			},
			deleteDept : function() {
				var treePanel = this.getDeptTree();
				var selNode = treePanel.getSelectionModel().getSelection()[0];
				var parentNode = selNode.parentNode;// 父节点
				var treeStore = this.getDeptTreeStore();
				var proxy = treeStore.getProxy();
				proxy.setExtraParam("id", selNode.getId());
				if (selNode) {
					if (selNode.isLeaf()) {
						Ext.Msg.confirm('部门', '是否确认删除？', function(choice) {
									if (choice == 'yes') {
										selNode.remove();
										if (!parentNode.hasChildNodes()) {
											parentNode.set('leaf', true);
										}
									}
								});
					} else {
						Ext.Msg.alert('不能删除非空节点');
					}
				} else {
					Ext.Msg.alert('请选择要删除的记录!');
				}
			},
			saveDept : function(button) {
				var win = button.up('window'), form = win.down('form'), record = form
						.getRecord(), values = form.getValues();

				// 当前被选中的菜单节点
				var selNode = this.getDeptTree().getSelectionModel()
						.getSelection()[0];
				var rootNode = this.getDeptTree().getRootNode();
				var parentNode = selNode.parentNode;// 父节点
				var allId = '';
				// var parentId=parentNode.get('id');
				if (record) {// 修改
					var oldValue = selNode.get('id');
					if (form.hasInvalidField()) {
						Ext.Msg.alert('请检查录入数据！');
						return false;
					}
					values.text = values.deptName + '(' + oldValue + ')';
					record.set(values);
					Ext.Msg.alert('信息修改成功！');
					win.close();
				} else {// 增加

					selNode.set('leaf', false);
					values.leaf = true;
					values.text = values.deptName + '(' + values.deptId + ')';;
					if (form.hasInvalidField()) {
						Ext.Msg.alert('请检查录入数据！');
						return false;
					}
					newNode = selNode.appendChild(values);
					selNode.expand();
					win.close();
				}
			},
			viewdept : function() {

				var treePanel = this.getDeptTree();
				var me = this;
				var selNode = treePanel.getSelectionModel().getSelection()[0];

				var nodeEditor = Ext.create('ManageDept.view.DeptView');
				nodeEditor.show();
				nodeEditor.down('form').loadRecord(selNode);
				nodeEditor.down('form').getComponent('deptId')
						.setDisabled(true);
				nodeEditor.down('form').getComponent('deptName')
						.setDisabled(true);
				nodeEditor.down('form').getComponent('deptDesc')
						.setDisabled(true);
				nodeEditor.down('form').getComponent('deptstandalone')
						.setDisabled(true);
				nodeEditor.down('form').getComponent('deptDesc')
						.setDisabled(true);

			},
			checkDept : function(textFld, node, optd) {
				var textValue = textFld.value;
				var selNode = this.getDeptTree().getSelectionModel().getSelection()[0];
				Ext.Ajax.request({
							url : 'admin/dept/checkdept',
							params : {
								'textValue' : textValue
							},
							success : function(response) {
								var resText = Ext.decode(response.responseText);
								var resMsg = resText.message
								var resFlag = resText.success
								var parentId=selNode.get('id')
								 if(textValue<parentId||textValue>=13706999999)
	                           	{
	                           	    textFld.focus();
									textFld.markInvalid("部门编码越界");
	                            } 
								if (resFlag == "false") {
									textFld.focus();
									textFld.markInvalid("部门编码重复");
								}
							}
						});

			},
			onItemExpand : function(node, optd) {
				var store = this.getDeptTreeStore();
				var mProxy = store.getProxy();
				mProxy.setExtraParam("id", node.get("id"));

			},
			onBeforeItemClick : function(viewtree, record, item, index, e,
					eOpts) {
				e.stopEvent();
			}
		})

;
