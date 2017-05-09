package com.cn.riwise.general.common;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File ff=new File("E:\\workspace\\.metadata\\.me_tcat7\\webapps\\General_riwise_cn\\tmp\\imgage\\2017\\05\\08\\20170508153226027.png");
		if(ff.renameTo(new File("E:\\20170508153226027.png"))){
			   System.out.println("File is moved successful!");
		}else{
			 System.out.println("File is failed to move!");
		};
	}
}
