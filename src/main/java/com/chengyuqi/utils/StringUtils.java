package com.chengyuqi.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static boolean hasText(String str) {
		
		return !(str==null||"".equals(str.trim()));
	}
	
	/**
	 * �ж��ֻ����Ƿ�Ϊ��ֵ
	 * @param src
	 * @return
	 */
	public static boolean judgeTelephoneIsOk(String src){
		
		String reg="^(1)\\d{10}";
		Pattern compile = Pattern.compile(reg);
		
		Matcher matcher = compile.matcher(src);
		boolean find = matcher.find();
		return find;
	}
	
	
	/**
	 * ������֤
	 * @param str 
	 * @return  
	 */
	public static boolean isEmail(String str) {
		
		String pattern = "^\\w+@\\w+\\.[a-zA-Z]{2,3}$";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(str);
		return matcher.matches();
	}
	
	/**
	 *  ����ַ���Դ
	 */
	static char charArray[] = new char[36];

	static {
		// ��������ַ�����ԭʼ����
		for (int i = 0; i < 10; i++) {
			charArray[i] = (char)('0' + i);
		}
		for (int i = 0; i < 26; i++) {
			charArray[i+10] = (char)('A' + i);
		}		
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊ�գ����ַ���Ҳ��Ϊ�ǵĿ�
	 * @param str
	 * @return Ϊ�շ���true  ���򷵻�false
	 * 
	 */
	public static boolean isBlank(String str) {
		return null==str||"".equals(str.trim());
	}

	/**
	 * �ж�һ���ַ���ʱ�����ֵ 
	 * @param str   
	 * @return �ǿշ���true  ���ַ�����շ���false
	 */
	public static boolean haveValue(String str) {
		return null!=str && !"".equals(str.trim());
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		String regex = "^\\d{1,}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str) {
		String regex = "^(135|136|138)\\d{8}$";
		Pattern compile = Pattern.compile(regex);
		Matcher matcher = compile.matcher(str);
		boolean find = matcher.find();
		return find;
	}


	/**
	 * 
	 * @param n
	 * @return
	 */
	public static String getRandomStr(int n) {

		Random random = new Random();
		//Math.random();
		//UUID.randomUUID();
		//a -z;
		//  StringBuilder  �̲߳���ȫ  ����ִ��Ч�ʸ� ��Ч�ʸߵ�ԭ���ڷ��ʵ�ʱ�򲻻����
		//  StringBuffer �̰߳�ȫ ����ִ��Ч�ʵ���
		// �������ʹ��StringBuilder  �� һ��������ִ��ֻ����һ���߳��ڲ�ִ�У�
		// Ҳ�����±����sb ���ᱻ����߳�ͬʱ���ʣ���������̰߳�ȫ�����⣬���ѡ��Ч�ʽϸߵ�StringBuilder
		StringBuilder sb = new StringBuilder();
		//StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			char randomChar = (char)('a' +  random.nextInt(26));// 0 ~  25;
			sb.append(randomChar);
		}
		return sb.toString();
	}

	/**
	 * ��ȡӢ�ĺ�������ϵ��ַ���
	 * @param n
	 * @return
	 */
	public static String getRandomStrNum(int n) {
		//char charArray[] = {'0','1' ..}
		Random random = new Random();
		//��ȡ����ַ���
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			// ��ȡ������±�
			int index =  random.nextInt(36);// 0 ~  25;
			char randomChar = charArray[index];
			sb.append(randomChar);
		}
		return sb.toString();
	}

	/**
	 * ��ȡ����ַ��� ����2Ϊn
	 * @param n
	 * @return 
	 * @throws UnsupportedEncodingException 
	 */
	public static String getGb2312(int n) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(getGb2312());
		}
		return sb.toString();
	}
	
	/**
	 * �����ȡһ�����ĺ���
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String getGb2312() throws UnsupportedEncodingException {
		byte word[] = new byte[2];
		//  0x1A   0x1A+94
		Random random = new Random();
		word[0] = (byte)(0xA1 + 0x10 + random.nextInt(39));
		word[1] = (byte)(0xA1  + random.nextInt(94));
		return new String(word,"GBK");
	}

	
	/**
	 * ��ַ�ж�
	 * @param url
	 * @return
	 */
	public static boolean checkURL(String url) { 
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"; 
        return Pattern.matches(regex, url); 
   }
	
	
}
