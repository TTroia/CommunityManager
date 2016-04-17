package com.hdc.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageZoomUtil {

	/**
	 * 强制压缩/放大图片到固定的大小，不判断比例
	 * Graphics缩放都是针对"图形"而不是"图像"的，所以处理后图片很不清晰
	 * @param srcFile 源文件
	 * @param destFile 目标文件
	 * @param zoomWidth 目标宽度
	 * @param zoomHeight
	 * @throws IOException
	 */
	public static void resizeFix(File srcFile, File destFile, int zoomWidth,
			int zoomHeight) throws IOException {
		
		BufferedImage srcBufferImage = javax.imageio.ImageIO.read(srcFile);//构造Image对象
		int width = srcBufferImage.getWidth();//原图片宽度
		int height = srcBufferImage.getHeight();
		//图片压缩或拉伸
		if ((Math.abs(width-zoomWidth)>=2) || (Math.abs(height-zoomHeight)>=2)) {
			//BufferedImage tag = new BufferedImage(zoomWidth,zoomHeight,BufferedImage.TYPE_INT_RGB);

			BufferedImage to = new BufferedImage(zoomWidth,zoomHeight, BufferedImage.TYPE_INT_RGB);  
 
            Graphics2D g2d = to.createGraphics();  
 
            to = g2d.getDeviceConfiguration().createCompatibleImage(zoomWidth, zoomHeight,  
                    Transparency.TRANSLUCENT);  
            g2d.dispose();  
            g2d = to.createGraphics();  
            Image from = srcBufferImage.getScaledInstance(zoomWidth,zoomHeight, srcBufferImage.SCALE_AREA_AVERAGING);  
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();  
 
            ImageIO.write(to, "png", destFile);
		} 
	}
	
	/**
	 * 对图片进行等比例缩放
	 * @param srcFile
	 * @param destFile
	 * @param zoomWidth
	 * @param zoomHeight
	 * @throws IOException
	 */
	public static void resizeZoom(File srcFile, File destFile, int zoomWidth,
			int zoomHeight) throws IOException {
		
		BufferedImage srcBufferImage = javax.imageio.ImageIO.read(srcFile);
		int width = srcBufferImage.getWidth();
		int height = srcBufferImage.getHeight();
		if (width > zoomWidth || height > zoomHeight) {
			if ((float) width / height > (float) zoomWidth / zoomHeight) {
				resizeFix(srcFile, destFile, zoomWidth, Math.round((float) zoomWidth * height / width));
			} else {
				resizeFix(srcFile, destFile, Math.round((float) zoomHeight * width / height), zoomHeight);
			}
		}
	}
	
}
