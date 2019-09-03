Ext.define('ManageResource.controller.ResourceController', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'moduleTree',
				selector : 'moduletree'
			}, {
				ref : 'resourceGrid',
				selector : 'resourcegrid'
			}, {
				ref : 'gridEdit',
				selector : 'gridedit'
			}],

	stores : ['ModuleTree', 'ResourceGrid'],

	init : function() {
		this.control({

					'moduletree' : {
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
					'gridedit button[action=save]' : {
						click : this.saveGrid
					}
				})
	},
	addGrid : function() {
		var store = this.getResourceGridStore();
		var selNode=this.getModuleTree().getSelectionModel().getSelection()[0];
		var mProxy = store.getProxy();
		var moduleId = store.getProxy().extraParams.moduleId;
		if(moduleId){
		  if(moduleId==selNode.getId()){
		   var edit = Ext.create('ManageResource.view.GridEdit').show();
		  }else{
		  	Ext.Msg.alert('请选择具体模块!');
		  }
		}else{
			Ext.Msg.alert('请选择具体模块!');
		}
	},
	editGrid : function() {
		
		var iGrid = this.getResourceGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		if (irecord) {
			var edit = Ext.create('ManageResource.view.GridEdit');
			edit.show();
			edit.down('form').loadRecord(irecord);
			edit.down('form').down('textfield').setDisabled(true);
		} else {
			Ext.Msg.alert('请选择要编辑的记录!');
		}
	},
	deleteGrid : function() {
		var iGridStore = this.getResourceGridStore();
		var iGrid = this.getResourceGrid();
		var irecord = iGrid.getSelectionModel().getSelection()[0];
		Ext.Msg.confirm('资源','是否确认删除？',function(choice){
		if (choice=='yes') {			
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
		if (record) {
			record.set(values);
		} else {
			values.module = this.getResourceGridStore().getProxy().extraParams.moduleId;
			this.getResourceGridStore().insert(0, values);
		}
		win.close();
	},
	onItemExpand : function(node, optd) {
		var store = this.getModuleTreeStore();
		var mProxy = store.getProxy();
		mProxy.setExtraParam("id", node.get("id"));

	},
	onBeforeItemClick : function(viewtree, record, item, index, e, eOpts) {

		e.stopEvent();
	},

	onClickTree : function(viewtree, record, item, index, e, eOpts) {
		if (record.isLeaf()) {
			var store = this.getResourceGridStore();
			var mProxy = store.getProxy();
			//清空store
			store.loadData([],false);
			mProxy.setExtraParam("moduleId", record.get("id"));
			store.load();
		}
	}

});
