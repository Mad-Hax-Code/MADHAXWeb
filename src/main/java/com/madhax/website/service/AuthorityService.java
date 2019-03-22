package com.madhax.website.service;

import com.madhax.website.domain.Authority;
import com.madhax.website.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority getById(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }

    public Set<Authority> getAll() {
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);
    }

    public void delete(Authority authority) {
        authorityRepository.delete(authority);
    }

    public void deleteById(Long id) {
        authorityRepository.deleteById(id);
    }
}
