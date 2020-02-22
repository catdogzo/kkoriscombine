package hospital.model.vo;

public class Hospital {
	private String hpId;
	private String hpName;
	private String hpDName;
	private String hpPhone;
	private String hpEmail;
	private String hpZip;
	private String hpLoc1;
	private String hpLoc2;
	private String hpIntro;
	private String hpPhoto;
	private int hpStart;
	private int hpEnd;
	private int hpLunch;
	
	public Hospital() {}

	public Hospital(String hpId, String hpName, String hpPhone, String hpLoc1) {
		super();
		this.hpId = hpId;
		this.hpName = hpName;
		this.hpPhone = hpPhone;
		this.hpLoc1 = hpLoc1;
	}

	public Hospital(String hpId, String hpName, String hpDName, String hpPhone, String hpEmail, String hpZip,
			String hpLoc1, String hpLoc2, int hpStart, int hpEnd, int hpLunch) {
		super();
		this.hpId = hpId;
		this.hpName = hpName;
		this.hpDName = hpDName;
		this.hpPhone = hpPhone;
		this.hpEmail = hpEmail;
		this.hpZip = hpZip;
		this.hpLoc1 = hpLoc1;
		this.hpLoc2 = hpLoc2;
		this.hpStart = hpStart;
		this.hpEnd = hpEnd;
		this.hpLunch = hpLunch;
	}

	public Hospital(String hpId, String hpName, String hpDName, String hpPhone, String hpEmail, String hpZip,
			String hpLoc1, String hpLoc2, String hpIntro, String hpPhoto, int hpStart, int hpEnd, int hpLunch) {
		super();
		this.hpId = hpId;
		this.hpName = hpName;
		this.hpDName = hpDName;
		this.hpPhone = hpPhone;
		this.hpEmail = hpEmail;
		this.hpZip = hpZip;
		this.hpLoc1 = hpLoc1;
		this.hpLoc2 = hpLoc2;
		this.hpIntro = hpIntro;
		this.hpPhoto = hpPhoto;
		this.hpStart = hpStart;
		this.hpEnd = hpEnd;
		this.hpLunch = hpLunch;
	}

	public String getHpId() {
		return hpId;
	}

	public void setHpId(String hpId) {
		this.hpId = hpId;
	}

	public String getHpName() {
		return hpName;
	}

	public void setHpName(String hpName) {
		this.hpName = hpName;
	}

	public String getHpDName() {
		return hpDName;
	}

	public void setHpDName(String hpDName) {
		this.hpDName = hpDName;
	}

	public String getHpPhone() {
		return hpPhone;
	}

	public void setHpPhone(String hpPhone) {
		this.hpPhone = hpPhone;
	}

	public String getHpEmail() {
		return hpEmail;
	}

	public void setHpEmail(String hpEmail) {
		this.hpEmail = hpEmail;
	}

	public String getHpZip() {
		return hpZip;
	}

	public void setHpZip(String hpZip) {
		this.hpZip = hpZip;
	}

	public String getHpLoc1() {
		return hpLoc1;
	}

	public void setHpLoc1(String hpLoc1) {
		this.hpLoc1 = hpLoc1;
	}

	public String getHpLoc2() {
		return hpLoc2;
	}

	public void setHpLoc2(String hpLoc2) {
		this.hpLoc2 = hpLoc2;
	}

	public String getHpIntro() {
		return hpIntro;
	}

	public void setHpIntro(String hpIntro) {
		this.hpIntro = hpIntro;
	}

	public String getHpPhoto() {
		return hpPhoto;
	}

	public void setHpPhoto(String hpPhoto) {
		this.hpPhoto = hpPhoto;
	}

	public int getHpStart() {
		return hpStart;
	}

	public void setHpStart(int hpStart) {
		this.hpStart = hpStart;
	}

	public int getHpEnd() {
		return hpEnd;
	}

	public void setHpEnd(int hpEnd) {
		this.hpEnd = hpEnd;
	}

	public int getHpLunch() {
		return hpLunch;
	}

	public void setHpLunch(int hpLunch) {
		this.hpLunch = hpLunch;
	}
	
}
