package net.purwana.rads.report.dao;

import net.purwana.rads.report.model.ReportWorkflowPackage;

public interface ReportWorkflowPackageDao {

    public boolean saveReportWorkflowPackage(ReportWorkflowPackage reportWorkflowPackage);

    public ReportWorkflowPackage getReportWorkflowPackage(String appId, String appVersion, String packageId, String packageVersion);
}
