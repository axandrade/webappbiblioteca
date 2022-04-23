package br.com.maralto.webappbiblioteca.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("SELECT USU_LOGIN, USU_SENHA, USU_ATIVO FROM USUARIOS WHERE USU_LOGIN=?")
		.authoritiesByUsernameQuery("SELECT u.USU_LOGIN, a.AUT_DESCRICAO FROM USUARIOS u" +
				" JOIN AUTORIZACAO_USUARIO ausu ON (ausu.USU_ID = u.USU_ID)" +
				" JOIN AUTORIZACOES a ON (a.AUT_ID = ausu.AUT_ID) WHERE u.USU_LOGIN=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/javax.faces.resource/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login.xhtml")
		.usernameParameter("j_username").passwordParameter("j_password").permitAll()
		.defaultSuccessUrl("/index.xhtml", true)
		.failureUrl("/login.xhtml?error=true")
		.and()
		.logout().logoutSuccessUrl("/login.xhtml")
		.deleteCookies("JSESSIONID")
		.and()
		.rememberMe().key("_spring_security_remember_me")
		.and().csrf().disable();
		
		//.and().csrf().disable(); desabilita o csrf dentro dos formularios
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
