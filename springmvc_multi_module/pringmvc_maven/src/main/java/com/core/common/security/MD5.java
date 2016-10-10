package com.core.common.security;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * MD5加密的工具类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月12日 上午9:22:33
 * @version 1.0
 */
public final class MD5 {
	/**
	 * 获取MD5加密的方法
	 * @param str 明文
	 * @return 加密过后的
	 */
	public static String getMD5(String str) throws Exception {
		/** 创建MD5加密对象 */
		MessageDigest md = MessageDigest.getInstance("MD5");
		/** 调用方法进行加密 */
		md.update(str.getBytes());
		/** 获取加密后的字节数组 */
		byte[] md5Bytes = md.digest();
		
		System.out.println(Arrays.toString(str.getBytes()));
		System.out.println(Arrays.toString(md5Bytes));
		
		String res = "";
		/** 
		 * 循环加密后的字符数组，将每一位转化成一个16进制的两位数，
		 * 如果不够两位前面补零 
		 */
		for (int i= 0; i < md5Bytes.length; i++){
			int temp = md5Bytes[i] & 0xFF;
			if (temp <= 0xF){
				res += "0";
			}
			res += Integer.toHexString(temp);
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(0xFF);
		System.out.println(getMD5("123456").length());
	}
}
