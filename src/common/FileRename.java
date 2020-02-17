package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class FileRename implements FileRenamePolicy {
	// 파일 이름 변경
	@Override
	public File rename(File originFile) {
		long currentTime = System.currentTimeMillis();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		int ranNum = (int)(Math.random() * 100000);
		
		String name = originFile.getName();
		String ext = null;
		
		int dot = name.lastIndexOf("."); // 확장자의 위치 가져오기
		if(dot != -1) {
			ext = name.substring(dot); // 확장자가 있으면 점위치부터 끝까지 넣기
		} else { 
			ext = ""; // 확장자가 없으면 빈칸
		}
		
		String fileName = sdf.format(new Date(currentTime)) + ranNum + ext;
		File newFile = new File(originFile.getParent(), fileName);
		
		return newFile;
	}

}
