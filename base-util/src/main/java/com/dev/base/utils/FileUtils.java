package com.dev.base.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: 文件相关工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:54:28</p>
 */
public class FileUtils {
	/**
	 * classpath绝对路径
	 */
	public final static String CLASS_PATH = getClassPath();
	
	/**
	 * 
			*@name 获取classpath绝对路径
			*@Description 相关说明 
			*@Time 创建时间:2014-7-22上午10:58:37
	 */
	private static String getClassPath(){
		try {
			return new File(FileUtils.class.getResource("/").getFile()).getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
			*@name 获取资源文件的绝对路径
			*@Description 相关说明 
			*@Time 创建时间:2014-7-22上午11:02:09
	 */
	public static String getAbsolutePath(String fileName){
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(CLASS_PATH)
				   .append(File.separator)
				   .append(fileName);
		
		return pathBuilder.toString();
	}
	
	/**
	 * 
			*@name 查询指定目录下后缀名符合条件的文件列表
			*@Description 默认为jpg
			*@Time 创建时间:2014-7-22上午9:26:38
	 */
	public static String getFileExt(String fileName) {
		if (StringUtils.isEmpty(fileName) 
				|| fileName.indexOf(".") == -1) {
			return "jpg";
		}

		int index = fileName.lastIndexOf(".") + 1;

		return fileName.substring(index, fileName.length());
	}
	
	/**
	 * 
			*@name 获取文件内容
			*@Description 相关说明 
			*@Time 创建时间:2015年1月14日下午4:15:08
	 */
	public static byte[] getContent(String fileName){  
        File f = new File(fileName);  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        BufferedInputStream in = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(f));  
            int bufSize = 1024;  
            byte[] buffer = new byte[bufSize];  
            int len = 0;  
            while (-1 != (len = in.read(buffer, 0, bufSize))) {  
                bos.write(buffer, 0, len);  
            }  
            return bos.toByteArray();  
        } 
        catch (IOException e) {  
            e.printStackTrace();  
        } 
        finally {  
            try {  
                in.close(); 
                bos.close();  
            } 
            catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        
        return null;
    }  
	
	/**
	 * 
			*@name 读取文件内容
			*@Description 相关说明 
			*@Time 创建时间:2015年1月30日下午6:04:39
	 */
	public static List<String> readContent(String fileName){
		List<String> result = new ArrayList<String>();
		FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
        	fileReader = new FileReader(fileName);
        	bufferedReader = new BufferedReader(fileReader);
        	String line = "";
			while((line = bufferedReader.readLine()) != null){
				result.add(line);
			}
		} 
        catch (IOException e) {
			e.printStackTrace();
		}
        finally{
        	try {
				bufferedReader.close();
			} 
        	catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        return result;
	}
	
	/**
	 * 
			*@name 中文名称
			*@Description 相关说明 
			*@Time 创建时间:2015年1月12日下午4:58:44
	 */
	public static List<File> listFile(String path,String filterArrayStr){
		File file = new File(path);
		return listFile(file, filterArrayStr);
	}
	
	/**
	 * 
			*@name 查询指定目录下后缀名符合条件的文件列表
			*@Description 相关说明 
			*@Time 创建时间:2015年1月12日下午4:29:05
	 */
	public static List<File> listFile(File path, String filterArrayStr) {
		List<File> result = new ArrayList<File>();

		// 过滤器后缀，逗号分隔
		Map<String, String> filterMap = MapUtils.newMap();
		if (!StringUtils.isEmpty(filterArrayStr)) {
			String[] filterArray = filterArrayStr.split(",");
			for (String filter : filterArray) {
				filterMap.put(filter, null);
			}
		}

		//递归遍历
		addFile(path,filterMap,new CustFilter(filterMap),result);
		return result;
	}
	
	//遍历文件
	private static List<File> addFile(File file,Map<String, String> filterMap,CustFilter filter,List<File> result){
		if (file.isFile()) {// 如果是文件，直接添加
			String fileExt = FileUtils.getFileExt(file.getName());
			if (filterMap.containsKey(fileExt)) {// 满足条件则加入list
				result.add(file);
			}
		} 
		else {// 文件夹则递归处理
			File[] fileArray = file.listFiles(filter);
			for (File temp : fileArray) {
				addFile(temp, filterMap,filter,result);
			}
		}
		
		return result;
	}
}

//文件过滤器
class CustFilter implements FileFilter{
	//过滤器后缀，逗号分隔
	private Map<String,String> filterMap = MapUtils.newMap();
	
	public CustFilter(String filterArrayStr) {
		if(!StringUtils.isEmpty(filterArrayStr)){
			String[] filterArray = filterArrayStr.split(",");
			for (String filter : filterArray) {
				filterMap.put(filter, null);
			}
		}
	}
	
	public CustFilter(Map<String, String> filterMap){
		this.filterMap = filterMap;
	}
	
	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {// 如果是目录也接受
			return true;
		} 
		else {// 如果文件名符合
			String fileExt = FileUtils.getFileExt(file.getName());
			return filterMap.containsKey(fileExt);
		}
	}
}
