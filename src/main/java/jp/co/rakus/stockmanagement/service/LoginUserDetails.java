package jp.co.rakus.stockmanagement.service;

import org.springframework.security.core.authority.AuthorityUtils;

import jp.co.rakus.stockmanagement.domain.Member;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	private final Member member;
	
	public LoginUserDetails(Member member){
		super(member.getMailAddress(), member.getPassword(), 
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
}
