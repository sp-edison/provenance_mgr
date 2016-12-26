package com.kisti.edison.SuperMan.model.portal;

public class SimulationJob {
	public SimulationJob(){}
	
	public long jobSeqNoVal = -1;
	
	public long groupIdVal = -1;
	
	public long  jobStatusVal = -1;
	
	public long  jobUnivFdVal = -1;
	
	public short jobInputDeckYnVal = -1;
	
	public String jobUuidVal = "";
	
	public String jobStartDt = "";
	
	public String jobEndDt = "";
	
	public String jobInputDeckNameVal = "";
	
	public String jobExecPathVal = "";
	
//	public long jobCompletionTimeVal = -1;
	public String jobCompletionTimeVal = "";
	
	public long jobUniversityFieldVal = -1;
	
//	public Date jobStartDtVal = new Date();
	public String jobStartDtVal = "";
	
//	public Date jobEndDtVal = new Date();
	public String jobEndDtVal = "";

	public void printContents() {
		// TODO Auto-generated method stub
		System.out.println("jobSeqNoVal: " + this.jobSeqNoVal);
		System.out.println("jobUuidVal: " + this.jobUuidVal);
		System.out.println("jobStatusVal: " + this.jobStatusVal);
		System.out.println("jobStartDtVal: " + this.jobStartDtVal);
		System.out.println("jobEndDtVal: " + this.jobEndDtVal);
		System.out.println("jobCompletionTimeVal: " + this.jobCompletionTimeVal);
		System.out.println("jobExecPathVal: " + this.jobExecPathVal);
		System.out.println("jobUniversityFieldVal: " + this.jobUniversityFieldVal);
		System.out.println("jobInputDeckYnVal: " + this.jobInputDeckYnVal);
		System.out.println("jobInputDeckNameVal: " + this.jobInputDeckNameVal);
		//break;
	}
	
	
}
