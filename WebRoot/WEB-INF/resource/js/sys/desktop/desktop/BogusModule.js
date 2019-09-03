/*!
* Ext JS Library 4.0
* Copyright(c) 2006-2011 Sencha Inc.
* licensing@sencha.com
* http://www.sencha.com/license
*/

var windowIndex = 0;

Ext.define('MyDesktop.BogusModule', {
    extend: 'Ext.ux.desktop.Module',

    init : function(){
        this.launcher = {
            text: 'Window '+(++windowIndex),
            iconCls:'bogus',
            handler : this.createWindow,       
            scope: this,
            contextMenu:null,
            windowId:windowIndex,
            afterRender: function () {
		        var me = this;
		       // me.el.on('contextmenu', me.onModuleItemMenu, me);
		    },
		     onModuleItemMenu: function (e) {
		        var me = this, menu = me.contextMenu;
		        //appme.getDesktop().el.mask();
		        e.stopEvent();
		        menu.showAt(e.getXY());
		        menu.doConstrain();
		    }
        };
        this.launcher.contextMenu = new Ext.menu.Menu(this.createMenuContextMenu());
    },
    customHtml: '',
    wndId:'',
    createWindow : function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus'+this.wndId);
        if(!win){
            win = desktop.createWindow({
                id: 'bogus'+this.wndId,
                title:this.text,
                maximized:true,
//                width:640,
 //               height:480,
  //               width:'100%',
//                 height:desktop.getHeight(),//2015.3.23 liuxuehai update
                html : this.customHtml,
                iconCls: 'bogus',
                animCollapse:false,
                constrainHeader:true
            });
        }
        win.show();
        return win;
    },
   createMenuContextMenu: function () {
        var me = this;
        return {
            defaultAlign: 'br-tr',
            items: [
                { text: '恢复', handler: me.onWindowMenuRestore, scope: me },
                { text: '最小化', handler: me.onWindowMenuMinimize, scope: me },
                { text: '最大化', handler: me.onWindowMenuMaximize, scope: me },
                '-',
                { text: '关闭', handler: me.onWindowMenuClose, scope: me }
            ],
            listeners: {
                beforeshow: me.onWindowMenuBeforeShow,
                hide: me.onWindowMenuHide,
                scope: me
            }
        };
    },
    onWindowMenuHide: function (menu) {
        Ext.defer(function() {
            menu.theWin = null;
        }, 1);
    },
    onWindowMenuBeforeShow: function(obj){
    	
    }	
});