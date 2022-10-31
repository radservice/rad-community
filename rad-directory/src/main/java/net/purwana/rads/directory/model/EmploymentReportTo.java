package net.purwana.rads.directory.model;

import net.purwana.rads.commons.spring.model.Auditable;
import java.io.Serializable;

public class EmploymentReportTo implements Serializable, Auditable {

    private String id;
    //join
    private Employment subordinate;
    private Employment reportTo;

    public Employment getReportTo() {
        return reportTo;
    }

    public void setReportTo(Employment reportTo) {
        this.reportTo = reportTo;
    }

    public Employment getSubordinate() {
        return subordinate;
    }

    public void setSubordinate(Employment subordinate) {
        this.subordinate = subordinate;
    }

    public String getAuditTrailId() {
        return subordinate.getId();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
