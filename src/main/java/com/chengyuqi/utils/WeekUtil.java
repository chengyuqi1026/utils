package com.chengyuqi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekUtil {
	

	/**
	 * ���ݴ����yyyy-MM-dd��ȡ���ڼ��Ƿ������� ����:2019-12-21:false,2019-12-22:true
	 * 
	 * @param strDate
	 * @return boolean
	 */

	public static boolean isOddDayOfWeekFromDate(String strDate) {
		boolean flag = true;
		int year = getYearFromDate(strDate);
		int month = getMonthFromDate(strDate);
		int day = getDayFromDate(strDate);
		int dayOfWeek = whatday(year, month, day);
		if (dayOfWeek % 2 == 0) {// ż��
			flag = false;
		}
		return flag;
	}

	/**
	 * ���ݴ����yyyy-MM-dd HH:mm:ss��ȡ���ڼ��Ƿ������� ����:2019-12-21 23:12:21:false,2019-12-22
	 * 09:12:21:true
	 * 
	 * @param strDateTime
	 * @return boolean
	 */
	public static boolean isOddDayOfWeekFromDateTime(String strDateTime) {

		boolean flag = true;
		int year = getYearFromDateTime(strDateTime);
		int month = getMonthFromDateTime(strDateTime);
		int day = getDayFromDateTime(strDateTime);
		int dayOfWeek = whatday(year, month, day);
		if (dayOfWeek % 2 == 0) {// ż��
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		System.out.println(isOddDayOfWeekFromDate("2019-12-23"));
	}

	// ��ķ����ɭ���㹫ʽ���������ж����ڼ�
	public static void CalculateWeekDay(int y, int m, int d) {
		if (m < 1 || m > 12) {
			System.out.println("��������·ݲ��ٷ�Χ�ڣ����������룡");
		}
		if (m == 1 || m == 2) {
			m += 12;
			y--;
		}
		int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
		switch (iWeek) {
		case 0:
			System.out.printf("����һ\n");
			break;
		case 1:
			System.out.printf("���ڶ�\n");
			break;
		case 2:
			System.out.printf("������\n");
			break;
		case 3:
			System.out.printf("������\n");
			break;
		case 4:
			System.out.printf("������\n");
			break;
		case 5:
			System.out.printf("������\n");
			break;
		case 6:
			System.out.printf("������\n");
			break;
		}
	}

	/**
	 * ���չ�ʽ
	 * 
	 * @param y:��
	 * @param m:��
	 * @param d:��
	 * @return
	 */
	public static int whatday(int y, int m, int d) {
		int ans;
		if (m == 1 || m == 2) {
			m += 12;
			y--;
		}
		if ((y < 1752) || (y == 1752 && m < 9) || (y == 1752 && m == 9 && d < 3))
			ans = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 + 5) % 7 + 1;
		else
			ans = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7 + 1;
		return ans;
	}

	/**
	 * ���ݸ�����ʽ���ڻ�ȡ�������
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 */
	public static int getYearFromDate(String dateStr) {
		// ��ȡstring��Ӧdate���ڣ�
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			System.out.println("����������ڸ�ʽ����:yyyy-MM-dd��ʽ");
			e.printStackTrace();
		}
		// ��ȡdate��Ӧ��Calendar����
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		// 3�����Դ�ca�л�ȡ���ָ����ڵ�����ֵ��
		// int day = ca.get(Calendar.DAY_OF_MONTH);// һ�����еĵڼ���
		// int month = ca.get(Calendar.MONTH)+1;// �ڼ�����
		int year = ca.get(Calendar.YEAR);// �����ֵ
		return year;
	}

	
	/**
	 * ���ݸ�����ʽ���ڻ�ȡ�����·�
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 */
	public static int getMonthFromDate(String dateStr) {
		// ��ȡstring��Ӧdate���ڣ�
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			System.out.println("����������ڸ�ʽ����:yyyy-MM-dd��ʽ");
			e.printStackTrace();
		}
		// ��ȡdate��Ӧ��Calendar����
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		// 3�����Դ�ca�л�ȡ���ָ����ڵ�����ֵ��
		// int day = ca.get(Calendar.DAY_OF_MONTH);// һ�����еĵڼ���
		int month = ca.get(Calendar.MONTH) + 1;// �ڼ�����
		// int year = ca.get(Calendar.YEAR);// �����ֵ
		return month;
	}


	/**
	 * ���ݸ�����ʽ���ڻ�ȡ�����·��е�����
	 * 
	 * @param dateStr
	 *            yyyy-MM-dd
	 */
	public static int getDayFromDate(String dateStr) {
		// ��ȡstring��Ӧdate���ڣ�
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			System.out.println("����������ڸ�ʽ����:yyyy-MM-dd��ʽ");
			e.printStackTrace();
		}
		// ��ȡdate��Ӧ��Calendar����
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		// 3�����Դ�ca�л�ȡ���ָ����ڵ�����ֵ��
		int day = ca.get(Calendar.DAY_OF_MONTH);// һ�����еĵڼ���
		// int month = ca.get(Calendar.MONTH)+1;// �ڼ�����
		// int year = ca.get(Calendar.YEAR);// �����ֵ
		return day;
	}


	/**
	 * ���ݸ�����ʽ���ڻ�ȡ�������
	 * 
	 * @param dateTimeStr
	 *            yyyy-MM-dd HH:mm:ss
	 */
	public static int getYearFromDateTime(String dateTimeStr) {
		// ��ȡstring��Ӧdate���ڣ�
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeStr);
		} catch (ParseException e) {
			System.out.println("����������ڸ�ʽ����:yyyy-MM-dd HH:mm:ss��ʽ");
			e.printStackTrace();
		}
		// ��ȡdate��Ӧ��Calendar����
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		// 3�����Դ�ca�л�ȡ���ָ����ڵ�����ֵ��
		// int day = ca.get(Calendar.DAY_OF_MONTH);// һ�����еĵڼ���
		// int month = ca.get(Calendar.MONTH)+1;// �ڼ�����
		int year = ca.get(Calendar.YEAR);// �����ֵ
		return year;
	}

	/**
	 * ���ݸ�����ʽ���ڻ�ȡ�����·�
	 * 
	 * @param dateTimeStr
	 *            yyyy-MM-dd HH:mm:ss
	 */
	public static int getMonthFromDateTime(String dateTimeStr) {
		// ��ȡstring��Ӧdate���ڣ�
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeStr);
		} catch (ParseException e) {
			System.out.println("����������ڸ�ʽ����:yyyy-MM-dd HH:mm:ss��ʽ");
			e.printStackTrace();
		}
		// ��ȡdate��Ӧ��Calendar����
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		// 3�����Դ�ca�л�ȡ���ָ����ڵ�����ֵ��
		// int day = ca.get(Calendar.DAY_OF_MONTH);// һ�����еĵڼ���
		int month = ca.get(Calendar.MONTH) + 1;// �ڼ�����
		// int year = ca.get(Calendar.YEAR);// �����ֵ
		return month;
	}

	/**
	 * ���ݸ�����ʽ���ڻ�ȡ�����·��е�����
	 * 
	 * @param dateTimeStr
	 *            yyyy-MM-dd HH:mm:ss
	 */
	public static int getDayFromDateTime(String dateTimeStr) {
		// ��ȡstring��Ӧdate���ڣ�
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTimeStr);
		} catch (ParseException e) {
			System.out.println("����������ڸ�ʽ����:yyyy-MM-dd HH:mm:ss��ʽ");
			e.printStackTrace();
		}
		// ��ȡdate��Ӧ��Calendar����
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		// 3�����Դ�ca�л�ȡ���ָ����ڵ�����ֵ��
		int day = ca.get(Calendar.DAY_OF_MONTH);// һ�����еĵڼ���
		// int month = ca.get(Calendar.MONTH)+1;// �ڼ�����
		// int year = ca.get(Calendar.YEAR);// �����ֵ
		return day;
	}
}