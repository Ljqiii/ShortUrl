package com.ljqiii.shorturl.service;


import com.ljqiii.shorturl.model.NormalUser;
import com.ljqiii.shorturl.repository.UserRepository;
import com.ljqiii.shorturl.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public boolean deleteUser(int id) {
        int i = userRepository.deleteRoleById(id);
        int ii = userRepository.deleteUserById(id);

        if (i == 1 && ii == 1) {
            return true;
        }
        return false;
    }



    public void newUser(String name, String password, boolean isadmin) {
        NormalUser user = new NormalUser();
        user.setName(name);

        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.insertUser(user);
        if (isadmin == true) {
            userRoleRepository.insertRole(user.getId(),
                    userRoleRepository.selectRoleId("ROLE_admin"));
        } else {
            userRoleRepository.insertRole(user.getId(),
                    userRoleRepository.selectRoleId("ROLE_user"));
        }
    }

}
