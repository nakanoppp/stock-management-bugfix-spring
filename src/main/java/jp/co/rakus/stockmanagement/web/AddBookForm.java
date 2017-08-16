package jp.co.rakus.stockmanagement.web;

import org.hibernate.validator.constraints.NotBlank;

public class AddBookForm {
	@NotBlank(message="書籍名を入力してください")
	private String name;
	@NotBlank(message="著者を入力してください")
	private String author;
	@NotBlank(message="出版社を入力してください")
	private String publisher;
	@NotBlank(message="価格を入力してください")
	private String price;
	@NotBlank(message="ISBNコードを入力してください")
	private String isbncode;
	@NotBlank(message="発売日を入力してください")
	private String saledate;
	@NotBlank(message="説明欄を入力してください")
	private String explanation;
	@NotBlank(message="在庫数を入力してください")
	private String stock;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsbncode() {
		return isbncode;
	}
	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
}
