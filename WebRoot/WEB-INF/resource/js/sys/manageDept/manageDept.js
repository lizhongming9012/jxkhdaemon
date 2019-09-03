
Ext.application({
    name: 'ManageDept',
    autoCreateViewport: true,
    models: ['DeptTree'], 
    appFolder:'res/js/sys/manageDept',   
    stores: ['DeptTree'],
    controllers: ['DeptController']
});