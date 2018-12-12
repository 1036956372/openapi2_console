package org;

import org.g4studio.core.properties.PropertiesFactory;
import org.g4studio.core.properties.PropertiesFile;
import org.g4studio.core.properties.PropertiesHelper;
import org.g4studio.core.server.G4Server;

/**
 * 系统启动
 * 
 * @author OSWorks-XC
 * @since 2009-01-13
 */
public class StartUp {
	public static void main(String[] args) {
		PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4SERVER);	//加载G4SERVER。property文件
		G4Server server = new G4Server(
				pHelper.getValue("g4server.WebContext"),
				new Integer(pHelper.getValue("g4server.WebPort"))
				);		//初始化G4server对象
		try {
			server.start();		//启动server
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
