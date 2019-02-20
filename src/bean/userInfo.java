package bean;

import java.io.Serializable;

public class userInfo implements Serializable{
	private String careCity;
	private String portrait;
	private int resultStatus;
	public String getCareCity() {
		return careCity;
	}
	public void setCareCity(String careCity) {
		this.careCity = careCity;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public int getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	
}
