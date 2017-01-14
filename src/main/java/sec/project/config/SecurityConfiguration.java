package sec.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
   //  we have to use this import - if want to use encrypt-method for passwords:
  // import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 // import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
  // we able to remove ^ this string for turn ON "default" feature back
 // under the Spring Framework for CSRF protection enabled;
        http.headers().disable();
        http.cors().disable();
     //   ^ with not disabled option (or custom-setting) headers can be next:
    // http://docs.spring.io/spring-security/site/docs/current/reference/html/headers.html
   // where also visible other options to configure headers-settings
  //   so there is possible to add something more interesting, than just disable all feature;
        http.authorizeRequests()
                .antMatchers("/hidden", "/css/**").permitAll()
      // we able to remove ^ this string for remove "debug"-page-(access)
     // when application goes be visible for users;
                .antMatchers("/formsrc").hasAuthority("ADMIN")
                .antMatchers("/fylkr").authenticated()
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