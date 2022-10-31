package net.purwana.rads.report.dao;

import java.util.Collection;
import net.purwana.rads.report.model.ReportWorkflowProcess;

public interface ReportWorkflowProcessDao {

    public boolean saveReportWorkflowProcess(ReportWorkflowProcess reportWorkflowProcess);

    public ReportWorkflowProcess getReportWorkflowProcess(String appId, String appVersion, String processDefId);

    public Collection<ReportWorkflowProcess> getReportWorkflowProcessList(String appId, String appVersion, String sort, Boolean desc, Integer start, Integer rows);

    public long getReportWorkflowProcessListSize(String appId, String appVersion);
}
