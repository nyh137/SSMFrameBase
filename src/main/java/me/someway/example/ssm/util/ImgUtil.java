package me.someway.example.ssm.util;

import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;

/**
 * 生成不同规格的图片
 */
public class ImgUtil {
    
    /**
     * 生成缩略图
     * @param file
     * @param suffix
     * @param width
     * @param height
     * @throws IOException
     */
    public static void scaleImg(File file,String suffix,int width,int height) throws IOException{
    	if(file.isDirectory()){
    		File[] subFiles = file.listFiles();
    		for (File subFile : subFiles) {
    			scaleImg(subFile, suffix, width, height);
			}
    	}else{
    		double length = file.length();
        	String path = file.getAbsolutePath();
        	int cut = path.lastIndexOf(".");
    		String prefixFile = StringUtils.substring(path, 0, cut);
    		String formatFile = StringUtils.substring(path, cut,path.length());
    		String scaleFileName = prefixFile+suffix+formatFile;
    		double quality = 1;
    		if(length>=100*1000){
    			quality = 0.8;			
    		}
    		Thumbnails.of(file).allowOverwrite(true).size(width,height).outputQuality(quality).toFile(scaleFileName);
    	}
    }
    
    /**
     * 批量添加后缀
     * @param file
     * @param suffix
     */
    public static void appendFileSuffix(File file, String suffix){
    	if(file.isDirectory()){
    		File[] subFiles = file.listFiles();
    		for (File subFile : subFiles) {
    			appendFileSuffix(subFile,suffix);
			}
    	}else{
    		String filePath = file.getAbsolutePath();
    		int cut = filePath.lastIndexOf(".");
    		String prefixFile = StringUtils.substring(filePath, 0, cut);
    		String formatFile = StringUtils.substring(filePath, cut,filePath.length());
    		File outFile = new File(prefixFile+suffix+formatFile);
    		FileUtil.copyFile(file, outFile);
    	}
    }
    
    public static void main(String[] args) throws IOException {
    	//大图片重新保存
    	//appendFileSuffix(new File("D:/data/img/"), "_big");
    	
    	//生成小图片
    	scaleImg(new File("D:/data/img/"), "", 200, 200);
	}
}
