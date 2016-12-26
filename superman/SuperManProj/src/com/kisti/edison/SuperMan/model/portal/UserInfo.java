package com.kisti.edison.SuperMan.model.portal;

public class UserInfo{
	
	public UserInfo(){}
		
	public long userIdVal = -1;
	
	public String screenNameVal = "";
	
	public String firstNameVal = "";
	
	public void printContents() {
		// TODO Auto-generated method stub
		System.out.println("userIdVal: " + this.userIdVal);
		System.out.println("screenName: " + this.screenNameVal);
		System.out.println("firstName: " + this.firstNameVal);
		//break;
	}
}
