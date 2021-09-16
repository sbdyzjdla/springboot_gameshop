package com.gameshop.config.auth;

import com.gameshop.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                        .antMatchers("/", "/css/**", "/js/**", "/img/**",
                                        "/fonts/**", "/sass/**", "/webfonts/**",
                                        "/h2-console/**",
                                        "/board/**", "/board/view_board/**", "/api/v1/qnaslist", "/api/v1/qnaslist/**",
                                        "/products", "/products/**", "/api/v1/consoles/nintendoCList/**",
                                        "/api/v1/consoles/ps5CList/**", "/api/v1/titles/nsSoftList/**",
                                        "/api/v1/titles/ps5SoftList/**",
                                        "/display/**",
                                        "/cart/**",
                                        "/about/**").permitAll()
                        .antMatchers("/api/v1/qnas/**", "/veirfyIamport/**").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                        .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }
}
