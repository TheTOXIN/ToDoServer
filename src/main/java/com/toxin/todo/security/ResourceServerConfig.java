package com.toxin.todo.security;

import com.toxin.todo.enums.RoleEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "todo-resource";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/*").permitAll()
            .antMatchers("/rest/role/**").hasAuthority(RoleEnum.ADMIN.name())
            .antMatchers("/rest/users/**").hasAuthority(RoleEnum.ADMIN.name())
            .antMatchers("/rest/tasks/**").hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.USER.name())
            .antMatchers("/todo/**").authenticated()
            .antMatchers("/hello").anonymous();
    }

}
