/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ouhk.comps380f.dao.NewUserRepository;
import ouhk.comps380f.model.NewUser;

/**
 *
 * @author LTW
 */
@Service
public class NewUserService implements UserDetailsService {

    @Resource
    NewUserRepository newUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        NewUser newuser = newUserRepo.findById(username).orElse(null);
        if (newuser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        
            authorities.add(new SimpleGrantedAuthority(newuser.getRole()));
        
        return new User(newuser.getUsername(), newuser.getPassword(), authorities);
    }

}
