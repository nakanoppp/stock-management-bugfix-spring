package jp.co.rakus.stockmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.rakus.stockmanagement.service.LoginUserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		// アクセス制限を無視するサーバのパスを指定？
		web.ignoring().antMatchers("/css/**", "/img/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/member/form", "/member/create").permitAll() // 認証なしでアクセスできるアドレスを指定
			.anyRequest().authenticated();
		http.formLogin()
			.loginProcessingUrl("/login") // ログインを行うメソッドに飛ぶアドレス
			.loginPage("/") // ログインページを表示するアドレス
			.defaultSuccessUrl("/book/list", false) // ログインが成功したときに飛ぶアドレス 
			.failureUrl("/?error=true") // ログイン失敗したときに飛ぶアドレス
			.usernameParameter("mailAddress").passwordParameter("password") // ログインするときに受け取るフォームのステータス
			.and();
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout/sessionInvalidate")) // ログアウト処理をするメソッドに飛ぶアドレス
			.logoutSuccessUrl("/"); // ログアウトした後に飛ばすアドレス 
	}
	
    @Configuration
    protected static class AuthenticationConfiguration
    extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        UserDetailsService userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService)
            .passwordEncoder(new StandardPasswordEncoder());

        }
    }
}
