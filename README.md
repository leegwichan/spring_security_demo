# spring_security_demo

SpringSecurity 기본 생성 방법

- auth
  - PrincipalDetails
    - POST "/login" 주소에 요청이 오면 대신 로그인을 진행
    - Security Session ⇒ Authentication ⇒ UserDetails
    - implements UserDetails
  
  - PrincipalDetailsService
    - Security 설정에서 loginProcessingUrl(”/login”);으로 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 메서드 실행
    - implements UserDetailsService
  
- config
    - SecurityConfig
      - @Configuration을 통해 Spring Container에 객체 등록
      - SecurityFilterChain 등록 : Security 관련 Option을 설정
      - BCryptPasswordEncoder 등록
      
    - WebMvcConfig
      - mustache → html 사용할 수 있도록 설정

- controller
  - AccessIndexController
    - 접근이 제한된 API 등록
    - SecurityConfig, Annotation(@Secured, @PreAuthorize) 으로 접근 제한 설정
  
  - HtmlpageController
    - login, join page를 띄우는 API
    - POST "/join" : 회원 가입 요청하는 API
  
- model : 회원 Entity
- repository : 회원 저장하는 repository
