package rvBoard.model.vo;

import java.sql.Date;

public class RvBoard {
	private int rvNum;
	private String rvTitle;
	private Date rvDate;
	private Date rvUpdate;
	private int rvStar;
	private String rvCon;
	private String hpId;
	private String usId;
	private String rvDel;
	private int rvLike;
	
	public RvBoard() {}

	public RvBoard(int rvNum, String rvTitle, Date rvDate, Date rvUpdate, int rvStar, String rvCon, String hpId,
			String usId, int rvLike, String rvDel) {
		super();
		this.rvNum = rvNum;
		this.rvTitle = rvTitle;
		this.rvDate = rvDate;
		this.rvUpdate = rvUpdate;
		this.rvStar = rvStar;
		this.rvCon = rvCon;
		this.hpId = hpId;
		this.usId = usId;
		this.rvDel = rvDel;
		this.rvLike = rvLike;
	}

	public RvBoard(int rvNum, String rvTitle, Date rvDate, String rvCon, String hpId, String usId, int rvStar) {
		super();
		this.rvNum = rvNum;
		this.rvTitle = rvTitle;
		this.rvDate = rvDate;
		this.rvCon = rvCon;
		this.hpId = hpId;
		this.usId = usId;
		this.rvStar = rvStar;
	}	
	
	
	public RvBoard(int rvNum, String rvTitle, Date rvDate, int rvStar, String rvCon, String hpId, String usId, int rvLike) {
		super();
		this.rvNum = rvNum;
		this.rvTitle = rvTitle;
		this.rvDate = rvDate;
		this.rvStar = rvStar;
		this.rvCon = rvCon;
		this.hpId = hpId;
		this.usId = usId;
		this.rvLike = rvLike;
	}	
	
	public RvBoard(String rvTitle, String rvCon, String hpId, String usId, int rvStar, Date rvDate) {
		super();
		this.rvTitle = rvTitle;
		this.rvDate = rvDate;
		this.rvStar = rvStar;
		this.rvCon = rvCon;
		this.hpId = hpId;
		this.usId = usId;
	}		

	public RvBoard(String hpId, int rvNum,  String rvTitle) {
		super();
		this.rvNum = rvNum;
		this.rvTitle = rvTitle;
		this.hpId = hpId;
	}	
	
	public RvBoard(int rvNum, String rvTitle, String rvCon) {
		super();
		this.rvNum = rvNum;
		this.rvTitle = rvTitle;
		this.rvCon = rvCon;
	}	
	
	public RvBoard(String rvTitle, String rvCon, String hpId, String usId, int rvStar) {
		super();
		this.rvTitle = rvTitle;
		this.rvStar = rvStar;
		this.rvCon = rvCon;
		this.hpId = hpId;
		this.usId = usId;
	}	
	
	
	public int getRvNum() {
		return rvNum;
	}

	public void setRvNum(int rvNum) {
		this.rvNum = rvNum;
	}

	public String getRvTitle() {
		return rvTitle;
	}

	public void setRvTitle(String rvTitle) {
		this.rvTitle = rvTitle;
	}

	public Date getRvDate() {
		return rvDate;
	}

	public void setRvDate(Date rvDate) {
		this.rvDate = rvDate;
	}

	public Date getRvUpdate() {
		return rvUpdate;
	}

	public void setRvUpdate(Date rvUpdate) {
		this.rvUpdate = rvUpdate;
	}

	public int getRvStar() {
		return rvStar;
	}

	public void setRvStar(int rvStar) {
		this.rvStar = rvStar;
	}

	public String getRvCon() {
		return rvCon;
	}

	public void setRvCon(String rvCon) {
		this.rvCon = rvCon;
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

	public String getRvDel() {
		return rvDel;
	}

	public void setRvDel(String rvDel) {
		this.rvDel = rvDel;
	}

	public int getRvLike() {
		return rvLike;
	}

	public void setRvLike(int rvLike) {
		this.rvLike = rvLike;
	}

	@Override
	public String toString() {
		return "RvBoard [rvNum=" + rvNum + ", rvTitle=" + rvTitle + ", rvDate=" + rvDate + ", rvUpdate=" + rvUpdate
				+ ", rvStar=" + rvStar + ", rvCon=" + rvCon + ", hpId=" + hpId + ", usId=" + usId + ", rvDel=" + rvDel
				+ ", rvLike=" + rvLike + "]";
	}

	
	
}
