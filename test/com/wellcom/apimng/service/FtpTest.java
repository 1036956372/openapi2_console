package com.wellcom.apimng.service;

import java.io.IOException;

import org.g4studio.core.ftp.FtpHelper;
import org.g4studio.core.net.ftp.FTPFile;


public class FtpTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		// TODO Auto-generated method stub
		FtpHelper ftpHelper = null;
		try {
			ftpHelper = new FtpHelper();
			// 连接
			ftpHelper.createConnection("127.0.0.1", "test", "test", 21);
			// 设置相对根路径
			ftpHelper.useWorkingDir("/files/中文路劲支持");
			// 上传文件
			 boolean b = ftpHelper.storeFile("D:/hzrmzf.pdf",
			 "hzrmzf.pdf");
			// 入参支持流
			// ftpHelper.storeFile(fis, filename)

			String ftpclientpath = "D:/usr";
			// 下载文件
		    b = ftpHelper.getFile(ftpclientpath + "/hzrmzf.pdf",
					"hzrmzf.pdf");
			if (b) {
				System.out.println("下载文件成功!");
				ftpHelper.removeFile("/files/中文路劲支持/hzrmzf.pdf");
			}

			FTPFile[] files = ftpHelper.getFtpClient().listFiles();
			for (FTPFile ftpFile : files) {
				System.out.println(ftpFile.getName());
				
			}

		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 释放连接非常重要
			ftpHelper.disconnect();
		}

		
 }

}
