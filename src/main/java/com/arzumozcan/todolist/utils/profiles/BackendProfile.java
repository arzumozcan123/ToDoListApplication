package com.arzumozcan.todolist.utils.profiles;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// spring.profiles.active=frontend
// spring.profiles.active=backend
// spring.profiles.active=fullstack

@Component// Spring nesnesi olmasını sağlamak için
@Profile("backend") //application.properties
public class BackendProfile implements IChooiseProfile{
    @Override
    public String message(String name) {
        return BackendProfile.class+" Profile: "+name;
    }
}