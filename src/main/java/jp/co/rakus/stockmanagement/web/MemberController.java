package jp.co.rakus.stockmanagement.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	private PasswordEncoder passwordEncoder(){
		return new StandardPasswordEncoder();
	}
	
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpForm() {
		return new MemberForm();
	}

	/**
	 * メンバー情報登録画面を表示します.
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form(Model model) {
		return "/member/form";
	}
	
	/**
	 * メンバー情報を登録します.
	 * @param form フォーム
	 * @param result リザルト
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form, BindingResult result, 
			Model model) {
		if(!form.getPassword().equals(form.getRePassword())){
			result.rejectValue("password", null, "異なる値が入力されました。もう一度入力してください");
		}
		Member checkMailAddressMember = memberService.findByMailAddress(form.getMailAddress());
		if(checkMailAddressMember != null){
			result.rejectValue("mailAddress", null, "メールアドレスはすでに登録されています");
		}
		if(result.hasErrors()){
			return form(model);
		}
		Member member = new Member();
		BeanUtils.copyProperties(form, member);
		// パスワードをSHA9-256でハッシュ値にする
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberService.save(member);
		return "redirect:/";
	}
	
}
