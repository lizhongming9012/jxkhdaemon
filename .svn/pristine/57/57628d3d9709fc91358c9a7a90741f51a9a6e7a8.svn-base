Ext.onReady(function() {
	Ext.QuickTips.init();
	var onConfirmDelete = function(btn) {
		if (btn == 'yes') {
			var treePanel = Ext.getCmp('treepanel');
			treePanel.el.mask('Deleting...', 'x-mask-loading');
			var selNode = treePanel.getSelectionModel().getSelection()[0];
			var parentNode = selNode.parentNode;
			Ext.Ajax.request({
						url : 'admin/modules/deleteNode',
						params : {
							id : selNode.getId()
						},
						success : function(response, opts) {
							treePanel.el.unmask();
							var responseJson = Ext
									.decode(response.responseText);

							if (responseJson.success) {
								selNode.remove();
								if(!parentNode.hasChildNodes()){
									parentNode.set('leaf',true);
									//parentNode.set('cls','file');
								}
							} else {
								Ext.Msg
										.alert('An error occured with the server.');
							}
						}
					});

		}

	}

	//树的增加触发事件，完成后台数据插入
	var onCompleteAdd = function(button) {
		var treePanel = Ext.getCmp('treepanel');
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		var newNode = {
			text : '',
			id : 'tmpNode',
			expanded : true,
			checked: null,
			leaf : (selNode.getId() != '0000')
		};
		newNode.text = button.up('window').down('form').getValues().text;
		button.up('window').close();
		selNode.set('leaf',false);
		newNode = selNode.appendChild(newNode);
	    newNode.set('checked',false);
	    selNode.expand();
	    treePanel.el.mask('Adding...', 'x-mask-loading');
	    if (newNode.get('text').length > 0) {
	        Ext.Ajax.request({
	            url    : 'admin/modules/insertNode',
	            params : {
	                newName : newNode.get('text'),
	                pid      : selNode.getId()
	            },
	            success : function (response, opts) {
	                treePanel.el.unmask();
	                var responseJson = Ext.decode(response.responseText);

	                if (!responseJson.success) {
	                    Ext.Msg.alert('An error occured with the server.');
	                    newNode.remove();
	                }
	                else {	                	
	                    newNode.setId(responseJson.nodeId);
	                }
	            }
	        });
	    }
	    else {
	        newNode.remove();
	    }
	}
	//树的增加触发事件，显示增加记录窗口
	var onAddNode = function() {
		var treePanel = Ext.getCmp('treepanel');
		if(treePanel.getSelectionModel().getSelection().length>1){
			Ext.msg.alert('不能选中多个节点同时操作!');
			return false;
		}
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		if (selNode) {
			nodeEditor = new Ext.window.Window({
						title : '添加模块',
						height : 200,
						width : 400,
						layout : 'fit',
						items : [{
									xtype : 'form',
									border : false,
									padding : ' 5 5 0 5',
									border : false,
									items : [{
												xtype : 'textfield',
												name : 'text',
												fieldLabel : '模块名'
											}]
								}],
						buttons : [{
									text : '保存',
									handler : onCompleteAdd
								}, {
									text : '取消',
									scope : this,
									handler : function(button) {button.up('window').close()}
								}]
					});
			nodeEditor.show();

		} else {
			Ext.Msg.alert('请选择欲插入的节点!');
		}
	}
	//编辑完成后触发事件，包括调用AJAX更新后台数据
	var onCompleteEdit = function(button) {
		var treePanel = Ext.getCmp('treepanel');
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		var oldValue = selNode.get('text');
		selNode.set('text',button.up('window').down('form').getValues().text);
		button.up('window').close();
		treePanel.el.mask('Editing...', 'x-mask-loading');
	    if (selNode.get('text').length > 0) {
	        Ext.Ajax.request({
				url    : 'admin/modules/updateNode',
	            params : {
	                id      : selNode.getId(),
	                newName : selNode.get('text')
	            },
	            success : function (response, opts) {
	                treePanel.el.unmask();
	                var responseJson = Ext.decode(response.responseText);

	                if (!responseJson.success) {
	                    Ext.Msg.alert('An error occured with the server.');
	                    selNode.set('text',oldValue);
	                }
	            }
	        });
	    }
	    else {
	        selNode.set('text',oldValue);
	    }
	};
	//树的编辑触发事件
	var onEditNode = function() {
		var treePanel = Ext.getCmp('treepanel');
		if(treePanel.getSelectionModel().getSelection().length>1){
			Ext.msg.alert('不能选中多个节点同时操作!');
			return false;
		}
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		if (selNode) {
			nodeEditor = new Ext.window.Window({
						title : '添加模块',
						height : 200,
						width : 400,
						layout : 'fit',
						items : [{
									xtype : 'form',
									border : false,
									padding : ' 5 5 0 5',
									border : false,
									items : [{
												xtype : 'textfield',
												name : 'text',
												fieldLabel : '模块名',
												value : selNode.get('text')
											}]
								}],
						buttons : [{
									text : '保存',
									handler : onCompleteEdit
								}, {
									text : '取消',
									scope : this,
									handler : function(button) {button.up('window').close()}
								}]
					});
			nodeEditor.show();

		} else {
			Ext.Msg.alert('请选择欲插入的节点!');
		}
	};
	//树的删除触发事件
	var onDeleteNode = function() {
		 var treePanel =  Ext.getCmp('treepanel');
	        var selNode = treePanel.getSelectionModel().getSelection()[0];

	        if (selNode.isLeaf()) {
	            Ext.MessageBox.confirm(
	                'Are you sure?',
	                '确认删除： ' + selNode.get('text'),
	                onConfirmDelete
	            )
	        }else{
	        	Ext.Msg.alert('不能删除非空节点');
	        }	   
	};
   
	// 定义树的store
	var treestore = Ext.create('Ext.data.TreeStore', {
				proxy : {
					type : 'ajax',
					url : 'admin/checkModuleTree',
					extraParams : {
						id : '0000'
					}// ,
					// reader: {
					// type: 'json'
					// }
				},
				root : {
					text : '模块管理',
					id : '0000',
					expanded : true
				}
			});
	// 响应树的扩展事件，传递node参数
	var onItemExpand = function(node, optd) {
		var mProxy = treestore.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	};
	//定义树
	var tree = Ext.create('Ext.tree.Panel', {
				id : 'treepanel',
				autoScroll : true,
				animate : true,
				store : treestore,
				layout : 'fit',
				margins : '0 0 2 2',
				padding : ' 0 0 5 5',
				renderTo : Ext.getBody(),
				listeners : {
					// itemcontextmenu : onCtxMenu,
					beforeitemexpand : onItemExpand,
					// 屏蔽鼠标左键单击事件
					beforeclick : function(a, b) {
						if (this.getSelectionModel().isSelected(a)) {
							return false;
						}
					}
				}
			});
	Ext.create('Ext.panel.Panel', {
				layout : 'fit',
				renderTo : document.body,
				bodyPadding : 10,
				dockedItems : [{
							xtype : 'toolbar',
							dock : 'top',
							items : [{
										xtype : 'button',
										text : '增加',
										handler : onAddNode
									}, {
										xtype : 'button',
										text : '修改',
										handler : onEditNode
									}, {
										xtype : 'button',
										text : '删除',
										handler : onDeleteNode
									}]
						}],
				items : tree
			});
});