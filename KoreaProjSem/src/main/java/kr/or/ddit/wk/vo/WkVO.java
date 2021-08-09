package kr.or.ddit.wk.vo;

import java.util.Date;

public class WkVO {
	private int empNum;
	private int siteNum;
	private Date wkStartDt;
	
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public int getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}
	public Date getWkStartDt() {
		return wkStartDt;
	}
	public void setWkStartDt(Date wkStartDt) {
		this.wkStartDt = wkStartDt;
	}
	@Override
	public String toString() {
		return "WkVO [empNum=" + empNum + ", siteNum=" + siteNum + ", wkStartDt=" + wkStartDt + "]";
	}
	
}
