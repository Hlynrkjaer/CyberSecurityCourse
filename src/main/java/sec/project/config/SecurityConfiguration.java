package sec.project.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
      // if we want to use encrypt-method for passwords - required to use next import-strings:
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
     //   It is can be OK, but there is also option for HttpOnly as "false()";
    //   and, as result, if will be possible to do cross-site-scripting...
   //      will be possible to get this csrf-token by the scripts.
  //    For potential fix: string can be "removed" for enabling "normal" CSRF-protection...
 //        or if such design is required indeed - will configure it properly and with security-meanings!
        http.cors().disable();
        http.headers().defaultsDisabled().contentTypeOptions();
      //   ^ with not disabled option (or custom-setting) headers can be next:
     // https://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html
    //     where also visible other options to configure headers-settings when required custom design.
   //   so, possible to add something more interesting than just to disable entire feature;
  //    there we tried to disable 'Content Type Options'-feature; Potential content sniffing is valid.    
 //       and CORS-disabled is just as addition to points which should be enabled in fact;
//    Those strings possible to remove for "get back" default "proper" security features!
        http.authorizeRequests()
                .antMatchers("/hidden", "/css/**").permitAll()
      //   we are able to remove ^ this "/hidden"-entry for remove "debug"-access (when application is released);
     //    and even more - good to remove entire html-page from released-version.
                .antMatchers("/formsrc").hasAuthority("ADMIN")
      //  possible to use such authority ^ checks for all pages where expected access only by admins. 
     //   for example, to "/fylkr"-resource.        
                .anyRequest().authenticated();
        http.formLogin().permitAll();
      //  but ^ good to think about logout too; currently, logout is not implemented at all;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
/*
      // We are able to add this method for encrypting passwords;
     // Additionally to this method we have to to re-design previous method too:
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   
    @Bean
    public PasswordEncoder passwordEncoder()
                                    {return new BCryptPasswordEncoder();}
*/
