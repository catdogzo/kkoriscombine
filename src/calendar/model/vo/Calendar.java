package calendar.model.vo;

import java.sql.Date;

public class Calendar {
	private int calNum;
	private String calName;
	private Date calStart;
	private Date calEnd;
	private String calMemo;
	private int rsNum;
	private String hpId;
	private String usId;
	private char calDel;
	
	public Calendar() {}
	
	public Calendar(int calNum, String calName, Date calStart, Date calEnd, String calMemo, int rsNum, String hpId,
			String usId, char calDel) {
		super();
		this.calNum = calNum;
		this.calName = calName;
		this.calStart = calStart;
		this.calEnd = calEnd;
		this.calMemo = calMemo;
		this.rsNum = rsNum;
		this.hpId = hpId;
		this.usId = usId;
		this.calDel = calDel;
	}

	public int getCalNum() {
		return calNum;
	}

	public void setCalNum(int calNum) {
		this.calNum = calNum;
	}

	public String getCalName() {
		return calName;
	}

	public void setCalName(String calName) {
		this.calName = calName;
	}

	public Date getCalStart() {
		return calStart;
	}

	public void setCalStart(Date calStart) {
		this.calStart = calStart;
	}

	public Date getCalEnd() {
		return calEnd;
	}

	public void setCalEnd(Date calEnd) {
		this.calEnd = calEnd;
	}

	public String getCalMemo() {
		return calMemo;
	}

	public void setCalMemo(String calMemo) {
		this.calMemo = calMemo;
	}

	public int getRsNum() {
		return rsNum;
	}

	public void setRsNum(int rsNum) {
		this.rsNum = rsNum;
	}

	public String getHpId() {
		return hpId;
	}

	public void setHpId(String hpId) {
		this.hpId = hpId;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public char getCalDel() {
		return calDel;
	}

	public void setCalDel(char calDel) {
		this.calDel = calDel;
	} 

	
	
}
