package com.chengyuqi.utils;

public class NumberUtil {
	/**
	 * �ж�����������Ƿ�������
	 * ��������true
	 * @param num
	 * @return
	 */
	public static Boolean isOddNumber(int num) {
		Boolean flag = null;
		if (num % 2 == 0) {
			flag = false;
		}else {
		flag = true;
		}
		return flag;
	}

	
}

	
