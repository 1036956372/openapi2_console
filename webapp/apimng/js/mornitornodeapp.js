Ext.onReady(function(){
	var iphost="";
	var logpath="-1";
	
	   // 申请查询变量
	var qappmsg = new Ext.form.TextField({
			id            : 'id_qappmsg',
			name          : 'appmsg',
			fieldLabel    : '程序描述',
			selectOnFocus : true,
			anchor        : '100%'
		 }); 
	var qhostid = new Ext.form.ComboBox({
 	   	id            : 'id_qhostid',
   		hiddenName : 'hostid', // 往后台传值字段名
   		store : HOSTIPStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		value : '', // 初始选中的列表项
   		fieldLabel : '主机ip',
   		emptyText : '请选择...',
   		//allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '100%'
	 });
	var qappnameid = new Ext.form.ComboBox({
 	   	id            : 'id_qappnameid',
   		hiddenName : 'appnameid', // 往后台传值字段名
   		store : APPNAMEIDStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		value : '', // 初始选中的列表项
   		fieldLabel : '程序名称',
   		emptyText : '请选择...',
   		//allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '100%'
	 });
	var qappstate = new Ext.form.ComboBox({
 	   	id            : 'id_qappstate',
   		hiddenName : 'appstate', // 往后台传值字段名
   		store : APPSTATUSStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		value : '', // 初始选中的列表项
   		fieldLabel : '状态',
   		emptyText : '请选择...',
   		//allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '100%'
	 });
	var qappenv = new Ext.form.ComboBox({
 	   id            : 'id_qappenv',
   		hiddenName : 'appenv', // 往后台传值字段名
   		store : APPENVStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		//value : '', // 初始选中的列表项
   		fieldLabel : '系统所属环境',
   		emptyText : '请选择...',
   		//allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '100%'
	 });
			

				
	var qFormPanel = new Ext.form.FormPanel({
			id : 'id_qmornitorNodeAppFormPanel',
	        name : 'qmornitorNodeAppFormPanel',
			region : 'north',
			title : '<span class="commoncss">查询条件<span>',
			collapsible : true,
			collapsed: true,
			border : true,
			labelWidth : 80, // 标签宽度
			// frame : true, //是否渲染表单面板背景色
			labelAlign : 'right', // 标签对齐方式
			bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
			buttonAlign : 'center',
			height : 130,
			items : [
			{
				layout : 'column',
				border : false,
				items : [
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qappmsg]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qhostid]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qappnameid]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qappstate]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qappenv]
						}
						
				
					]
			}],
			buttons : [{
						text : '查询',
						iconCls : 'previewIcon',
						handler : function() {
							Ext.getCmp('tbi_edit').disable();
							Ext.getCmp('tbi_del').disable();
							iphost="";
							logpath="-1";
							ebtncx_clicked();
							btncx_clicked();
						}
					}, {
						text : '重置',
						iconCls : 'tbar_synchronizeIcon',
						handler : function() {
							qFormPanel.getForm().reset();
						}
					}]
		});
			
	// 申请列表区域
	var qGridStore = new Ext.data.JsonStore({
		url               : './MornitorNodeApp/list.do',
		root              : 'ROOT',
		totalProperty     : 'TOTALCOUNT',
		id                : 'appid',
		fields            : ['appid','appmsg','hostid','port','installpath','logpath','appnameid','appstate','memo','appenv']
	});
	
	
	// 翻页排序时带上查询条件
   qGridStore.on('beforeload', function() {
	      this.baseParams = qFormPanel.getForm().getValues();
   });

	var qGridCheckbox = new Ext.grid.CheckboxSelectionModel();
	var qGridColumns = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		//qGridCheckbox,

		{
			header            : '系统所属环境',
			dataIndex         : 'appenv',
			width             : 140,
			align             : 'center',
			renderer 		  : APPENVRender
		}
		 ,
		{
			header            : '程序描述',
			dataIndex         : 'appmsg',
			width             : 150,
			align             : 'center'
		}
		 ,
		{
			header            : '主机ip',
			dataIndex         : 'hostid',
			width             : 150,
			align             : 'center',
			renderer 		  : HOSTIPRender
		}
		 ,
		{
			header            : '端口',
			dataIndex         : 'port',
			width             : 70,
			align             : 'center'
		}
		 ,
		{
			header            : '部署路径',
			dataIndex         : 'installpath',
			width             : 350,
			align             : 'center'
		}
		 ,
		{
			header            : '日志路径',
			dataIndex         : 'logpath',
			width             : 350,
			align             : 'center'
		}
		 ,
		{
			header            : '程序名称',
			dataIndex         : 'appnameid',
			width             : 140,
			align             : 'center',
			renderer 		  : APPNAMEIDRender
		}
		 ,
		{
			header            : '状态',
			dataIndex         : 'appstate',
			width             : 70,
			align             : 'center',
			renderer 		  : APPSTATUSRender
		}
		 /*,
		{
			header            : 'memo',
			dataIndex         : 'memo',
			width             : 70,
			align             : 'center'
		}*/
		 
				
	]);
	qGridColumns.defaultSortable = true;
				
	// 每页显示条数下拉选择框
	var pagesize_combo = new Ext.form.ComboBox({
				name : 'pagesize',
				triggerAction : 'all',
				mode : 'local',
				store : new Ext.data.ArrayStore({
							fields : ['value', 'text'],
							data : [[10, '10条/页'], [20, '20条/页'],
									[50, '50条/页'], [100, '100条/页'],
									[250, '250条/页'], [500, '500条/页']]
						}),
				valueField : 'value',
				displayField : 'text',
				value : '20',
				editable : false,
				width : 85
			});
	var number = parseInt(pagesize_combo.getValue());
	// 改变每页显示条数reload数据
	pagesize_combo.on("select", function(comboBox) {
				bbar.pageSize = parseInt(comboBox.getValue());
				number = parseInt(comboBox.getValue());
				qGridStore.reload({
							params : {
								start : 0,
								limit : bbar.pageSize
							}
						});
			});

	// 分页工具栏
	var bbar = new Ext.PagingToolbar({
				pageSize : number,
				store : qGridStore,
				displayInfo : true,
				displayMsg : '显示{0}条到{1}条,共{2}条',
				plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
				emptyMsg : "没有符合条件的记录",
				items : ['-', '&nbsp;&nbsp;', pagesize_combo]
			});

			
			
			// 表格工具栏
	var tbar = new Ext.Toolbar({
				items : [{
							text : '新增',
							iconCls : 'addIcon',
							id : 'id_tbi_add',
							handler : function() {
								btnxz_clicked();
							}
						}, {
							text : '修改',
							id : 'tbi_edit',
							iconCls : 'edit1Icon',
							disabled : true,
							handler : function() {
								btnxg_clicked();
							}
						}, {
							text : '删除',
							id : 'tbi_del',
							iconCls : 'deleteIcon',
							disabled : true,
							handler : function() {
								btnsc_clicked();
							}
						}, '->', {
							text : '刷新',
							iconCls : 'arrow_refreshIcon',
							handler : function() {
								qGridStore.reload();
							}
						}]
			});

			
	 var qGridPanel = new Ext.grid.GridPanel({
			// 表格面板标题,默认为粗体，我不喜欢粗体，这里设置样式将其格式为正常字体
			title : '<span class="commoncss">部署程序信息登记表</span>',
			height : 500,
			id : 'id_grid_mornitorNodeApp',
			autoScroll : true,
			frame : true,
			region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
			store : qGridStore, // 数据存储
			stripeRows : true, // 斑马线
			cm : qGridColumns, // 列模型
			tbar : tbar, // 表格工具栏
			bbar : bbar,// 分页工具栏
			viewConfig : {
              // 不产横向生滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
			  // forceFit : true
			},
			loadMask : {
				msg : '正在加载表格数据,请稍等...'
			}
		});
				
			
			// 监听行选中事件
	 qGridPanel.on('rowclick', function(pGrid, rowIndex, event) {
		 var record = qGridPanel.getSelectionModel().getSelected();
		 if (record.data.apiid!=""){
			  iphost = HOSTIPRender(record.data.hostid);
			  logpath = record.data.logpath;
		 };
				Ext.getCmp('tbi_edit').enable();
				Ext.getCmp('tbi_del').enable();
				ebtncx_clicked();
			});

	 qGridPanel.on('rowdblclick', function(grid, rowIndex, event) {
				btnxg_clicked();
			});
			
				
				
				
	// 申请明细窗体各个控件
	   var eappid = new Ext.form.Hidden({
		      id			: 'id_eappid',
		      name		    : 'appid'
	       });
       var eappmsg = new Ext.form.TextField({
	          id            : 'id_eappmsg',
		      name          : 'appmsg',
		      fieldLabel    : '程序描述',
		      allowBlank : false,
		      labelStyle    : micolor,
		      maxLength	    : 20,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 
       var ehostid = new Ext.form.ComboBox({
    	   id            : 'id_ehostid',
      		hiddenName : 'hostid', // 往后台传值字段名
      		store : HOSTIPStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
      								// />标签对应
      		mode : 'local',
      		triggerAction : 'all',
      		valueField : 'value',
      		displayField : 'text',
      		value : '', // 初始选中的列表项
      		fieldLabel : '主机id',
      		emptyText : '请选择...',
      		allowBlank : false,
      		forceSelection : true, // 选中内容必须为下拉列表的子项
      		editable : true, // 选择输入框可编辑
      		typeAhead : true, // 输入的时候自动匹配待选项目
      		labelStyle    : micolor,
      		anchor : '99%'
   	 });        	        
       var eport = new Ext.form.TextField({
	          id            : 'id_eport',
		      name          : 'port',
		      fieldLabel    : '端口',
		      allowBlank : false,
		      labelStyle    : micolor,
		      maxLength	    : 10,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	        	        
       var einstallpath = new Ext.form.TextField({
	          id            : 'id_einstallpath',
		      name          : 'installpath',
		      fieldLabel    : '部署路径',
		      allowBlank : false,
		      labelStyle    : micolor,
		      maxLength	    : 100,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	        	        
       var elogpath = new Ext.form.TextField({
	          id            : 'id_elogpath',
		      name          : 'logpath',
		      fieldLabel    : '日志路径',
		      allowBlank : false,
		      labelStyle    : micolor,
		      maxLength	    : 100,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	 
       var eappnameid = new Ext.form.ComboBox({
    	   id            : 'id_eappnameid',
      		hiddenName : 'appnameid', // 往后台传值字段名
      		store : APPNAMEIDStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
      								// />标签对应
      		mode : 'local',
      		triggerAction : 'all',
      		valueField : 'value',
      		displayField : 'text',
      		value : '', // 初始选中的列表项
      		fieldLabel : '程序名称',
      		emptyText : '请选择...',
      		allowBlank : false,
      		forceSelection : true, // 选中内容必须为下拉列表的子项
      		editable : true, // 选择输入框可编辑
      		typeAhead : true, // 输入的时候自动匹配待选项目
      		labelStyle    : micolor,
      		anchor : '99%'
   	 });
       var eappenv = new Ext.form.ComboBox({
    	   id            : 'id_eappenv',
      		hiddenName : 'appenv', // 往后台传值字段名
      		store : APPENVStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
      								// />标签对应
      		mode : 'local',
      		triggerAction : 'all',
      		valueField : 'value',
      		displayField : 'text',
      		value : '', // 初始选中的列表项
      		fieldLabel : '系统所属环境',
      		emptyText : '请选择...',
      		allowBlank : false,
      		forceSelection : true, // 选中内容必须为下拉列表的子项
      		editable : true, // 选择输入框可编辑
      		typeAhead : true, // 输入的时候自动匹配待选项目
      		labelStyle    : micolor,
      		anchor : '99%'
   	 });
       var eappstate = new Ext.form.ComboBox({
    	   id            : 'id_eappstate',
      		hiddenName : 'appstate', // 往后台传值字段名
      		store : APPSTATUSStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
      								// />标签对应
      		mode : 'local',
      		triggerAction : 'all',
      		valueField : 'value',
      		displayField : 'text',
      		value : '', // 初始选中的列表项
      		fieldLabel : '状态',
      		emptyText : '请选择...',
      		allowBlank : false,
      		forceSelection : true, // 选中内容必须为下拉列表的子项
      		editable : true, // 选择输入框可编辑
      		typeAhead : true, // 输入的时候自动匹配待选项目
      		labelStyle    : micolor,
      		anchor : '99%'
   	 }); 	        	        
       var ememo = new Ext.form.TextField({
	          id            : 'id_ememo',
		      name          : 'memo',
		      fieldLabel    : 'memo',
		      allowBlank	: true,
		      labelStyle    : micolor,
		      maxLength	    : 100,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	        	        
		
				

	var eFormPanel = new Ext.form.FormPanel({
	    id : 'id_emornitorNodeAppFormPanel',
	    name : 'emornitorNodeAppFormPanel',
	    defaultType : 'textfield',
	    labelAlign : 'right',
	    labelWidth : 80,
	    frame : false,
	    bodyStyle : 'padding:5 5 0',
		items         : [
				eappid
				,
				eappmsg
				,
				ehostid
				,
				eport
				,
				einstallpath
				,
				elogpath
				,
				eappnameid
				,
				eappenv
				,
				eappstate
				,
				ememo
				
		]
	});
				
		// 申请明细窗体
	var eWindow = new Ext.Window({
		layout : 'fit',
		width : 400,
		height : 315,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		modal : true,
		title : '<span class="commoncss">窗口标题</span>',
		// iconCls : 'page_addIcon',
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [eFormPanel],
		buttons : [{
			text : '保存',
			iconCls : 'acceptIcon',
			handler : function() {
				if (runMode == '0') {
					Ext.Msg.alert('提示',
							'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
					return;
				}
				btnbc_clicked()
			}
		}, {
			text : '重置',
			id : 'btnReset',
			iconCls : 'tbar_synchronizeIcon',
			handler : function() {
				clearForm(eFormPanel.getForm());
			}
		}, {
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				eWindow.hide();
			}
		}]
	});
				
	var dFormPanel = new Ext.form.FormPanel({
		id : 'id_dmornitorNodeAppFormPanel',
        name : 'dmornitorNodeAppFormPanel',
		region : 'east',
		title : '<span class="commoncss">日志结果<span>',
		collapsible : true,
		//collapsed: true,
		border : true,
		labelWidth : 50, // 标签宽度
		// frame : true, //是否渲染表单面板背景色
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:3 20 0', // 表单元素和表单面板的边距
		buttonAlign : 'center',
		width : 550,//120
		items : [ ]
	});		
//*****************************************************************日志返回结果start**************************************************************//		
	// 申请列表区域
	var eGridStore = new Ext.data.JsonStore({
		url               : './MornitorNodeApp/listLogFile.do',
		root              : 'ROOT',
		totalProperty     : 'TOTALCOUNT',
		//id                : 'filedsid',
		fields            : ['isfile','filesize','filename','fttppath','lastModifiedDate']
	});
	
	
	// 翻页排序时带上查询条件
   eGridStore.on('beforeload', function() {
	      //this.baseParams = qFormPanel.getForm().getValues();
	   this.baseParams.iphost = iphost;
	   this.baseParams.logpath = logpath;
   });

	var eGridCheckbox = new Ext.grid.CheckboxSelectionModel();
	var eGridColumns = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		{
			header : '下载',
			dataIndex : 'download',
			renderer : function(value, cellmeta, record) {
					if (record.data['isfile'] == 'false') {
						return null;
					}
				return '<a href="javascript:void(0);"><img src="./resource/image/ext/edit1.png"/></a>';
			},
			width : 35
		}
		,
		//eGridCheckbox,
		{
			header            : '文件名',
			dataIndex         : 'filename',
			width             : 150,
			align             : 'center'
		}
		 ,
		{
			header            : '文件大小',
			dataIndex         : 'filesize',
			width             : 80,
			align             : 'center'
		}
		 ,
		{
			header            : '更新时间',
			dataIndex         : 'lastModifiedDate',
			width             : 130,
			//hidden 			  : true,
			align             : 'center'
		}
		 ,
		{
			header            : '文件地址',
			dataIndex         : 'fttppath',
			width             : 100,
			hidden 			  : true,
			align             : 'center'
		}
		 ,
		 {
				header : '操作',
				dataIndex : 'isfile',
				renderer : function(value,cellmeta,record) {
					
						if (value == 'false') {
							return '<a href="javascript:void(0);">进入</a>';
						}
					
					return '<a href="javascript:void(0);">查看</a>';
				},
				width : 35
			}
		 
				
	]);
	eGridColumns.defaultSortable = true;
				
	// 每页显示条数下拉选择框
	var epagesize_combo = new Ext.form.ComboBox({
				name : 'pagesize',
				triggerAction : 'all',
				mode : 'local',
				store : new Ext.data.ArrayStore({
							fields : ['value', 'text'],
							data : [[10, '10条/页'], [20, '20条/页'],
									[50, '50条/页'], [100, '100条/页'],
									[250, '250条/页'], [500, '500条/页']]
						}),
				valueField : 'value',
				displayField : 'text',
				value : '20',
				editable : false,
				width : 85
			});
	
	var enumber = parseInt(epagesize_combo.getValue());
	// 改变每页显示条数reload数据
	epagesize_combo.on("select", function(comboBox) {
				ebbar.pageSize = parseInt(comboBox.getValue());
				enumber = parseInt(comboBox.getValue());
				eGridStore.reload({
							params : {
								start : 0,
								limit : ebbar.pageSize
							}
						});
			});

	// 分页工具栏
	var ebbar = new Ext.PagingToolbar({
				pageSize : enumber,
				store : eGridStore,
				displayInfo : true,
				displayMsg : '显示{0}条到{1}条,共{2}条',
				plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
				emptyMsg : "没有符合条件的记录",
				items : ['-', '&nbsp;&nbsp;', epagesize_combo]
			});

			
			
			// 表格工具栏
	var etbar = new Ext.Toolbar({
				items : [ '->', {
							text : '刷新',
							iconCls : 'arrow_refreshIcon',
							handler : function() {
								//apikey = "";
								//alert(apikey);
								eGridStore.reload();
							}
						}]
			});

			
	 var eGridPanel = new Ext.grid.GridPanel({
			// 表格面板标题,默认为粗体，我不喜欢粗体，这里设置样式将其格式为正常字体
			title : '<span class="commoncss">日志信息</span>',
			height : 500,
			width : 500,
			id : 'id_grid_eapiBasicFields',
			autoScroll : true,
			frame : true,
			region : 'east', // 和VIEWPORT布局模型对应，充当center区域布局
			store : eGridStore, // 数据存储
			stripeRows : true, // 斑马线
			cm : eGridColumns, // 列模型
			tbar : etbar, // 表格工具栏
			bbar : ebbar,// 分页工具栏
			viewConfig : {
              // 不产横向生滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
			  // forceFit : true
			},
			loadMask : {
				msg : '正在加载表格数据,请稍等...'
			}
		});
	 eGridPanel.on("cellclick", function(grid, rowIndex, columnIndex, e) {
		    var store = grid.getStore();
			var record = store.getAt(rowIndex);
			var fieldName = grid.getColumnModel().getDataIndex(columnIndex);
			if (fieldName == 'isfile' ) {
				//console.log(record.get('isfile'));
				var fttppath = record.get('fttppath');
				if('false'==record.get('isfile')){
					
					
					
					eGridStore.removeAll();
					eGridStore.baseParams.fttppath=fttppath;
					eGridStore.reload({
								params : {
									
									'start' : 0,
									'limit' : ebbar.pageSize
								}
							});
				}else if('true'==record.get('isfile')){
					window.open('./MornitorNodeApp/tailfile.do?fttppath='+encodeURIComponent(encodeURIComponent(fttppath)));  
					/*Ext.Ajax.request({
						url : './MornitorNodeApp/tailfile.do',
						success : function(response) {
							var resultArray = Ext.util.JSON
									.decode(response.responseText);
							
							//Ext.g/etCmp('apirequest').setValue(resultArray.link);
						},
						failure : function(response) {
							var resultArray = Ext.util.JSON
									.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
						},
						params : {
							'fttppath':fttppath
						}
					})*/
				}
				
			}  
			
			if (fieldName == 'download') {
				if('true'==record.get('isfile')){
				    
				    showWaitMsg();
				    (function(){
						    // do something 1
						     //showWaitMsg();
						    (function(){ // 写在里面
						        // do something2
						        hideWaitMsg();
						    }).defer(1000);
						}).defer(1000);


					var fttppath = record.get("fttppath");
					// 通过iFrame实现类ajax文件下载
					// 这个很重要
					//window.open('./MornitorNodeApp/downfile.do?fttppath='+fttppath); 
					var downloadIframe = document.createElement('iframe');
					downloadIframe.src = './MornitorNodeApp/downfile.do?fttppath='
							+ encodeURIComponent(encodeURIComponent(fttppath));
					downloadIframe.style.display = "none";
					document.body.appendChild(downloadIframe);
					
				}				
			}
		});	
	//***********************************************************日志返回结果end**********************************************************//
	// 布局
	// 如果把form作为center区域的话,其Height属性将失效。
	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [qFormPanel, qGridPanel,eGridPanel]
			});	
				
		
	/**
	 * 日志查询
	 */
	function ebtncx_clicked(){
		eGridStore.removeAll();
		eGridStore.baseParams = {
	  			'logpath'			: logpath,
	  			'iphost'			: escape(iphost)
	 		};
		eGridStore.load({
					params : {
						'start' : 0,
						'limit' : ebbar.pageSize
					}
				});
	}
	
	/**
	 * 查询列表
	 */
	//ebtncx_clicked();
	function btncx_clicked(){
		var params = qFormPanel.getForm().getValues();
		params.start = 0;
		params.limit = bbar.pageSize;
		qGridStore.load({
					params : params
				});
	}
	
	btncx_clicked();
	
	// 保存
	function btnbc_clicked(){
			  if (!eFormPanel.getForm().isValid())
			     return;
			     
			  	var execmethod = '';
			    if(eappid.getValue() == ''){
					execmethod = 'save.do';
				}else{
					execmethod = 'update.do';
				};
			  	
			  	eFormPanel.form.submit({
					url : './MornitorNodeApp/'+execmethod,
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) { // 回调函数有2个参数
						eWindow.hide();
						qGridStore.reload();
						form.reset();
						Ext.MessageBox.alert('提示', action.result.msg);
					},
					failure : function(form, action) {
						Ext.Msg
								.alert('提示', '操作部署程序信息登记表信息失败! <BR>请确认您已经登录, 且网络保持畅通!'
												+ action.result.msg);
					}
				});
		　}
						
			
	// 新增界面
	function btnxz_clicked(){
		eWindow.setTitle('新增部署程序信息登记表信息');
			eappid.setValue('');
			eappmsg.setValue('');
			ehostid.setValue('');
			eport.setValue('');
			einstallpath.setValue('');
			elogpath.setValue('');
			eappnameid.setValue('');
			eappstate.setValue('');
			ememo.setValue('');
			eappenv.setValue('');
		eWindow.show();
				
	}
			
	// 修改界面
	function btnxg_clicked(){
		var record = qGridPanel.getSelectionModel().getSelected();
		if(!record){
			Ext.MessageBox.alert('提示', '请选择要修改的部署程序信息登记表信息');
		}
		else{
			eWindow.setTitle('修改部署程序信息登记表信息');
			eFormPanel.getForm().loadRecord(record);
			eWindow.show();
		};
	};

				
	// 删除操作
	function btnsc_clicked() {
		var rows = qGridPanel.getSelectionModel().getSelections();
		if (Ext.isEmpty(rows)) {
			Ext.Msg.alert('提示', '请先选中要删除的项目!');
			return;
		}
		var strChecked = jsArray2JsString(rows, 'appid');
		Ext.Msg.confirm(
						'请确认',
						'<span style="color:red"><b>提示:</b>删除部署程序信息登记表信息,请慎重.</span><br>继续删除吗?',
						function(btn, text) {
							if (btn == 'yes') {
								if (runMode == '0') {
									Ext.Msg
											.alert('提示',
													'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
									return;
								}
								showWaitMsg();
								Ext.Ajax.request({
									url : './MornitorNodeApp/delete.do',
									success : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										iphost="";
										logpath="-1";
										//ebtncx_clicked();
										qGridStore.reload();
										Ext.Msg.alert('提示', resultArray.msg);
									},
									failure : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										Ext.Msg.alert('提示', resultArray.msg);
									},
									params : {
										strChecked : strChecked
									}
								});
							}
						});
	}
			
});


