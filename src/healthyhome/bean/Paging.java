package healthyhome.bean;

public class Paging {
	private Integer pageSize;
	private Integer nowPage;
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getNowPage() {
		return nowPage;
	}
	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}
	public Paging(Integer pageSize, Integer nowPage) {
		super();
		this.pageSize = pageSize;
		this.nowPage = nowPage;
	}
	public Paging() {
		super();
	}
	
}
