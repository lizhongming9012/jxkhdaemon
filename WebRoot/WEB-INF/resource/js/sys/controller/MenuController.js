Ext.define('Sys.controller.MenuController', {
    extend: 'Ext.app.Controller',

    refs: [{
        ref: 'menuTreeView',
        selector: 'menutreeview'
    },
    {
        ref: 'contentCardView',
        selector: 'contentcardview'
    }
    ],

    stores: ['SysTree'],
    
    init: function() {    	
      this.control({
      'viewport panel toolbar button[action=btLogout]' : {
    	  click : function(){
    		  Ext.Msg.confirm('登出', '您确定退出系统吗？', function(btn, text) {
    			  if (btn == 'yes') {
    				  location.href=basePath+'j_spring_security_logout';
    			  				}
    		  			});
    		  		}
			},
	'viewport panel toolbar button[action=changePwd]' : {
			click : function (){
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
													if(action.result.success){
																	Ext.Msg.alert('提示', "修改成功");
														          dlg.close();
																}else{
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
	},
        'menutreeview': {
                beforeitemclick: this.onBeforeItemClick,
                itemclick : this.onClickTree
            }
       })
    },
   
    onItemExpand:  function(node,optd){
			var store = this.getSysTreeStore();					     
			var mProxy=store.getProxy();
		    mProxy.setExtraParam("id",node.get("id"));
	} ,	
	onBeforeItemClick: function(viewtree, record, item, index, e, eOpts ){
		
		 e.stopEvent();
	},
	
	onClickTree: function(viewtree, record, item, index, e, eOpts ){
		var content = this.getContentCardView();		
	    if(!record.isLeaf()){
	    	return false;
	    }
	    if(content.getChildByElement(record.get("id"))){
	    	content.setActiveTab(record.get("id"));
	    	return false;
	    }
	    content.el.mask('加载中...');
		var tab = Ext.create('Ext.panel.Panel', {
		    title : record.get("text"),	    
			closable : true,
			id : record.get("id"),
			layout: {
                    type: 'fit',
                    align: 'stretch'
                },
		    html: '',
		    lisnteners:{show:function(){content.el.unmask();}}
		});
		tab.html='<iframe onload="Ext.getCmp(\'sysTabPanel\').el.unmask();" id="frame'+record.get("id")+'" src="'+record.get("href")+'" width=100% height="100%" frameBorder="0"></iframe>';
		content.add(tab).show();
		content.setActiveTab(tab);
	}
});
