package org.jxkh.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 日志的处理工具类,将日志信息和异常信息写入到D:/logs/文件夹中 */
public class LogUtil {

	public static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");

	/** 添加异常信息到日志中 */
	public static void logEx(String info, Exception e) {
		// 日志信息
		addLog(info);
		if (e != null) {
			StackTraceElement[] stes = e.getStackTrace();
			for (StackTraceElement ste : stes) {
				addLog(ste.toString());
			}
		}
	}

	/** 添加日志 */
	public static void addLog(String info) {
		// PrintWriter printer = null;
		FileWriter writer = null;
		try {
			File file = new File("D:/logs/logs.txt");
			if (file.exists() && file.length() > 500000) {// 大约500k
				String path = "D:\\logs\\logs.txt";
				File dest = new File(path + "D:/logs/logs"
						+ sdf2.format(new Date()) + ".txt");
				// 重命名
				if (file.renameTo(dest)) {
				} else {
					file.delete();
				}
				file = new File("D:/logs/logs.txt");
			}
			// 增量写log
			writer = new FileWriter(file, true);
			writer.write(info = (sdf.format(new Date()) + ":" + info));
			// System.out.println(info);
			writer.write("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/** 增量写log */
	public static void testFile(String info) {
		// PrintWriter printer = null;
		FileWriter writer = null;
		try {
			// 增量写log
			writer = new FileWriter("D:/logs/file.txt", true);
			writer.write(info);
			// System.out.println(info);
			writer.write("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
