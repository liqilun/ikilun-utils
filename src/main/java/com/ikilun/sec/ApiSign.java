package com.ikilun.sec;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.ikilun.util.StringUtil;

public class ApiSign {
	/**
	 * 对请求参数集进行MD5签名
	 * @param params 待签名的请求参数集
	 * @param secretCode 签名密码
	 * @return 返回null 或 32位16进制大写字符串
	 */
	public static String signMD5(Map<String, String> params, String secretCode){
		if(params == null || params.isEmpty())
			return "";
		if(params instanceof java.util.TreeMap){
			return signMD5Inner((TreeMap<String, String>) params, secretCode);
		}else{
			TreeMap<String, String> treeMap = new TreeMap<>();
			treeMap.putAll(params);
			return signMD5Inner(treeMap, secretCode);
		}
	}
	/**
	 * 对请求参数集进行MD5签名
	 * @param param 待签名的请求参数集
	 * @param secretCode 签名密码
	 * @return 返回32位16进制大写字符串
	 */
	private static String signMD5Inner(TreeMap<String, String> param, String secretCode){
		return MessageDigestUtil.md5(signStr(param, secretCode, false)).toUpperCase();
	}

	/**
	 * 将请求参数按key=value&key=valuesecretCode拼接
	 * <br/>排除key为sign和signmethod的key-value 
	 * @param param 请求参数
	 * @param secretCode 签名密码
	 * @return
	 */
	public static String signStr(TreeMap<String, String> param, String secretCode, boolean startAppend){
		StringBuilder orgin = new StringBuilder();
		String value = "";
		for(String name : param.keySet()){
			//参与签名的值不包括参数中的签名值和签名方法
			if(!StringUtils.equalsIgnoreCase(name, ApiSysParamConstants.SIGN)
					&& !StringUtils.equalsIgnoreCase(name, ApiSysParamConstants.SIGNMETHOD)){
				value = param.get(name);
				if(StringUtils.isEmpty(value)){
					value = "";
				}
				orgin.append(name).append("=").append(value).append("&");
			}
		}
		if(startAppend){
			return secretCode+StringUtils.substringBeforeLast(orgin.toString(), "&");
		}
		return StringUtils.substringBeforeLast(orgin.toString(), "&") + secretCode;
	}
}
