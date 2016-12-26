package com.kisti.edison.SuperMan.model.PROV;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.kisti.edison.SuperMan.Constants;
import com.kisti.edison.SuperMan.DataExtractor.EDISON_DB_Connector;
import com.kisti.edison.SuperMan.model.portal.Simulation;
import com.kisti.edison.SuperMan.model.portal.SimulationJob;
import com.kisti.edison.SuperMan.model.portal.SimulationJobData;
import com.kisti.edison.SuperMan.model.portal.UserInfo;

public class ProvDataCollector {
	private static Vector<Simulation> sim_rec_vector = new Vector<Simulation>();
	private static Vector<SimulationJob> simjob_rec_vector = new Vector<SimulationJob>();
	private static Vector<SimulationJobData> simjobdata_rec_vector = new Vector<SimulationJobData>();
	private static Vector<UserInfo> userinfo_rec_vector = new Vector<UserInfo>();
	
	/****
	 * Flush provenance data for next fetch
	 */
	public static void flushProvenanceData(){
		sim_rec_vector = new Vector<Simulation>();
		simjob_rec_vector = new Vector<SimulationJob>();
		simjobdata_rec_vector = new Vector<SimulationJobData>();
		userinfo_rec_vector = new Vector<UserInfo>();
	}
	
	/****
	 * Collect a simulation provenance data on a given simulation uuid
	 * @param simUuid
	 */
	public static void collectProvenanceData(String simUuid){
		
		// TODO Auto-generated method stub
		if(EDISON_DB_Connector.open(false)){
			System.out.println("Database successfully opened!");
		}
				
		//String query = "SELECT count(*) FROM EDAPP_Solver";
		//String query = "SELECT count(*) FROM EDSIM_Simulation";
		//String query = "SELECT count(*) FROM EDSIM_SimulationJob";
				
		//int numJobCnt = 5;
//				String query = "SELECT "+simUuidFN+", count("+jobSeqNoFN+") as numJobs"
//							+ " FROM " + simJobTblName
//							+ " WHERE "+jobEndDtFN+" - "+jobStartDtFN+" >= "+jobCompletionTimeThreshold+" and " + jobExecPathFN + " like '%"+solverNameVal+"%' "
//							+ " group by "+simUuidFN 
//							+ " having count(*) >= "+numJobCnt
//							+ " order by numJobs asc";
		//System.out.println(query);
		System.out.println("Requested uuid: " + simUuid);		
		String query = 
				"SELECT t0."+Constants.simUuidFN+"," + 
				" t0."+Constants.groupIdFN+","+
			    " t0."+Constants.userIdFN+","+
			    " t0."+Constants.scienceAppIdFN+","+
			    " t0."+Constants.scienceAppNameFN+","+
			    " t1."+Constants.jobUuidFN+","+
			    " t1."+Constants.jobSeqNoFN+","+
			    " t1."+Constants.jobStatusFN+","+
			    " t1."+Constants.jobStartDtFN+","+
			    " t1."+Constants.jobEndDtFN+","+"\n"+ 
			    //" TO_SECONDS(t1."+Constants.jobEndDtFN+") - TO_SECONDS(t1."+Constants.jobStartDtFN+") as elapsedTime,"+ 
			    " SEC_TO_TIME(TO_SECONDS(t1."+Constants.jobEndDtFN+") - TO_SECONDS(t1."+Constants.jobStartDtFN+")) as elapsedTime,"+ 
				" t1."+Constants.jobExecPathFN+","+ 
			    " t1."+Constants.jobUniversityFieldFN+","+ 
			    " t1."+Constants.jobInputDeckYnFN+","+
			    " t1."+Constants.jobInputDeckNameFN+","+
			    " t2."+Constants.jobDataFN+","+
			    " t3."+Constants.screenNameFN+","+
			    " t3."+Constants.firstNameFN+
			    "\n"+
			" FROM "+Constants.SIMULATION_TABLE_NAME+" t0,"+
			" " + Constants.SIMULATION_JOB_TABLE_NAME+" t1,"+
			" " + Constants.USER_TABLE_NAME+" t3,"+
			" " + Constants.SIMULATION_JOB_DATA_TABLE_NAME+" t2\n"+
			" WHERE t0."+Constants.simUuidFN+" = \'"+simUuid+"\'\n" +
			" and t0."+Constants.simUuidFN+" = t1."+Constants.simUuidFN+"\n"+
			" and t1."+Constants.jobUuidFN+" = t2."+Constants.jobUuidFN+"\n"+
			" and t0."+Constants.userIdFN +" = t3."+Constants.userIdFN+"\n"+
			" order by "+Constants.jobSeqNoFN+" asc";
//System.out.println(query);	
//		SELECT t0.simulationUuid, t0.groupId, t0.userId, t0.scienceAppId, t0.scienceAppName, t1.jobUuid, t1.jobSeqNo, t1.jobStatus, t1.jobStartDt, t1.jobEndDt,
//		 SEC_TO_TIME(TO_SECONDS(t1.jobEndDt) - TO_SECONDS(t1.jobStartDt)) as elapsedTime, t1.jobExecPath, t1.jobUniversityField, t1.jobInputDeckYn, t1.jobInputDeckName, t2.jobData, t3.screenName, t3.firstName
//		 FROM EDSIM_Simulation t0, EDSIM_SimulationJob t1, User_ t3, EDSIM_SimulationJobData t2
//		 WHERE t0.simulationUuid = 'e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2'
//		 and t0.simulationUuid = t1.simulationUuid
//		 and t1.jobUuid = t2.jobUuid
//		 and t0.userId = t3.userId
//		 order by jobSeqNo asc
		sim_rec_vector = new Vector<Simulation>();
		ResultSet rs = EDISON_DB_Connector.executeQuery(query);
		try {
			while(rs.next()){
				Simulation sim_rec = new Simulation();
				sim_rec.simUuidVal = rs.getString(1);
				sim_rec.groupIdVal = rs.getLong(2);
				sim_rec.userIdVal = rs.getLong(3);
				sim_rec.scienceAppIdVal = rs.getLong(4);
				sim_rec.scienceAppNameVal = rs.getString(5);
				sim_rec.printContents();
				if(sim_rec_vector.indexOf(sim_rec_vector) < 0)
					sim_rec_vector.add(sim_rec);
				
				SimulationJob simjob_rec = new SimulationJob();
				simjob_rec.jobUuidVal = rs.getString(6);
				simjob_rec.jobSeqNoVal =  rs.getLong(7);
				simjob_rec.jobStatusVal = rs.getLong(8);
				simjob_rec.jobStartDtVal = rs.getString(9);
				simjob_rec.jobEndDtVal = rs.getString(10);
//				simjob_rec.jobCompletionTimeVal = rs.getLong(11);
				simjob_rec.jobCompletionTimeVal = rs.getString(11);
				simjob_rec.jobExecPathVal = rs.getString(12);
				simjob_rec.jobUniversityFieldVal = rs.getInt(13);
				simjob_rec.jobInputDeckYnVal= rs.getShort(14);
				simjob_rec.jobInputDeckNameVal= rs.getString(15);
				simjob_rec.printContents();
				simjob_rec_vector.add(simjob_rec);
				
				SimulationJobData simjobdata_rec = new SimulationJobData();
				simjobdata_rec.jobDataVal = rs.getString(16);
				simjobdata_rec.printContents();
				simjobdata_rec_vector.add(simjobdata_rec);
				

				UserInfo user_rec = new UserInfo();
				user_rec.userIdVal =  rs.getLong(3);
				user_rec.screenNameVal = rs.getString(17);
				user_rec.firstNameVal = rs.getString(18);
				user_rec.printContents();
				if(userinfo_rec_vector.indexOf(user_rec) < 0)
					userinfo_rec_vector.add(user_rec);
								
				
				System.out.println("========================");
				//break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("# of solvers: " + Integer.toString(cnt));
		EDISON_DB_Connector.close();
	}

	public static Vector<Simulation> getSimulationRecords() {
		// TODO Auto-generated method stub
		return sim_rec_vector;
	}

	public static Vector<SimulationJob> getSimulationJobRecords() {
		// TODO Auto-generated method stub
		return simjob_rec_vector;
	}

	public static Vector<SimulationJobData> getSimulationJobDataRecords() {
		// TODO Auto-generated method stub
		return simjobdata_rec_vector;
	}
	
	public static Vector<UserInfo> getUserInfoRecords() {
		// TODO Auto-generated method stub
		return userinfo_rec_vector;
	}

//	query = "SELECT " + simUuidFN + ", " + jobSeqNoFN + ", " + groupIdFN +", " + jobUuidFN + ", "+jobStatusFN+", "+jobStartDtFN+", " + jobEndDtFN + ", "
//					+ jobEndDtFN + "-" +jobStartDtFN + " as " + jobCompletionTimeFN + ", " + jobExecPathFN+", " 
//					+ jobUniversityFieldFN+", "+jobInputDeckYnFN+", "+jobInputDeckNameFN +" "
//					+ "	FROM " + simJobTblName
//					+ " WHERE " + simUuidFN + " = '"+simUuidQueryVal+"'"
//					+ " ORDER BY " + jobSeqNoFN +" ASC ";
//	rs = Main.executeQuery(query);
//	System.out.println(query);
//	try {
//		while(rs.next()){
//			simUuidVal = rs.getString(1);
//			jobSeqNoVal =  rs.getLong(2);
//			groupIdVal = rs.getLong(3);
//			jobUuidVal = rs.getString(4);
//			jobStatusVal = rs.getLong(5);
//			jobStartDtVal = rs.getDate(6);
//			jobEndDtVal = rs.getDate(7);
//			jobCompletionTimeVal = rs.getLong(8);
//			jobExecPathVal = rs.getString(9);
//			jobUniversityFieldVal = rs.getInt(10);
//			jobInputDeckYnVal= rs.getShort(11);
//			jobInputDeckNameVal= rs.getString(11);
////int cnt = rs.getInt(1);	
////					System.out.println("# of solvers: " + Integer.toString(cnt));
//			System.out.println("simUuid: " + simUuidVal);
//			System.out.println("jobSeqNoVal: " + jobSeqNoVal);
//			System.out.println("groupIdVal: " + groupIdVal);
//			System.out.println("jobUuidVal: " + jobUuidVal);
//			System.out.println("jobStatusVal: " + jobStatusVal);
//			System.out.println("jobStartDtVal: " + jobStartDtVal);
//			System.out.println("jobEndDtVal: " + jobEndDtVal);
//			System.out.println("jobCompletionTimeVal: " + jobCompletionTimeVal);
//			System.out.println("jobExecPathVal: " + jobExecPathVal);
//			System.out.println("jobUniversityFieldVal: " + jobUniversityFieldVal);
//			System.out.println("jobInputDeckYnVal: " + jobInputDeckYnVal);
//			System.out.println("jobInputDeckNameVal: " + jobInputDeckNameVal);
//			//break;
//			System.out.println("========================");
//		}
//		rs.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
////				e.printStackTrace();
//	}
}
