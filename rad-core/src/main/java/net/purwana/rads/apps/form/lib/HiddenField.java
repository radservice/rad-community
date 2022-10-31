package net.purwana.rads.apps.form.lib;

import java.util.Map;
import net.purwana.rads.apps.app.service.AppUtil;
import net.purwana.rads.apps.form.model.Element;
import net.purwana.rads.apps.form.model.FormBuilderPaletteElement;
import net.purwana.rads.apps.form.model.FormBuilderPalette;
import net.purwana.rads.apps.form.model.FormData;
import net.purwana.rads.apps.form.service.FormUtil;
import net.purwana.rads.commons.util.ResourceBundleUtil;

public class HiddenField extends Element implements FormBuilderPaletteElement {

    @Override
    public String getName() {
        return "Hidden Field";
    }

    @Override
    public String getVersion() {
        return "5.0.0";
    }

    @Override
    public String getDescription() {
        return "Hidden Field Element";
    }

    @Override
    public String renderTemplate(FormData formData, Map dataModel) {
        String template = "hiddenField.ftl";

        // set value
        String value = FormUtil.getElementPropertyValue(this, formData);
        String priority = getPropertyString("useDefaultWhenEmpty");
        
        if (priority != null && !priority.isEmpty()) {
            if (("true".equals(priority) && (value == null || value.isEmpty()))
                    || "valueOnly".equals(priority)) {
                value = getPropertyString("value");
            }
        } else {
            if (getPropertyString("value") != null && !getPropertyString("value").isEmpty()) {
                value = getPropertyString("value");
            }
        } 

        dataModel.put("value", value);

        String html = FormUtil.generateElementHtml(this, formData, template, dataModel);
        return html;
    }

    @Override
    public String getClassName() {
        return getClass().getName();
    }

    @Override
    public String getFormBuilderTemplate() {
        return "<label class='label'>" + ResourceBundleUtil.getMessage("net.purwana.rads.apps.form.lib.HiddenField.pluginLabel") + "</label>";
    }

    @Override
    public String getLabel() {
        return "Hidden Field";
    }

    @Override
    public String getPropertyOptions() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/form/hiddenField.json", null, true, "message/form/HiddenField");
    }

    @Override
    public String getFormBuilderCategory() {
        return FormBuilderPalette.CATEGORY_GENERAL;
    }

    @Override
    public int getFormBuilderPosition() {
        return 100;
    }

    @Override
    public String getFormBuilderIcon() {
        return "<i class=\"fas fa-eye-slash\"></i>";
    }
    
    //Force to be readonly when it set to `Always Use Default Value`
    @Override
    public Boolean isReadonly(FormData formData) {
        if ("valueOnly".equalsIgnoreCase(getPropertyString("useDefaultWhenEmpty"))) {
            return true;
        } else {
            return super.isReadonly(formData);
        }
    }
}
