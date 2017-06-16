package com.ikilun.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtil {
	/**
	 * 判断字符是否是英文字母.
	 * 
	 * @param c
	 *            字符
	 * @return true/false
	 */
	public static boolean isAlpha(char c) {
		return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
	}
	public static String formatNumber(Number number) {
		if (number == null) {
			return "0";
		}
		NumberFormat formatter = new DecimalFormat("###,##0.##");  
		return formatter.format(number);  
	}
	/**
	 * 分转元
	 */
	public static BigDecimal changeF2Y(long amount) throws Exception{    
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));    
    }
}
