package com.core.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import java.io.InputStream;
import java.net.URLDecoder;

/**
 * 专门下载的控制器
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年9月16日 下午2:43:13
 * @version 1.0
 */
public class DownLoadAction extends ActionSupport {
	
	private static final long serialVersionUID = -3807708998063556454L;
	/** 文件下载 */
	private String downFileName;
	/** 下载的文件名 */
	private String fileName;
	/** 下载的文件URL */
	private String fileUrl;
	
	@Override
	public String execute() {
		try{
			// 响应过程：utf-8 -> iso8859-1 -> utf-8 (firefox、chrome、opera)
			// 响应过程：utf-8 -> iso8859-1 -> gbk (msie)
			// 响应过程：utf-8 -> gbk --> iso8859-1 -> gbk (msie、firefox、chrome、opera)
			this.downFileName = URLDecoder.decode(fileName, "gbk"); // utf-8 > gbk
			this.downFileName = new String(this.downFileName.getBytes("gbk"), "iso8859-1");
			this.downFileName +=  "." + FilenameUtils.getExtension(fileUrl);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	/** inputName属性指定的 */
	public InputStream getFileStream(){
		InputStream inputStream = ServletActionContext.getServletContext()
						.getResourceAsStream(fileUrl);
		return inputStream;
	}
	
	/** setter and getter method */
	public String getDownFileName() {
		return downFileName;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		/**try {
			fileName = new String(fileName.getBytes("iso8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		System.out.println(fileUrl);
		this.fileUrl = fileUrl;
	}
}
