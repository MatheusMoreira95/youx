package com.example.Youx.services;

import com.example.Youx.entities.Enfermeiro;
import com.example.Youx.repositories.EnfermeiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class UserDetailsServiceImpl implements UserDetailsService {


    final EnfermeiroRepository userRepository;

    public UserDetailsServiceImpl(EnfermeiroRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Enfermeiro enfermeiro = userRepository.findByNome(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + username));

        return new User(enfermeiro.getUsername(), enfermeiro.getPassword(), true, true, true, true, enfermeiro.getAuthorities());
    }
}
