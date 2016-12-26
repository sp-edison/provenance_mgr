package com.kisti.edison.SuperMan.model.PROV;

import java.util.HashMap;
import java.util.Vector;

import com.kisti.edison.SuperMan.Constants;
import com.kisti.edison.SuperMan.executable.Main;
import com.kisti.edison.SuperMan.model.portal.Simulation;
import com.kisti.edison.SuperMan.model.portal.SimulationJob;
import com.kisti.edison.SuperMan.model.portal.SimulationJobData;
import com.kisti.edison.SuperMan.model.portal.UserInfo;

public class PROVModeller {
	
	/*****
	 * Construct a PROV document based on given data
	 * @param simVec a vector of simulation records
	 * @param simJobVec a vector of simulation job records
	 * @param simJobDataVec a vector of simulation job data records
	 */
	public static String constructPROVDoc(Vector<UserInfo> userVec, 
										Vector<Simulation> simVec, 
										Vector<SimulationJob> simJobVec,
										Vector<SimulationJobData> simJobDataVec
	){
		HashMap<Long, String> userInfoMap = buildUserInfoMap(userVec);
		if(userInfoMap == null){
			System.out.println("There is no user.");
			return null;
		}
			
		String res = "";
		// if a simulation record exists, 
		// then create a PROV doc
		int numSims = simVec.size();
		if(numSims > 0){
			// for each simulation record
			for(int i=0;i< numSims;i++){
				// it gets iterated to fetch necessary information
				Simulation simRec = simVec.elementAt(i);
				
				// get a specific area
				long groupId = simRec.groupIdVal;
								
				// get simulation uuid 
				String simUuid = simRec.simUuidVal;
				
				// get user id
				long userId = simRec.userIdVal;
				// get screen name
				String screenName = userInfoMap.get(new Long(userId));
				if(screenName == null)
					Main._logger.reportError("No user screen name.");
					
				// get science app id
				long scienceAppId = simRec.scienceAppIdVal;
				// get science app name
				String scienceAppName = simRec.scienceAppNameVal;
// ActedOnBehalfOf				
System.out.println("" + scienceAppName + Constants.ACTED_ON_BEHALF_OF + screenName);
				
				// get number of jobs
				int numJobs = simJobVec.size();
				// get number of job data
				int numJobData = simJobVec.size();
				
				long prevNum = 0;
				String prevUuid = "";
				// for each job 
				for(int j=0;j<numJobs;j++){
					
					SimulationJob simJobRec = simJobVec.elementAt(j);
					
					// job uuid 
					String jobUuidVal = simJobRec.jobUuidVal;
					// job sequence number
					long jobSeqNum = simJobRec.jobSeqNoVal;
				
// WasDerivedFrom
String wasDerivedFrom = jobSeqNum + "(" + jobUuidVal +")" + Constants.WAS_DERIVED_FROM;
if(prevNum > 0){
	wasDerivedFrom += prevNum + "("+ prevUuid + ")";
}else{
	wasDerivedFrom += simUuid;
}
System.out.println(wasDerivedFrom);
					
					// job completion time
					String jobCompletedTime = simJobRec.jobCompletionTimeVal;
					// job exec path
					String jobPath = simJobRec.jobExecPathVal;
					
					String jobData = "";
					SimulationJobData simJobDataRec = simJobDataVec.elementAt(j);
					if(simJobDataRec != null){
						jobData = simJobDataRec.jobDataVal;
						// parseJobData(jobData);
					}
// WasUsedBy
String wasUsedBy = "";
if(!jobData.equals("")){
	wasUsedBy = jobData + Constants.WAS_USED_BY + scienceAppName;
}
System.out.println(wasUsedBy);

//WasAttributedTo
String wasAttributedTo = "";
wasAttributedTo = simUuid + Constants.WAS_ATTRIBUTED_TO + scienceAppName;
System.out.println(wasAttributedTo);

					prevNum = jobSeqNum;
					prevUuid = jobUuidVal;
				}
//provFactory.addAgentAttributes(agent1, "mbox", "derek@example.org");				
				
				
				//Long user = simRec.userIdVal;
				//String sciAppName = simRec.scienceAppNameVal;
//				res = PROVTypeDefinition.Used(user.toString(), sciAppName);
//				res += PROVTypeDefinition.WasAssociatedWith(, sciAppName);
//				res += PROVTypeDefinition.Used(user.toString(), sciAppName);
//				res = PROVTypeDefinition.WasAssociatedWith(A, B)
				break;
			}
		}
		return res;
	}

	/*****
	 * Return a map of user's screen name from user id
	 * @param userVec
	 * @return user map 
	 */
	private static HashMap<Long, String> buildUserInfoMap(Vector<UserInfo> userVec) {
		// TODO Auto-generated method stub
		if(userVec == null | userVec.size() == 0) return null;
		else{ 
			HashMap<Long, String> userMap = new HashMap<Long, String>();
			for(int i=0;i<userVec.size();i++){
				UserInfo ui = userVec.get(i);
				userMap.put(new Long(ui.userIdVal), new String(ui.screenNameVal));
			}
			return userMap;
		}
	}
}
