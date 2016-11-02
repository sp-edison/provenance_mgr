SELECT t0.groupid, t0.scienceAppName, t0.simulationUuid, avg(TIME_TO_SEC(TIMEDIFF(jobEndDt,jobStartDt))) as avgComplTimeInSec, count(t1.jobSeqNo) as numJobs 
FROM EDSIM_Simulation t0, 
     EDSIM_SimulationJob t1
WHERE t0.simulationUuid = t1.simulationUuid 
group by groupid, scienceAppName, simulationUuid
having count(*) > 10 
order by avgComplTimeInSec desc, numJobs desc

SELECT simulationUuid, jobSeqNo, TIME_TO_SEC(TIMEDIFF(jobEndDt,jobStartDt)) as completedTime 
FROM `EDSIM_SimulationJob` 
where simulationUuid = 'a30a9f1e-b933-4a65-8ff3-4049ac4b137d'
ORDER BY `EDSIM_SimulationJob`.`jobSeqNo` ASC

54dcae8f-fc31-47e8-b67a-b6ff2b1a924c


--and jobEndDt - jobStartDt >= 60 
SELECT simulationUuid, count(jobSeqNo) as numJobs 
FROM `EDSIM_SimulationJob` 
where jobEndDt - jobStartDt >= 60 
group by simulationUuid 
having count(*) > 10 
order by numJobs asc LIMIT 0, 10;

SELECT simulationUuid, count(jobSeqNo) as numJobs 
FROM `EDSIM_SimulationJob` 
where jobEndDt - jobStartDt >= 60 
--and jobExecPath like '%2D_Comp%'
group by simulationUuid 
having count(*) >= 10
order by numJobs asc


simulationUuid numJobs 

e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2 5 
b97e0413-e629-40ec-bb77-94862712ecf7 6 
2fc6a907-f1d5-4b38-b925-d3880343e622 6 
ef9ddf8c-062e-4b5b-a8ee-07247e39cc54 8 
600dd1d2-16f1-41fb-83cb-aa918b6c8cd0 10 
f0383f54-035e-4e8a-ac9a-eaa498a906ef 12 
a30a9f1e-b933-4a65-8ff3-4049ac4b137d 13 
81230dd8-fd85-471e-ba36-1a9760a4c384 13 
ed11deb0-0d1a-42d5-89a9-4c852b1fe7c1 13 
9b019c84-7bbd-4f80-bda2-937ae818cc93 16 

SELECT simulationUuid, jobSeqNo, 
groupId, jobUuid, jobStatus, jobStartDt,
jobEndDt, jobExecPath, jobUniversityField,  jobInputDeckYn, jobInputDeckName
FROM `EDSIM_SimulationJob` 
where simulationUuid = 'e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2'
ORDER BY `EDSIM_SimulationJob`.`jobSeqNo` ASC


SELECT t0.simulationUuid, 
       t0.groupid,
       t0.userId,
       t0.scienceAppId,
       t0.scienceAppName,
       t1.jobUuid, 
       t1.jobSeqNo, 
       t1.jobStatus, 
       t1.jobStartDt,
       t1.jobEndDt, 
       t1.jobEndDt - t1.jobStartDt as elapsedTime,
       t1.jobExecPath, 
       t1.jobUniversityField, 
       t1.jobInputDeckYn, 
       t1.jobInputDeckName,
       t2.jobData
FROM EDSIM_Simulation t0, 
     EDSIM_SimulationJob t1,
     EDSIM_SimulationJobData t2
WHERE t0.simulationUuid = 'e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2' 
and t0.simulationUuid = t1.simulationUuid 
and t1.jobUuid = t2.jobUuid
order by jobSeqNo asc

INSERT INTO NSOExpl_S4_New_Var (DBMS, runID, QUERYNUM, PK_PR, NUM_REPEATS, SEC_IDX_PR, SQ_PR, SKEW_PR)
	SELECT dbms, runid, querynum, PK_PR as 0,  FROM SKEW_S4_CTQATC;



simulationUuid  jobSeqNo groupId jobUuid jobStatus jobStartDt jobEndDt jobExecPath jobUniversityField jobInputDeckYn jobInputDeckName 
 
e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2 1 23212 a4729361-1379-4c4d-aedc-838ed9ca9829 1701011 2016-09-03 03:06:44 2016-09-03 15:07:42 /EDISON/SOLVERS/2D_Comp_P/2.5.0/bin/2D_Comp_P 1501999 1 -param 
e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2 2 23212 585a7cd7-0919-4824-af8e-fdf024b2d174 1701011 2016-09-03 03:06:53 2016-09-03 15:41:19 /EDISON/SOLVERS/2D_Comp_P/2.5.0/bin/2D_Comp_P 1501999 1 -param 
e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2 3 23212 8fb7a4d8-c961-4fb1-a54f-d8273219a09f 1701011 2016-09-03 03:07:07 2016-09-03 20:06:17 /EDISON/SOLVERS/2D_Comp_P/2.5.0/bin/2D_Comp_P 1501999 1 -param 
e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2 4 23212 82969e74-ac57-4566-bff6-79895b249caa 1701011 2016-09-03 03:07:15 2016-09-04 02:16:15 /EDISON/SOLVERS/2D_Comp_P/2.5.0/bin/2D_Comp_P 1501999 1 -param 
e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2 5 23212 b3d2444a-ba11-48ce-bc00-083b1213ba06 1701011 2016-09-03 03:07:22 2016-09-04 05:17:32 /EDISON/SOLVERS/2D_Comp_P/2.5.0/bin/2D_Comp_P 1501999 1 -param 

