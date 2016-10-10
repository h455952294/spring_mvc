package com.core.action;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码的控制器
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年10月9日 下午4:46:22
 * @version 1.0
 */
public class BarCodeAction extends ActionSupport {
	
	private static final long serialVersionUID = -4247792425601326088L;
	/** 定义生成二维码图片的宽度 */
	private static final int WIDTH = 300;
	/** 定义生成二维码图片的高度 */
	private static final int HEIGHT = 300;
	/** 定义生成二维码图片中的内容 */
	private String uri;
	
	@Override
	public String execute() throws Exception {
		/** 设置响应的内容为图片 */
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("images/jpeg");
		
		if (uri == null || "".equals(uri)){
			uri = "http://www.fkjava.org";
		}
		
		/** 创建二维码编码的集合 */
		Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		/** 创建二维码字节的转换对象 */
		BitMatrix matrix = new MultiFormatWriter().encode(uri, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
		/** 输出的二维码图片 */
		MatrixToImageWriter.writeToStream(matrix, "jpeg", response.getOutputStream());
		return NONE;
	}

	/** setter and getter method */
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
}
