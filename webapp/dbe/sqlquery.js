// DEB Action定义..
DBE.SQLQueryPanel = function(config) {
	// actions
	var actions = new DBE.SQLQueryPanelActions(this);

	// sql editor
	var editorConfig = {
		region : 'north',
		split : true,
		collapsible : true,
		collapseMode : 'mini',
		minSize : 80,
		actions : actions.getActions(),
		keys : [{
			key : [Ext.EventObject.ENTER],
			scope : this,
			ctrl : false,
			shift : false,
			alt : true,
			fn : function(key, eventObj) {
				// alt + Enter 时运行sql
				this.runSQL();
			}
		}]
	};
	var sqlEditor = null;
	if (Ext.isIE) {
		sqlEditor = new DBE.SQLSwfEditor(editorConfig);
		console.log("ie"+sqlEditor);
	} else if(navigator.userAgent.indexOf("Chrome")>-1) {		//DbExplor的版本为2.2，没有Ext.isChrome
		sqlEditor = new DBE.SQLSwfEditor(editorConfig);
		console.log("ch"+sqlEditor);
	} else {
		sqlEditor = new DBE.SQLEditor(editorConfig);
		console.log("ot"+sqlEditor);
		console.log(Ext);
		
	}
	
	/*function myBrowser() {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器
        var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
        var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
        var isChrome = userAgent.indexOf("Chrome") > -1; //判断是否Chrome浏览器
        if (isIE) {
            var IE5 = IE55 = IE6 = IE7 = IE8 = false;
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            IE55 = fIEVersion == 5.5;
            IE6 = fIEVersion == 6.0;
            IE7 = fIEVersion == 7.0;
            IE8 = fIEVersion == 8.0;
            if (IE55) {
                return "IE55";
            }
            if (IE6) {
                return "IE6";
            }
            if (IE7) {
                return "IE7";
            }
            if (IE8) {
                return "IE8";
            }
        }//isIE end
        if (isFF) {
            return "FF";
        }
        if (isOpera) {
            return "Opera";
        }
        if (isChrome) {
            return "Chrome";
        }
    }//myBrowser() end
*/	


	// 创建grid（使用一个默认的TableInfo模型）
	var grid = new DBE.DynamicQueryGrid({
		tableinfo : {
			columns : [{
				name : 'Result',
				id : 'Result',
				extType : {
					booleanType : false,
					dateFormat : "",
					dateType : false,
					directShow : true,
					format : "",
					longType : false,
					numberType : false,
					sortable : true,
					type : "auto"
				}
			}]
		}
	});

	// 构建数据窗口
	var grid1 = new Ext.Panel({
		title : '网格数据',
		region : 'center',
		minSize : 80,
		layout : 'fit',
		plain : true,
		frame : false,
		border : false,
		items : grid
	});

	var message = new Ext.form.TextArea({
		id : 'node',
		value : ""
	})
	// 构建消息窗口
	var grid2 = new Ext.Panel({
		title : '消息窗口',
		region : 'center',
		minSize : 80,
		layout : 'fit',
		plain : true,
		frame : false,
		border : false,
		items : message
	});

	var queryGridPanel = new Ext.TabPanel({
		region : 'center',
		width : 450,
		height : 300,
		activeTab : 0,
		frame : true,
		items : [grid1, grid2]
	});

	var ds = grid.getStore();
	ds.on('load', function() {
		var record = ds.getAt(0);
		if (record != null) {
			var data = ds.getAt(0).data['Result'];
			if (data == undefined) {
				// sql 为select语，显示数据窗口
				queryGridPanel.setActiveTab(0);
			} else {
				// sql 为update,insert和delete时，显示消息窗口
				message.setValue(data);
				queryGridPanel.setActiveTab(1);
			}
		}

	});

	// 准备配置参数
	var cfg = {
		layout : 'border',
		plain : true,
		frame : false,
		border : false,
		autoScroll : true,
		autoSize : true,
		split : true,
		items : [sqlEditor, queryGridPanel]
	};
	config = Ext.applyIf(config || {}, cfg);

	// call 父类构建器
	DBE.SQLQueryPanel.superclass.constructor.call(this, config);

	// 公布属性
	this.grid = grid;
	this.grid1 = grid1;
	this.message = message;
	this.queryGridPanel = queryGridPanel;
	this.sqlEditor = sqlEditor;
	this.actions = actions;
};
Ext.extend(DBE.SQLQueryPanel, Ext.Panel, {
	/**
	 * 初始化 查询面板，包括设置sql，以及执行查询..
	 */
	init : function(sql) {
		this.sqlEditor.init();
		// 构建工具栏按钮
		this.grid.buildTBarItems();

		if (sql && sql.length > 0) {
			// alert("runsql"+sql);
			this.runSQL(sql);
		} else {
			// load初始空数据
			this.grid.store.loadData({
				total : 1,
				rows : [{
					Result : 'no records.'
				}]
			});
		}
	},
	/**
	 * 执行指定的sql语句
	 */
	runSQL : function(sql, append) {
		var hasInitSQL = !!sql;
		if (!sql) {
			// 从编辑器中取得默认sql
			sql = this.sqlEditor.getSQLText();
		}

		if (sql && sql.length > 0) {
			this.grid.reload(sql);
			this.queryGridPanel.setActiveTab(0);
		} else {
			alert("请输入SQL语句~~");
		}
		//alert('wait');
		if (hasInitSQL) {
			if (append) {
				this.sqlEditor.setSQLText(sql, true);
			} else {
				this.sqlEditor.setSQLText(sql, false);
			}
		}
	},
	/*
	 * 执行SQL文件，返回相应的消息
	 * 
	 */
	runSQLFile : function(data) {
		this.message.setValue(data);
		this.queryGridPanel.setActiveTab(1);

	},

	/*
	 * 打开SQL文件，将文件内容在sqlEditor中显示
	 * 
	 */
	openSQLFile : function(data) {
		this.sqlEditor.setSQLText(data);

	}

});