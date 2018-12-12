package com.wellcom.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import org.g4studio.core.metatype.Dto;
import org.g4studio.core.metatype.impl.BaseDto;
import org.g4studio.core.resource.util.StringUtils;
import org.g4studio.core.util.G4Constants;
import org.g4studio.core.util.G4Utils;

import com.fourinone.BeanContext;
import com.fourinone.FileAdapter;
import com.fourinone.FileAdapter.ByteReadParser;
import com.fourinone.FttpAdapter;
import com.fourinone.FttpAdapter.FileProperty;
import com.fourinone.FttpAdapter.FttpReadAdapter;
import com.fourinone.FttpException;

public class FttpUtil {
	static {
		String path = FttpServer.class.getResource("/").getPath()
				+ "config.xml";
		System.out.println("config文件路径:" + path);
		try {
			path = java.net.URLDecoder.decode(path, "utf8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BeanContext.setConfigFile(path);
	}

	/**
	 * @param args
	 * @throws FttpException
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws FttpException,
			UnsupportedEncodingException {
		// TODO Auto-generated method stub
		/*
		 * String[] fttproots = FttpAdapter.fttpRoots(); for(int i=0;
		 * i<fttproots.length; i++){ System.out.println(fttproots[i]);
		 * 
		 * FttpAdapter fa = new FttpAdapter("fttp://"+fttproots[i]); String[]
		 * roots = fa.listRoots(); for(int j=0; j<roots.length; j++){
		 * System.out.println(roots[j]); }
		 * 
		 * }
		 */

		/*
		 * String logpath =
		 * "fttp://192.168.193.240/d:/appopenapi2/log/minutes/";
		 * 
		 * List listDir = getFilesInDir(logpath);
		 * System.out.println(listDir.getClass()); PaginatedArrayList
		 * paginatedList = new PaginatedArrayList(listDir, 10);
		 * System.out.println("第" + (paginatedList.getPageIndex() + 1) + "页");
		 * ListIterator pageList = paginatedList.listIterator(); List
		 * pageFileList = paginatedList.getPage();
		 * System.out.println(pageFileList.getClass() + "," +
		 * pageFileList.size());
		 * 
		 * String jsonstr = JsonHelper.encodeList2PageJson(pageFileList,
		 * listDir.size(), null); System.out.println(jsonstr); while
		 * (pageList.hasNext()) { BaseDto baseDto = (BaseDto) pageList.next();
		 * System.out.println(baseDto.getAsString("filepath")); }
		 * 
		 * while (paginatedList.isNextPageAvailable()) { System.out.println("第"
		 * + (paginatedList.getPageIndex() + 1) + "页");
		 * paginatedList.nextPage(); // pageList = paginatedList.listIterator();
		 * // while (pageList.hasNext()) { // BaseDto baseDto = (BaseDto)
		 * pageList.next(); //
		 * System.out.println(baseDto.getAsString("filepath")); // // }
		 * pageFileList = paginatedList.getPage(); jsonstr =
		 * JsonHelper.encodeList2PageJson(pageFileList, listDir.size(), null);
		 * System.out.println(jsonstr); }
		 */

		String fttppath= "fttp://192.168.193.240/d:/appopenapi2/log/post/rest-post-2016-04-22.log";
		FttpUtil.tailFile(fttppath, 0, 100);
		// RandomAccessFile RandomAccessFile ;
		// ByteReadParser brp = FileAdapter.getByteReadParser(bts);

		// System.out.println("logstr1:"+new String(brp.readLine(),"gbk"));

		// byte[] tmp = brp.read("\r\n".getBytes());

		// System.out.println("logstr1:"+new String(tmp,"gbk"));
		// byte[] tmp =brp.readLast("\r\n".getBytes());
		// System.out.println("logstr1:"+new String(tmp,"gbk"));

		/*
		 * while(true){ if( brp.readLine()==null) break; System.out.println(new
		 * String(brp.readLine(),"gbk"));
		 * 
		 * }
		 */

	}

	/**
	 * 读取文件内容
	 * @param fttppath
	 *            文件路径
	 * @param end
	 *            文件末尾索引
	 * @param num
	 *            读取行数
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String tailFile(String fttppath, int end, int num)
			throws FttpException, UnsupportedEncodingException {
		String str = "";
		int line_length = 500; // 500 bytes
		FttpAdapter fa = null;
		if(!StringUtils.hasLength(fttppath)){
			return str;
		}
		try {
			fa = new FttpAdapter(fttppath);
			FileProperty fileProperty_tmp = fa.getProperty();
			long filesize = fileProperty_tmp.length();
            
			FttpReadAdapter reader =null;
			if(filesize < 1024 * 100){  //小于100k字节的全部显示
				reader = fa.getFttpReader();
			}else{
				int readlength = line_length * num;
				if(filesize>readlength)
			       reader = fa.getFttpReader(filesize - readlength, readlength);
				else 
					reader = fa.getFttpReader();
			}
			byte[] bts = reader.readAll();
			str = new String(bts, "gbk");
			//System.out.println("获取日志信息:" + str);
		} finally {
			if (fa != null)
				fa.close();
		}
		return str;

	}
	
	/**
	 * 获取文件基本信息及文件内容
	 * @param fttppath 文件路径
	 * @return
	 * @throws Exception 
	 */
	public static Dto getFileBean(String fttppath)
			throws Exception {
		Dto  dto =null;
		FttpAdapter fa = null;
		if(!StringUtils.hasLength(fttppath)){
			return dto;
		}
		try {
			dto = new BaseDto();
			fa = new FttpAdapter(fttppath);
			int index = fttppath.lastIndexOf(GZipUtils.EXT);
			int s1 = fttppath.length() - index; 
			//文件压缩
			if(s1 !=3 && fa.gZip()){
				fa.close();
				fa = new FttpAdapter(fttppath+GZipUtils.EXT);
			}
				FileProperty fileProperty = fa.getProperty();
				dto.put("fileSize", fileProperty.length());
				dto.put("fileName", fileProperty.getName());
				FttpReadAdapter reader =null;
				reader = fa.getFttpReader();
				byte[] bts = reader.readAll();
				
				//2017-12-27新增压缩功能
				//byte[] gz_data = GZipUtils.compress(bts);
				dto.put("fileContent", bts);
			
		} finally {
			if (fa != null)
				fa.close();
		}
		return dto;
		
	}

	/**
	 * 文件字节单位转换
	 * 
	 * @param fileS
	 * @return
	 */
	public static String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 读取指定目录的文件列表
	 * 
	 * @param path
	 * @return
	 * @throws FttpException
	 */
	public static List<BaseDto> getFilesInDir(String path) throws FttpException {
		List<BaseDto> baseDtoList = new ArrayList<BaseDto>();
		FttpAdapter fa1 = null;
		if(!StringUtils.hasLength(path)){
			return baseDtoList;
		}
		
		try {
			fa1 = new FttpAdapter(path);
			FileProperty fileProperty = fa1.getProperty();
			if (fileProperty.exists()) {
				if (fileProperty.isDirectory()) {
					// System.out.println(fileProperty.getPath());//

					String[] roots = fileProperty.list();
					for (int j = 0; j < roots.length; j++) {
						BaseDto dto = new BaseDto();
						fa1 = new FttpAdapter(path + roots[j]);
						FileProperty fileProperty_tmp = fa1.getProperty();
						if (fileProperty_tmp.isFile()) {
							// System.out
							// .println(fileProperty_tmp.getName()
							// + ","
							// + FormetFileSize(fileProperty_tmp
							// .length()));

							dto.put("filename", fileProperty_tmp.getName());
							dto.put("filesize",
									FormetFileSize(fileProperty_tmp.length()));
							dto.put("isfile", "true");
							dto.put("fttppath", fileProperty_tmp.getPath());
							dto.put("lastModifiedDate",
									G4Utils.Date2String( fileProperty_tmp.lastModifiedDate(), G4Constants.FORMAT_DateTime)  );
						} else {
							dto.put("filename", fileProperty_tmp.getName());
							dto.put("filesize", "");
							dto.put("isfile", "false");
							dto.put("fttppath", fileProperty_tmp.getPath());
							dto.put("lastModifiedDate",
									G4Utils.Date2String( fileProperty_tmp.lastModifiedDate(), G4Constants.FORMAT_DateTime)  );
						}
						baseDtoList.add(0, dto);
					}
				}
			}
		} finally {
			if (fa1 != null)
				fa1.close();
		}
		return baseDtoList;
	}

}
