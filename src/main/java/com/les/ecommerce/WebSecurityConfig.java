package com.les.ecommerce;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		boolean isToAuth = true;
		if(isToAuth) {
			http.
	        authorizeRequests()
	        .antMatchers("/admin/cliente/cadastro/{id}","/admin/cliente/salvar").hasAnyAuthority("USER")
	        .antMatchers("/admin/**").hasAuthority("ADMIN")
	        .antMatchers("/**", "/h2/**").permitAll()
	        .antMatchers("/conta/login", "/").permitAll()
	        .anyRequest().authenticated()
	        .and().csrf().disable().formLogin()
	        .loginPage("/conta/login").defaultSuccessUrl("/")
	        .usernameParameter("email")
	        .passwordParameter("password")
	        .and()
	        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/sair"))
	        .logoutSuccessUrl("/").and().exceptionHandling()
	        .accessDeniedPage("/access-denied");
		}
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/h2/**");
	}
}
