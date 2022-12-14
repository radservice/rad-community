package net.purwana.rads.apps.generator.model;

import net.purwana.rads.apps.app.model.AppDefinition;
import net.purwana.rads.plugin.base.ExtDefaultPlugin;
import net.purwana.rads.plugin.property.model.PropertyEditable;
import net.purwana.rads.plugin.property.service.PropertyUtil;

/**
 * A base abstract class to develop a Generator plugin. 
 * 
 */
public abstract class GeneratorPlugin extends ExtDefaultPlugin implements PropertyEditable {
    protected String formId;
    protected AppDefinition appDef;

    /**
     * Gets the current working form id  
     * @return 
     */
    public String getFormId() {
        return formId;
    }

    /**
     * Sets the current working form id
     * @param formId 
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /** 
     * Gets the current working App Definition
     * @return 
     */
    public AppDefinition getAppDefinition() {
        return appDef;
    }

    /**
     * Sets the current working App Definition
     * @param appDef 
     */
    public void setAppDefinition(AppDefinition appDef) {
        this.appDef = appDef;
    }
    
    /**
     * Flag to decide whether or not to disable this generator
     * @return 
     */
    public boolean isDisabled() {
        return false;
    }
    
    /**
     * Convenience method to get default properties from Plugin Properties Options
     * @return 
     */
    public String getDefaultPropertyValues(){
        return PropertyUtil.getDefaultPropertyValues(getPropertyOptions());
    }

    /**
     * Gets the explanation and usage for this generator to display for admin user
     * @return 
     */
    public abstract String getExplanation();

    /**
     * Execute the generator to generate/scaffolding work for app
     * based on current working form.
     * 
     * Please utilize net.purwana.rads.apps.generator.service.GeneratorUtil in the 
     * generation logic
     * 
     * @return 
     */
    public abstract GeneratorResult generate();

}
