package pet.model.vo;

import java.sql.Date;

public class Pet {
	private int petNum;
	private String petName;
	private Date petBirth;
	private String petGender;
	private String petCate;
	private String petSpec;
	private double petWeight;
	private String petVacc;
	private String petNeut;
	private Date petAdd;
	private Date petUpdate;
	private String usId;
	private String petDel;
	
	public Pet() {}

	public Pet(int petNum, String petName, String petGender, String petSpec, double petWeight) {
		super();
		this.petNum = petNum;
		this.petName = petName;
		this.petGender = petGender;
		this.petSpec = petSpec;
		this.petWeight = petWeight;
	}

	public Pet(int petNum, String petName, Date petBirth, String petGender, String petCate, String petSpec,
			double petWeight, String petVacc, String petNeut, String usId) {
		super();
		this.petNum = petNum;
		this.petName = petName;
		this.petBirth = petBirth;
		this.petGender = petGender;
		this.petCate = petCate;
		this.petSpec = petSpec;
		this.petWeight = petWeight;
		this.petVacc = petVacc;
		this.petNeut = petNeut;
		this.usId = usId;
	}

	public Pet(int petNum, String petName, Date petBirth, String petGender, String petCate, String petSpec,
			double petWeight, String petVacc, String petNeut, Date petAdd, Date petUpdate, String usId, String petDel) {
		super();
		this.petNum = petNum;
		this.petName = petName;
		this.petBirth = petBirth;
		this.petGender = petGender;
		this.petCate = petCate;
		this.petSpec = petSpec;
		this.petWeight = petWeight;
		this.petVacc = petVacc;
		this.petNeut = petNeut;
		this.petAdd = petAdd;
		this.petUpdate = petUpdate;
		this.usId = usId;
		this.petDel = petDel;
	}

	public int getPetNum() {
		return petNum;
	}

	public void setPetNum(int petNum) {
		this.petNum = petNum;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public Date getPetBirth() {
		return petBirth;
	}

	public void setPetBirth(Date petBirth) {
		this.petBirth = petBirth;
	}

	public String getPetGender() {
		return petGender;
	}

	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}

	public String getPetCate() {
		return petCate;
	}

	public void setPetCate(String petCate) {
		this.petCate = petCate;
	}

	public String getPetSpec() {
		return petSpec;
	}

	public void setPetSpec(String petSpec) {
		this.petSpec = petSpec;
	}

	public double getPetWeight() {
		return petWeight;
	}

	public void setPetWeight(double petWeight) {
		this.petWeight = petWeight;
	}

	public String getPetVacc() {
		return petVacc;
	}

	public void setPetVacc(String petVacc) {
		this.petVacc = petVacc;
	}

	public String getPetNeut() {
		return petNeut;
	}

	public void setPetNeut(String petNeut) {
		this.petNeut = petNeut;
	}

	public Date getPetAdd() {
		return petAdd;
	}

	public void setPetAdd(Date petAdd) {
		this.petAdd = petAdd;
	}

	public Date getPetUpdate() {
		return petUpdate;
	}

	public void setPetUpdate(Date petUpdate) {
		this.petUpdate = petUpdate;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getPetDel() {
		return petDel;
	}

	public void setPetDel(String petDel) {
		this.petDel = petDel;
	}
	
}
