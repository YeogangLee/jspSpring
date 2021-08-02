package kr.or.ddit.item.vo;

//자바빈 클래스 
public class Item_old {

	private int itemId;
	private String itemName;
	private int price;
	private String description;
	private String pictureUrl;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", description=" + description
				+ ", pictureUrl=" + pictureUrl + "]";
	}
	
	
}
