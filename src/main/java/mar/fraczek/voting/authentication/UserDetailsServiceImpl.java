package mar.fraczek.voting.authentication;

import lombok.RequiredArgsConstructor;
import mar.fraczek.voting.repo.VoterRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final VoterRepository voterRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return voterRepository.findByPesel(username).map(voter -> new VoterUser(voter.getPesel()))
                .orElseThrow(() -> new RuntimeException(""));
    }

    @RequiredArgsConstructor
    public class VoterUser implements UserDetails {

        private final String pesel;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singletonList(new SimpleGrantedAuthority("user"));
        }

        @Override
        public String getPassword() {
//            return "$2y$10$khq1krdZWD0ThftBc9/R/uHpBjibUXLqSL7wlvFqYlKs3gWkFS1U.";
            return bCryptPasswordEncoder.encode("password");
        }

        @Override
        public String getUsername() {
            return pesel;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
