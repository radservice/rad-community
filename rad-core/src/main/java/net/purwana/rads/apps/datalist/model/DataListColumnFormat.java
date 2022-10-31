package net.purwana.rads.apps.datalist.model;

import net.purwana.rads.plugin.property.model.PropertyEditable;

/**
 * Interface of Datalist Column Formatter plugin
 */
public interface DataListColumnFormat extends PropertyEditable {

    /**
     * Format column value 
     * 
     * @param dataList
     * @param column
     * @param row
     * @param value
     * @return
     */
    String format(DataList dataList, DataListColumn column, Object row, Object value);
}
