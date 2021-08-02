package kr.or.ddit.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class T06_FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(T06_FileUploadController.class);

	//이미지 업로드
	@RequestMapping(value="/registerFile01", method=RequestMethod.POST)
	public String registerFile01(MultipartFile picture) {
		logger.info("registerFile01");
		
		String uploadFolder = "D:\\upload";
		
		logger.info("originalName : " + picture.getOriginalFilename());
		logger.info("size : " + picture.getSize());
		logger.info("contentType : " + picture.getContentType());
		
		File saveFile = new File(uploadFolder, picture.getOriginalFilename());
		try {
			picture.transferTo(saveFile);
		}catch (Exception ex) {
			logger.info(ex.getMessage());
		}
		
		return "book/success";
	}
	
	//ajax 이미지 업로드
	@RequestMapping(value="/uploadAjax", method=RequestMethod.GET)
	public String uploadAjax() {
		logger.info("uploadAjax");
		
		return "book/uploadAjax"; //forward
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadAjaxAction", method=RequestMethod.POST)
	public Map<String, Object> uploadAjaxPost(MultipartFile[] uploadFile) {

		logger.info("uploadAjaxPost");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(uploadFile.length == 0) {
			return map;
			
		}else {
			int i = 0;
			
	//		String uploadFolder = "D:\\upload";
			String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dditProj\\src\\main\\webapp\\resources\\images";
			
			//new File(파일 저장 경로, 파일명)
			File uploadPath = new File(uploadFolder, getFolder());
			logger.info("업로드 경로 : " + uploadPath);
			
			if(uploadPath.exists() == false) {
				uploadPath.mkdirs();
			}
			
			for(MultipartFile multipartFile : uploadFile) {
				logger.info("--------------------------");
				logger.info("Upload 파일명 : " + multipartFile.getOriginalFilename());
				logger.info("Upload 파일 사이즈 : " + multipartFile.getSize());
				
				String uploadFileName = multipartFile.getOriginalFilename();
				
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
				logger.info("경로없는 파일명 : " + uploadFileName);
				
				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + uploadFileName;
				
				map.put("data"+(i++), uploadFileName);
				
				File saveFile = new File(uploadPath, uploadFileName);
				
				try {
					multipartFile.transferTo(saveFile);
				} catch (IllegalStateException | IOException e) {
					logger.info(e.getMessage());
				}
			}//end for
		
			System.out.println("map : " + map);

			return map;

		}//end if
		
	}
	
	// 2021\07\29
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	
}
