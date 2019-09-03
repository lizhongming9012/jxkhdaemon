Ext.define('ManageUser.view.UserGrid', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.usergrid',
	store : 'UserGrid',
	viewConfig : {
		enableTextSelection : true
	},// 可选择
	border : 0,
	dockedItems : [ {
		xtype : 'pagingtoolbar',
		store : 'UserGrid',
		dock : 'bottom',
		displayInfo : true
	} ],
	columns : {
		//defaults : {
		//	resizable : false
		//},
		items : [ {
			text : '序号',
			dataIndex : 'xh',
			width : 50,
			xtype : 'rownumberer'
		}, {
			text : '用户ID',
			dataIndex : 'userId',
			width : 120
		}, {
			text : '登陆账号',
			dataIndex : 'userAccount',
			width : 120
		}, {
			text : '姓名',
			dataIndex : 'userName',
			width : 120
		}, {
			text : '性别',
			dataIndex : 'userSex',
			width : 50,
			renderer : function(value) {
				if (value == '1') {
					return '男';
				} else if (value == '2') {
					return '女';
				}
				return value;
			}
		}, {
			text : '用户描述',
			dataIndex : 'userDesc',
			width : 120
		}, {
			text : '移动电话',
			dataIndex : 'userMobile',
			width : 100,
			renderer : function(value) {
				if (value == 'null') {
					return '';
				}
				return value;
			}
		}, {
			text : '部门',
			dataIndex : 'userDesktop',
			width : 300
		}
		// , {
		// text : '用户属性',
		// dataIndex : 'userType',
		// flex : 1,
		// renderer : function(val, metaData) {
		// if (val == '内') {
		// // metaData.style = 'background-color:#00FF00';
		// val = "内网用户";
		// }
		// if (val == '外') {
		// // metaData.style = 'background-color:#EE0000';
		// val = "外网用户";
		// }
		//
		// return val;
		// }
		// }
		, {
			text : '是否有效',
			dataIndex : 'isValid',
			width : 100,
			renderer : function(val, metaData) {
				if (val == 'Y') {
					metaData.style = 'background-color:#90EE90';
					val = "有效";
				}
				if (val == 'N') {
					metaData.style = 'background-color:#CD2626';
					val = "无效";
				}
				return val;
			}
		} ]
	}
})