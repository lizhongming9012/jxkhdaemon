Ext.define('ManageUser.view.UserOrderGrid', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.userordergrid',
			store : 'UserGrid',
			viewConfig : {
				enableTextSelection : true
			},// 可选择
			border : 0,
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'UserGrid',
						dock : 'bottom',
						displayInfo : true
					}],
			selType : 'cellmodel',
			plugins : [{
						ptype : 'cellediting',
						clicksToEdit : 1
					}],
			columns : {
				defaults : {
					resizable : false
				},
				items : [{
							text : '序号',
							dataIndex : 'xh',
							width : 50,
							xtype : 'rownumberer'
						},{
							text : '姓名',
							dataIndex : 'userName',
							flex : 3
						}, {
							text : '排序',
							dataIndex : 'userOrder',
							flex : 3,
							editor : {
								        xtype:'numberfield',
										name : 'userOrder',
										hideTrigger : true

									}
						}]
			}
		})