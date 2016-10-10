package com.core.common.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * 分页标签处理类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014年9月10日 上午10:11:42
 * @version 1.0
 */
public class PagerTag extends SimpleTagSupport {
	
	/** 请求URL中的占位符 */
	private static final String TAG = "{0}";
	
	/** 当前页码 */
	private int pageIndex;
	/** 每页显示的数量 */
	private int pageSize;
	/** 总记录条数 */
	private int recordCount;
	/** 请求的URL page.action?pageIndex={0}*/
	private String submitUrl;
	/** 样式 */
	private String style = "sabrosus";
	
	/** 总页数 */
	private int totalPage;

	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder res = new StringBuilder();
		StringBuilder str = new StringBuilder();
		/** 判断总记录条数 */
		if (this.recordCount > 0){
			
			/** this.totalPage = (this.recordCount - 1) / this.pageSize + 1; */
			/** 计算出总页数 */
			this.totalPage = (this.recordCount % this.pageSize == 0) 
							? this.recordCount / this.pageSize 
							: (this.recordCount / this.pageSize) + 1;
			/** 控制上一页 与 下一页 加不加标签 */
			if (pageIndex == 1){ // 在首页
				str.append("<span class='disabled'>上一页</span>");
				
				/** 加中间的页码 */
				calcPage(str);
				
				/** 判断是不是只有一页 */
				if (pageIndex == totalPage){
					str.append("<span class='disabled'>下一页</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
					str.append("<a href='"+ tempUrl +"'>下一页</a>");
				}
				
			}else if(pageIndex == totalPage){ // 在尾页
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='"+ tempUrl +"'>上一页</a>");
				
				/** 加中间的页码 */
				calcPage(str);
				
				str.append("<span class='disabled'>下一页</span>");
			}else{ // 在中间
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex - 1));
				str.append("<a href='"+ tempUrl +"'>上一页</a>");
				
				/** 加中间的页码 */
				calcPage(str);
				
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(pageIndex + 1));
				str.append("<a href='"+ tempUrl +"'>下一页</a>");
			}
			/** 拼接最后的结果 */
			int startNum = (this.pageIndex - 1) * this.pageSize + 1; // 开始记录数
			int endNum = this.pageIndex == this.totalPage ? this.recordCount : this.pageIndex * this.pageSize;
			res.append("<table align='center' width='98%' class='"+ style +"' style='font-size:13px;'>")
			   .append("<tr><td>"+ str.toString())
			   .append("跳转到<input type='text' size='2' id='page_jump_num'/>")
			   .append("<input type='button' value='确定' id='page_jump_btn'/>")
			   .append("</td></tr>")
			   .append("<tr align='center'><td>总共<font color='red'>"+ this.recordCount +"</font>条记录，当前显示"+ startNum +"-"+ endNum +"条记录。</td></tr>")
			   .append("</table>");
			res.append("<script type='text/javascript'>");
			res.append("   document.getElementById('page_jump_btn').onclick = function(){")
			   .append("        var pageNum = document.getElementById('page_jump_num').value;")
			   .append("        if (!/^\\d+$/.test(pageNum) || pageNum < 1 || pageNum > "+ totalPage +"){")
			   .append("           alert('请输入[1-"+ this.totalPage +"]之间的页码！');")
			   .append("        }else{")
			   .append("           var submitUrl = '" + this.submitUrl + "';")
			   .append("           window.location = submitUrl.replace('"+ TAG +"', pageNum);")
			   .append("        }")
			   .append("   };");
			res.append("</script>");
			
			
		}else{
			res.append("<table align='center' style='font-size:13px;'><tr><td>")
			   .append("总共<font color='red'>0</font>条记录，当前显示0-0条记录。")
			   .append("</td></tr></table>");
		}
		this.getJspContext().getOut().println(res.toString());
	}
	
	/** 计算中间的页码方法 */
	private void calcPage(StringBuilder str) {
		/** 如果总页数小于10 */
		if (totalPage <= 10){
			/** 一次全部显示页码 */
			for (int i = 1; i <= totalPage; i++){
				/** 判断当页码 */
				if (i == pageIndex){
					str.append("<span class='current'>"+ i +"</span>");
				}else{
					String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
				}
			}
		}else{
			// 靠首页近些 123456789...100
			if (pageIndex <= 8){
				for (int i = 1; i <= 9; i++){
					/** 判断当页码 */
					if (i == pageIndex){
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
				str.append("...").append("<a href='"+ tempUrl +"'>"+ totalPage +"</a>");
			}
			// 靠尾页近些1...9293949596979899100
			else if (pageIndex + 8 >= totalPage){
				
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>").append("...");
				
				for (int i = totalPage - 9; i <= totalPage; i++){
					/** 判断当页码 */
					if (i == pageIndex){
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
				}
			}
			// 在中间1...464748495051525354...100
			else{
				String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+ tempUrl +"'>1</a>").append("...");
				for (int i = pageIndex - 4; i <= pageIndex + 4; i++){
					/** 判断当页码 */
					if (i == pageIndex){
						str.append("<span class='current'>"+ i +"</span>");
					}else{
						tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+ tempUrl +"'>"+ i +"</a>");
					}
					
				}
				tempUrl = this.submitUrl.replace(TAG, String.valueOf(totalPage));
				str.append("...").append("<a href='"+ tempUrl +"'>"+ totalPage +"</a>");
			}
		}
	}

	/** setter and getter method */
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex <= 1 ? 1 : pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 1 ? 1 : pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public String getSubmitUrl() {
		return submitUrl;
	}
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
}
