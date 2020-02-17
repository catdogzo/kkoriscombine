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
	
	
}
