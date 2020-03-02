package common.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper{

	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String key) {
		String value = "";
		
		// request객체에 담긴 파라미터 이름(key)이 userPwd, newPwd인 경우 암호화
		if(key != null && (key.equals("userPwd") || key.equals("newPwd"))) {
			value = getSha512(super.getParameter(key));
		} else {
			value = super.getParameter(key);
			// request객체에 담긴 파라미터 key값이 userPwd나 joinUserPwd, newPwd가 아니면 그냥 가져오겠다
		}
				
		return value;
	}

	private String getSha512(String userPwd) {
		String encPwd = null;
		
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = userPwd.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		return encPwd;
	}
}