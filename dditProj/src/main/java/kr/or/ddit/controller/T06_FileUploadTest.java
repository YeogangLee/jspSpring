package kr.or.ddit.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class T06_FileUploadTest {

	public static void main(String[] args) {
		
		//1.getFolder() 메서드를 위한 테스트
		//: 콘솔에 날짜 찍기, File.separator 이용했을 때 모습 확인
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		System.out.println(str);
		System.out.println(str.replace("-", File.separator));
		
		//2. UUID 이용 테스트
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		//-> 1acc7f7f-8038-472d-aa1a-427a193811e0
		
	}

}
