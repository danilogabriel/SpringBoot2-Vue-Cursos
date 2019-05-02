package capacita.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*","/js/*","/css/*","/api/auth/*", "/favicon.ico", "/console/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/*").permitAll();

        //.authorizeRequests()
        //.antMatchers(HttpMethod.POST, "/api/auth").permitAll();
        //.antMatchers("/api/*").access("hasRole('ROLE_ADMIN')")
        //.antMatchers("/api/cursos*").access("hasRole('ROLE_USER')")
        //.antMatchers("/api/usuarios*").access("hasRole('ROLE_USER')")
        //.anyRequest().authenticated();

    }
}
