package jp.co.rakus.stockmanagement.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.service.BookService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 書籍関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/book")
@Transactional
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ServletContext application;
	
	@ModelAttribute
	public AddBookForm setAddBookForm(){
		return new AddBookForm();
	}
	
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public BookForm setUpForm() {
		return new BookForm();
	}
	
	/**
	 * 書籍リスト情報を取得し書籍リスト画面を表示します.
	 * @param model モデル
	 * @return 書籍リスト表示画面
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/list";
	}
	
	/**
	 * 書籍詳細情報を取得し書籍詳細画面を表示します.
	 * @param id 書籍ID
	 * @param model　モデル
	 * @return　書籍詳細画面
	 */
	@RequestMapping(value = "show/{bookId}")
	public String show(@PathVariable("bookId") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "book/show";
	}
	
	/**
	 * 書籍更新を行います.
	 * @param form フォーム
	 * @param result リザルト情報
	 * @param model　モデル
	 * @return　書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return show(form.getId(), model);
		}
		Book book = bookService.findOne(form.getId());
		book.setStock(form.getStock());
		bookService.update(book);
		return list(model);
	}
	
	/**
	 * 書籍情報を登録する.
	 * 
	 * @param form 入力情報を受け取るフォーム
	 * @param result エラー用のリザルト
	 * @param model リクエストスコープ
	 * @param multipartFile アップロードされたファイル
	 * @return 登録成功画面
	 */
	@RequestMapping("/add")
	public String add(@Validated AddBookForm form, BindingResult result, Model model, @RequestParam("imgFile") MultipartFile multipartFile){
		boolean isError = false;
		if(multipartFile.isEmpty()){
			model.addAttribute("imgFileError", "画像ファイルを選択してください");
			isError = true;
		}
		if(result.hasErrors() || isError){
			return addBook(model);
		}
		byte[] fileBytes = null;
		try {
			fileBytes = multipartFile.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String encodedFile = Base64.getEncoder().encodeToString(fileBytes);
		
		Book book = new Book();
		BeanUtils.copyProperties(form, book);
		book.setSaledate(form.getDateSaledate());
		book.setPrice(Integer.parseInt(form.getPrice()));
		book.setStock(Integer.parseInt(form.getStock()));
		book.setImage(encodedFile);
		book.setId(bookService.getLastId()+1);
		bookService.save(book);
		return "redirect:/book/add-success";
	}
//	public String add(@Validated AddBookForm form, BindingResult result, Model model, @RequestParam("imgFile") MultipartFile multipartFile){
//		boolean isError = false;
//		if(multipartFile.isEmpty()){
//			model.addAttribute("imgFileError", "画像ファイルを選択してください");
//			isError = true;
//		}
//		if(result.hasErrors()){
//			isError = true;
//		}
//		if(isError){
//			return addBook(model);
//		}
////		if(result.hasErrors() || isError){
////			return addBook(model);
////		}
//		
//		try{
////			multipartFile.transferTo(new File(System.getProperty("user.dir") +"\\src\\main\\webapp\\img\\"+ multipartFile.getOriginalFilename()));			
//			multipartFile.transferTo(new File(application.getRealPath("/img/") + multipartFile.getOriginalFilename()));						
//		}catch(IOException e){
//			e.printStackTrace();
//			model.addAttribute("imgFileError", "画像ファイルの名前が読み込めません");
//			return addBook(model);
//		}
//		
//		Book book = new Book();
//		BeanUtils.copyProperties(form, book);
////		try {
////			book.setSaledate(new SimpleDateFormat("yyyyMMdd").parse(form.getSaledate()));
////		} catch (ParseException e) {
////			e.printStackTrace();
////			result.rejectValue("saledate", null, "入力形式が違います");
////			return addBook(model);
////		}
//		book.setSaledate(form.getDateSaledate());
//		book.setPrice(Integer.parseInt(form.getPrice()));
//		book.setStock(Integer.parseInt(form.getStock()));
//		book.setImage(multipartFile.getOriginalFilename());
//		book.setId(bookService.getLastId()+1);
//		bookService.save(book);
//		return "redirect:/book/add-success";
//	}
	
	/**
	 * 書籍登録画面を表示します.
	 * 
	 * @param model リクエストスコープ
	 * @return 書籍登録画面
	 */
	@RequestMapping("/add-book")
	public String addBook(Model model){
		return "book/addBook";
	}
	
	/**
	 * 登録成功画面を表示します.
	 * 
	 * @return 登録成功画面
	 */
	@RequestMapping("/add-success")
	public String success(){
		return "book/addSuccess";
	}

}
