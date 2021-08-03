package kr.or.ddit.wk.vo;

public class WkVO {

	private String empNum;
	private String siteNum;
	
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getSiteNum() {
		return siteNum;
	}
	public void setSiteNum(String siteNum) {
		this.siteNum = siteNum;
	}
	@Override
	public String toString() {
		return "WkVO [empNum=" + empNum + ", siteNum=" + siteNum + "]";
	}
	
	
}
