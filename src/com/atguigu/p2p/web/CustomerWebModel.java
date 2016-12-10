package com.atguigu.p2p.web;

/**
 * 仅限于控制器和前台之间的数据传递
 * @author zy
 *
 */
public class CustomerWebModel 
{
	private String queryJsonStr = "";
	private int    nowPage = 1;
	private int    pageShow = 0;
	
	
	public String getQueryJsonStr() {
		
		return queryJsonStr;
	}
	public void setQueryJsonStr(String queryJsonStr) {
		this.queryJsonStr = queryJsonStr;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPageShow() {
		return pageShow;
	}
	public void setPageShow(int pageShow) {
		this.pageShow = pageShow;
	}
	
}
