package reservation.model.vo;

import java.sql.Time;
import java.sql.Timestamp;

public class Reservation {
	private int rsNum;
	private Timestamp rsDate;
	private String rsMemo;
	private int petNum;
	private String hmCate;
	private String hpId;
	private String rsVisit;
	private String rsDel;
	
	public Reservation() {}
	
	public Reservation(Timestamp rsDate, String hpId) {
		super();
		this.rsDate = rsDate;
		this.hpId = hpId;
	}
	
	public Reservation(int rsNum, Timestamp rsDate, String hmCate, String hpId) {
		super();
		this.rsNum = rsNum;
		this.rsDate = rsDate;
		this.hmCate = hmCate;
		this.hpId = hpId;
	}
	
	public Reservation(Timestamp rsDate, String rsMemo, int petNum, String hmCate, String hpId) {
		super();
		this.rsDate = rsDate;
		this.rsMemo = rsMemo;
		this.petNum = petNum;
		this.hmCate = hmCate;
		this.hpId = hpId;
	}

	public Reservation(int rsNum, Timestamp rsDate, String rsMemo, int petNum, String hmCate, String hpId,
			String rsVisit, String rsDel) {
		super();
		this.rsNum = rsNum;
		this.rsDate = rsDate;
		this.rsMemo = rsMemo;
		this.petNum = petNum;
		this.hmCate = hmCate;
		this.hpId = hpId;
		this.rsVisit = rsVisit;
		this.rsDel = rsDel;
	}

	public int getRsNum() {
		return rsNum;
	}

	public void setRsNum(int rsNum) {
		this.rsNum = rsNum;
	}

	public Timestamp getRsDate() {
		return rsDate;
	}

	public void setRsDate(Timestamp rsDate) {
		this.rsDate = rsDate;
	}

	public String getRsMemo() {
		return rsMemo;
	}

	public void setRsMemo(String rsMemo) {
		this.rsMemo = rsMemo;
	}

	public int getPetNum() {
		return petNum;
	}

	public void setPetNum(int petNum) {
		this.petNum = petNum;
	}

	public String getHmCate() {
		return hmCate;
	}

	public void setHmCate(String hmCate) {
		this.hmCate = hmCate;
	}

	public String getHpId() {
		return hpId;
	}

	public void setHpId(String hpId) {
		this.hpId = hpId;
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
