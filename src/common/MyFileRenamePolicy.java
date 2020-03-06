package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) { // 시, 분, 초 랜덤값까지 이용할 것이므로 겹치지 않는다.
		long currentTime = System.currentTimeMillis(); // 시간을 밀리세컨드로 가져옴
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)(Math.random() * 100000);
		
		String name = originFile.getName(); // 파일 이름 뽑아주는 것이다.
		String ext =  null; // 확장자를 받아놓은 변수를 초기화한다.
		int dot = name.lastIndexOf("."); // 확장자의 위치를 가져오기 위한 것이다. 가장 마지막에 있는 점이 확장자를 나타내기 위한 점이다.
		if(dot != -1) {
			ext = name.substring(dot);
		} else {
			ext = "";
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext; 
		File newFile = new File(originFile.getParent(), fileName); // 부모 경로를 만들어줬다.
		
		
		return newFile;
	}

}
