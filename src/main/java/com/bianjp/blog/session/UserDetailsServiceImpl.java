package com.bianjp.blog.session;

import com.bianjp.blog.entity.User;
import com.bianjp.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || username.isEmpty()) {
      throw new UsernameNotFoundException("Empty username: " + username);
    }

    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("username=" + username + " not found");
    }

    return new UserDetailsImpl(user);
  }
}
