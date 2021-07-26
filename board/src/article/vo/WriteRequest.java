package article.vo;

public class WriteRequest {
   private Writer writer; // 객체 - writerId, writerName 필드가 있음
   private String title;
   private String content;
   
   public WriteRequest(Writer writer, String title, String content) {
      super();
      this.writer = writer;
      this.title = title;
      this.content = content;
   }

   public Writer getWriter() {
      return writer;
   }

   public void setWriter(Writer writer) {
      this.writer = writer;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   @Override
   public String toString() {
      return "WriteRequest [writer=" + writer + ", title=" + title + ", content=" + content + "]";
   }
   
   
}