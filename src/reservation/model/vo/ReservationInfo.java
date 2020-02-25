package reservation.model.vo;

import java.sql.Timestamp;

public class ReservationInfo {
	private int rsNum;
	private String hpName;
	private Timestamp rsDate;
	private String hmCate;
	private String petName;
	private String rsVisit;
	private String rsDel;
	
	public ReservationInfo() {}

	public ReservationInfo(int rsNum, String hpName, Timestamp rsDate, String hmCate, String petName) {
		super();
		this.rsNum = rsNum;
		this.hpName = hpName;
		this.rsDate = rsDate;
		this.hmCate = hmCate;
		this.petName = petName;
	}
	
	public ReservationInfo(int rsNum, String hpName, Timestamp rsDate, String hmCate, String petName, String rsVisit,
			String rsDel) {
		super();
		this.rsNum = rsNum;
		this.hpName = hpName;
		this.rsDate = rsDate;
		this.hmCate = hmCate;
		this.petName = petName;
		this.rsVisit = rsVisit;
		this.rsDel = rsDel;
	}

	public int getRsNum() {
		return rsNum;
	}

	public void setRsNum(int rsNum) {
		this.rsNum = rsNum;
	}

	public String getHpName() {
		return hpName;
	}

	public void setHpName(String hpName) {
		this.hpName = hpName;
	}

	public Timestamp getRsDate() {
		return rsDate;
	}

	public void setRsDate(Timestamp rsDate) {
		this.rsDate = rsDate;
	}

	public String getHmCate() {
		return hmCate;
	}

	public void setHmCate(String hmCate) {
		this.hmCate = hmCate;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getRsVisit() {
		return rsVisit;
	}

	public void setRsVisit(String rsVisit) {
		this.rsVisit = rsVisit;
	}

	public String getRsDel() {
		return rsDel;
	}

	public void setRsDel(String rsDel) {
		this.rsDel = rsDel;
	}
	
}
