Ext
		.define(
				'Gztz.controller.GztzController',
				{
					extend : 'Ext.app.Controller',

					refs : [ {
						ref : 'gztzGrid',
						selector : 'gztzgrid'
					}, {
						ref : 'gridEdit',
						selector : 'gridedit'
					} ],

					stores : [ 'GztzGrid', 'GztzqueryGrid' ],
					catId : 0,
					init : function() {
						var istore = this.getGztzGridStore();
						istore.on('write', function() {// store写回后台成功时，回调函数
							Ext.Msg.alert('保存成功！')
						});
						this.control({
							'viewport panel toolbar button[action=add]' : {
								click : this.addGrid
							},
							'viewport panel toolbar button[action=update]' : {
								click : this.updateGrid
							},
							'viewport panel toolbar button[action=delete]' : {
								click : this.deleteGrid
							},
							'gridedit button[action=save]' : {
								click : this.saveGrid
							},
							'gridedit button[action=close]' : {
								click : this.reloadGrid
							},
							'gridedit button[name=fileButton]' : {
								click : this.uploadAttachHandler
							},
							'gridedit button[name=filecxButton]' : {
								click : this.cxupload
							},
							'#queryId' : { // 响应查询按钮
								click : this.queryGztz
							},
							'#resetId' : { // 响应重置按钮
								click : this.resetGztz
							}
						})
					},
					queryGztz : function() {
						values = Ext.getCmp('gztzFormCx').getValues();
						var iproxy = this.getGztzGridStore().getProxy();
						iproxy.extraParams = values;
						this.getGztzGridStore().load();
					},
					resetGztz : function() {
						Ext.getCmp('gztzFormCx').getForm().reset();
					},
					cxupload : function() {
						Ext.getCmp('displayAttachName').setValue(null);
						Ext.getCmp('attachname').setValue(null);
					},
					uploadAttachHandler : function(oButton) {
						var form = oButton.up('form').getForm();
						var uniForm = oButton.up('form').up('form');

						if (form.isValid()) {
							form
									.submit({
										url : 'admin/xzjx/gztz/uploadGztzAttachmentFile',

										waitMsg : '上传附件...',
										success : function(fp, o) {
											Ext.Msg.alert("上传成功!");
											var oldValue = uniForm
													.down(
															'textfield[name=attachname]')
													.getValue();
											var oldUploadValue = uniForm
													.down(
															'textfield[name=attchnameupload]')
													.getValue();
											var oldDisplayValue = uniForm
													.down(
															'textfield[name=displayAttachName]')
													.getValue();
											if (oldValue.length == 0) {
												uniForm
														.down(
																'textfield[name=attachname]')
														.setValue(
																o.result.attchname);
												uniForm
														.down(
																'textfield[name=attchnameupload]')
														.setValue(
																o.result.attchnameupload);
												uniForm
														.down(
																'textfield[name=displayAttachName]')
														.setValue(
																o.result.displayAttachName);
											} else {
												uniForm
														.down(
																'textfield[name=attachname]')
														.setValue(
																oldValue
																		+ ","
																		+ o.result.attchname);
												uniForm
														.down(
																'textfield[name=attchnameupload]')
														.setValue(
																oldUploadValue
																		+ ","
																		+ o.result.attchnameupload);
												uniForm
														.down(
																'textfield[name=displayAttachName]')
														.setValue(
																oldDisplayValue
																		+ ","
																		+ o.result.displayAttachName);
											}
											uniForm.down(
													'textfield[name=savepath]')
													.setValue(o.result.path);

										},
										failure : function(fp, o) {
											Ext.Msg.alert(o.result.message);
										}
									});
						}
					},
					addGrid : function() {
						// 打开页面
						window
								.open(basePath
										+ 'admin/xzjx/gztz/gztzeditPublish?action=create');
					},
					// addGrid_old : function() {
					// alert('通知发布'+basePath);
					// var pal = Ext.create('Gztz.view.GridEdit');
					// 定义通知发送的最小窗口
					// pal.width = 690;
					// pal.height =480;
					// pal.show();
					// },
					updateGrid : function() {
						var iGrid = this.getGztzGrid();
						var irecord = iGrid.getSelectionModel().getSelection()[0];
						if ((irecord)) {
							window
									.open(basePath
											+ 'admin/xzjx/gztz/gztzeditPublish?action=update&id='
											+ irecord.get('id'));
							/*
							 * this.addGrid();
							 * Ext.getCmp('gztzForm').loadRecord(irecord); var
							 * attachname=irecord.get('attachname');
							 * Ext.getCmp('filecxButton').setVisible(true);
							 * Ext.getCmp('displayAttachName').setValue(attachname);
							 */
						}
					},
					deleteGrid : function() {
						var grid = this.getGztzGrid();
						var gridStore = this.getGztzGridStore();
						var records = grid.getSelectionModel().getSelection();
						if (records.length > 1) {
							gridStore.getProxy().api.destroy = "admin/xzjx/gztz/gztzDeleteList";
						} else {
							gridStore.getProxy().api.destroy = "admin/xzjx/gztz/gztzDelete";
						}
						Ext.Array.each(records, function(record, index,
								countriesItSelf) {
							gridStore.remove(record);
						});
						gridStore.sync();
					},
					// 网页编辑模式通知保存
					// saveGridolde : function(button) {
					// var ipan= button.up('form');
					// var
					// contForm=window.frames["gztzframe"].document.getElementById("sbutton");
					// //ipan.body.mask('保存中...');
					// contForm.click();
					// var checkFlag =
					// window.frames["gztzframe"].document.getElementById("checkFlag").value;
					// if(checkFlag=="0"){
					// button.setVisible(false);
					// }
					// var store = this.getGztzGridStore();
					// var mProxy = store.getProxy();
					// //清空store
					// store.loadData([],false);
					// store.load();
					// },
					saveGrid : function(button) {
						var win = button.up('window'), form = win.down('form'), record = form
								.getRecord(), values = form.getValues();
						if (record) {// 修改
							record.set(values);
							this.getGztzGridStore().sync();
							// this.getGztzGridStore().loadData([], false);
							win.close();
						} else {
							this.getGztzGridStore().insert(0, values);
							this.getGztzGridStore().sync();
							// this.getGztzGridStore().loadData([], false);
						}
						win.close();
					},
					reloadGrid : function(button) {
						var store = this.getGztzGridStore();
						var mProxy = store.getProxy();
						// 清空store
						store.loadData([], false);
						mProxy.setExtraParam("id", this.catId);
						store.load();
						button.up('window').close();
					}

				});
