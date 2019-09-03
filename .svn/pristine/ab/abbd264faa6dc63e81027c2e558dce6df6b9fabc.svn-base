Ext.define('ManageDept.view.DeptEdit', {
	extend : 'Ext.window.Window',
	alias : 'widget.deptedit',
	requires : [ 'Ext.form.Panel', 'ManageDept.model.DeptTree' ],
	stores : [ 'DeptTree' ],
	title : '部门维护',
	layout : 'fit',
	width : 480,

	initComponent : function() {
		this.items = [ {
			xtype : 'form',
			padding : '5 5 0 5',
			border : true,
			region : 'center',// 此属性待定
			style : 'background-color: #fff;',
			items : [ {
				xtype : 'textfield',
				name : 'deptId',
				itemId : 'deptId',// 为edit窗口指定是否为可编辑选项设定
				width : '80%',
				min : 11,
				max : 11,
				allowBlank : false,
				// validator: function(value){
				// var valueInt=parseInt(value);
				// if(valueInt>13706000000&&valueInt<13706999999)
				// {
				// return true;
				// }
				// else
				// {
				// return false;
				// }

				// },//对录入数据进行校验。存在一个问题，如何在失去焦点后进行校验？
				msgTarget : 'side',
				invalidText : '部门编号必须为11位',
				fieldLabel : '部门编号'
			}, {
				xtype : 'textfield',
				name : 'deptName',
				width : '80%',
				allowBlank : false,
				msgTarget : 'side',
				fieldLabel : '部门名称'
			}, {
				xtype : 'textfield',
				name : 'deptDesc',
				width : '80%',
				fieldLabel : '部门描述'
			},
			// {
			// xtype : 'fieldcontainer',
			// fieldLabel : '是否独立部门',
			// width : '80%',
			// defaultType: 'radiofield',
			// items: [
			// {
			// boxLabel : '是',
			// name : 'deptstandalone',
			// width : '80%',
			// inputValue: 'Y',
			// id : 'radio1'
			// }, {
			// boxLabel : '否',
			// width : '80%',
			// name : 'deptstandalone',
			// inputValue: 'N',
			// id : 'radio2'
			// }
			// ]
			// },
			{
				xtype : 'textfield',
				name : 'deptsort',
				width : '80%',
				fieldLabel : '部门序号'
			} ]
		} ];

		this.buttons = [ {
			text : '保存',
			action : 'save'
		}, {
			text : '取消',
			scope : this,
			handler : this.close
		} ];

		this.callParent(arguments);
	}
});
