# CyberSecurityCourse
<sub>cybersecuritybase-project ( task from https://cybersecuritybase.github.io/ -- Course Project One -- )</sub>
- - - -
There will be **check-steps**:

* for improve design,
* adding some features and
* other related steps (with additional steps for troublepoints-usage) to my project-application.

Something like documentation for first design of this task available with next [URL](https://github.com/marrbjorn/documentation/tree/master/F-Secure%20Cyber%20Security%20Base%20MOOC.fi%20-First%20Project/)
- - - -
#### FIRST EXTEND-STEPS TO MY PROJECT APPLICATION:


<kbd>**»**</kbd> Added point with "http.headers()"-disabled; and point with disabled "http.cors()";

<kbd>**»**</kbd> Small "addition" as some kind of "mapping" (trick-feature); 

**First one** provide some kind of ability to "improve" troubles with security configuration.

      As result - disabled most of common "headers" and features, which good to have with "enabled" status;
      
      This is just disabled default options under the Spring framework; 
      But will be more interesting to work with custom implementations;
      
      Because, basically, required to work with custom ones...
      if we indeed want to disable/change some of "headers"-features!
      
      later we did switch to http.headers().defaultsDisabled().contentTypeOptions() only;
      Potential content sniffing is valid! 
      Also as addition to "form.html" where we used 'title' before meta-charset-tag.
      
**Second one** provide some kind of potential troublepoint with Unvalidated Redirects.

      With current view: this is just kind of "trick"-feature;
      But provide additional steps to "play" with project-application;
      
      Also partly related with adding another vulnerable point.
      What can be covered as "Trouble Ten" from OWASP TOP TEN list.

- - - -
#### SECOND EXTEND-STEPS TO MY PROJECT APPLICATION:
<kbd>**»**</kbd> Re-designed steps for CSRF-attack and CSRF-protection view;

<kbd>**»**</kbd> "Small improve" point for disabling "http.headers()";

<kbd>**»**</kbd> Also added feature to show the "username" after the login;

**First one** will provide CSRF-protection in fact; Previously it was disabled completely;

But I decided to re-design it; And firstly just start to use CSRF-protection (provided by Spring configuration);

Then I decided to do next points with CSRF-protection:

There enabled CSRF-protection, but with specific option as: 

    csrfTokenRepository(CookieCsrfTokenRepository); 

As result will be something like "CSRFtoken as cookie"; As feature - this is can be helpful and good.

But I also decided to do this with additional "option"; so result will be as:

    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); 

This will trigger situation that if application will be with Cross-site-scripting troublepoint:

should be possible to get CSRF-token (based on disabled HttpOnly for this Csrftoken-cookie);

     As result - next situation:
     CSRF protection is enabled with project (by Spring framework);
     
     With this design - covered mainly only POST/DELETE request forms. And ignored GET-request forms.
     If we would like to use csrf-token for GET-request forms - possible to add it with HTML template;
     Thymeleaf will provide this feature. And I 'tried' to use it as input-hidden form.
     
     Generally, this is worst design - because GET-method for critical things.... not nice.
     
     Then CSRF protection with certain setting about cookie/csrf-token (with HttpOnly-disabled).
     
     As result - possible to get this kind of "cookie" by scripts.
     
     So, CSRF protection for POST-request forms is works. And kind of csrf-tokens for GET-requests.
     
     But we are able to do CSRF-attack for this GET-request form (anyway).
     And we are able to do CSRF-attack for POST-request form - if "csrf-tokens" will be known for us.
     With Cross-Site-Scripting trouble under the project-application - quite likely to perform it.
     
     So we are able to exploit and did the CSRF attack with this project-application based on points:
     
     GET-request forms do not really protected by CSRF-tokens;
     POST-request forms vulnerable if possible to get csrf-token (by using Cross-site-scripting as example).
     
     Some of this points can be more visible, when we add log-out (on current time there is "mapping");
     
     Also with enabled CSRF protection and with default state about database-console:
     hibernate/h2-console access will be 'broken' based on points that CSRF-tokens is missing with login there;
 
 
 
 **Second one** will provide situation that certain http-**security**-header (by Spring setting) will be missing;
 
 Most common and good one headers are there; Indeed more nice to have this feature enabled;
 
 Also as additional to "disabled"-point for http.cors; 
 
 Unavailable "direct" exploiting it yet; But, anyway, already visible as potential troublepoints...
 
 
 
 **Third one** just provide "view"-point. :)
 
 When logged - will be visible username under the form-page;
 
      Can be just as "trigger" of visible understanding which user there..
      Also based on point that not added proper work for 'log-out' yet;
      
      Can be helpful for explaining some points about.
