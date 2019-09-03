Ext.define('ManageMenu.controller.MenuController', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'menuTree',
				selector : 'menutree'
			}, {
				ref : 'menuEdit',
				selector : 'menuedit'
			}],

	stores : ['MenuTree', 'ModuleTreePicker', 'AuthorityComboBox'],

	init : function() {
		//this.getMenuTreeStore().on({write:this.onStoreWrite,scope: this});//回调函数
		this.control({

					'menutree' : {
						beforeitemexpand : this.onItemExpand,
						beforeitemclick : this.onBeforeItemClick

					},
					'viewport toolbar button[action=add]' : {
						click : this.addMenu
					},
					'viewport toolbar button[action=edit]' : {
						click : this.editMenu
					},
					'viewport  toolbar button[action=delete]' : {
						click : this.deleteMenu
					},
					'menuedit button[action=save]' : {
						click : this.saveMenu
					},
					'menuedit form #droptree' : {
						select : this.dropTreeItemSelect
					}
					
				})
	},
	
	addMenu : function() {
		var treePanel = this.getMenuTree();
		if (treePanel.getSelectionModel().getSelection().length > 1) {
			Ext.msg.alert('不能选中多个节点同时操作!');
			return false;
		}
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		if (selNode) {
			var nodeEditor = Ext.create('ManageMenu.view.MenuEdit');
			nodeEditor.show();

		} else {
			Ext.Msg.alert('请选择欲插入的节点!');
		}
	},
	editMenu : function() {
		var treePanel = this.getMenuTree();
		var me = this;
		if (treePanel.getSelectionModel().getSelection().length > 1) {
			Ext.msg.alert('不能选中多个节点同时操作!');
			return false;
		}
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		if (selNode) {
			var nodeEditor = Ext.create('ManageMenu.view.MenuEdit');
			nodeEditor.show();
			nodeEditor.down('form').loadRecord(selNode);
			nodeEditor.down('form').getComponent('auth-combobox').setDisabled(true);
			nodeEditor.down('form').getComponent('droptree').setDisabled(true);
		} else {
			Ext.Msg.alert('请选择欲插入的节点!');
		}
	},
	deleteMenu : function() {
		var treePanel = this.getMenuTree();
		var selNode = treePanel.getSelectionModel().getSelection()[0];
		var parentNode = selNode.parentNode;// 父节点
		var treeStore = this.getMenuTreeStore();
		var proxy = treeStore.getProxy();
		proxy.setExtraParam("id", selNode.getId());
		if (selNode) {
			if (selNode.isLeaf()) {
				Ext.Msg.confirm('菜单', '是否确认删除？', function(choice) {
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
	saveMenu : function(button) {
		var win = button.up('window'), form = win.down('form'), record = form
				.getRecord(), values = form.getValues();
				
		// 当前被选中的菜单节点
		var selNode = this.getMenuTree().getSelectionModel().getSelection()[0];
		if (record) {//修改
			var oldValue = selNode.get('text');			
			if (values.text.length > 0) {
				record.set(values);	
				win.close();
			} 
		} else {//增加
			win.close();
			selNode.set('leaf', false);
			values.parentId=selNode.getId();
			values.leaf=true;
			newNode = selNode.appendChild(values);
			selNode.expand();
		}
	},
	onItemExpand : function(node, optd) {
		var store = this.getMenuTreeStore();
		var mProxy = store.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	},
	onBeforeItemClick : function(viewtree, record, item, index, e, eOpts) {
		e.stopEvent();
	},
	dropTreeItemSelect : function(treePicker, record, eOpts) {

		var authorityCombo = treePicker.up('window').down('form').getComponent('auth-combobox');
		authorityCombo.clearValue();// 清空权限
		var store = this.getAuthorityComboBoxStore();
		var mProxy = store.getProxy();
		store.loadData([], false);// 清空store
		mProxy.setExtraParam("moduleId", record.get("id"));
		store.load();
	}
});
