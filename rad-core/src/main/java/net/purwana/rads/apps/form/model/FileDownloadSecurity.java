package net.purwana.rads.apps.form.model;

import java.util.Map;

public interface FileDownloadSecurity {
    boolean isDownloadAllowed(Map requestParameters);
}
