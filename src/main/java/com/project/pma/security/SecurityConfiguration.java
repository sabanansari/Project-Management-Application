package com.project.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
//	
//	@Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//            .build();
//    }
	
	@Autowired
	public DataSource dataSource;
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
	
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	  throws Exception {
	    auth.jdbcAuthentication()
	      .usersByUsernameQuery("select username,password,enabled "
	        + "from user_accounts "
	        + "where username = ?")
	      .authoritiesByUsernameQuery("select username,role "
	        + "from user_accounts "
	        + "where username = ?")
	      .dataSource(dataSource).passwordEncoder(bCryptEncoder);
	}
	
	
	
//	 @Bean
//	    public InMemoryUserDetailsManager userDetailsService() {
//	        @SuppressWarnings("deprecation")
//			UserDetails user = User.withDefaultPasswordEncoder()
//	            .username("user")
//	            .password("password")
//	            .roles("USER")
//	            .build();
//	        
//	        @SuppressWarnings("deprecation")
//			UserDetails user2 = User.withDefaultPasswordEncoder()
//	            .username("adminUser")
//	            .password("pass123")
//	            .roles("ADMIN")
//	            .build();
//	        
//	        return new InMemoryUserDetailsManager(user,user2);
//	    }
	
//	  @Bean
//	    public UserDetailsManager users(DataSource dataSource) {
//	        UserDetails user = User.withDefaultPasswordEncoder()
//	            .username("user")
//	            .password("password")
//	            .roles("USER")
//	            .build();
//	        
//			UserDetails user2 = User.withDefaultPasswordEncoder()
//            .username("adminUser")
//            .password("pass123")
//            .roles("ADMIN")
//            .build();
//	        
//	        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//	        users.createUser(user);
//	        users.createUser(user2);
//	        return users;
//	    }
	 
	 @Bean
	 SecurityFilterChain web(HttpSecurity http) throws Exception {
	 	http
	 		.authorizeHttpRequests(authorize -> {
				try {
					authorize
						.requestMatchers("/projects/new").hasRole("ADMIN")
						.requestMatchers("/projects/save").hasRole("ADMIN")
						.requestMatchers("/employees/new").hasRole("ADMIN")
						.requestMatchers("/employees/save").hasRole("ADMIN")
//						.requestMatchers("/","/**").permitAll()
						.anyRequest().authenticated().and().formLogin();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 		);

	 	return http.build();
	 }
}