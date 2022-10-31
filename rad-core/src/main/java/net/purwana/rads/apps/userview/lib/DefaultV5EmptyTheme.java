package net.purwana.rads.apps.userview.lib;

import net.purwana.rads.apps.userview.model.UserviewV5Theme;
import net.purwana.rads.plugin.base.HiddenPlugin;

public class DefaultV5EmptyTheme extends UserviewV5Theme implements HiddenPlugin {

    public String getName() {
        return "V5 Userview Empty Theme";
    }

    public String getVersion() {
        return "5.0.0";
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "Empty Theme";
    }

    public String getClassName() {
        return getClass().getName();
    }

    public String getPropertyOptions() {
        return "";
    }
}
