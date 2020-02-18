package knBoard.model.vo;

import java.sql.Date;

public class KnBoard {
	private int knNum;
	private String knTtitle;
	private String knCon;
	private String usNick;
	private int knView;
	private Date knDate;
	private Date knUpdate;
	private String knDel;

	public KnBoard() {}

	public KnBoard(int knNum, String knTtitle, String knCon, String usNick, int knView, Date knDate, Date knUpdate,
			String knDel) {
		super();
		this.knNum = knNum;
		this.knTtitle = knTtitle;
		this.knCon = knCon;
		this.usNick = usNick;
		this.knView = knView;
		this.knDate = knDate;
		this.knUpdate = knUpdate;
		this.knDel = knDel;
	}

	public KnBoard(int knNum, String knTtitle, String knCon, Date knUpdate) {
		super();
		this.knNum = knNum;
		this.knTtitle = knTtitle;
		this.knCon = knCon;
		this.knUpdate = knUpdate;
	}

	public int getKnNum() {
		return knNum;
	}

	public void setKnNum(int knNum) {
		this.knNum = knNum;
	}

	public String getKnTtitle() {
		return knTtitle;
	}

	public void setKnTtitle(String knTtitle) {
		this.knTtitle = knTtitle;
	}

	public String getKnCon() {
		return knCon;
	}

	public void setKnCon(String knCon) {
		this.knCon = knCon;
	}

	public String getUsNick() {
		return usNick;
	}

	public void setUsNick(String usNick) {
		this.usNick = usNick;
	}

	public int getKnView() {
		return knView;
	}

	public void setKnView(int knView) {
		this.knView = knView;
	}

	public Date getKnDate() {
		return knDate;
	}

	public void setKnDate(Date knDate) {
		this.knDate = knDate;
	}

	public Date getKnUpdate() {
		return knUpdate;
	}

	public void setKnUpdate(Date knUpdate) {
		this.knUpdate = knUpdate;
	}

	public String getKnDel() {
		return knDel;
	}

	public void setKnDel(String knDel) {
		this.knDel = knDel;
	}


}
