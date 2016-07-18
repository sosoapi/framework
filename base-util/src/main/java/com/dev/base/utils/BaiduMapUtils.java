package com.dev.base.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import com.dev.base.json.JsonUtils;

/**
 * 
		* <p>Title: 百度地图相关工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:56:04</p>
 */
public class BaiduMapUtils {
	/**
	 * 默认编码
	 */
	private static String DEFAULT_CHARSET = "utf-8";
	
	/**
	 * 百度地图api开发者密钥
	 */
	private static String DEVELOPER_KEY = "3F46FfgQn5L7GTuHgvLZF15K";
	
	/**
	 * 地理编码接口
	 * 由地理位置获取经纬度
	 */
	private static String GEO_CODE_GEO_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + DEVELOPER_KEY + "&address=";
	
	/**
	 * 逆地理编码接口
	 * 由经纬度获取地理位置
	 */
	private static String GEO_CODE_REGEO_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=" + DEVELOPER_KEY + "&location=";
	
	/**
	 * 地图api调用成功
	 */
	private static int STATUS_SUCCESS = 0;
	
	private static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	/**
	 * 
			*@name 获取指定url的内容
			*@Description 相关说明 
			*@Time 创建时间:2014-4-9下午4:01:59
	 */
	public static String getContent(String url){
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			return EntityUtils.toString(httpEntity, DEFAULT_CHARSET);
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally{
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "";
	}
	
	/**
	 * 
			*@name 根据地址获取相应经纬度
			*@Description {longitude:经度,latitude:纬度}
			*@Time 创建时间:2014-4-9下午4:09:48
	 */
	public static Map<String,String> geo(String address,String city){
		String url = "";
		try{
			url = GEO_CODE_GEO_URL + URLEncoder.encode(address,DEFAULT_CHARSET);
			if (!StringUtils.isEmpty(city)) {
				url += "&city=" + URLEncoder.encode(city,DEFAULT_CHARSET);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Map<String, Object> content = JsonUtils.toObject(getContent(url), HashMap.class);
		
		Map<String, String>	result = MapUtils.newMap();
		//请求失败
		if (STATUS_SUCCESS == (Integer)content.get("status")) {
			Map<String, Object> respResult = (Map<String, Object>)content.get("result");
			Map<String, Object> location = (Map<String, Object>)respResult.get("location");
			
			result.put("longitude", "" + location.get("lng"));
			result.put("latitude", "" + location.get("lat"));
		}
		
		return result;
	}
	
	/**
	 * 
			*@name 根据经纬度获取地址
			*@Description 相关说明 
			*@Time 创建时间:2014-4-9下午4:12:56
			*@param longitude	地址经度
			*@param latitude	地址纬度
	 */
	public static String regeo(String longitude,String latitude){
		String url = GEO_CODE_REGEO_URL + latitude + "," + longitude;
		Map<String, Object> content = JsonUtils.toObject(getContent(url), HashMap.class);
		
		//请求失败
		if (STATUS_SUCCESS == (Integer)content.get("status")) {
			Map<String, Object> respResult = (Map<String, Object>)content.get("result");
			return (String)respResult.get("formatted_address");
		}
				
		return "";
	}
	
	public static void main(String[] args) {
		//{longitude=114.12969736627, latitude=22.544113768738}
		System.out.println(geo("安海路35号(近三一教堂 )","厦门"));
		
		System.out.println(regeo("114.12969736627", "22.544113768738"));
	}
}
