package net.purwana.rads.apps.app.model;

import net.purwana.rads.apps.form.model.Form;
import net.purwana.rads.apps.form.model.FormData;
import net.purwana.rads.plugin.property.model.PropertyEditable;
import net.purwana.rads.workflow.model.WorkflowProcessResult;

public interface StartProcessFormModifier extends PropertyEditable {
    
    /**
     * Modify the form element to add start process related element
     * 
     * @param form
     * @param formData
     * @param processDefId
     */
    public void modify(Form form, FormData formData, String processDefId);
    
    /**
     * Custom start process form submission handling after form data saved.Return null to start process as usual.
     * 
     * @param form
     * @param formData
     * @param result
     * @return 
     */
    public WorkflowProcessResult customSubmissionHandling(Form form, FormData formData, WorkflowProcessResult result);
}
