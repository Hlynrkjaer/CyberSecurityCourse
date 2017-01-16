package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, String> accountDetails;

    @PostConstruct
    public void init() {
        this.accountDetails = new TreeMap<>();
        this.accountDetails.put("Valravn","(+F?%2^hXV=)2PbE@&;dJ4D]{SkZ_`Sf94n%S]uaNVP>;2w<sB?2^g");
        this.accountDetails.put("Wardruna","fh2T[_6H h9ZfWn%pFxA_UP\"A^ym2`N+{3ge_'2&\\V~dFVAP*}Q/M");
        this.accountDetails.put("Ilmarinen",";.;vmRh>Xd'5<r[5uBHgcHs3G`c7WBRe3*%Tw]y&'cDL-Qe<HkV:Z9#");
        this.accountDetails.put("Karhu","d'Y].=;msd:)fg)a<dzN{V5bj.S_fAe4&)U*/~m>Y,37>}3K'jb47^\\dds*m");
        this.accountDetails.put("Asgeir","Fylkir");
        this.accountDetails.put("Eivor","Sinklars Visa");
        this.accountDetails.put("Heilung","Krigsgaldr");
        this.accountDetails.put("Skogvidr","D%GfS_s5{@2{,U9LBc!wv7XG4U5B&8\"r?Y;:;&4+34(>S\"Y'VkS89Q");
        this.accountDetails.put("admin","admin");
        this.accountDetails.put("CMS","CMSpassword");
      //      ^ we able to remove this "default" passwords for "admin"-access;
     //      and passwords, which can be as "backdoor" under the "tools in use";
    // but also will be good to use "encrypt"-methods for store passwords in database;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!this.accountDetails.containsKey(username)) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        if (this.accountDetails.get(username).equals("admin")){
        return new org.springframework.security.core.userdetails.User(
             username,
                this.accountDetails.get(username),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        }
        return new org.springframework.security.core.userdetails.User(
                username,
                this.accountDetails.get(username),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}