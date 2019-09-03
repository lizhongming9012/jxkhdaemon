
Ext.application({
    name: 'ManageAuthority',
    autoCreateViewport: true,
    models: ['ModuleTree','AuthorityGrid'], 
    appFolder:'res/js/sys/manageAuthority',   
    stores: ['ModuleTree','AuthorityGrid','ResourceComboBox'],
    controllers: ['AuthorityController']
});