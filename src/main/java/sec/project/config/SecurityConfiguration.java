package sec.project.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
      //  we have to use next import-strings,
     //             if we want to use encrypt-method for passwords:
    // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
   //  import org.springframework.security.crypto.password.PasswordEncoder;
  //   import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
      // with this ^ setting there will be "csrf-token as cookie";
     //   this is can be OK, but there is option for HttpOnly as "false()" also;
    //   as result if there will be cross-site-scripting ability...
   //       we will be able to get this csrf-token by the scripts.
  //    String can be "removed" for enabling "normal" CSRF-protection...
 //         or if this design required - will configure it normally...
//                       and with security-meanings!
        http.cors().disable();
        http.headers().disable();
      //  ^ with not disabled option (or custom-setting) headers can be next:
     // http://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html
    //      where also visible other options to configure headers-settings
   //           for situations, when required custom design
  //   so there is possible to add something more interesting, than just disable all feature;
 //     and CORS-disabled there just as additional to points, which should be enabled in fact;
//    This strings possible to remove for "get back" default "proper" security features!
        http.authorizeRequests()
                .antMatchers("/hidden", "/css/**").permitAll()
      //    we able to remove ^ this string for remove "debug"-page-(access)
     //         when application goes to be visible for users;
                .antMatchers("/formsrc").hasAuthority("ADMIN")
                .anyRequest().authenticated();
        http.formLogin().permitAll();
     //  ^ good to think about logout too;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
/* 
  // We able to add this method for provide option: encrypt passwords;
 // Additionally to this method we have re-design also previous method:
// auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   
    @Bean
    public PasswordEncoder passwordEncoder()
                                    {return new BCryptPasswordEncoder();}
*/