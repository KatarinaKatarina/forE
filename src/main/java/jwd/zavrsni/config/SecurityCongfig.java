package jwd.zavrsni.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityCongfig extends WebSecurityConfigurerAdapter {

	//za InMemory probu
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("A").password("A").roles("USER");
		System.out.println("role");
		// ko se loguje sa user&pass A, dobija ulogu usera
	}

	@Override//configure se zove metod iz WebSecConfAdapter, pa moramo da
	// prepravimo
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //dole objasnjeno
		
		
		http.authorizeRequests().antMatchers("/login").permitAll() // antMatchers("/login") stranica koju Spring pravi i
																	// ona ima permitAll() -svima dozvolu daje
				.antMatchers("/**").hasRole("USER") // dakle, pristup ostalim stranicama ce imati samo role USER
				.and().
				formLogin(); // ako nije logovan, prebaci na login
		
		}
	
	
	
	// pucalo mi je na put, post
	
	// You might be running up against CSRF protection,
	// which is enabled by default in Spring Security.
	// CSRF will allow HTTP "safe" methods like GET, HEAD, and OPTION
	// but block "unsafe" methods like PUT, POST, DELETE, etc
	// if those methods do not provide the proper token (since no CSRF was
	// configured, those request don't have a token -> blocked).
	// For testing purposes, you can disable it by adding http.csrf().disable() to
	// the configure() method.
}
