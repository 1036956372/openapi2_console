Ext.onReady(function(){
	var apikey = "-9";
	var index = 0 ;
	//var postindex = 0 ;
	var fieldsname = [];
	var fieldsismust = [];
	var qapilbStore = new Ext.data.Store({		//加载全部api类型		存放在客户端的数据记录对象Record 的一个集合，它是 ExtJS 可视组件(例如：GridPanel、ComboBox)的数据源，为这些组件提供数据输入
		proxy : new Ext.data.HttpProxy({		//Http代理,通过Connection 对象请求指定的 url 来加载数据
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
		baseParams : {		//An object containing properties which are to be sent as parameters.
			apisystemid : apiSystemid
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
	var apibasicStore = new Ext.data.JsonStore({		//加载某个API类型的全部API方法，APIID，
		url               : './ApiBasic/list.do',
		root              : 'ROOT',
		totalProperty     : 'TOTALCOUNT',
		id                : 'apiid',
		fields            : ['apiid','apiname','apimethod','apicategory','apisystem','httpmethod','appkey','appsecret','returnformat',],
		listeners:{  
	        load:function(store,records){  
	            for(var i=0;i<records.length;i++){  
	                records[i].set('apinamemethod',records[i].get('apimethod')+'('+records[i].get('apiname')+')');  
	            }  
	        }  
	    }  

	});
	
	var eGridStore = new Ext.data.JsonStore({			//加载某个API的具体信息，对应表API_BASIC_FIELDS
		url               : './ApiBasicFields/list.do',		
		root              : 'ROOT',
		totalProperty     : 'TOTALCOUNT',
		id                : 'filedsid',
		fields            : ['filedsid','apiid','filedname','ismust','filedcode',]
	});
	
	var qapicategory = new Ext.form.ComboBox({		//定义API类目行样式
		id            : 'id_qapicategory',
		hiddenName          : 'apicategory',
		fieldLabel    : 'Api类目',
		//labelStyle    : micolor,
	    store : qapilbStore, // 引
		mode : 'remote',
		triggerAction : 'all',
		valueField : 'apicategoryid',
		displayField : 'apicategory',
		//value : '', // 初始选中的列表项    
		emptyText : '请选择...',
		allowBlank : false,
		forceSelection : true,
		typeAhead : true,
		pageSize : 10,
		minListWidth : 270,
		resizable : true,
		editable : false,
		//listeners :{"blur":function (){qapilbStore.load();}
		anchor : '99%'
	 }); 		
	qapicategory.on('select', function() {			//关联API类目行下拉按钮方法事件
		//qapilbStore.reload();
		var rowIndex = apibasicStore.find("apiid", "" + apikey);
		if(index > 0){
			for(i=0;i<index;i++){
				 
				qFormPanel.remove('ApiBasicFields' + i);
			}
			qFormPanel.doLayout();
		}
		showField(qapimethod);
		hideField(qreturnformat);
		hideField(qhttpmethod);
		hideField(qappkey);
		hideField(qappsecret);
		Ext.getCmp('apirequest').setValue('');
		Ext.getCmp('apiresponse').setValue('');
		qapimethod.reset();
		qapimethod.setValue('');
			var value = qapicategory.getValue();
			//alert(value);
			apibasicStore.baseParams = {
		  			'apicategory'			: escape(value)
		 		};
			apibasicStore.load();
		});
	
	var qapimethod = new Ext.form.ComboBox({	//定义API方法名行样式
		id            : 'id_qapimethod',
		hiddenName          : 'apimethod',
		fieldLabel    : 'Api方法名',
		//labelStyle    : micolor,
	    store : apibasicStore, // 引
		mode : 'remote',
		triggerAction : 'all',
		valueField : 'apiid',
		displayField : 'apinamemethod',
		//value : '', // 初始选中的列表项
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
	
	qapimethod.on('select', function(){		//定义API方法名行下拉按钮事件
		showField(qapimethod);
		showField(qreturnformat);
		showField(qhttpmethod);
		showField(qappkey);
		showField(qappsecret);
		Ext.getCmp('apirequest').setValue('');
		Ext.getCmp('apiresponse').setValue('');
		//qapilbStore.load();
		apikey = qapimethod.getValue();
		var rowIndex = apibasicStore.find("apiid", "" + apikey);
		if(index > 0){
			for(i=0;i<index;i++){
				 
				qFormPanel.remove('ApiBasicFields' + i);
			}
			qFormPanel.doLayout();
		}
		
		var record = apibasicStore.getAt(rowIndex);
		qFormPanel.getForm().loadRecord(record);
		//qapicategory.getRawValue(record.data.apicategory);
		/*var erowIndex = apilbStore.find("apicategoryid", "" + record.data.apicategory);
	    if (erowIndex < 0){
	    	Ext.Msg.alert(erowIndex);
		    return ;
	    }
	    var erecord = apilbStore.getAt(erowIndex);
	    qapicategory.setRawValue('('+OPENAPIRender(erecord.get('apisystemid'))+')'+erecord.get('apicategoryname'));
	    //qapicategory.getRawValue(record.data.apicategory);*/
	    
    	eGridStore.baseParams = {
	  			'apiid'			: escape(apikey)
	 		};
	    eGridStore.reload();
	    
		//alert(value);
	})
	
	eGridStore.on('load',function(){
		fieldsname.length = 0;
		fieldsismust.length = 0;
		var _state = true ;
		for(var i =0;i<eGridStore.getCount();i++){
			var ID = 'ApiBasicFields' + i;
			if(eGridStore.getAt(i).get('ismust')=='1'){		//1表示必填
				_state = false;
			}else{
				_state = true ;
			}
			var label = eGridStore.getAt(i).get('filedcode');	//参数名
			
			//若fieldname不为空且与fieldcode不同，则label=fieldname+fieldcode
			if(eGridStore.getAt(i).get('filedname')!=null&&
					eGridStore.getAt(i).get('filedname')!=''&&
					eGridStore.getAt(i).get('filedname')!=eGridStore.getAt(i).get('filedcode')){		
						label=eGridStore.getAt(i).get('filedname') + '('+ label + ')';
			}
			
			
			qFormPanel.add({				
				id 			  : ID,
				xtype		  : 'textfield',
				emptyText	  : ISMUSTRender(eGridStore.getAt(i).get('ismust')),
				name          : eGridStore.getAt(i).get('filedcode'),
				fieldLabel    : label,
				allowBlank	  : _state,
				selectOnFocus : true,
				anchor        : '99%'
			}
					);
			
			
			fieldsname.push(eGridStore.getAt(i).get('filedcode'));
			fieldsismust.push(eGridStore.getAt(i).get('ismust'));
		}
		index = eGridStore.getCount()
		qFormPanel.doLayout();
	})
	var qreturnformat = new Ext.form.ComboBox({		//返回数据格式选择框控制器
   		id            : 'id_qreturnformat',
   		hiddenName : 'returnformat', // 往后台传值字段名
   		store : RETURNFORMATStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		//value : '', // 初始选中的列表项
   		fieldLabel : '返回数据格式',
   		emptyText : '请选择...',
   		allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '99%'
   	});
	
	var qhttpmethod = new Ext.form.ComboBox({		//提交方式选择框控制器
   		id            : 'id_qhttpmethod',
   		hiddenName : 'httpmethod', // 往后台传值字段名
   		store : HTTPMETHODStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
   								// />标签对应
   		mode : 'local',
   		triggerAction : 'all',
   		valueField : 'value',
   		displayField : 'text',
   		//value : '', // 初始选中的列表项
   		fieldLabel : '提交方式', 
   		emptyText : '请选择...',
   		allowBlank : false,
   		forceSelection : true, // 选中内容必须为下拉列表的子项
   		editable : true, // 选择输入框可编辑
   		typeAhead : true, // 输入的时候自动匹配待选项目
   		//labelStyle    : micolor,
   		anchor : '99%'
   	});
	var qappkey = new Ext.form.TextField({
		id            : 'id_qappkey',
		name          : 'appkey',
		fieldLabel    : 'AppKey',
		allowBlank : false,
		//hidden : true,
		selectOnFocus : true,
		anchor        : '99%'
	 }); 
	var qappsecret = new Ext.form.TextField({
		id            : 'id_qappsecret', 
		name          : 'appsecret',
		fieldLabel    : 'AppSecret',
		//hidden : true,
		allowBlank : false,
		selectOnFocus : true,
		anchor        : '99%'
	 }); 
	/*var qfields = new Ext.form.TextField({
		id            : 'id_qfields',
		name          : 'fields',
		fieldLabel    : 'fields',
		selectOnFocus : true,
		anchor        : '90%'
	 }); */
	var qFormPanel = new Ext.form.FormPanel({		//api单条测试样式,左边面板
		id : 'id_qapiDebugFormPanel',
        name : 'qapiDebugeFormPanel',
        autoScroll:true, 
		region : 'center',
		title : '<span class="commoncss">api单条测试<span>',
		collapsible : true,
		//collapsed: true,
		border : true,
		labelWidth : 150, // 标签宽度
		// frame : true, //是否渲染表单面板背景色
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
		buttonAlign : 'center',
		//width : 147,//120
		items : [
		         qapicategory,
		         qapimethod,
		         qreturnformat,
		         qhttpmethod,
		         qappkey,
		         qappsecret
		         ],
	    buttons : [{
				text : '提交',
				iconCls : 'previewIcon',
				handler : function() {
					bttj_clicked();
				}
			}, {
				text : '重置',
				iconCls : 'tbar_synchronizeIcon',
				handler : function() {
					if(index > 0){
						for(i=0;i<index;i++){
							 
							qFormPanel.remove('ApiBasicFields' + i);
						}
						qFormPanel.doLayout();
					}
					hideField(qapimethod);
					hideField(qreturnformat);
					hideField(qhttpmethod);
					hideField(qappkey);
					hideField(qappsecret);
					Ext.getCmp('apirequest').setValue('');
					Ext.getCmp('apiresponse').setValue('');
					qFormPanel.getForm().reset();
					apibasicStore.baseParams = {
				  			'apicategory'			: ''
				 		};
					apibasicStore.reload({
						params : {
							'start' : 0,
							'limit' : 10
						}
					});
					apikey = -9;
					index = 0;
					//postindex = 0;
				}
			}]
	});
	
	
	//控件显示以及隐藏
	function hideField(field)  
    {  
       field.disable();// for validation  
       field.hide();  
       field.getEl().up('.x-form-item').setDisplayed(false); // hide label  
    }  
  
    function showField(field)  
    {  
       field.enable();  
       field.show();  
       field.getEl().up('.x-form-item').setDisplayed(true);// show label  
    }  
	
	
	var eFormPanel = new Ext.form.FormPanel({		//API单条测试请求结果面板
		id : 'id_eapiDebugFormPanel',
        name : 'eapiDebugeFormPanel',
		region : 'east',
		title : '<span class="commoncss">api单条测试返回结果<span>',
		collapsible : true,
		//collapsed: true,
		border : true,
		labelWidth : 50, // 标签宽度
		// frame : true, //是否渲染表单面板背景色
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:3 20 0', // 表单元素和表单面板的边距
		buttonAlign : 'center',
		width : 550,//120
		items : [ {
		            xtype: "label",
		            forId: "apirequest",
		            text: "API请求参数:"
		        }, {
		            name: "apirequest",
		            xtype: "textarea",
		            id: "apirequest",
		            inputId: "apirequest",
		            hideLabel: true,
		            height : 100,
		            readOnly : true,
		            style : {
                    	marginBottom:'30px' 
                    	},
		            anchor : '99%'
		        },
		          {
                    xtype: "label",
                    forId: "apiresponse",
                    text: "API返回结果:"
                }, {
                    name: "apiresponse",
                    xtype: "textarea",
                    id: "apiresponse",
                    inputId: "apiresponse",
                    hideLabel: true,
                    height : 400,
                    readOnly : true,
                    anchor : '99%'
                }]
	});
	var viewport = new Ext.Viewport({		//一个特殊的容器，用来描述应用的可见区域 (浏览器的视口)
		layout : 'border',
		items : [qFormPanel,eFormPanel]
	});	
	hideField(qapimethod);
	hideField(qreturnformat);
	hideField(qhttpmethod);
	hideField(qappkey);
	hideField(qappsecret);
	//**********************************************************添加post参数start*****************************************************//
	
	/*function addpostfield(){
		postindex =postindex+1;
		var ID = 'postfield' + postindex ;
		var postname = 'postName' + postindex ;
		var postvalue = 'postValue' + postindex ;
		qFormPanel.add({
			id	   : ID ,
			layout : 'column',
			border : false,
			items  : [{
				columnWidth : .5,
				layout : 'form',
				border : false,
				items	: [{
					id			  : postname,
					xtype		  : 'textfield',
					fieldLabel    : postname,
					allowBlank	  : false,
					selectOnFocus : true,
					anchor        : '100%'
				}]
			},{
				columnWidth : .5,
				layout : 'form',
				border : false,
				items	: [{
					id			  : postvalue,
					xtype		  : 'textfield',
					fieldLabel    : postvalue,
					allowBlank	  : false,
					selectOnFocus : true,
					anchor        : '100%'
				}]
			}]
		});
		
		qFormPanel.doLayout();
	}*/
	//**********************************************************添加post参数end*****************************************************//
	function bttj_clicked (){			//提交函数
		//var obj = [];
		if (!qFormPanel.getForm().isValid())
		     return;
		if(fieldsismust.length > 0){
			var _isalert = true;
			var _ishave = false;
			for(i=0;i<fieldsismust.length;i++){
				if(fieldsismust[i]=='2'){
					_ishave = true;
					if(Ext.getCmp('ApiBasicFields' + i).getValue()!=''){
						_isalert = false;
					}
				}
			}
			if(_ishave){
				if(_isalert){
					Ext.Msg.alert('提示', '其中有些参数必须填写一项!');
					return;
				}
			}
			//qFormPanel.doLayout();
		}
		var API = {};		//定义提交的请求参数
		var fields = [];
		
		//var postfield = {};
		API.apimethod = qapimethod.getRawValue();
		API.returnformat = qreturnformat.getRawValue();
		var rowIndex = qapilbStore.find("apicategoryid", "" + qapicategory.getValue());
		if(rowIndex < 0){
			API.apicategory = '';
		}
		else{
			var record = qapilbStore.getAt(rowIndex);
			API.apicategory = record.get('apisystemid');
		}
		
		//API.httpmessage = qhttpmessage.getValue();
		//alert(API.httpmessage);
		API.appkey = qappkey.getValue();
		API.appsecret = qappsecret.getValue();
		API.httpmethod = qhttpmethod.getRawValue();
		if(fieldsname.length > 0){
			for(i=0;i<fieldsname.length;i++){
				if(Ext.getCmp('ApiBasicFields' + i).getValue()!=''){
					var fieldsdata = {};
					fieldsdata.fieldkey =fieldsname[i];
					fieldsdata.fieldvalue = Ext.getCmp('ApiBasicFields' + i).getValue();
					//fields[fieldsname[i]] =  Ext.getCmp('ApiBasicFields' + i).getValue();
					fields.push(fieldsdata);
				}
			}
			API.fields = fields;
			
			//qFormPanel.doLayout();
		}
		/*if(postindex > 0){
			for(i=0;i<postindex;i++){
				//var ID = 'postfield' + i ;
				var a = i+1
				var postname = 'postName' + a;
				var postvalue = 'postValue' + a ;
				alert(postname);
				postfield[Ext.getCmp(postname).getValue()] =  Ext.getCmp(postvalue).getValue();apirequest
			}
			API.postfield = postfield;
			
			//qFormPanel.doLayout();
		}*/
		//alert(JSON.stringify(API));
		Ext.Ajax.request({
			url : './ApiDebug/apidebug.do',
			success : function(response) {
				var resultArray = Ext.util.JSON
						.decode(response.responseText);		//获取不到JSON字符串?Uncaught SyntaxError: Unexpected identifier
				Ext.getCmp('apirequest').setValue(resultArray.link);
				Ext.getCmp('apiresponse').setValue(response.responseText);
				Ext.Msg.alert('提示', response.responseText);
			},
			failure : function(response) {
				var resultArray = Ext.util.JSON
						.decode(response.responseText);
				Ext.Msg.alert('提示', resultArray.msg);
			},
			params : {json: Ext.encode(API)}
		});
		//obj.push(API);
		}
	
	
	
})