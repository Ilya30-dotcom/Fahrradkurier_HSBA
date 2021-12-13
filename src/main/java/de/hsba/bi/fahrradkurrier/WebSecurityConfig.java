package de.hsba.bi.fahrradkurrier;

import de.hsba.bi.fahrradkurrier.user.User;
import de.hsba.bi.fahrradkurrier.user.UserRoleEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/jobs").hasAnyRole(UserRoleEnum.CUSTOMER.toString(), UserRoleEnum.COURIER.toString())
                .antMatchers("/jobs/**/edit").hasRole(UserRoleEnum.CUSTOMER.toString())
                .antMatchers("/jobs/create").hasRole(UserRoleEnum.CUSTOMER.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/jobs", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/?logout")
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
