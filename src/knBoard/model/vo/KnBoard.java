package knBoard.model.vo;

import java.sql.Date;

public class KnBoard {
	private int knNum;
	private String knTitle;
	private String knCon;
	private String usId;
	private String usNick;
	private int knView;
	private Date knDate;
	private Date knUpdate;
	private String knDel;

	public KnBoard() {}

	public KnBoard(int knNum, String knTitle, String knCon, String usId, String usNick, int knView, Date knDate, Date knUpdate,
			String knDel) {
		super();
		this.knNum = knNum;
		this.knTitle = knTitle;
		this.knCon = knCon;
		this.usId = usId;
		this.usNick = usNick;
		this.knView = knView;
		this.knDate = knDate;
		this.knUpdate = knUpdate;
		this.knDel = knDel;
	}

	public KnBoard(int knNum, String knTitle, String knCon, Date knUpdate) {
		super();
		this.knNum = knNum;
		this.knTitle = knTitle;
		this.knCon = knCon;
		this.knUpdate = knUpdate;
	}

	public KnBoard(String knTitle, String knCon, String usNick) {
		super();
		this.knTitle = knTitle;
		this.knCon = knCon;
		this.usNick = usNick;
	}

	public KnBoard(int knNum, String knTitle, String knCon, String usId, String usNick, int knView, Date knDate) {
		super();
		this.knNum = knNum;
		this.knTitle = knTitle;
		this.knCon = knCon;
		this.usId = usId;
		this.usNick = usNick;
		this.knView = knView;
		this.knDate = knDate;
	}

	public KnBoard(int knNum, String knTitle, Date knDate, String knCon, String usNick) {
		super();
		this.knNum = knNum;
		this.knTitle = knTitle;
		this.knCon = knCon;
		this.usNick = usNick;
		this.knDate = knDate;
	}	
	
	public KnBoard(int knNum, String knTitle, String knCon) {
		super();
		this.knNum = knNum;
		this.knTitle = knTitle;
		this.knCon = knCon;
	}

	public int getKnNum() {
		return knNum;
	}

	public void setKnNum(int knNum) {
		this.knNum = knNum;
	}

	public String getknTitle() {
		return knTitle;
	}

	public void setknTitle(String knTitle) {
		this.knTitle = knTitle;
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

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}


}
