package com.kisti.edison.SuperMan.executable;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kisti.edison.SuperMan.Constants;
import com.kisti.edison.SuperMan.model.PROV.PROVDocWriter;
import com.kisti.edison.SuperMan.model.PROV.PROVModeller;
import com.kisti.edison.SuperMan.model.PROV.ProvDataCollector;
import com.kisti.edison.SuperMan.utility.SuperManLogger;


/****
 * Provenance Framework Main
 * 
 * @author yksuh
 *
 */
public class Main {
	
	/****
	 * Observer or executor logger for logging
	 */
	public static SuperManLogger _logger;

	/****
	 * Set either an superman-logger for its own logging.
	 * 
	 * @param loggerTypeName
	 *            SuperMan
	 */
	public static void setSuperManLogger(String loggerTypeName) {
		_logger = new SuperManLogger(loggerTypeName);
		String computername = "";
		try {
			computername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.err.println("The executable cannot get host name");
			System.err.println("Please retry again.");
			System.exit(-1);
		}
		SimpleDateFormat logsdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String strLoggingTime = logsdf.format(new Date(System
				.currentTimeMillis()));
		
		String logFileName = Constants.SUPERMAN_LOG_DIR_NAME + File.separator
				+ computername + "." + loggerTypeName + "." + strLoggingTime
				+ ".log";
		
		try {
			_logger.setAZDBLabAppender(computername,strLoggingTime,loggerTypeName,logFileName);
		} catch (IOException e) {
			// e.printStackTrace();
			System.err.println("Appender cannot be created due to permission.");
			System.err.println(loggerTypeName + " shuts down. ");
			System.err.println("Please retry again.");
			System.exit(-1);
		}
		_logger.setAZDBLabLoggerName(logFileName);
	}
	
	public static void main(String[] args) {
		
		// Set a logger for Superman.
		Main.setSuperManLogger(Constants.SUPERMAN_NAME);
		
		String simUuidQueryVal = "e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2";
		ProvDataCollector.collectProvenanceData(simUuidQueryVal);
		String provDoc = PROVModeller.constructPROVDoc(
									  ProvDataCollector.getUserInfoRecords(),
									  ProvDataCollector.getSimulationRecords(), 
									  ProvDataCollector.getSimulationJobRecords(), 
									  ProvDataCollector.getSimulationJobDataRecords());
		PROVDocWriter.writetoFile(provDoc);
		ProvDataCollector.flushProvenanceData();
	}
}
