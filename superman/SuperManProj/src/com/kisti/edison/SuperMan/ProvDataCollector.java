package com.kisti.edison.SuperMan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ProvDataCollector {
	private static Vector<Simulation> sim_rec_vector = new Vector<Simulation>();
	private static Vector<SimulationJob> simjob_rec_vector = new Vector<SimulationJob>();
	private static Vector<SimulationJobData> simjobdata_rec_vector = new Vector<SimulationJobData>();
	
	/****
	 * Flush provenance data for next fetch
	 */
	public static void flushProvenanceData(){
		sim_rec_vector = new Vector<Simulation>();
		simjob_rec_vector = new Vector<SimulationJob>();
		simjobdata_rec_vector = new Vector<SimulationJobData>();
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
			    " t1."+Constants.jobEndDtFN+" - t1."+Constants.jobStartDtFN+" as elapsedTime,"+ 
			    " t1."+Constants.jobExecPathFN+","+ 
			    " t1."+Constants.jobUniversityFieldFN+","+ 
			    " t1."+Constants.jobInputDeckYnFN+","+
			    " t1."+Constants.jobInputDeckNameFN+","+
			    " t2."+Constants.jobDataFN+"\n"+
			" FROM "+Constants.SIMULATION_TABLE_NAME+" t0,"+
			" " + Constants.SIMULATION_JOB_TABLE_NAME+" t1,"+
			" " + Constants.SIMULATION_JOB_DATA_TABLE_NAME+" t2\n"+
			" WHERE t0."+Constants.simUuidFN+" = \'"+simUuid+"\'\n" +
			" and t0."+Constants.simUuidFN+" = t1."+Constants.simUuidFN+"\n"+
			" and t1."+Constants.jobUuidFN+" = t2."+Constants.jobUuidFN+"\n"+
			" order by "+Constants.jobSeqNoFN+" asc";
//System.out.println(query);	
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
//				sim_rec_vector.add(sim_rec);
				
				SimulationJob simjob_rec = new SimulationJob();
				simjob_rec.jobUuidVal = rs.getString(6);
				simjob_rec.jobSeqNoVal =  rs.getLong(7);
				simjob_rec.jobStatusVal = rs.getLong(8);
				simjob_rec.jobStartDtVal = rs.getString(9);
				simjob_rec.jobEndDtVal = rs.getString(10);
				simjob_rec.jobCompletionTimeVal = rs.getLong(11);
				simjob_rec.jobExecPathVal = rs.getString(12);
				simjob_rec.jobUniversityFieldVal = rs.getInt(13);
				simjob_rec.jobInputDeckYnVal= rs.getShort(14);
				simjob_rec.jobInputDeckNameVal= rs.getString(15);
				simjob_rec.printContents();
//				simjob_rec_vector.add(simjob_rec);
				
	////int cnt = rs.getInt(1);	
				
				SimulationJobData simjobdata_rec = new SimulationJobData();
				simjobdata_rec.jobDataVal = rs.getString(16);
				simjobdata_rec.printContents();
//				simjobdata_rec_vector.add(simjobdata_rec);
				//System.out.println("========================");
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
