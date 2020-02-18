package knBoard.model.vo;

import java.sql.Date;

public class KnReply {
	private int knrNum;
	private String knrCon;
	private String usId;
	private Date knrDate;
	private int knNum;
	private String knrDel;
	
	public KnReply() {}

	public KnReply(int knrNum, String knrCon, String usId, Date knrDate, int knNum, String knrDel) {
		super();
		this.knrNum = knrNum;
		this.knrCon = knrCon;
		this.usId = usId;
		this.knrDate = knrDate;
		this.knNum = knNum;
		this.knrDel = knrDel;
	}

	public int getKnrNum() {
		return knrNum;
	}

	public void setKnrNum(int knrNum) {
		this.knrNum = knrNum;
	}

	public String getKnrCon() {
		return knrCon;
	}

	public void setKnrCon(String knrCon) {
		this.knrCon = knrCon;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public Date getKnrDate() {
		return knrDate;
	}

	public void setKnrDate(Date knrDate) {
		this.knrDate = knrDate;
	}

	public int getKnNum() {
		return knNum;
	}

	public void setKnNum(int knNum) {
		this.knNum = knNum;
	}

	public String getKnrDel() {
		return knrDel;
	}

	public void setKnrDel(String knrDel) {
		this.knrDel = knrDel;
	}

	@Override
	public String toString() {
		return "KnReply [knrNum=" + knrNum + ", knrCon=" + knrCon + ", usId=" + usId + ", knrDate=" + knrDate
				+ ", knNum=" + knNum + ", knrDel=" + knrDel + "]";
	}
		
}
