package net.purwana.rads.apps.app.model;

import net.purwana.rads.apps.form.model.Form;
import net.purwana.rads.apps.form.model.FormData;
import net.purwana.rads.plugin.property.model.PropertyEditable;
import net.purwana.rads.workflow.model.WorkflowAssignment;

public interface ProcessFormModifier extends PropertyEditable {
    
    /**
     * Modify the form element to add process assignment related element
     * 
     * @param form
     * @param formData
     * @param assignment 
     */
    public void modify(Form form, FormData formData, WorkflowAssignment assignment);
    
    /**
     * Custom assignment form submission handling after form data saved. 
     * Return false to complete assignment as usual.
     * 
     * @param form
     * @param formData
     * @param assignment
     * @return 
     */
    public boolean customSubmissionHandling(Form form, FormData formData, WorkflowAssignment assignment);
}
