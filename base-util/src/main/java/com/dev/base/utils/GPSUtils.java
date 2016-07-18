package com.dev.base.utils;

import org.springframework.util.StringUtils;

/**
 * 
		* <p>Title: gps工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:51:57</p>
 */
public class GPSUtils {
	/**
	 * 地球半径，单位km
	 */
	public final static double EARTH_RADIUS = 6378.137; 
	 
	/**
	 * 
			*@name 根据两点间的经纬度获取相关距离，单位km
			*@Description 相关说明 
			*摘自:http://www.cnblogs.com/ouling/archive/2011/08/26/2154555.html
			*@Time 创建时间:2014-3-31下午3:11:17
			*@param srcLatitude		源纬度，单位弧度
			*@param srcLongitude	源经度，单位弧度
			*@param destLatitude	目的纬度，单位弧度
			*@param destLongitude	目的经度，单位弧度
	 */
	public static double getDistance(double srcLatitude, double srcLongitude,
										double destLatitude, double destLongitude) {
		double radLat1 = (srcLatitude * Math.PI / 180.0);
		double radLat2 = (destLatitude * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (srcLongitude - destLongitude) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;

		return s/1000;
	}
	
	/**
	 * 
			*@name 根据两点间的经纬度获取相关距离
			*@Description 相关说明 
			*摘自:http://www.cnblogs.com/ouling/archive/2011/08/26/2154555.html
			*@Time 创建时间:2014-3-31下午3:11:17
			*@param srcLatitude		源纬度，单位弧度
			*@param srcLongitude	源经度，单位弧度
			*@param destLatitude	目的纬度，单位弧度
			*@param destLongitude	目的经度，单位弧度
	 */
	public static double getDistance(String srcLatitude, String srcLongitude,
											String destLatitude, String destLongitude) {
		if (StringUtils.isEmpty(srcLatitude)
				|| StringUtils.isEmpty(srcLongitude)
				|| StringUtils.isEmpty(destLatitude)
				|| StringUtils.isEmpty(destLongitude)) {
			
			return 0;
		}
		
		double srcLat = Double.parseDouble(srcLatitude);
		double srcLng = Double.parseDouble(srcLongitude);
		double destLat = Double.parseDouble(destLatitude);
		double destLng = Double.parseDouble(destLongitude);
		
		return getDistance(srcLat, srcLng, destLat, destLng);
	}
	
	/**
	 *  
			*@name 由角度转换为弧度
			*@Description 相关说明 
			*@Time 创建时间:2014-4-2下午2:52:55
	 */
    public static double radian(double d){  
        return (d*Math.PI)/180.00; 
    } 
}
