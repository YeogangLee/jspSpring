package kr.or.ddit.item.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.item.service.ItemService;
import kr.or.ddit.item.vo.Item;
import kr.or.ddit.item.vo.ItemMultiVO;

//@RequestMapping("/item")
//@Controller
public class ItemController_clear {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController_clear.class);

	@Autowired
	ItemService itemSerivce;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register() {
		return "item/register"; //forward
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(MultipartFile picture, @ModelAttribute Item item, Model model) {
		//상품명
		String itemName = item.getItemName();
		//가격
		int price = item.getPrice();
		//개요
		String description = item.getDescription();
		//파일
		MultipartFile file = item.getPicture();
		logger.info("파일명 : " + file.getOriginalFilename());

		//파일 업로드
//		String pictureUrl = uploadFile(file); //picture를 던져도 상관없다.
		String[] pictureUrl = uploadFile(file); //picture를 던져도 상관없다.
		item.setPictureUrl(pictureUrl[0]);
		logger.info("최종itemVO : " + item);
		
		// item : Http 파라미터 + 업로드된 이미지 명
		int itemId = this.itemSerivce.create(item);
		logger.info("insert된 itemId : " + itemId);
		model.addAttribute("item", item); //최종결과 item VO를 model에 담아 VO를 통으로 보내보기
		
		return "item/success"; //forward
	}
	
	//다중 이미지 업로드
	@RequestMapping(value="/uploadFormAction", method=RequestMethod.GET)
	public String uploadFormAction() {
		return "item/uploadForm"; //forward
	}
	
	@RequestMapping(value="/uploadFormAction", method=RequestMethod.POST)
	public String uploadFormActionPost(@ModelAttribute ItemMultiVO itemMultiVo) {
		logger.info("itemMultiVO : " + itemMultiVo.toString());
		
		//itemVo.getPictures() : 업로드한 File 객체 배열
		//itemMultiVo.getPictures().length : 업로드한 File 객체 배열 길이
//		String[] picturesUrl = new String[itemMultiVo.getPictures().length];
		String[][] picturesUrl = new String[itemMultiVo.getPictures().length][2];
		
		int cnt = 0;
		for(MultipartFile multipartFile : itemMultiVo.getPictures()) {
			//[0]: 파일명, [1]: 사이즈
			String[] result = uploadFile(multipartFile);
//			picturesUrl[cnt++] = uploadFile(multipartFile); //후위 연산자, 대입 후 1 증가
			picturesUrl[cnt][0] = result[0];
			picturesUrl[cnt++][1] = result[1];
		}
		
		for(int j=0; j < picturesUrl.length; j++) {
			logger.info(j + "번째 이미지 업로드 최종경로  : "+picturesUrl[j][0]);
		}
		
		Item item = new Item();
		item.setItemName(itemMultiVo.getItemName());
		item.setPrice(itemMultiVo.getPrice());
		item.setDescription(itemMultiVo.getDescription());
		//파라미터는 ItemVO, 기존의 create 메서드를 그대로 사용하기 위해
		//return 값 : 신규로 추가된 데이터의 itemId값 //itemId?
		//item => ITEM 테이블용, pictureUrl => ITEM_ATCH 테이블용(파일명, 사이즈)
		int itemId = itemSerivce.create(item, picturesUrl);
		
		return "item/success"; //forward
	}
	
	//[공통]파일업로드 메소드. 파일 객체를 파라미터로 보내면, 해당 파일 객체의 중복되지 않는 파일명을 리턴받는다.
	public String[] uploadFile(MultipartFile picture) {
//		String uploadFolder = "D:\\upload";
		String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\dditProj\\src\\main\\webapp\\resources\\images";
		//getFolder() : 연 월 일 폴더로 처리
		File uploadPath = new File(uploadFolder, getFolder());
		
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
		}
		
		String uploadFileName = picture.getOriginalFilename();
		int pictureSize = (int) picture.getSize();
		
		//IE를 위한 처리(경로를 제외하고, 파일명만 가져오는 작업)
		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
		
		UUID uuid = UUID.randomUUID();
		uploadFileName = uuid.toString() + "_" + uploadFileName;
		
		File saveFile = new File(uploadPath, uploadFileName);
		
		try {
			picture.transferTo(saveFile);
			String[] result = new String[2];
			//0 : 파일의 경로 및 이름, 1 : 파일 크기
			result[0] = getFolder().replace("\\", "/") + uploadFileName; //최종파일명
			result[1] = pictureSize+"";
			
			return result;
			
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
	}
	

}
