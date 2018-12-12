Ext.onReady(function(){
	var apikey = "-9";
	
	var apilbStore = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
					url : './ApiCategory/list.do'
				}),
		reader : new Ext.data.JsonReader({
			                totalProperty : 'TOTALCOUNT',
			                root : 'ROOT'
			            }, [{
			            	name : 'apicategoryid'
			            },{
			            	name : 'apicategoryname'
			            },{
			            	name : 'apisystemid'
			            }]),
		baseParams : {
			apicategoryid : ''
		}
	});
	apilbStore.load();
	var eapilbStore = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
					url : './ApiCategory/list.do'
				}),
		reader : new Ext.data.JsonReader({
			                totalProperty : 'TOTALCOUNT',
			                root : 'ROOT'
			            }, [{
			            	name : 'apicategoryid'
			            },{
			            	name : 'apicategoryname'
			            },{
			            	name : 'apisystemid'
			            }]),
		baseParams : {
			apicategoryid : ''
		}
	});
	eapilbStore.load();
	
	var qapilbStore = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
					url : './ApiCategory/list.do'
				}),
		reader : new Ext.data.JsonReader({
			                totalProperty : 'TOTALCOUNT',
			                root : 'ROOT'
			            }, [{
			            	name : 'apicategoryid'
			            },{
			            	name : 'apicategoryname'
			            },{
			            	name : 'apisystemid'
			            }]),
		baseParams : {
			apicategoryid : ''
		},
		listeners:{  
	        load:function(store,records){  
	            for(var i=0;i<records.length;i++){  
	                records[i].set('apicategory','('+OPENAPIRender(records[i].get('apisystemid'))+')'+records[i].get('apicategoryname'));  
	            }  
	        }  
	    }  
	});
	qapilbStore.load();
	   // 申请查询变量
	var qapiname = new Ext.form.TextField({
			id            : 'id_qapiname',
			name          : 'apiname',
			fieldLabel    : 'Api中文名称',
			selectOnFocus : true,
			anchor        : '100%'
		 }); 
	var qapimethod = new Ext.form.TextField({
			id            : 'id_qapimethod',
			name          : 'apimethod',
			fieldLabel    : 'Api方法名',
			selectOnFocus : true,
			anchor        : '100%'
		 }); 
	var qapicategory = new Ext.form.ComboBox({
		id            : 'id_qapicategory',
		hiddenName          : 'apicategory',
		fieldLabel    : 'Api类目',
		//labelStyle    : micolor,
	    store : qapilbStore, // 引
		mode : 'remote',
		triggerAction : 'all',
		valueField : 'apicategoryid',
		displayField : 'apicategory',
		value : '', // 初始选中的列表项
		emptyText : '请选择...',
		allowBlank : true,
		forceSelection : true,
		typeAhead : true,
		pageSize : 10,
		minListWidth : 270,
		resizable : true,
		editable : false,
		anchor : '100%'
	 }); 
	/*var qapisystem = new Ext.form.ComboBox({
		id            : 'id_qapisystem',
		hiddenName : 'apisystem', // 往后台传值字段名
		store : OPENAPIStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
								// />标签对应
		mode : 'local',
		triggerAction : 'all',
		valueField : 'value',
		displayField : 'text',
		value : '', // 初始选中的列表项
		fieldLabel : 'Api所属系统',
		emptyText : '请选择...',
		//allowBlank : false,
		forceSelection : true, // 选中内容必须为下拉列表的子项
		editable : true, // 选择输入框可编辑
		typeAhead : true, // 输入的时候自动匹配待选项目
		anchor : '100%'
	});*/
	var qhttpmethod = new Ext.form.ComboBox({
   		id            : 'id_qhttpmethod',
   		hiddenName : 'httpmethod', // 往后台传值字段名
   		store : HTTPMETHODStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		value : '', // 初始选中的列表项
   		fieldLabel : '提交方式',
   		emptyText : '请选择...',
   		//allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '99%'
   	});
	var qappkey = new Ext.form.TextField({
			id            : 'id_qappkey',
			name          : 'appkey',
			fieldLabel    : 'appkey',
			selectOnFocus : true,
			anchor        : '100%'
		 }); 
	var qappsecret = new Ext.form.TextField({
			id            : 'id_qappsecret',
			name          : 'appsecret',
			fieldLabel    : 'appsecret',
			selectOnFocus : true,
			anchor        : '100%'
		 }); 
	var qreturnformat = new Ext.form.ComboBox({
   		id            : 'id_qreturnformat',
   		hiddenName : 'returnformat', // 往后台传值字段名
   		store : RETURNFORMATStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		value : '', // 初始选中的列表项
   		fieldLabel : '返回数据格式',
   		emptyText : '请选择...',
   		//allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '99%'
   	});
			
				
	var qFormPanel = new Ext.form.FormPanel({
			id : 'id_qapiBasicFormPanel',
	        name : 'qapiBasicFormPanel',
			region : 'north',
			title : '<span class="commoncss">查询条件<span>',
			collapsible : true,
			collapsed: true,
			border : true,
			labelWidth : 50, // 标签宽度
			// frame : true, //是否渲染表单面板背景色
			labelAlign : 'right', // 标签对齐方式
			bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
			buttonAlign : 'center',
			height : 147,//120
			items : [{
				layout : 'column',
				border : false,
				items  :[{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qapiname]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qapimethod]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qapicategory]
						}
						]
				},
			{
				layout : 'column',
				border : false,
				items : [{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qreturnformat]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qhttpmethod]
						}
						,
						{
							columnWidth   : .33,
							layout        : 'form',
							labelWidth    : 80, // 标签宽度
							defaultType   : 'textfield',
							border        : false,
							items         : [qappkey]
						}
						
						
				
					]
			},{
				layout : 'column',
				border : false,
				items  :[{
					columnWidth   : .33,
					layout        : 'form',
					labelWidth    : 80, // 标签宽度
					defaultType   : 'textfield',
					border        : false,
					items         : [qappsecret]
				}]
				}],
			buttons : [{
						text : '查询',
						iconCls : 'previewIcon',
						handler : function() {
							Ext.getCmp('tbi_edit').disable();
							Ext.getCmp('tbi_del').disable();
							apikey = "-9";
							btncx_clicked();
							ebtncx_clicked();
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
		url               : './ApiBasic/list.do',
		root              : 'ROOT',
		totalProperty     : 'TOTALCOUNT',
		id                : 'apiid',
		fields            : ['apiid','apiname','apimethod','apicategory','apisystem','httpmethod','appkey','appsecret','returnformat','sortno','apinamemethod'],
		listeners:{  
	        load:function(store,records){  
	            for(var i=0;i<records.length;i++){  
	                records[i].set('apinamemethod',records[i].get('apimethod')+'('+records[i].get('apiname')+')');  
	            }  
	        }  
	    } 
	});
	
	
	// 翻页排序时带上查询条件
   qGridStore.on('beforeload', function() {
	      this.baseParams = qFormPanel.getForm().getValues();
   });
   getOneDisplay = function(value, meta, record) {
	    var rowIndex = apilbStore.find("apicategoryid", "" + value);
	    if (rowIndex < 0)
	    return '';
	    var record = apilbStore.getAt(rowIndex);
	    return record ? record.get('apicategoryname')+'('+OPENAPIRender(record.get('apisystemid'))+')' : '';
	}
	var qGridCheckbox = new Ext.grid.CheckboxSelectionModel();
	var qGridColumns = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		//qGridCheckbox,
		/*{
			header            : 'Api主键',
			dataIndex         : 'apiid',
			width             : 100,
			align             : 'center'
		}
		,*/
		{
			header            : 'Api中文名称',
			dataIndex         : 'apiname',
			width             : 200,
			align             : 'center'
		}
		 ,
		{
			header            : 'Api方法名',
			dataIndex         : 'apimethod',
			width             : 270,
			align             : 'center'
		}
		 ,
		{
			header            : 'Api类目',
			dataIndex         : 'apinamemethod',
			width             : 270,
			align             : 'center'
			/*editor			  :{
				xtype	: 'combo',
				valueField : 'value',
		   		displayField : 'text',
		   		store	: apilbStore
			},
			renderer 		  : getOneDisplay*/
			
		}
		 /*,
		{
			header            : 'Api所属系统',
			dataIndex         : 'apisystem',
			width             : 240,
			align             : 'center',
			renderer 		  : OPENAPIRender
		}*/
		 ,
		{
			header            : '提交方式 ',
			dataIndex         : 'httpmethod',
			width             : 80,
			align             : 'center',
			renderer 		  : HTTPMETHODRender
		}
		 ,
		{
			header            : 'APPkey',
			dataIndex         : 'appkey',
			width             : 270,
			align             : 'center'
		}
		 ,
		{
			header            : 'APPsecret',
			dataIndex         : 'appsecret',
			width             : 100,
			align             : 'center'
		}
		 ,
		{
			header            : '返回数据格式 ',
			dataIndex         : 'returnformat',
			width             : 100,
			align             : 'center',
			renderer			  : RETURNFORMATRender
		},
		{
			header            : '排序',
			dataIndex         : 'sortno',
			width             : 100,
			align             : 'center'
		}
		 
				
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
			title : '<span class="commoncss">Api命令基本信息</span>',
			height : 500,
			id : 'id_grid_apiBasic',
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
			 apikey = record.data.apiid;
		 };
				Ext.getCmp('tbi_edit').enable();
				Ext.getCmp('tbi_del').enable();
				Ext.getCmp('etbi_edit').disable();
				Ext.getCmp('etbi_del').disable();
				ebtncx_clicked();
			});

	 qGridPanel.on('rowdblclick', function(grid, rowIndex, event) {
		 var record = qGridPanel.getSelectionModel().getSelected();
		 if (record.data.apiid!=""){
			 apikey = record.data.apiid;
		 };
				btnxg_clicked();
				//ebtncx_clicked();
			});
			
				
				
	// 申请明细窗体各个控件
	   var eapiid = new Ext.form.Hidden({
		      id			: 'id_eapiid',
		      name		    : 'apiid'
	       });
       var eapiname = new Ext.form.TextField({
	          id            : 'id_eapiname',
		      name          : 'apiname',
		      fieldLabel    : 'Api中文名称',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 50,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	        	        
       var eapimethod = new Ext.form.TextField({
	          id            : 'id_eapimethod',
		      name          : 'apimethod',
		      fieldLabel    : 'Api方法名',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 100,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	 
      
       var eapisystem = new Ext.form.ComboBox({
			id            : 'id_eapisystem',
			hiddenName : 'apisystem', // 往后台传值字段名
			store : OPENAPIStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
									// />标签对应
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '', // 初始选中的列表项
			fieldLabel : 'Api所属系统',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true, // 选中内容必须为下拉列表的子项
			editable : true, // 选择输入框可编辑
			typeAhead : true, // 输入的时候自动匹配待选项目
			labelStyle    : micolor,
			anchor : '99%'
		});   
       eapisystem.on('select', function() {
    	   
    	   eapicategory.reset();
    	   eapicategory.setValue('');
			var value = eapisystem.getValue();
			//alert(value);
			eapilbStore.baseParams = {
		  			'apisystemid'			: escape(value)
		 		};
			eapilbStore.load();
		});
       
       var eapicategory = new Ext.form.ComboBox({
      		id            : 'id_eapicategory',
      		hiddenName          : 'apicategory',
      		fieldLabel    : 'Api类目',
      		labelStyle    : micolor,
      	    store : eapilbStore, // 引
      		mode : 'remote',
      		triggerAction : 'all',
      		valueField : 'apicategoryid',
      		displayField : 'apicategoryname',
      		value : '', // 初始选中的列表项
      		emptyText : '请选择...',
      		allowBlank : false,
      		forceSelection : true,
      		typeAhead : true,
      		pageSize : 10,
      		minListWidth : 270,
      		resizable : true,
      		editable : false,
      		anchor : '99%'
      	 }); 
       var ehttpmethod = new Ext.form.ComboBox({
		   		id            : 'id_ehttpmethod',
		   		hiddenName : 'httpmethod', // 往后台传值字段名
		   		store : HTTPMETHODStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
		   								// />标签对应
		   		mode : 'local',
		   		triggerAction : 'all',
		   		valueField : 'value',
		   		displayField : 'text',
		   		value : '', // 初始选中的列表项
		   		fieldLabel : '提交方式',
		   		emptyText : '请选择...',
		   		allowBlank : false,
		   		forceSelection : true, // 选中内容必须为下拉列表的子项
		   		editable : true, // 选择输入框可编辑
		   		typeAhead : true, // 输入的时候自动匹配待选项目
		   		labelStyle    : micolor,
		   		anchor : '99%'
		   	});
       var eappkey = new Ext.form.TextField({
	          id            : 'id_eappkey',
		      name          : 'appkey',
		      fieldLabel    : 'APPkey',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 32,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	        	        
       var eappsecret = new Ext.form.TextField({
	          id            : 'id_eappsecret',
		      name          : 'appsecret',
		      fieldLabel    : 'APPsecret',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 24,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	        	        
       var ereturnformat = new Ext.form.ComboBox({
	   		id            : 'id_ereturnformat',
	   		hiddenName : 'returnformat', // 往后台传值字段名
	   		store : RETURNFORMATStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
	   								// />标签对应
	   		mode : 'local',
	   		triggerAction : 'all',
	   		valueField : 'value',
	   		displayField : 'text',
	   		value : '', // 初始选中的列表项
	   		fieldLabel : '返回数据格式',
	   		emptyText : '请选择...',
	   		allowBlank : false,
	   		forceSelection : true, // 选中内容必须为下拉列表的子项
	   		editable : true, // 选择输入框可编辑
	   		typeAhead : true, // 输入的时候自动匹配待选项目
	   		labelStyle    : micolor,
	   		anchor : '99%'
	   	});
       var esortno = new Ext.form.TextField({
	          id            : 'id_esortno',
		      name          : 'sortno',
		      fieldLabel    : '排序',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 50,
		      selectOnFocus : true,
		      anchor        : '99%'
	       }); 	       
				

	var eFormPanel = new Ext.form.FormPanel({
	    id : 'id_eapiBasicFormPanel',
	    name : 'eapiBasicFormPanel',
	    defaultType : 'textfield',
	    labelAlign : 'right',
	    labelWidth : 80,
	    frame : false,
	    bodyStyle : 'padding:5 5 0',
		items         : [
				eapiid
				,
				eapiname
				,
				eapimethod
				,
				eapisystem
				,
				eapicategory
				,
				ehttpmethod
				,
				eappkey
				,
				eappsecret
				,
				ereturnformat
				,
				esortno
				
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
				/*eapilbStore.baseParams = {
			  			'apisystemid'			: ''
			 		};
				eapilbStore.load();*/
			}
		}]
	});
//****************************************************************api参数start*******************************************************//
	// 申请列表区域
	var eGridStore = new Ext.data.JsonStore({
		url               : './ApiBasicFields/list.do',
		root              : 'ROOT',
		totalProperty     : 'TOTALCOUNT',
		id                : 'filedsid',
		fields            : ['filedsid','apiid','filedname','ismust','sortno','filedcode']
	});
	
	
	// 翻页排序时带上查询条件
   eGridStore.on('beforeload', function() {
	      //this.baseParams = qFormPanel.getForm().getValues();
	   this.baseParams.apiid = apikey;
   });

	var eGridCheckbox = new Ext.grid.CheckboxSelectionModel();
	var eGridColumns = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),
		//eGridCheckbox,
		/*{
			header            : 'api命令表主键',
			dataIndex         : 'apiid',
			width             : 100,
			align             : 'center'
		}
		 ,*/
		{
			header            : '参数名称',
			dataIndex         : 'filedname',
			width             : 100,
			align             : 'center'
		}
		 ,
		{
			header            : '参数',
			dataIndex         : 'filedcode',
			width             : 100,
			align             : 'center'
		}
		 ,
		{
			header            : '是否必填',
			dataIndex         : 'ismust',
			width             : 100,
			align             : 'center',
			renderer		  : ISMUSTRender
		},
		{
			header            : '排序',
			dataIndex         : 'sortno',
			width             : 100,
			align             : 'center'
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
	var enumber = parseInt(pagesize_combo.getValue());
	// 改变每页显示条数reload数据
	epagesize_combo.on("select", function(comboBox) {
				bbar.pageSize = parseInt(comboBox.getValue());
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
				items : [{
							text : '新增',
							iconCls : 'addIcon',
							id : 'id_etbi_add',
							handler : function() {
								ebtnxz_clicked();
							}
						}, {
							text : '修改',
							id : 'etbi_edit',
							iconCls : 'edit1Icon',
							disabled : true,
							handler : function() {
								ebtnxg_clicked();
							}
						}, {
							text : '删除',
							id : 'etbi_del',
							iconCls : 'deleteIcon',
							disabled : true,
							handler : function() {
								ebtnsc_clicked();
							}
						}, '->', {
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
			title : '<span class="commoncss">Api命令提交参数信息</span>',
			height : 500,
			width : 400,
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
				
			
			// 监听行选中事件
	 eGridPanel.on('rowclick', function(pGrid, rowIndex, event) {
				Ext.getCmp('etbi_edit').enable();
				Ext.getCmp('etbi_del').enable();
			});

	 eGridPanel.on('rowdblclick', function(grid, rowIndex, event) {
				ebtnxg_clicked();
			});
	 var gfiledsid = new Ext.form.Hidden({
	      id			: 'id_gfiledsid',
	      name		    : 'filedsid'
      });
	 var gapiid = new Ext.form.Hidden({
	         id            : 'id_gapiid',
		      name          : 'apiid',
		      fieldLabel    : 'api命令表主键',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 22,
		      selectOnFocus : true,
		      anchor        : '99%'
	      });
	  var gfiledname = new Ext.form.TextField({
	         id            : 'id_gfiledname',
		      name          : 'filedname',
		      fieldLabel    : '参数名称',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 30,
		      selectOnFocus : true,
		      anchor        : '99%'
	      });
	  var gfiledcode = new Ext.form.TextField({
	         id            : 'id_gfiledcode',
		      name          : 'filedcode',
		      fieldLabel    : '参数',
		      allowBlank	: false,
		      labelStyle    : micolor,
		      maxLength	    : 30,
		      selectOnFocus : true,
		      anchor        : '99%'
	      });
	  var gismust = new Ext.form.ComboBox({
			id            : 'id_gismust',
			hiddenName : 'ismust', // 往后台传值字段名
			store : ISMUSTStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
									// />标签对应
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '', // 初始选中的列表项
			fieldLabel : '是否必填',
			emptyText : '请选择...',
			allowBlank : false,
			labelStyle    : micolor,
			forceSelection : true, // 选中内容必须为下拉列表的子项
			editable : true, // 选择输入框可编辑
			typeAhead : true, // 输入的时候自动匹配待选项目
			anchor : '99%'
		});
	  
	  var gsortno = new Ext.form.TextField({
          id            : 'id_gsortno',
	      name          : 'sortno',
	      fieldLabel    : '排序',
	      allowBlank	: false,
	      labelStyle    : micolor,
	      maxLength	    : 50,
	      selectOnFocus : true,
	      anchor        : '99%'
       }); 
	   	        
	var gFormPanel = new Ext.form.FormPanel({
	   id : 'id_gapiBasicFieldsFormPanel',
	   name : 'gapiBasicFieldsFormPanel',
	   defaultType : 'textfield',
	   labelAlign : 'right',
	   labelWidth : 80,
	   frame : false,
	   bodyStyle : 'padding:5 5 0',
		items         : [
				gfiledsid
				,
				gapiid
				,
				gfiledname
				,
				gfiledcode
				,
				gismust
				,
				gsortno
				
		]
	});	
	
	var gWindow = new Ext.Window({
		layout : 'fit',
		width : 400,
		height : 185,
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
		items : [gFormPanel],
		buttons : [{
			text : '保存',
			iconCls : 'acceptIcon',
			handler : function() {
				if (runMode == '0') {
					Ext.Msg.alert('提示',
							'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
					return;
				}
				ebtnbc_clicked()
			}
		}, {
			text : '重置',
			id : 'gbtnReset',
			iconCls : 'tbar_synchronizeIcon',
			handler : function() {
				clearForm(gFormPanel.getForm());
			}
		}, {
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				gWindow.hide();
			}
		}]
	});
				
	function ebtncx_clicked(){
		//var params = "";
		//params.start = 0;
		//params.apiid= apikey;
		//params.limit = bbar.pageSize;
		eGridStore.removeAll();
		eGridStore.baseParams = {
	  			'apiid'			: escape(apikey)
	 		};
		eGridStore.load({
					params : {
						'start' : 0,
						'limit' : bbar.pageSize
					}
				});
	}
	//ebtncx_clicked();
	
	// 保存
	function ebtnbc_clicked(){
		//alert(gapiid.getValue());
		//return;
				if(gapiid.getValue()==''){
					Ext.Msg
					.alert('提示', '请先选择api命令基本信息');
					return;
				}
			  if (!gFormPanel.getForm().isValid())
			     return;
			     
			  	var execmethod = '';
			    if(gfiledsid.getValue() == ''){
					execmethod = 'save.do';
				}else{
					execmethod = 'update.do';
				};
			  	
			  	gFormPanel.form.submit({
					url : './ApiBasicFields/'+execmethod,
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) { // 回调函数有2个参数
						gWindow.hide();
						eGridStore.reload();
						form.reset();
						Ext.MessageBox.alert('提示', action.result.msg);
					},
					failure : function(form, action) {
						Ext.Msg
								.alert('提示', '操作Api命令提交参数信息信息失败! <BR>请确认您已经登录, 且网络保持畅通!'
												+ action.result.msg);
					}
				});
		　}
	
	// 新增界面
	function ebtnxz_clicked(){
		gWindow.setTitle('新增Api命令提交参数信息');
			gfiledsid.setValue('');
			if(apikey=='-9'){
				gapiid.setValue('');
			}else{
				gapiid.setValue(apikey);
			};
			gfiledname.setValue('');
			gismust.setValue('');
			gsortno.setValue('');
			gfiledcode.setValue('');
		gWindow.show();
				
	}
	
	// 修改界面
	function ebtnxg_clicked(){
		var record = eGridPanel.getSelectionModel().getSelected();
		if(!record){
			Ext.MessageBox.alert('提示', '请选择要修改的Api类目表信息');
		}
		else{
			gWindow.setTitle('修改Api类目表信息');
			gFormPanel.getForm().loadRecord(record);
			gWindow.show();
		};
	};
	
	// 删除操作
	function ebtnsc_clicked() {
		var rows = eGridPanel.getSelectionModel().getSelections();
		if (Ext.isEmpty(rows)) {
			Ext.Msg.alert('提示', '请先选中要删除的项目!');
			return;
		}
		var strChecked = jsArray2JsString(rows, 'filedsid');
		Ext.Msg.confirm(
						'请确认',
						'<span style="color:red"><b>提示:</b>删除Api命令提交参数信息信息,请慎重.</span><br>继续删除吗?',
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
									url : './ApiBasicFields/delete.do',
									success : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										eGridStore.reload();
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
	
	function apiiddelete(){
		var rows = qGridPanel.getSelectionModel().getSelections();
		var strChecked = jsArray2JsString(rows, 'apiid');
				Ext.Ajax.request({
					url : './ApiBasicFields/apiiddelete.do',
					success : function(response) {
						var resultArray = Ext.util.JSON
								.decode(response.responseText);
						 apikey = "-9";
						eGridStore.reload();
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
//***************************************************************api参数end*******************************************************//
	// 布局
	// 如果把form作为center区域的话,其Height属性将失效。
	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [qFormPanel, qGridPanel,eGridPanel]
			});	
				
			
	/**
	 * 查询列表
	 */
	function btncx_clicked(){
		qGridStore.removeAll();
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
			    if(eapiid.getValue() == ''){
					execmethod = 'save.do';
				}else{
					execmethod = 'update.do';
				};
			  	
			  	eFormPanel.form.submit({
					url : './ApiBasic/'+execmethod,
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
								.alert('提示', '操作Api命令基本信息信息失败! <BR>请确认您已经登录, 且网络保持畅通!'
												+ action.result.msg);
					}
				});
		　}
			
			
			
	// 新增界面
	function btnxz_clicked(){
		eWindow.setTitle('新增Api命令基本信息信息');
			eapiid.setValue('');
			eapiname.setValue('');
			eapimethod.setValue('');
			eapicategory.setValue('');
			eapisystem.setValue('');
			ehttpmethod.setValue('');
			eappkey.setValue('');
			eappsecret.setValue('');
			ereturnformat.setValue('');
			esortno.setValue('');
		eWindow.show();
				
	}
			
	// 修改界面
	function btnxg_clicked(){
		var record = qGridPanel.getSelectionModel().getSelected();
		if(!record){
			Ext.MessageBox.alert('提示', '请选择要修改的Api命令基本信息信息');
		}
		else{
			eWindow.setTitle('修改Api命令基本信息信息');
			var rowIndex = apilbStore.find("apicategoryid", "" + record.data.apicategory);
			var _open=function(){
				eFormPanel.getForm().loadRecord(record);
				    if (rowIndex < 0){
				    	eapicategory.setValue('');
						eapisystem.setValue('');
						eWindow.show();
				    	return '';
				    }
				    var records = apilbStore.getAt(rowIndex);
				    eapisystem.setValue(records.get('apisystemid'));
				    //return record ? record.get('apicategoryname')+'('+OPENAPIRender(record.get('apisystemid'))+')' : '';
				    eapilbStore.removeListener('load',_open);
				eWindow.show();
			}
			//eapilbStore.removeAll();
			//eapilbStore.load();
				eapilbStore.baseParams = {
			  			'apisystemid'			: apilbStore.getAt(rowIndex).get('apisystemid')
			 		};
	
			eapilbStore.on('load',_open);
			eapilbStore.load();
			
			
		};
	};

				
	// 删除操作
	function btnsc_clicked() {
		var rows = qGridPanel.getSelectionModel().getSelections();
		if (Ext.isEmpty(rows)) {
			Ext.Msg.alert('提示', '请先选中要删除的项目!');
			return;
		}
		var strChecked = jsArray2JsString(rows, 'apiid');
		Ext.Msg.confirm(
						'请确认',
						'<span style="color:red"><b>提示:</b>删除Api命令基本信息信息,请慎重.</span><br>继续删除吗?',
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
									url : './ApiBasic/delete.do',
									success : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										apikey = "-9";
										qGridStore.reload();
										apiiddelete();
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


