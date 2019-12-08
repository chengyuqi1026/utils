package com.chengyuqi.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;

public class FileUtils {
	
	/**
	 * ��ȡ�ļ�����չ��
	 * 
	 * @param String fileName
	 */
	public static synchronized String getSuffixName(String fileName) {

		//��ȡ���һ�����λ��
		int dotPos = fileName.lastIndexOf('.');
		if(dotPos<0)
			return "";
		return fileName.substring(dotPos);
	}

	

	/**
	 * 
	 * @param fileName
	 */
	public static void delFile(String fileName) {

		File file = new File(fileName);
		// ��ȡ�ļ��ķָ�����
		String fileSeperator = getProperty("file.separator");

		//�ļ�������
		if(!file.exists())
			return ;

		// �����Ŀ¼
		if(file.isDirectory()) {
			//�ݹ�ɾ����Ŀ¼�����ļ�
			String[] list = file.list();
			for (int i = 0; i < list.length; i++) {
				String childFileName = fileName+ fileSeperator + list[i];
				delFile(childFileName);
			}
		}
		// ������ļ� ����ɾ����Ŀ¼֮�� ɾ������
		file.delete();		
	}

	/**
	 * ��������key ��ȡϵͳ��������ֵ
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {	
		Properties properties = System.getProperties();
		/*//��ȡ��������
		//System.getenv();	
		//ArrayList<String> arrayList = new ArrayList<String>();
		//
		//arrayList.forEach(x->{System.out.println(x);});
		 Set<Object> keySet = properties.keySet();
		for (Object hKey: keySet) {
			System.out.println("hKey is " + hKey);
			System.out.println("hValue is " + properties.getProperty((String) hKey));
		}*/
		return properties.getProperty(key);
	} 
	
	/**
	 * ��ȡϵͳ�Ļ�������
	 * @param key
	 * @return
	 */
	public static String getEnv(String key) {	
		Map<String, String> getenv = System.getenv();
		/*getenv.forEach((hKey,hValue)->{

			System.out.println("key is " + hKey );

			System.out.println("vlaue is " + hValue );

		});*/
		return getenv.get(key);
	}

	/**
	 * ��ȡ�ļ��Ĵ�С
	 * @param fileName
	 * @return
	 */
	public static long getSize(String fileName) {		
		File file = new File(fileName);
		if(!file.exists() || ! file.isFile())
			return 0;
		return file.length();	
	}
	
	/**
	 *  �Ƚ����������ļ��������Ƿ���ͬ
	 * @param src  ԭĿ¼
	 * @param dst  Ŀ��Ŀ¼
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void comparePath(String src,String dst) throws FileNotFoundException, IOException {
		 File srcFile = new File(src);// 
		 File dstFile = new File(dst);//
		 if(!srcFile.exists()) {
			 System.out.println(" Դ�ļ� ������  "  + src);
			 return;
		 }		 
		 if(!dstFile.exists()) {
			 System.out.println(" Ŀ���ļ� ������  "  + dst);
			 return;
		 }
		 if(srcFile.isFile() && dstFile.isFile()) {
			 if(srcFile.length() != dstFile.length()) {
				 System.out.println(" �ļ����Ȳ�һ��" + src);
			 }else {
				 byte[] md5Src = DigestUtils.md5(new FileInputStream(srcFile));
				 byte[] md5Dst = DigestUtils.md5(new FileInputStream(dstFile));
				 String strMd5Src = new String(md5Src);
				 String strMd5Dst = new String(md5Dst);
				 if(!strMd5Src.equals(strMd5Dst)) {
					 System.out.println(" �ļ����ݲ�һ��  " +  src);
				 }
			}
			 return ;
		 }
 
		 if(srcFile.isDirectory()) {
			 // �ݹ飬 ����
			 String[] list = srcFile.list();
			 for (int i = 0; i < list.length; i++) {
				 //Դ�ļ������ļ�·��
				 String childSrcFile = src + "\\" + list[i];
				//Ŀ���ļ������ļ�·��
				 String childDstFile = dst + "\\" + list[i];
				 comparePath(childSrcFile,childDstFile);
			}		 
		 }
	}

	
	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static String read(String fileName) throws IOException {		
		//���ڴ洢�ļ�����
		StringBuilder sb = new StringBuilder();		
		// �����ļ�����
		File file = new File(fileName);	
		//�����ļ�������
		FileInputStream fis = new FileInputStream(file);
		// ����������
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
		String ln=null;
		//���ж���
		while ((ln= br.readLine())!=null) {
			sb.append(ln);
		}		
		closeStream(br,fis);	
		return sb.toString();
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public static List<String> readByLines(String fileName) throws IOException {
		//���ڴ洢�ļ�����
		List<String> lines = new ArrayList();
		// �����ļ�����
		File file = new File(fileName);
		//�����ļ�������
		FileInputStream fis = new FileInputStream(file);
		// ����������
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
		String ln=null;
		//���ж���
		while ((ln= br.readLine())!=null) {
			//sb.append(ln);
			lines.add(ln);
		}
		
		closeStream(br,fis);		
		return lines;
	}
	

	/**
	 *  �ر���
	 * @param stream
	 * @throws IOException 
	 */
	public static void closeStream(Closeable ... stream) throws IOException {
		for (int i = 0; i < stream.length; i++) {
			stream[i].close();
		}
	}
	

	/**
	 * �����ļ�
	 * @param srcFile  Դ�ļ�
	 * @param dstFile  Ŀ���ļ�
	 * @throws IOException 
	 */
	public synchronized static void copy(String srcFileName ,String dstFileName) throws IOException {
		// Դ�ļ�
		File srcFile = new File(srcFileName);
		File dstFile = new File(dstFileName);
		FileInputStream fis = new FileInputStream(srcFile);
		FileOutputStream fos  = new FileOutputStream(dstFile);
		byte b[]=new byte[1024];

		while(fis.read(b)>0) {
			fos.write(b);
		}
		closeStream(fos,fis);
	}
	
}
