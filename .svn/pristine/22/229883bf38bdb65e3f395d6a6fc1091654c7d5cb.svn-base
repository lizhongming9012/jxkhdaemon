package org.jxkh.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

public class GenerateFileName {
	/**
	 * 
	 
	 * @param path
	 *            实际物理路径例如："c://tomcat/webapp/portal//WEB-INF//resource//images//"
	 * @param suffix
	 *            like '.html'
	 * @return
	 */
	public static String generateFileName(String path,
			String suffix)

	{
		StringBuffer fileName = new StringBuffer();
		fileName.append(path);
		UUID uuid = UUID.randomUUID();
		fileName.append(uuid);
		fileName.append(suffix);
		return fileName.toString();
	}
/**
 * 
 * @param content 写入的大字符串内容
 * @param fileName 文件名称，包括完整目录和文件名字及后缀
 */
	public static void generateFile(String content, String fileName)

	{
		File file = new java.io.File(fileName);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();		}
		int length=0;
		char[] buffer=new char[2048];
		BufferedReader br=new BufferedReader(new StringReader(content));

		try {
			file.createNewFile();
			FileWriter writer=new FileWriter(file);
			while((length=br.read(buffer))!=-1)			{
				writer.write(buffer, 0, length);
			}
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	   
		
	}

}
