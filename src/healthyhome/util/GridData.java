package healthyhome.util;

import java.util.List;

/**
 * jqGrid查询返回的数据封装类
 * 
 */
public class GridData<T> {
    /**
     * 当前页
     */
    private long page;
    /**
     * 总页数
     */
    private long total;
    /**
     * 总记录数据
     */
    private long records;
    /**
     * 查询返回的数据
     */
    private List<T> rows;

    /**
     * @return the page
     */
    public long getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(long page) {
        this.page = page;
    }

    /**
     * @return the records
     */
    public long getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(long records) {
        this.records = records;
    }

    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * @return the total
     */
    public long getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(long total) {
        this.total = total;
    }

}
