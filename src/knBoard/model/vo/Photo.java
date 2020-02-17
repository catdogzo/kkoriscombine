package knBoard.model.vo;

import java.sql.Date;

public class Photo {
	private int phNum;
	private String phOrig;
	private String phChng;
	private String phPath;
	private Date phUpload;
	private int phFnum;
	private int rvNum;
	private int knNum;
	private String phDel;
	
	public Photo() {}

	public Photo(int phNum, String phOrig, String phChng, String phPath, Date phUpload, int phFnum, int rvNum,
			int knNum, String phDel) {
		super();
		this.phNum = phNum;
		this.phOrig = phOrig;
		this.phChng = phChng;
		this.phPath = phPath;
		this.phUpload = phUpload;
		this.phFnum = phFnum;
		this.rvNum = rvNum;
		this.knNum = knNum;
		this.phDel = phDel;
	}

	public int getPhNum() {
		return phNum;
	}

	public void setPhNum(int phNum) {
		this.phNum = phNum;
	}

	public String getPhOrig() {
		return phOrig;
	}

	public void setPhOrig(String phOrig) {
		this.phOrig = phOrig;
	}

	public String getPhChng() {
		return phChng;
	}

	public void setPhChng(String phChng) {
		this.phChng = phChng;
	}

	public String getPhPath() {
		return phPath;
	}

	public void setPhPath(String phPath) {
		this.phPath = phPath;
	}

	public Date getPhUpload() {
		return phUpload;
	}

	public void setPhUpload(Date phUpload) {
		this.phUpload = phUpload;
	}

	public int getPhFnum() {
		return phFnum;
	}

	public void setPhFnum(int phFnum) {
		this.phFnum = phFnum;
	}

	public int getRvNum() {
		return rvNum;
	}

	public void setRvNum(int rvNum) {
		this.rvNum = rvNum;
	}

	public int getKnNum() {
		return knNum;
	}

	public void setKnNum(int knNum) {
		this.knNum = knNum;
	}

	public String getPhDel() {
		return phDel;
	}

	public void setPhDel(String phDel) {
		this.phDel = phDel;
	}

}
