package com.kisti.edison.SuperMan.model.PROV;

public class PROVTypeDefinition {
	public static String ACTIVITY_NAMESPACE = "edisonA";
	public static String AGENT_NAMESPACE = "edisonG";
	public static String ENTITY_NAMESPACE = "edisonE";
	
	public static String RUNTYPE = ACTIVITY_NAMESPACE+":run";
	public static String UPLOADTYPE = ACTIVITY_NAMESPACE+":upload";
	public static String USERTYPE = AGENT_NAMESPACE+":user_";
	
	
	/****
	 * Return WasAssociatedWith type
	 * @return
	 */
	public static String WasAssociatedWith(String A, String B){
		// A: activity
		// B: agent
		return A + " Was Associated With " + B;
	}
	
	/****
	 * Return WasUsedBy type
	 * @return
	 */
	public static String Used(String A, String B){
		// A: activity
		// B: entity
		return A + " Used " + B;
	}
	
	/****
	 * Return WasGeneratedBy type
	 * @return
	 */
	public static String WasGeneratedBy(String A, String B){
		// A: entity (eg. sim_id, input/output files)
		// B: activity (eg. run)
		return A + " Was Generated By " + B;
	}

	/****
	 * Return WasGeneratedBy type
	 * @return
	 */
	public static String WasAttributedTo(String A, String B){
		// A: entity
		// B: agent (eg. userid)
		return A + " Was Generated By " + B;
	}
}
