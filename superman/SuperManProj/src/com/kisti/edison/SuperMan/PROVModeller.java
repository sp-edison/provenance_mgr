package com.kisti.edison.SuperMan;

import java.util.Vector;

public class PROVModeller {
	
	/*****
	 * Construct a PROV document based on given data
	 * @param simVec a vector of simulation records
	 * @param simJobVec a vector of simulation job records
	 * @param simJobDataVec a vector of simulation job data records
	 */
	public static String constructPROVDoc(Vector<Simulation> simVec, 
										Vector<SimulationJob> simJobVec,
										Vector<SimulationJobData> simJobDataVec
	){
		String res = "";
		// if a simulation record exists, 
		// then create a PROV doc
		int numSims = simVec.size();
		if(numSims > 0){
			for(int i=0;i< numSims;i++){
				Simulation simRec = simVec.elementAt(i);
				Long user = simRec.userIdVal;
				String sciAppName = simRec.scienceAppNameVal;
//				res = PROVTypeDefinition.Used(user.toString(), sciAppName);
//				res += PROVTypeDefinition.WasAssociatedWith(, sciAppName);
//				res += PROVTypeDefinition.Used(user.toString(), sciAppName);
				res = PROVTypeDefinition.WasAssociatedWith(A, B)
				break;
			}
		}
		return res;
	}
}
