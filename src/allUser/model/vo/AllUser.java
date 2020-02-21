package allUser.model.vo;

import java.sql.Date;

public class AllUser {
	private String auId;
	private String auPwd;
	private String auKind;
	private Date enroll;
	private Date update;
	private String auDel;
	
	public AllUser() {}
	
	public AllUser(String auId, String auPwd) {
		super();
		this.auId = auId;
		this.auPwd = auPwd;
	}

	
	public AllUser(String auId, String auPwd, String auKind) {
		super();
		this.auId = auId;
		this.auPwd = auPwd;
		this.auKind = auKind;
	}

	public AllUser(String auId, String auPwd, String auKind, Date enroll, Date update, String auDel) {
		super();
		this.auId = auId;
		this.auPwd = auPwd;
		this.auKind = auKind;
		this.enroll = enroll;
		this.update = update;
		this.auDel = auDel;
	}

	public String getAuId() {
		return auId;
	}

	public void setAuId(String auId) {
		this.auId = auId;
	}

	public String getAuPwd() {
		return auPwd;
	}

	public void setAuPwd(String auPwd) {
		this.auPwd = auPwd;
	}

	public String getAuKind() {
		return auKind;
	}

	public void setAuKind(String auKind) {
		this.auKind = auKind;
	}

	public Date getEnroll() {
		return enroll;
	}

	public void setEnroll(Date enroll) {
		this.enroll = enroll;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public String getAuDel() {
		return auDel;
	}

	public void setAuDel(String auDel) {
		this.auDel = auDel;
	}	

}
