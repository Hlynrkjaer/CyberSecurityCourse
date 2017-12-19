# CyberSecurityCourse
<sub>cybersecuritybase-project ( task from https://cybersecuritybase.github.io/ -- Course Project One -- )</sub>
<hr />
There will be **check-steps**:
<ul>
<li>for improve design,</li>
<br />
<li>adding some features and</li>
<br />
<li>other related steps (with additional steps for troublepoints-usage) to my project-application.</li>
<br />
</ul>
Something like documentation for first design of this task available with next <a href="https://github.com/marrbjorn/documentation/tree/master/F-Secure%20Cyber%20Security%20Base%20MOOC.fi%20-First%20Project" target="_blank">URL</a>
<hr />
## FIRST EXTEND-STEPS TO MY PROJECT APPLICATION:


<strong>--></strong> There added "http.headers()"-disabled-point; and "http.cors()"-disabled-point;

<strong>--></strong> Small "add"-point as some kind of "mapping" (trick-feature); 

--
First one provide some kind of ability to "improve" troubles with security configuration.

      As result there will be disabled most of common "headers" and features, which good to have "enabled";
      
      And.. there is just "disabled" option under the Spring; 
      But will be more interesting to work with custom-ones;
      
      Basically should be required to work with "custom"..
      if we want to disable/change some of "headers"-features!
      
--
Second one provide some kind of potential troublepoint with Unvalidated Redirects.

      With current view: this is just kind of "trick"-feature;
      But provide additional steps to "play" with project-application;
      
      Also this is partly can be related with extend-steps for adding the next "vulnerable"-point.
      Which can be covered by Trouble Ten from OWASP TOP TEN..

<hr />
## SECOND EXTEND-STEPS TO MY PROJECT APPLICATION:


<strong>--></strong> There was re-design steps for CSRF-attack and CSRF-protection under the project-application;

<strong>--></strong> "Small improve" point for disabling "http.headers()";

<strong>--></strong> There also added feature to show the "username" after the login;

--
<strong>First one</strong> will provide CSRF-protection in fact; Previously it was just "disabled" totally;

But I decided to re-design it; And firstly just start to use CSRF-protection under the Spring configuration;

But after some dreams - I decided to do next points with CSRF-protection:

There will be enabled CSRF-protection, but with specific option as: 

    csrfTokenRepository(CookieCsrfTokenRepository); 

As result there will be kind of "CSRFtoken as cookie"; As feature - this is can be helpful and good.

But I also decided to do this with additional "option"; so result will be as:

    http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); 

This is will trigger situation that if application will be with Cross-site-scripting troublepoint...

So there is will be possibility to get CSRF-token (based on HttpOnly disabled for this Csrftoken-cookie);

     So as result there will be next situation:
     We have the CSRF protection enabled under the project-application by Spring;
     
     This is covered mainly just POST/DELETE request forms. And do not cover the GET-request forms.
     If we have dreams to use csrf-token for GET-request forms... so we able to add it under the HTML template;
     Thymeleaf will provide this feature. And I use it with this project-application as input-hidden form.
     
     But mainly this is kind of trick, because GET-method for critical things.... not nice.
     
     Also CSRF-protection with setting about cookie/csrf-token and with HttpOnly-disabled for this.
     
     As result there is possibility get this kind of "cookie" by scripts.
     
     So there work CSRF-protection for POST-request forms. And "kind" of csrf-tokens for GET-requests.
     
     But we able to do CSRF-attack for this GET-request form. This is will be work.
     And we able to do CSRF-attack for POST-request form - if "csrf-tokens" will be known for us.
     With Cross-Site-Scripting trouble under the project-application - this is can be with common steps.
     
     So we able to exploiting and did the CSRF attack with this project-application based on points:
     
     GET-request forms do not really protected by CSRF-tokens;
     POST-request forms vulnerable if there is possible to get csrf-token (by using Cross-site-scripting as example).
     
     First of this points can be more visible, when we add log-out (on current time there is "mapping");
     
     Also with CSRF-enabling and with not changes about database-console:
     hibernate/h2-console access will be not available based on points that CSRF-tokens is missing with login there;
 
 
 
 <strong>Second one</strong> will provide situation that http-security-headers (by Spring setting) will be missing;
 
 There is most common and good one headers; More nice to have this feature enabled;
 
 This is can be also as additional to "disabled"-point for http.cors; 
 
 There is missing "direct" exploiting it yet; But this is already visible as potential troublepoints...
 
 
 
 <strong>Third one</strong> just provide "view"-point. :)
 
 When there is login - will be visible username under the form-page;
 
      This is can be just "trigger" of visible understanding which user there..
      Based also on points.. that there is missing log-out/proper work yet;
      
      This is can be helpful already for explaining some points about.
