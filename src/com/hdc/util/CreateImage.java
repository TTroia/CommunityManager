package com.hdc.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 图片合成
 * 
 * @author Dan
 * @date 2015-11-7 下午7:13:39
 */
public class CreateImage {

	/**
	 * 两张图片合成一张图片
	 * @param filePath 二维码路径
	 * @param file2Path 头像路径
	 * @param savePath 合成图片路径
	 * @param x
	 * @param y
	 */
	public static boolean createPicTwo(String filePath,String file2Path,
			String savePath, int x, int y,int x2, int y2) {
		try {
			// 读取第一张图片
			File fileOne = new File("d:\\data\\WebRoot\\myLegou\\meitu.jpg");
			//File fileOne = new File("d:\\meitu.jpg");
			BufferedImage bufferImage = ImageIO.read(fileOne);
			int width = bufferImage.getWidth();// 图片宽度
			int height = bufferImage.getHeight();// 图片高度
			// 从图片中读取RGB
			int[] ImageArrayOne = new int[width * height];
			ImageArrayOne = bufferImage.getRGB(0, 0, width, height, ImageArrayOne, 0, width);
			
			// 对第二张图片做相同的处理 二维码
			File fileTwo = new File(filePath);
			ImageZoomUtil.resizeFix(fileTwo, fileTwo, 210, 210);
			BufferedImage butterImageTwo = ImageIO.read(fileTwo);
			int widthTwo = butterImageTwo.getWidth();// 图片宽度
			int heightTwo = butterImageTwo.getHeight();// 图片高度
			int[] ImageArrayTwo = new int[widthTwo * heightTwo];
			ImageArrayTwo = butterImageTwo.getRGB(0, 0, widthTwo, heightTwo,
					ImageArrayTwo, 0, widthTwo);
			
			// 生成新图片
			BufferedImage newImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			newImage.setRGB(0, 0, width, height, ImageArrayOne, 0, width);// 设置RGB
			newImage.setRGB(x, y, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);
			
			// 对第三张图片做相同的处理 头像
			if(file2Path!=null&&file2Path!=""){
				File fileThd = new File(file2Path);
				BufferedImage butterImageThd = ImageIO.read(fileThd);
				int widthThd = butterImageThd.getWidth();// 图片宽度
				int heightThd = butterImageThd.getHeight();// 图片高度
				int[] ImageArrayThd = new int[widthThd * heightThd];
				ImageArrayThd = butterImageThd.getRGB(0, 0, widthThd, heightThd,
						ImageArrayThd, 0, widthThd);
				
				newImage.setRGB(x2, y2, widthThd, heightThd, ImageArrayThd, 0, widthThd);
			}

			File outFile = new File(savePath);
			ImageIO.write(newImage, "jpg", outFile);// 写图片
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 根据网址下载图片
	 * @param fileUrl
	 * @param savePath
	 * @return
	 */
	public static boolean downloadPic(String fileUrl,String savePath){
		try {
			URL url=new URL(fileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			BufferedInputStream bis=new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos=new FileOutputStream(new File(savePath));
			byte[] buf=new byte[8096];
			int size=0;
			while ((size=bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.close();
			bis.close();
			conn.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//头像 64x64 从（28，28）
		//二维码 210x210 从（138，515）
		String headUrl="http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0";
		headUrl=headUrl.substring(0, headUrl.lastIndexOf('/')+1)+"64";
		String headPath="D:/head.jpg";
		CreateImage.downloadPic(headUrl, headPath);
		createPicTwo("d:\\testqrcode.jpg",headPath,
				"d:\\out.jpg", 138, 515, 28, 28);
	}

}
