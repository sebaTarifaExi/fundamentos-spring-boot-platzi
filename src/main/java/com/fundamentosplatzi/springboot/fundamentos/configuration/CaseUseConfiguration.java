package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.springboot.fundamentos.caseuse.GetUserImplement;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class CaseUseConfiguration {
    @RequestMapping
    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
