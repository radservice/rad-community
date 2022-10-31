package net.purwana.rads.report.dao;

import java.util.Collection;
import net.purwana.rads.report.model.ReportWorkflowProcessInstance;

public interface ReportWorkflowProcessInstanceDao {

    public boolean saveReportWorkflowProcessInstance(ReportWorkflowProcessInstance reportWorflowProcessInstance);

    public ReportWorkflowProcessInstance getReportWorkflowProcessInstance(String instanceId);

    public Collection<ReportWorkflowProcessInstance> getReportWorkflowProcessInstanceList(String appId, String appVersion, String processDefId, String sort, Boolean desc, Integer start, Integer rows);

    public Collection<ReportWorkflowProcessInstance> getReportWorkflowProcessInstanceList(String appId, String appVersion, String processDefId, boolean hasSlaOnly, String sort, Boolean desc, Integer start, Integer rows);

    public long getReportWorkflowProcessInstanceListSize(String appId, String appVersion, String processDefId);
}
