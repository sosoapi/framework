package com.dev.base.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
		* <p>Title: 模板工具类</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月21日下午2:48:04</p>
 */
public class TemplateUtils {
	/**
	 * 
			*@name 字符串变量替换
			*@Description 相关说明 
			*@Time 创建时间:2015年6月29日下午3:28:19
			*@param paramMap 包含变量名称的字典
			*@param template 变量名为"${name}"
	 */
	public static String process(String template,Map<String, Object> paramMap){
		if (paramMap == null || paramMap.isEmpty()) {
			return template;
		}
		
		//生成匹配模式的正则表达式 
//	    String patternStr = "\\$\\{(" + StringUtils.join(paramMap.keySet(), "|") + ")\\}"; 
		String patternStr = "\\{(" + StringUtils.join(paramMap.keySet(), "|") + ")\\}"; 
		Pattern pattern = Pattern.compile(patternStr); 
	    Matcher matcher = pattern.matcher(template);
	    StringBuffer result = new StringBuffer(); 
	    Object replaceValue = null;
	    while(matcher.find()) { 
	    	replaceValue = paramMap.get(matcher.group(1));
	    	if (replaceValue != null) {
	    		matcher.appendReplacement(result, replaceValue.toString()); 
			}
	    	else{
	    		matcher.appendReplacement(result, ""); 
	    	}
	    } 
	    matcher.appendTail(result); 
	    
	    return result.toString();
	}
	
	public static void main(String[] args) {
		//被替换关键字的的数据源 
	    Map<String,Object> tokens = new HashMap<String,Object>(); 
	    tokens.put("firstName", "bill"); 
	    tokens.put("lastName", "gate"); 

	    //匹配类似velocity规则的字符串 
	    String template = "hello,this is ${firstName} ${lastName}"; 
	    
	    System.out.println(process(template,tokens));
	}
}
