package message.model.vo;

import java.sql.Date;

public class Message {
	private int msgNum;
	private String msgTitle;
	private Date msgDate;
	private String msgCon;
	private String rNick;
	private String sNick;
	private String rsgId;
	private String ssgId;
	private String ssgDel;
	private String rsgDel;
	private String status;
	
	public Message() {}

	public Message(int msgNum, String msgTitle, Date msgDate, String msgCon, String rNick, String sNick, String rsgId,  String ssgId, String ssgDel, String rsgDel,	String status) {
		super();
		this.msgNum = msgNum;
		this.msgTitle = msgTitle;
		this.msgDate = msgDate;
		this.msgCon = msgCon;
		this.rNick = rNick;
		this.sNick = sNick;
		this.rsgId = rsgId;
		this.ssgId = ssgId;
		this.ssgDel = ssgDel;
		this.rsgDel = rsgDel;
		this.status = status;
	}

	
	
	
	public Message(int msgNum, String msgTitle, Date msgDate, String msgCon, String rNick, String sNick) {
		super();
		this.msgNum = msgNum;
		this.msgTitle = msgTitle;
		this.msgDate = msgDate;
		this.msgCon = msgCon;
		this.rNick = rNick;
		this.sNick = sNick;
	}

	public int getMsgNum() {
		return msgNum;
	}

	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}
	
	public String getMsgCon() {
		return msgCon;
	}
	
	public void setMsgCon(String msgCon) {
		this.msgCon = msgCon;
	}
	
	public String getRNick() {
		return rNick;
	}
	
	public void setRNick(String rNick) {
		this.rNick = rNick;
	}
	
	public String getSNick() {
		return sNick;
	}
	
	public void setSNick(String sNick) {
		this.sNick = sNick;
	}
	
	public String getRsgId() {
		return rsgId;
	}
	
	public void setRsgId(String rsgId) {
		this.rsgId = rsgId;
	}
	
	public String getSsgId() {
		return ssgId;
	}

	public void setSsgId(String ssgId) {
		this.ssgId = ssgId;
	}


	public String getSsgDel() {
		return ssgDel;
	}

	public void setSsgDel(String ssgDel) {
		this.ssgDel = ssgDel;
	}

	public String getRsgDel() {
		return rsgDel;
	}

	public void setRsgDel(String rsgDel) {
		this.rsgDel = rsgDel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}


