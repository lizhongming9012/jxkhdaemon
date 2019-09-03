/*
 * ! Ext JS Library 4.0 Copyright(c) 2006-2011 Sencha Inc. licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('MyDesktop.App', {
	extend : 'Ext.ux.desktop.App',

	requires : ['Ext.window.MessageBox', 'Sys.store.SysTree',
			'Ext.ux.desktop.ShortcutModel', //'MyDesktop.SystemStatus',
			//'MyDesktop.VideoWindow', 'MyDesktop.GridWindow',
			//'MyDesktop.TabWindow', 'MyDesktop.AccordionWindow',
			//'MyDesktop.Notepad', 
			'MyDesktop.BogusMenuModule',
			'MyDesktop.BogusModule',
			// 'MyDesktop.Blockalanche',
			'MyDesktop.Settings'],

	init : function() {
		// custom logic before getXYZ methods get called...
		appme = this;

		this.callParent();

		// now ready...
	},
	leafModuleArray : [],
	genBogusMenu : function gen(node) {// 递归根据树生成功能模块的launcher
		var iArray = [];
		node.eachChild(function(child) {
			var title = child.get('text');
			var imenu;
			if (child.isLeaf()) {
				imenu = new MyDesktop.BogusModule();
				imenu.app = appme;
				imenu.wndId = child.get('id');
				imenu.text = child.get('text');
				imenu.id = 'bogus' + child.get('id');
				imenu.customHtml = '<iframe  id="frame'
						+ child.get("id")
						+ '" src="'
						+ child.get("href")
						+ '" width=100% height="100%" frameBorder="0"></iframe>';
				appme.leafModuleArray.push(imenu);
			} else {
				imenu = new MyDesktop.BogusMenuModule();
				var childArray = gen(child);
				for (var i = 0; i < childArray.length; i++) {
					imenu.launcher.menu.items.push(childArray[i]);
				}
			}
			imenu.launcher.text = title;
			iArray.push(imenu.launcher);
		});
		return iArray;
	},
	getLeafModule : function(name) {// 根据ID获取bogusModule (对应开始菜单的叶子节点)20140509
		var ms = this.leafModuleArray;
		for (var i = 0, len = ms.length; i < len; i++) {
			var m = ms[i];
			if (m.id == name || m.appType == name) {

				return m;
			}
		}
		return null;
	},
	getModules : function() {
		var me = this;
		var genArray = [];
		var moduleArray = [];

		moduleArray = this.genBogusMenu(treeStore.getRootNode());
		Ext.each(moduleArray, function(obj) {// 重新包装首层为对象
					var bogusMenu;
					if (obj.menu) {
						bogusMenu = new MyDesktop.BogusMenuModule();

					} else {
						bogusMenu = new MyDesktop.BogusModule();
					}
					bogusMenu.launcher = obj;
					genArray.push(bogusMenu);
				});
		return genArray;
	},

	getDesktopConfig : function() {
		var me = this, ret = me.callParent();

		return Ext.apply(ret, {
			// cls: 'ux-desktop-black',

			contextMenuItems : [{
						text : '背景设置',
						handler : me.onSettings,
						scope : me
					}],

			shortcuts : Ext.create('Ext.data.Store', {
						model : 'Ext.ux.desktop.ShortcutModel',
						url : 'admin/desktop/shortcuts',
						proxy : {
							type : 'ajax',
							url : 'admin/desktop/shortcuts',
							reader : {
								type : 'json',
								root : 'shortcuts'
							},
							extraParams : {
								'userAccount' : userAccount
							}
						},
						autoLoad : true
					}),

			//wallpaper : 'res/js/sys/desktop/desktop/wallpapers/Blue-Sencha.jpg',//默认桌面
			wallpaper : 'res/wallpaper/haiyi/images/bg.jpg',//默认桌面
			wallpaperStretch : false
		});
	},

	// config for the start menu
	getStartConfig : function() {
		var me = this, ret = me.callParent();

		return Ext.apply(ret, {
					title : userName,
					iconCls : 'user',
					height : 300,
					toolConfig : {
						width : 100,
						items : [{
									text : '设置',
									iconCls : 'settings',
									handler : me.onSettings,
									scope : me
								}, '-', {
									text : '修改密码',
									iconCls : 'settings',
									handler : me.onChangePwd,
									scope : me
								}, '-', {
									text : '退出',
									iconCls : 'logout',
									handler : me.onLogout,
									scope : me
								}]
					}
				});
	},

	getTaskbarConfig : function() {
		var ret = this.callParent();

		return Ext.apply(ret, {
					quickStart : [{
								name : 'Accordion Window',
								iconCls : 'accordion',
								module : 'acc-win'
							}, {
								name : 'Grid Window',
								iconCls : 'icon-grid',
								module : 'grid-win'
							}],
					trayItems : [{
								xtype : 'label',
								text : '在线人数:' + ols
							},
							/*{
								xtype : 'label',
								text : deptName
							}, {
								xtype : 'label',
								text : userName
							}, */
							{
								xtype : 'trayclock',
								flex : 1
							}]
				});
	},

	onLogout : function() {
	logout();
	},

	onChangePwd : function() {
		changePwd();
	},
	onSettings : function() {
		var dlg = new MyDesktop.Settings({
					desktop : this.desktop
				});
		dlg.show();
	}
});
