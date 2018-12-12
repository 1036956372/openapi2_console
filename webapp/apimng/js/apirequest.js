Ext.onReady(function(){
	var qapisystemid = new Ext.form.ComboBox({
		id            : 'id_qapisystemid',
		hiddenName : 'apisystemid', // 往后台传值字段名
		store : OPENAPIStore, // 引用的代码表数据源和<G4Studio:ext.codeStore
								// />标签对应
		mode : 'local',
		triggerAction : 'all',
		valueField : 'value',
		displayField : 'text',
		//value : '', // 初始选中的列表项
		fieldLabel : '所属系统',
		emptyText : '请选择...',
		allowBlank : false,
		forceSelection : true, // 选中内容必须为下拉列表的子项
		editable : true, // 选择输入框可编辑
		typeAhead : true, // 输入的时候自动匹配待选项目
		//labelStyle    : micolor,
		anchor : '99%'
	});
	var qhttpmethod = new Ext.form.ComboBox({
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
	qhttpmethod.on('select', function() {
		var httpmethod = qhttpmethod.getValue();
			if("2"==httpmethod){
				showField(qfields);
			}else{
				qfields.setValue("");
				hideField(qfields);
				
				}
		});
	var qurlrequest = new Ext.form.TextArea({
        id            : 'id_qurlrequest',
	      name          : 'urlrequest',
	      fieldLabel    : 'url请求',
	      allowBlank	: false,
	      height		: 200,
	      //labelWidth	: 80,
	      //labelStyle    : micolor,
	      maxLength	    : 3000,
	      selectOnFocus : true,
	      anchor        : '99%'
     });
	var qfields = new Ext.form.TextArea({
        id            : 'id_qfields',
	      name          : 'fields',
	      fieldLabel    : '提交数据信息',
	      allowBlank	: false,
	      height		: 200,
	      //labelWidth	: 80,
	      //labelStyle    : micolor,
	      maxLength	    : 3000,
	      selectOnFocus : true,
	      anchor        : '99%'
     });
	var qFormPanel = new Ext.form.FormPanel({
		id : 'id_qapirequestFormPanel',
        name : 'qapirequestFormPanel',
        autoScroll:true, 
		region : 'center',
		title : '<span class="commoncss">网关请求单条测试<span>',
		collapsible : true,
		//collapsed: true,
		border : true,
		labelWidth : 100, // 标签宽度
		// frame : true, //是否渲染表单面板背景色
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
		buttonAlign : 'center',
		//width : 147,//120
		items : [
		         qapisystemid
		         ,
		         qhttpmethod
		         ,
		         qurlrequest
		         ,
		         qfields
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
					qFormPanel.getForm().reset();
					hideField(qfields);
				}
			}]
	});
	var eFormPanel = new Ext.form.FormPanel({
		id : 'id_eapiDebugFormPanel',
        name : 'eapiDebugeFormPanel',
		region : 'east',
		title : '<span class="commoncss">网关请求单条测试返回结果<span>',
		collapsible : true,
		//collapsed: true,
		border : true,
		labelWidth : 50, // 标签宽度
		// frame : true, //是否渲染表单面板背景色
		labelAlign : 'right', // 标签对齐方式
		bodyStyle : 'padding:3 20 0', // 表单元素和表单面板的边距
		buttonAlign : 'center',
		width : 550,//120
		items : [ /*{
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
		        },*/
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
	//hideField(qfields);
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [qFormPanel,eFormPanel]
	});	
	hideField(qfields);
	
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
    function bttj_clicked (){
    	Ext.getCmp('apiresponse').setValue("");
		//var obj = [];
		if (!qFormPanel.getForm().isValid())
		     return;
		var API = {};
		var method="doGet.do";
		API.apisystemid=qapisystemid.getValue();
		API.urlrequest=qurlrequest.getValue();
		if("2"==qhttpmethod.getValue()){
			API.fields=qfields.getValue();
			method = "doPost.do";
		}
		Ext.Ajax.request({
			url : './ApiRequest/'+method,
			success : function(response) {
				var resultArray = Ext.util.JSON
						.decode(response.responseText);
				//Ext.getCmp('apirequest').setValue(resultArray.link);
			Ext.getCmp('apiresponse').setValue(response.responseText);
				//Ext.Msg.alert('提示', response.responseText);
			},
			failure : function(response) {
				var resultArray = Ext.util.JSON
						.decode(response.responseText);
				Ext.Msg.alert('提示', resultArray.msg);
			},
			params : API
		})
	}
	
})