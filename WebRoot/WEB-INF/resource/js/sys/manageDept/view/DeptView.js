Ext.define('ManageDept.view.DeptView', {
	extend : 'Ext.window.Window',
	alias : 'widget.deptview',
	requires : [ 'Ext.form.Panel' ],
	title : '部门信息',
	layout : 'fit',
	width : 480,

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			padding : '5 5 0 5',
			border : false,
			style : 'background-color: #fff;',
			items : [ {
				xtype : 'textfield',
				name : 'deptId',
				itemId : 'deptId',// 为edit窗口指定是否为可编辑选项设定
				width : '80%',
				allowBlank : false,
				maxLength : 11,
				minLength : 11,
				msgTarget : 'side',
				invalidText : '部门编号必须为11位',
				fieldLabel : '部门编号'
			}, {
				xtype : 'textfield',
				name : 'deptName',
				itemId : 'deptName',
				width : '80%',
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '部门名称'
			}, {
				xtype : 'textfield',
				name : 'deptDesc',
				itemId : 'deptDesc',
				width : '80%',
				fieldLabel : '部门描述'
			}
			// , {
			// xtype : 'textfield',
			// name : 'deptstandalone',
			// width : '80%',
			// allowBlank : false,
			// itemId : 'deptstandalone',
			// msgTarget : 'side',
			// fieldLabel : '是否独立部门'
			// }
			]
		} ];

		this.callParent(arguments);
	}
});
