package com.dev.base.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import com.dev.base.exception.ValidateException;
import com.dev.base.exception.errorcode.SysErrorCode;
import com.dev.base.json.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 
		* <p>Title: httpclient工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:09:42</p>
 */
public class HttpClientUtils {

    /**
     * 定义全局httpClient类
     */
    private static HttpClient httpclient = HttpClientBuilder.create().setMaxConnPerRoute(10).build();
    
    /** 默认编码*/
    public static final String DEFAULT_ENCODE = "UTF-8";

    /**
     * 
    		*@name 统一报文协议
    		*@Description 
    		*	远程调用返回值采用统一报文
    		*	成功:
    		*		errorCode:0
    		*		data:返回数据
    		*
    		*	失败: 
    		*		errorCode:非0
    		*		errorMsg:错误提示信息
    		*		data:错误提示数据(可选)
    		*@Time 创建时间:2014-5-15下午3:59:23
    		*@param url
    		*@param responseClass
     */
    public static <T> T getExt(String url, Class<T> responseClass) {
        HttpGet get = new HttpGet(url);
        String result = execute(get,DEFAULT_ENCODE);

        return dealResponse(result,responseClass);
    }
    
    public static <T> T getExtType(String url, TypeReference<T> typeReference) {
        HttpGet get = new HttpGet(url);
        String result = execute(get,DEFAULT_ENCODE);

        return dealResponseType(result,typeReference);
    }
    
    //处理返回值
    private static <T> T dealResponseType(String result,TypeReference<T> typeReference){
    	Map response = JsonUtils.toObject(result, HashMap.class);
    	String errorCode = response.get("errorCode").toString();
    	Object data = response.get("data");
    	
        //远程执行业务错误
        if (!SysErrorCode.SUCCESS.equals(errorCode)) {
			//错误处理
        	String errorTip = response.get("errorMsg").toString();
        	throw new ValidateException(errorCode,errorTip,data);
		}
        
        //远程服务统一将返回值放入key为data的map中
        return (T)JsonUtils.toObject(data.toString(),typeReference);
    }
    
    /**
     * 
    		*@name 统一报文协议
    		*@Description 
    		*	远程调用返回值采用统一报文
    		*	成功:
    		*		errorCode:0
    		*		data:返回数据
    		*
    		*	失败: 
    		*		errorCode:非0
    		*		errorMsg:错误提示信息
    		*		data:错误提示数据(可选)
    		*@Time 创建时间:2014-5-15下午3:59:23
    		*@param url
    		*@param responseClass
     */
    public static <T> T postExt(String path, Map<String, Object> paramsMap, Class<T> responseClass) {
        // 实现将请求 的参数封装封装到HttpEntity中。
        HttpEntity httpEntity = new StringEntity(JsonUtils.toJson(paramsMap), ContentType.APPLICATION_JSON);
        // 使用HttpPost请求方式
        HttpPost httpPost = new HttpPost(path);
        // 设置请求参数到Body中。
        httpPost.setEntity(httpEntity);
        String result = execute(httpPost,DEFAULT_ENCODE);
        
        return dealResponse(result,responseClass);
    }
    
    //处理返回值
    private static <T> T dealResponse(String result,Class<T> responseClass){
    	Map response = JsonUtils.toObject(result, HashMap.class);
    	String errorCode = response.get("errorCode").toString();
    	Object data = response.get("data");
    	
        //远程执行业务错误
        if (!SysErrorCode.SUCCESS.equals(errorCode)) {
			//错误处理
        	String errorTip = response.get("errorMsg").toString();
        	throw new ValidateException(errorCode,errorTip,data);
		}
        
        //远程服务统一将返回值放入key为data的map中
        return (T)data;
    }

    /**
     * 
    		*@name 执行HttpRequest请求，并且返回执行的结果
    		*@Description 相关说明 
    		*@Time 创建时间:2014-5-7上午9:18:29
     */
    public static String execute(HttpUriRequest request,String charset) {
        try {
            HttpResponse response = httpclient.execute(request);
            validateStatusCode(response,charset);
            return EntityUtils.toString(response.getEntity(), charset);
        } 
        catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } 
        catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }

    /**
     * 
    		*@name 获取httpclient
    		*@Description 相关说明 
    		*@Time 创建时间:2015年5月13日下午5:07:58
     */
    public static HttpClient getClient() {
    	return httpclient;
    }
    
    /**
     * 
    		*@name 验证服务器端返回的状态码
    		*@Description 相关说明 
    		*@Time 创建时间:2014-5-7上午9:19:51
     */
    public static void validateStatusCode(HttpResponse response,String charset) throws ParseException, IOException {
    	int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200){
        	throw new RuntimeException("调用失败,状态码:" + statusCode + " " + EntityUtils.toString(response.getEntity(), charset));
        }
    }
    
    /**
     * 
    		*@name get请求
    		*@Description 相关说明 
    		*@Time 创建时间:2015年4月23日下午1:24:16
     */
    public static String doGet(String url,Map<String, Object> params){
    	return doGet(url, params, DEFAULT_ENCODE);
    }
    
    /**
     * 
    		*@name get请求
    		*@Description 相关说明 
    		*@Time 创建时间:2015年4月23日下午1:24:16
     */
    public static String doGet(String url,Map<String, Object> params, String charset){
    	HttpGet get = new HttpGet(buildUrl(url, params,charset));
        String result = execute(get,charset);
        
        return result;
    }
    
    /**
     * 
    		*@name 下载文件
    		*@Description 相关说明 
    		*@Time 创建时间:2015年5月13日上午11:25:39
     */
    public static void doGetFile(String url,Map<String, Object> params, String filePath,FileResponHandler handler){
    	HttpGet get = new HttpGet(buildUrl(url, params,DEFAULT_ENCODE));
    	try {
            HttpResponse response = httpclient.execute(get);
            validateStatusCode(response,DEFAULT_ENCODE);
            String fileName = handler.getFileName(response);
            File file = new File(filePath + SystemUtils.FILE_SEPARATOR + fileName);
            if (!file.exists()) {
				file.createNewFile();
			}
            IOUtils.copy(response.getEntity().getContent(), new FileOutputStream(file));
        } 
        catch (ClientProtocolException e) {
        	throw new RuntimeException(e);
        } 
        catch (IOException e) {
        	throw new RuntimeException(e);
        }
    }
    
    /**
     * 
    		*@name post请求
    		*@Description 相关说明 
    		*@Time 创建时间:2015年4月23日下午1:25:12
     */
    public static String doPost(String url,Map<String, Object> params, String charset){
    	HttpPost post = new HttpPost(url);
		if (params != null) {
			try {
				HttpEntity httpEntity = new UrlEncodedFormEntity(parseParams(params), charset);
				post.setEntity(httpEntity);
			} 
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return execute(post, charset);
    }
    
    /**
     * 
    		*@name post请求
    		*@Description 相关说明 
    		*@Time 创建时间:2015年4月23日下午1:25:12
     */
    public static String doPost(String url,Map<String, Object> params){
    	return doPost(url, params, DEFAULT_ENCODE);
    }
    
    /**
     * 
    		*@name 提交json信息
    		*@Description 相关说明 
    		*@Time 创建时间:2015年5月7日下午4:57:53
     */
    public static String doPostJson(String url,Map<String, Object> urlParams,String json){
    	url = buildUrl(url, urlParams, DEFAULT_ENCODE);
    	HttpPost post = new HttpPost(url);
		if (!StringUtils.isEmpty(json)) {
			//支持JSON格式的数据
            StringEntity jsonEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
			post.setEntity(jsonEntity);
		}
		
		return execute(post, DEFAULT_ENCODE);
    }
    
    /**
     * 
    		*@name 上传文件
    		*@Description 相关说明 
    		*@Time 创建时间:2015年5月11日下午5:54:24
    		*@param url
    		*@param urlParams
    		*@param content 文件内容
    		*@param name form-data name属性
    		*@param fileName form-data filename属性
     */
    public static String doPostFile(String url,Map<String, Object> urlParams,
    								byte[] content,String name,String fileName){
    	url = buildUrl(url, urlParams, DEFAULT_ENCODE);
    	HttpPost post = new HttpPost(url);
    	
    	MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    	builder.addBinaryBody(name, content, ContentType.DEFAULT_BINARY, fileName);
    	post.setEntity(builder.build());
    	
    	return execute(post, DEFAULT_ENCODE);
    }
    
    /**
     * 
    		*@name 上传文件
    		*@Description 相关说明 
    		*@Time 创建时间:2015年5月13日上午10:38:22
    		*@param url
    		*@param urlParams
    		*@param file
    		*@param name
     */
	public static String doPostFile(String url, Map<String, Object> urlParams,File file, String name) {
		url = buildUrl(url, urlParams, DEFAULT_ENCODE);
		HttpPost post = new HttpPost(url);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody(name, file);

		post.setEntity(builder.build());
		return execute(post, DEFAULT_ENCODE);
	}

    
    /**
     * 
    		*@name 组装get请求url
    		*@Description url编码 
    		*@Time 创建时间:2015年5月11日上午11:23:44
    		*@param url
    		*@param params
    		*@param charset
     */
    public static String buildUrl(String url, Map<String, Object> params,String charset) {
		StringBuilder urlBuilder = new StringBuilder(url);
		if (params != null && !params.isEmpty()) {
			urlBuilder.append("?");
			urlBuilder.append(URLEncodedUtils.format(parseParams(params), charset));
		}
		
		return urlBuilder.toString();
	}
    
    /**
     * 
    		*@name 组装get请求url
    		*@Description 相关说明 
    		*@Time 创建时间:2015年5月14日下午3:56:39
    		*@param url
    		*@param params
    		*@param charset
    		*@param encoded 是否编码
     */
    public static String buildUrl(String url,Map<String, Object> params,
    								String charset,boolean encoded){
    	if (encoded) {
			return buildUrl(url, params, charset);
		}
    	
    	StringBuilder urlBuilder = new StringBuilder(url);
    	if (params != null && !params.isEmpty()) {
    		urlBuilder.append("?");
    		Set<String> keySet = params.keySet();
    		for (String key : keySet) {
				urlBuilder.append(key).append("=").append(params.get(key));
				urlBuilder.append("&");
			}
    		
    		urlBuilder.deleteCharAt(urlBuilder.length() - 1);
		}
    	
    	return urlBuilder.toString();
    }
    
    /**
     * 
    		*@name 获取ssl链接
    		*@Description  
    		*@CreateDate 2015年12月31日下午2:34:40
     */
    public static SSLConnectionSocketFactory getSSLConnectionSocketFactory(String keyStoreType,String certFilePath,String certPassword) throws Exception{
    	KeyStore keyStore  = KeyStore.getInstance(keyStoreType);
//        FileInputStream instream = new FileInputStream(new File(certFilePath));
    	InputStream instream = null;
        try {
        	instream = new ClassPathResource(certFilePath).getInputStream();
            keyStore.load(instream, certPassword.toCharArray());
        } 
        finally {
            instream.close();
        }

        SSLContext sslContext = SSLContexts.custom()
                		  				   .loadKeyMaterial(keyStore, certPassword.toCharArray())
                		  				   .build();
        
        return new SSLConnectionSocketFactory(sslContext,new String[] { "TLSv1" },null,
                	SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
    }
    
    /**
     * 
    		*@name 获取ssl
    		*@Description  
    		*@CreateDate 2015年12月30日下午9:27:48
     */
    public static CloseableHttpClient getSSLHttpClient(SSLConnectionSocketFactory sslsf){
    	return HttpClients.custom()
                		  .setSSLSocketFactory(sslsf)
                		  .build();
    }
    
    //组装请求消息
    private static List<NameValuePair> parseParams(Map<String, Object> params){
    	List<NameValuePair> list = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		Object value = null;
		for (String key : keySet) {
			value = params.get(key);
			if (value != null) {
				list.add(new BasicNameValuePair(key, value.toString()));
			}
			else {
				list.add(new BasicNameValuePair(key, ""));
			}
		}
		
		return list;
    }
    
    public interface FileResponHandler{
    	/**
    	 * 
    			*@name 获取文件名称
    			*@Description 相关说明 
    			*@Time 创建时间:2015年5月13日上午11:29:09
    			*@param response
    	 */
    	String getFileName(HttpResponse response);
    }
}
