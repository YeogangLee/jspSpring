package kr.or.ddit.util;

//페이징 처리 전용 클래스
public class Pagination {

	private int total;		  //전체 게시글의 개수
	private int currentPage;  //사용자가 요청한 페이지 번호
	private int totalPages;	  //전체 페이지의 개수
	private int startPage;	  //페이지 이동 링크의 시작 번호
	private int endPage;	  //페이지 이동 링크의 끝 번호
	
	//생성자
	//size : 한 화면에 보여질 게시글 행의 개수
	public Pagination(int total, int currentPage, int size) {
		this.total = total;
		this.currentPage = currentPage;
		//아래는 자동으로 세팅되는 값들
		this.totalPages = totalPages;
		this.startPage = startPage;
		this.endPage = endPage;
		
		if(total==0) {//전체 게시글 개수가 0(게시글이 없다면..)
			totalPages = 0;
			startPage = 0;
			endPage = 0;
			
		}else {//게시글이 있다면
			// 17(total) / 3(size 한 화면에 보여질 행의 수) = 5...2
			totalPages = total / size;
			if(total % size > 0) {
				totalPages++; //6(5+1)
			}
			
			/*
			- 시작 페이지 번호 구하는 공식
			: 사용자 현재 페이지 / 페이징 크기 * 페이징크기 + 1;
			 
			- 종료 페이지 번호 구하는 공식
			: 시작 페이지 번호 + 페이징크기 - 1
			 
			 처리1)
			 단, 3, 6페이지.. 즉 종료 페이지 번호일 경우 시작 페이지 번호 공식이 적용이 안 된다.
			 ex. 3페이지 -> 3 / 3 * 3 + 1 = 4
			 이럴 경우 : 결과값 - 페이징 크기
			 
			 처리2)
			 전체 개수가 페이징 개수로 나누어 떨어질 때
			 ex. total : 15
			 4클릭
			 시작 번호 = 4 / 3 * 3 + 1 = 4
			 끝 번호 = 4 + 3 - 1 = 6
			 하지만 6에는 페이지가 없다.

			 */
			
			startPage = currentPage / 3 * 3 + 1;
			
			int modVal = currentPage % 3;
			if(modVal == 0) startPage -= 3;
			
			endPage = startPage + 2;
			if(endPage > totalPages) endPage = totalPages;
			
		}
	}//생성자 end 

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "Pagination [total=" + total + ", currentPage=" + currentPage + ", totalPages=" + totalPages
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", getTotal()=" + getTotal()
				+ ", getCurrentPage()=" + getCurrentPage() + ", getTotalPages()=" + getTotalPages()
				+ ", getStartPage()=" + getStartPage() + ", getEndPage()=" + getEndPage() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	//게시글이 없는지 체크. 없으면 true 반환
	public boolean hasNoArticles() {
		return total == 0;
	}
	
	//게시글이 존재하는지 체크. 있으면 true 반환
	public boolean hasArticles() {
		return total > 0;
	}
	
}
