package hospital.model.vo;

public class HpMedical {
	private String hmCate;
	private String hpId;
	private int hmMin;
	private int hmMax;
	
	public HpMedical() {}
	
	public HpMedical(String hmCate, int hmMin, int hmMax) {
		super();
		this.hmCate = hmCate;
		this.hmMin = hmMin;
		this.hmMax = hmMax;
	}

	public HpMedical(String hmCate, String hpId, int hmMin, int hmMax) {
		super();
		this.hmCate = hmCate;
		this.hpId = hpId;
		this.hmMin = hmMin;
		this.hmMax = hmMax;
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

	public int getHmMin() {
		return hmMin;
	}

	public void setHmMin(int hmMin) {
		this.hmMin = hmMin;
	}

	public int getHmMax() {
		return hmMax;
	}

	public void setHmMax(int hmMax) {
		this.hmMax = hmMax;
	}
	
}
