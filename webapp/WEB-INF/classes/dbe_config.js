{
	// 系统支持的数据库类型定义
	dbtypes : [{
		name : 'Derby',
		url : 'jdbc:derby://<server>/<dbname>;create=true',
		driver : 'org.apache.derby.jdbc.ClientDriver',
		service : 'qimingx.dbe.service.impl.DerbyDBInfoService'	
	}, {
		name : 'ORACLE10g',
		url : 'jdbc:oracle:thin:@<server>:<dbname>',
		driver : 'oracle.jdbc.driver.OracleDriver',
		service : 'qimingx.dbe.service.impl.OracleDBInfoService'
	}, {
		name : 'MS_SQLSERVER',
		url : 'jdbc:sqlserver://<server>;databaseName=<dbname>',
		driver : 'com.microsoft.sqlserver.jdbc.SQLServerDriver',
		service : 'qimingx.dbe.service.impl.SQLServerDBInfoService'
	}, {
		name : 'MySQL',
		url : 'jdbc:mysql://<server>/<dbname>?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai"',
		driver : 'com.mysql.jdbc.Driver',
		service : 'qimingx.dbe.service.impl.MysqlDBInfoService'
	}, {
		name : 'HSQL_Server',
		url : 'jdbc:hsqldb:hsql://<server>/<dbname>',
		driver : 'org.hsqldb.jdbcDriver',
		service : 'qimingx.dbe.service.impl.HSQLDBInfoService'
	}, {
		name : 'PostgreSQL',
		url : 'jdbc:postgresql://<server>/<dbname>',
		driver : 'org.postgresql.Driver',
		service : 'qimingx.dbe.service.impl.PostgreSQLDBInfoService'
	}, {
		name : 'DB2',
		url : 'jdbc:db2://<server>/<dbname>',
		driver : 'com.ibm.db2.jcc.DB2Driver',
		service : 'qimingx.dbe.service.impl.DefaultDBInfoService'
	}]
}
