package com.cn.riwise.general.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片 需要在定义有个异常
 * 
 * @author guanlongfeng
 * @date 2017年5月8日 下午4:59:07
 * 
 */
public class ImageUrl {
	public static final String tmpurl = "/tmp/image/";
	public static final String windowsurl = "c://";
	public static final String linuxsurl = "//var//www//imageurl//";

	public static void removeurl(String url) {
		File ff = new File(url);
		String yearfile = "" + getFileNameNew().substring(0, 4);
		String monthfile = "//" + getFileNameNew().substring(4, 6);
		String datefile = "//" + getFileNameNew().substring(6, 8);
		String tmpurl = linuxsurl + yearfile + monthfile + datefile;
		File destFile = new File(tmpurl);
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		String newfilestr = destFile + url.split("\\")[url.split("\\").length];
		if (ff.renameTo(new File(newfilestr))) {
			System.out.println("图片移动成功");
		} else {
			System.out.println("图片移动失败！");
		}
		;
	}

	private static String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}
}
