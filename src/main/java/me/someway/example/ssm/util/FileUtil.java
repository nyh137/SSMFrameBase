package me.someway.example.ssm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 *
 */
public class FileUtil {
	private static final Logger LOG = Logger.getLogger(FileUtil.class);
	
	/**
	 * 
	 * I/O 读写文件
	 * @param inFile
	 * @param outFile
	 */
	public static void copyFile(File inFile, File outFile) {
		try {
			InputStream inStream= new FileInputStream(inFile);
			OutputStream outStream = new FileOutputStream(outFile);
			copyFile(inStream, outStream);
		} catch (FileNotFoundException e) {
			LOG.error("找不到文件", e);
		}
	}
	
	
	/**
	 * I/O读写    单独使用时多用于文件读取输出到页面
	 * @param inStream
	 * @param outStream response.getOutputStream
	 */
	public static void copyFile(InputStream inStream, OutputStream outStream) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(inStream);// 输入缓冲流
			bos = new BufferedOutputStream(outStream);// 输出缓冲流
			byte data[] = new byte[4096];// 缓冲字节数
			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
		} catch (IOException e) {
			LOG.error("文件读写失败：", e);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.flush();
					bos.close();
				}
				if (outStream != null) {
					outStream.flush();
					outStream.close();
				}
			} catch (IOException e) {
				LOG.error("IO异常", e);
			}
		}
	}
	
	
	
	
	
}
