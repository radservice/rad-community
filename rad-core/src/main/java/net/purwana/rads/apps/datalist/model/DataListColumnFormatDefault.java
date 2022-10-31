package net.purwana.rads.apps.datalist.model;

import net.purwana.rads.plugin.base.ExtDefaultPlugin;

/**
 * A base abstract class to develop a Datalist Column Formatter
 */
public abstract class DataListColumnFormatDefault extends ExtDefaultPlugin implements DataListColumnFormat {
    private DataList datalist;

    public DataList getDatalist() {
        return datalist;
    }

    public void setDatalist(DataList datalist) {
        this.datalist = datalist;
    }
}
