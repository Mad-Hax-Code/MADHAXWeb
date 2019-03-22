package com.madhax.website.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;

public class Authority extends BaseEntity implements GrantedAuthority {

    @Column(name = "authority")
    private String authority;

    public Authority() { }

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
