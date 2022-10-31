package net.purwana.rads.directory.dao;

import net.purwana.rads.directory.model.EmploymentReportTo;

public interface EmploymentReportToDao {

    Boolean addEmploymentReportTo(EmploymentReportTo employmentReportTo);

    Boolean updateEmploymentReportTo(EmploymentReportTo employmentReportTo);

    Boolean deleteEmploymentReportTo(String id);

    EmploymentReportTo getEmploymentReportTo(String id);
}
