package com.kisti.edison.SuperMan;

/****
 * Provenance Framework Main
 * 
 * @author yksuh
 *
 */
public class Main {
	public static void main(String[] args) {
		String simUuidQueryVal = "e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2";
		ProvDataCollector.collectProvenanceData(simUuidQueryVal);
		String provDoc = PROVModeller.constructPROVDoc(ProvDataCollector.getSimulationRecords(), 
									  ProvDataCollector.getSimulationJobRecords(), 
									  ProvDataCollector.getSimulationJobDataRecords());
		PROVDocWriter.writetoFile(provDoc);
		ProvDataCollector.flushProvenanceData();
	}
}
