package com.interfacesv.demo.security;

import com.interfacesv.demo.component.JwtTokenProvider;
import com.interfacesv.demo.exception.CustomAccessDeniedHandler;
import com.interfacesv.demo.exception.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encoderPassword() {
        return new BCryptPasswordEncoder();
    }

//    //잠깐 인증 끄는 코드
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.httpBasic().disable().csrf().disable();
//    }
    @Override
    public void configure(WebSecurity web) { //인증을 무시할 경로들을 설정한다.
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/h2-console/**"); //static 하위폴더에는 무조건 접근 가능해야함
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //http 관련 인증 설정이 가능합니다. (REST API로 할 것이기 때문에 세션이아닌 JTW 토큰 발급으로 변경해야함)

        // JWT 토큰 방식을 사용하므로 스프링 시큐리티 기본 로그인 페이지 사용 X
        http.httpBasic().disable();

        // Restful API이므로 csrf 사용 X
        http.csrf().disable();

        // 세션 사용 X
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //
        http.authorizeRequests()
                .antMatchers("/api/v1/auth/login", "/exception/**").permitAll() // login, logout
                .antMatchers("/api/v1/auth/logout").authenticated()
                .antMatchers("/api/v1/board", "/api/v1/board/findById", "/api/v1/board/findByStudent", "/api/v1/schedule", "/api/v1/schedule/findById", "/api/v1/schedule").access("hasRole('USER') or hasRole('ADMIN')") //USER, ADMIN만 접근 가능
                .antMatchers("/api/v1/board/**","/api/v1/shedule/**", "/api/v1/user/**").hasRole("ADMIN") //ADMIN만 접근 가능
                .anyRequest().authenticated(); //나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능

        //JwtFilter 추가
        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        //JwtAuthentication exception handling
        http.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        //access Denial handler
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());

    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception { //로그인할 때 필요한 정보를 가져오는 곳
//        auth.userDetailsService(userService) //유저 정보를 가져오는 서비스를 userService으로 지정합니다.
//                //패스워드 인코더는 아까 빈으로 등록해놓은 passwordEncoder()를 사용합니다. (BCrypt)
//                //해당 서비스(userService)에서는 UserDetailsService를 implements해서
//                //loadUserByUsername() 구현해야함 (서비스 참고)
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }
}