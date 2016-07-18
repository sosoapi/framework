package com.dev.base.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.dev.base.json.JsonUtils;

/**
 * 
		* <p>Title: 高德地图rest api 工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:58:09</p>
 */
public class AmapUtils {
	/**
	 * 默认编码
	 */
	private static String DEFAULT_CHARSET = "utf-8";
	
	/**
	 * 高德地图api开发者密钥
	 */
	private static String DEVELOPER_KEY = "7569127f20ff768df2efb3117d629cd4";
	
	/**
	 * 地理编码接口
	 * 由地理位置获取经纬度
	 */
	private static String GEO_CODE_GEO_URL = "http://restapi.amap.com/v3/geocode/geo?key=" + DEVELOPER_KEY + "&address=";
	
	/**
	 * 逆地理编码接口
	 * 由经纬度获取地理位置
	 */
	private static String GEO_CODE_REGEO_URL = "http://restapi.amap.com/v3/geocode/regeo?key=" + DEVELOPER_KEY + "&location=";
	
	/**
	 * 高德api调用成功
	 */
	private static String STATUS_SUCCESS = "1";
	
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
	public static Map geo(String address){
		String url = "";
		try{
			url = GEO_CODE_GEO_URL + URLEncoder.encode(address,DEFAULT_CHARSET);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		Map<String, Object> content = JsonUtils.toObject(getContent(url), HashMap.class);
		
		Map<String, String>	result = MapUtils.newMap();
		String status = (String)content.get("status");
		//请求失败
		if (STATUS_SUCCESS.equals(status)) {
			//如果匹配多个，默认取第一个
			List<Map> geoCodeList = (List<Map>)content.get("geocodes");
			String[] location = ((String)geoCodeList.get(0).get("location")).split(",");
			
			result.put("longitude", location[0]);
			result.put("latitude", location[1]);
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
		String url = GEO_CODE_REGEO_URL + longitude + "," + latitude;
		Map<String, Object> content = JsonUtils.toObject(getContent(url), HashMap.class);
		
		String status = (String)content.get("status");
		//请求失败
		if (STATUS_SUCCESS.equals(status)) {
			//如果匹配多个，默认取第一个
			Map regeoCode = (Map)content.get("regeocode");
			return (String)regeoCode.get("formatted_address");
		}
				
		return "";
	}
	
//	public static void main(String[] args) {
//		//{longitude=114.123369, latitude=22.537754}
//		System.out.println(geo("广东省深圳市罗湖区春风路67号宏丰大厦1楼(庐山酒店对面)"));
//		
//		System.out.println(regeo("114.123369", "22.537754"));
//	}
}
