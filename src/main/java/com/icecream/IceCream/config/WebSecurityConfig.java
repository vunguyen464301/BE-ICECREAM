package com.icecream.IceCream.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService);

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// access api post permitAll
		http.cors().and().csrf().disable().headers().frameOptions().deny();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/", "/login", "/logout").permitAll()
				.antMatchers("/account/signin").permitAll()
				.antMatchers("/account/signup").permitAll()
				.antMatchers("/catalogue/").permitAll()
				.antMatchers("/product/findProduct/**").permitAll()
				.antMatchers("/product/findPageProduct/**").permitAll()
				.antMatchers(HttpMethod.GET, "/product/{\\d+}").permitAll()
				.antMatchers("/productFeedback/findAll").permitAll()
				.antMatchers("/productFeedback/productId/**").permitAll()
				.antMatchers("/productFeedback/accountId/**").permitAll();
		http.httpBasic().and().authorizeRequests().and().formLogin();
		http.authorizeRequests().antMatchers("/account/find/**").hasAnyRole("ADMIN", "USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/account/password").hasAnyRole("ADMIN", "USER",
				"CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/account/").hasAnyRole("ADMIN", "USER", "CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/account/{\\d+}").hasAnyRole("ADMIN");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/product/").hasAnyRole("ADMIN", "USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/product/").hasAnyRole("ADMIN", "USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/product/{\\d+}").hasAnyRole("ADMIN", "USER");

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/orders/").hasAnyRole("ADMIN", "USER", "CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/orders/").hasAnyRole("ADMIN", "USER", "CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/orders/").hasAnyRole("ADMIN", "USER", "CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/orders/{\\d+}").hasAnyRole("ADMIN", "USER",
				"CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/orders/details/{\\d+}").hasAnyRole("ADMIN", "USER",
				"CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/orders/details/").hasAnyRole("ADMIN", "USER",
				"CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/orders/getAll/").hasAnyRole("ADMIN", "USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/orders/find/**").hasAnyRole("ADMIN", "USER", "CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/orders/findAll/**").hasAnyRole("ADMIN", "USER");

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/productFeedback/").hasAnyRole("ADMIN", "USER", "CUSTOMER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/productFeedback/{\\d+}").hasRole("ADMIN");
		
//set token
//		http.authorizeRequests().and() 
//				.rememberMe().tokenRepository(this.persistentTokenRepository()) 
//				.tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

	}
	
//get token jdbc
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}
	
}
