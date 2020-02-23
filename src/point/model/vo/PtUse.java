package point.model.vo;

public class PtUse {
	private int ptcSerial;
	private String ptcName;
	private String ptcUse;
	
	public PtUse() {}

	public PtUse(int ptcSerial, String ptcName, String ptcUse) {
		super();
		this.ptcSerial = ptcSerial;
		this.ptcName = ptcName;
		this.ptcUse = ptcUse;
	}

	public PtUse(int ptcSerial, String ptcName) {
		super();
		this.ptcSerial = ptcSerial;
		this.ptcName = ptcName;
	}
	
	public int getPtcSerial() {
		return ptcSerial;
	}

	public void setPtcSerial(int ptcSerial) {
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
