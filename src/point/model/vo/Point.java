package point.model.vo;

import java.sql.Date;

public class Point {
	private int ptNum;
	private int ptUse;
	private int ptAdd;
	private int ptCate;
	private Date ptDate;
	private int ptdNum;
	private int ptcSerial;
	private String us_id;
	private String ptHis;
	
	public Point() {}

	public Point(int ptNum, int ptUse, int ptAdd, int ptCate, Date ptDate, int ptdNum, int ptcSerial, String us_id, String ptHis) {
		super();
		this.ptNum = ptNum;
		this.ptUse = ptUse;
		this.ptAdd = ptAdd;
		this.ptCate = ptCate;
		this.ptDate = ptDate;
		this.ptdNum = ptdNum;
		this.ptcSerial = ptcSerial;
		this.us_id = us_id;
		this.ptHis = ptHis;
	}

	public Point(int ptNum, int ptUse, int ptAdd, int ptCate, Date ptDate, String us_id) {
		super();
		this.ptNum = ptNum;
		this.ptUse = ptUse;
		this.ptAdd = ptAdd;
		this.ptCate = ptCate;
		this.ptDate = ptDate;
		this.us_id = us_id;
	}	
	
	public Point(int ptNum, int ptUse, int ptAdd, int ptCate, Date ptDate, String us_id, String ptHis) {
		super();
		this.ptNum = ptNum;
		this.ptUse = ptUse;
		this.ptAdd = ptAdd;
		this.ptCate = ptCate;
		this.ptDate = ptDate;
		this.us_id = us_id;
		this.ptHis = ptHis;
	}
	
	
	public String getPtHis() {
		return ptHis;
	}

	public void setPtHis(String ptHis) {
		this.ptHis = ptHis;
	}

	public int getPtNum() {
		return ptNum;
	}

	public void setPtNum(int ptNum) {
		this.ptNum = ptNum;
	}

	public int getPtUse() {
		return ptUse;
	}

	public void setPtUse(int ptUse) {
		this.ptUse = ptUse;
	}

	public int getPtAdd() {
		return ptAdd;
	}

	public void setPtAdd(int ptAdd) {
		this.ptAdd = ptAdd;
	}

	public int getPtCate() {
		return ptCate;
	}

	public void setPtCate(int ptCate) {
		this.ptCate = ptCate;
	}

	public Date getPtDate() {
		return ptDate;
	}

	public void setPtDate(Date ptDate) {
		this.ptDate = ptDate;
	}

	public int getPtdNum() {
		return ptdNum;
	}

	public void setPtdNum(int ptdNum) {
		this.ptdNum = ptdNum;
	}

	public int getPtcSerial() {
		return ptcSerial;
	}

	public void setPtcSerial(int ptcSerial) {
		this.ptcSerial = ptcSerial;
	}

	public String getUs_id() {
		return us_id;
	}

	public void setUs_id(String us_id) {
		this.us_id = us_id;
	}

	@Override
	public String toString() {
		return "Point [ptNum=" + ptNum + ", ptUse=" + ptUse + ", ptAdd=" + ptAdd + ", ptCate=" + ptCate + ", ptDate="
				+ ptDate + ", ptdNum=" + ptdNum + ", ptcSerial=" + ptcSerial + ", us_id=" + us_id + ", ptHis=" + ptHis
				+ "]";
	}

	
}
