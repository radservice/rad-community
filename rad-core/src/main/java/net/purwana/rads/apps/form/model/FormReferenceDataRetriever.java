package net.purwana.rads.apps.form.model;

/**
 * This interface is used by net.purwana.rads.apps.form.service.FormUtil.loadFormData() 
 * and net.purwana.rads.apps.form.service.FormUtil.loadFormDataJson() method to fetch 
 * submitted form data values including data from subforms, and reference fields.
 * 
 */
public interface FormReferenceDataRetriever {

    /**
     * Retrieve form data rows for an array of specified primary key values.
     * @param primaryKeyValues
     * @return 
     */
    public FormRowSet loadFormRows(String[] primaryKeyValues, FormData formData);
    
}
