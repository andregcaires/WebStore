package com.andregcaires.webstoreapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.andregcaires.webstoreapi.security.JWTAuthenticationFilter;
import com.andregcaires.webstoreapi.security.JWTUtil;


/*
 * Classe que define configurações de segurança (JWT)
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;	
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;
	
	/*
	 * Lista de paths liberados para acesso sem autenticação
	 * */
	public static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	// caminhos para recuperação de dados (GET)
	public static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		// habilita acesso ao h2 console no profile test
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		// realiza a ativação do bean do método corsConfigurationSource
		// e disabilita proteção contra ataques CSRF (por ser stateless)
		http.cors()
			.and()
			.csrf().disable();
		
		
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll() // libera permissão para GET
			
			.antMatchers(PUBLIC_MATCHERS).permitAll()// autoriza todos os paths listados para acesso publico
			
			.anyRequest().authenticated(); // registra todos os outros paths para serem autenticados
		
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		// assegura que back não criará sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}
	
	/*
	 * Aplica acesso básico de multiplas fontes para todos os caminhos com configurações básicas
	 * */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/*
	 * Método indica qual o userDetailService usado e qual o algoritmo de codificação da senha
	 * */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		
	}
	
}
