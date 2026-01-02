package Habits.Habits.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()) // só pra teste rápido (depois ativa se for web real)

				// REGRAS DE ACESSO
				.authorizeHttpRequests(auth -> auth
						// 1. Libera o login.html e tudo que for estático
						.requestMatchers("/Front/Login/**", "/src/**", "/cadastrar").permitAll()
						
						
//						.requestMatchers("/**","/cadastrarHabito","/pegarHabito").permitAll()
//						 .anyRequest().permitAll())

						// 2. Rotas protegidas por ROLE
						// .requestMatchers("/admin/**").hasRole("ADMIN")
						// requestMatchers("/user/**").hasRole("USER")

						// 3. Qualquer outra coisa precisa estar logado
						.anyRequest().authenticated()) 

				// CONFIGURAÇÃO DO FORM LOGIN PERSONALIZADO
				.formLogin(form -> form.loginPage("/Front/Login/login.html").usernameParameter("email")
						.passwordParameter("senha").loginProcessingUrl("/login")
						.defaultSuccessUrl("/Front/Home/home.html", true)
						.failureUrl("/Front/Login/login.html?erro=Erro ao tentar entrar!!!").permitAll())

				// LOGOUT
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login.html?logout")
						.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
