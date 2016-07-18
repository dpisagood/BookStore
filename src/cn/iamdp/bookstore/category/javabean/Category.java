package cn.iamdp.bookstore.category.javabean;

public class Category {
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Categroy [cid=" + cid + ", cname=" + cname + "]";
	}
	
}
