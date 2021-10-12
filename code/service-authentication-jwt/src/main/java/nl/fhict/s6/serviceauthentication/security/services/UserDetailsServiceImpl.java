package nl.fhict.s6.serviceauthentication.security.services;

import nl.fhict.s6.serviceauthentication.datamodels.UserDao;
import nl.fhict.s6.serviceauthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDao> userByEmail = userRepository.findByEmail(username);
        if(userByEmail.isPresent())
        {
            return UserDetailsImpl.build(userByEmail.get());
        }
        Optional<UserDao> userByUsername = userRepository.findByUsername(username);
        if(userByUsername.isPresent())
        {
            return UserDetailsImpl.build(userByUsername.get());
        }
        throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
}
