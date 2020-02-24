package notice.model.vo;

import java.sql.Date;

public class Notice {
	private int rNum;
	private int noNum;
	private String noTitle;
	private Date noDate;
	private String noCon;
	private String auId;
	
	public Notice() {}

	public Notice(int rNum, int noNum, String noTitle, Date noDate, String noCon, String auId) {
		super();
		this.rNum = rNum;
		this.noNum = noNum;
		this.noTitle = noTitle;
		this.noDate = noDate;
		this.noCon = noCon;
		this.auId = auId;
	}
	
	public Notice(int noNum, String noTitle, Date noDate, String noCon, String auId) {
		super();
		this.noNum = noNum;
		this.noTitle = noTitle;
		this.noDate = noDate;
		this.noCon = noCon;
		this.auId = auId;
	}
	
	public Notice(String noTitle, String noCon, String auId) {
		super();
		this.noTitle = noTitle;
		this.noCon = noCon;
		this.auId = auId;
	}
	
	public Notice(int noNum, String noTitle, String noCon, String auId) {
		super();
		this.noNum = noNum;
		this.noTitle = noTitle;
		this.noCon = noCon;
		this.auId = auId;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public int getNoNum() {
		return noNum;
	}

	public void setNoNum(int noNum) {
		this.noNum = noNum;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}

	public Date getNoDate() {
		return noDate;
	}

	public void setNoDate(Date noDate) {
		this.noDate = noDate;
	}

	public String getNoCon() {
		return noCon;
	}

	public void setNoCon(String noCon) {
		this.noCon = noCon;
	}

	public String getAuId() {
		return auId;
	}

	public void setAuId(String auId) {
		this.auId = auId;
	}
	
	
}
