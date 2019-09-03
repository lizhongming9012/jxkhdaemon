Ext
		.define(
				'Sys.view.Viewport',
				{
					extend : 'Ext.container.Viewport',
					layout : 'border',
					id : 'viewport',
					requires : [ 'Sys.view.MenuTreeView',
							'Sys.view.ContentCardView' ],
					initComponent : function() {
						this.items = [
								{
									region : 'north', // position for region
									xtype : 'panel',
									// height : 100,
									height : 35,
									split : false, // enable resizing
									margins : '0 0 0 0',
									dockedItems : [ {
										xtype : 'toolbar',
										dock : 'top',
										id : '3434',
										items : [ {
											xtype : 'component',
											width : '80%'
										}, {
											xtype : 'label',
											text : '当前用户：'
										}, {
											xtype : 'label',
											text : userName
										}, {
											xtype : 'component',
											width : 20
										}, {
											text : '注销',
											action : 'btLogout',
											id : 'btLogout'
										}, {
											text : '修改密码',
											action : 'changePwd',
											id : 'changePwd'
										} ]
									} ]
//								 ,
//								 items : [ {
//								 xtype : 'component',
//								 html:'<div><center><h1>绩效管理系统</h1></center></div>'
//								 } ]
								},
								{
									region : 'west', // position for region
									xtype : 'panel',
									split : true, // enable resizing
									margins : '0 5 5 5',
									layout : 'fit',
									collapsible : true,
									autoSroll : true,
									items : {
										width : 250,
										xtype : 'menutreeview',
										id : 'west-region',
										margins : '0 0 0 0',
										layout : {
											type : 'vbox',
											align : 'stretch'
										}
									}
								},
								{
									region : 'center', // position for region
									xtype : 'panel',
									split : false, // enable resizing
									margins : '0 0 0 0',
									layout : 'fit',

									items : [ {
										xtype : 'contentcardview',
										flex : 1,
										layout : {
											type : 'vbox',
											align : 'stretch'
										},
										items : {
											title : '首页',
											html : '<div><img src="'
													+ basePath
													+ 'res/images/back.jpg" width="100%" height="100%" /></div>'
										}
									} ]
								} ]
						this.callParent();
					}
				});