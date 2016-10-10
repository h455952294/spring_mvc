package com.core.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 基础的异步请求控制器
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月10日 上午9:39:35
 * @version 1.0
 */
public abstract class AbstractAjaxAction extends ActionSupport {
	
	private static final long serialVersionUID = -7438818431786337305L;

	@Override
	public String execute() {
		try{
			/** 调用子类处理业务 */
			String json = this.ajaxTask();
			System.out.println(json);
			
			/** 对响应数据进行压缩,提高响应速度 */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			/** 创建GZIP压缩对象 */
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(json.getBytes("utf-8"));
			gzip.flush();
			gzip.close();
			
			/** 告诉浏览器响应数据用GZIP压缩的 */
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("Content-Encoding", "GZIP");
			response.setContentType("text/html;charset=UTF-8");
			/** 向浏览器输出响应数据 */
			response.getOutputStream().write(bos.toByteArray());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return NONE;
	}
	
	/** 由子类实现业务处理 */
	protected abstract String ajaxTask() throws Exception;
}
