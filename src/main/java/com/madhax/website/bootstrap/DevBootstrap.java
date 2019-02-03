package com.madhax.website.bootstrap;

import com.madhax.website.domain.User;
import com.madhax.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextResfreshedEvent) {
        initData();
    }

    private void initData() {

    }
}
