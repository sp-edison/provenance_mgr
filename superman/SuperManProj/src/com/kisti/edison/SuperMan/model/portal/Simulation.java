package com.kisti.edison.SuperMan;

public class Simulation {
	public Simulation(){}
	
	public String simUuidVal = "";

	public long userIdVal = -1;

	public long scienceAppIdVal =  -1;

	public long groupIdVal = -1;

	public String scienceAppNameVal = "";

	public String edisonClusterNameVal = "";

	public void printContents() {
		// TODO Auto-generated method stub
		System.out.println("simUuid: " + this.simUuidVal);
		System.out.println("groupIdVal: " + this.groupIdVal);
		System.out.println("userIdVal: " + this.userIdVal);
		System.out.println("scienceAppIdVal: " + this.scienceAppIdVal);
		System.out.println("scienceAppNameVal: " + this.scienceAppNameVal);
	} 
}
