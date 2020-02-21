package user.model.vo;

import java.sql.Date;

public class User {
	private String usId;
	private String usName;
	private String usPhone;
	private String usNick;
	private String usEmail;
	private Date usBirth;
	private String usGender;
	private String usPhoto;
	private String usIntro;
	
	public User() {}
	
	public User(String usId, String usName, String usPhone, String usNick, String usEmail, Date usBirth,
			String usGender) {
		super();
		this.usId = usId;
		this.usName = usName;
		this.usPhone = usPhone;
		this.usNick = usNick;
		this.usEmail = usEmail;
		this.usBirth = usBirth;
		this.usGender = usGender;
	}

	public User(String usId, String usName, String usPhone, String usNick, String usEmail, Date usBirth,
			String usGender, String usPhoto, String usIntro) {
		super();
		this.usId = usId;
		this.usName = usName;
		this.usPhone = usPhone;
		this.usNick = usNick;
		this.usEmail = usEmail;
		this.usBirth = usBirth;
		this.usGender = usGender;
		this.usPhoto = usPhoto;
		this.usIntro = usIntro;
	}

	public String getUsId() {
		return usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getUsName() {
		return usName;
	}

	public void setUsName(String usName) {
		this.usName = usName;
	}

	public String getUsPhone() {
		return usPhone;
	}

	public void setUsPhone(String usPhone) {
		this.usPhone = usPhone;
	}

	public String getUsNick() {
		return usNick;
	}

	public void setUsNick(String usNick) {
		this.usNick = usNick;
	}

	public String getUsEmail() {
		return usEmail;
	}

	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}

	public Date getUsBirth() {
		return usBirth;
	}

	public void setUsBirth(Date usBirth) {
		this.usBirth = usBirth;
	}

	public String getUsGender() {
		return usGender;
	}

	public void setUsGender(String usGender) {
		this.usGender = usGender;
	}

	public String getUsPhoto() {
		return usPhoto;
	}

	public void setUsPhoto(String usPhoto) {
		this.usPhoto = usPhoto;
	}

	public String getUsIntro() {
		return usIntro;
	}

	public void setUsIntro(String usIntro) {
		this.usIntro = usIntro;
	}
	
	
}
