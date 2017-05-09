package com.cn.riwise.general.common;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
/**
 * 
 * <p class="detail">
 * 功能：公共Action
 * </p>
 * 
 * @ClassName: BaseAction
 * @version V1.0
 * @date 2014年9月25日
 * @author wangsheng
 */
public class ImageBase {
	private String allowSuffix = "jpg,png,gif,jpeg";// 允许文件格式
	private long allowSize = 2L;// 允许文件大小
	private String fileName;
	private String[] fileNames;

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public long getAllowSize() {
		return allowSize * 1024 * 1024;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：重新命名文件
	 * </p>
	 * 
	 * @author wangsheng
	 * @date 2014年9月25日
	 * @return
	 */
	private String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：文件上传
	 * </p>
	 * 
	 * @author wangsheng
	 * @date 2014年9月25日
	 * @param files
	 * @param destDir
	 * @throws Exception
	 */
	public void uploads(MultipartFile[] files, String destDir,
			HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			fileNames = new String[files.length];
			int index = 0;
			for (MultipartFile file : files) {
				String suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf(".") + 1);
				int length = getAllowSuffix().indexOf(suffix);
				if (length == -1) {
					throw new Exception("请上传允许格式的文件");
				}
				if (file.getSize() > getAllowSize()) {
					throw new Exception("您上传的文件大小已经超出范围");
				}
				String realPath = request.getSession().getServletContext()
						.getRealPath("/");
				File destFile = new File(realPath + destDir);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				String fileNameNew = getFileNameNew() + "." + suffix;//
				File f = new File(destFile.getAbsoluteFile() + "\\"
						+ fileNameNew);
				file.transferTo(f);
				f.createNewFile();
				fileNames[index++] = basePath + destDir + fileNameNew;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * <p class="detail">
	 * 功能：文件上传
	 * </p>
	 * 
	 * @author wangsheng
	 * @date 2014年9月25日
	 * @param files
	 * @param destDir
	 * @throws Exception
	 */
	public void upload(String id,String utype,MultipartFile file,
			HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1) {
				throw new Exception("请上传允许格式的文件");
			}
			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			
			String yearfile=getFileNameNew().substring(0, 4);
			String monthfile="/"+getFileNameNew().substring(4, 6);
			String datefile="/"+getFileNameNew().substring(6, 8);
			String tmpurl=ImageUrl.tmpurl+yearfile+monthfile+datefile;
			File destFile = new File(realPath + tmpurl);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			if(id==null){
				id="0";
			}
			if(utype==null){
				utype="0";
			}
			String fileNameNew = id+"_"+utype+"_"+getFileNameNew().substring(8, 16) + "." + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			fileName = basePath + tmpurl +"/"+ fileNameNew;
		} catch (Exception e) {
			throw e;
		}
	}
}
