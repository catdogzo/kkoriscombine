package point.model.vo;

public class PtUse {
	private String ptcSerial;
	private String ptcName;
	private String ptcUse;
	
	public PtUse() {}

	public PtUse(String ptcSerial, String ptcName, String ptcUse) {
		super();
		this.ptcSerial = ptcSerial;
		this.ptcName = ptcName;
		this.ptcUse = ptcUse;
	}

	public PtUse(String ptcSerial, String ptcName) {
		super();
		this.ptcSerial = ptcSerial;
		this.ptcName = ptcName;
	}
	
	public String getPtcSerial() {
		return ptcSerial;
	}

	public void setPtcSerial(String ptcSerial) {
		this.ptcSerial = ptcSerial;
	}

	public String getPtcName() {
		return ptcName;
	}

	public void setPtcName(String ptcName) {
		this.ptcName = ptcName;
	}

	public String getPtcUse() {
		return ptcUse;
	}

	public void setPtcUse(String ptcUse) {
		this.ptcUse = ptcUse;
	}

	@Override
	public String toString() {
		return "PtUse [ptcSerial=" + ptcSerial + ", ptcName=" + ptcName + ", ptcUse=" + ptcUse + "]";
	}
	
	
}
