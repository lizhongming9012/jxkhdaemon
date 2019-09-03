Ext.define('ManageMenu.view.AuthorityComboBox', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.authoritycombobox',
	fieldLabel : '选择资源',
	displayField : 'authorityDesc',
	valueField : 'authorityId',
	width : 500,
	labelWidth : 130,
	store : 'AuthorityComboBox',
	queryParam : 'q',
	queryMode : 'remote'
})
