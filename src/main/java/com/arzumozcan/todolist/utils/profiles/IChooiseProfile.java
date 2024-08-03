package com.arzumozcan.todolist.utils.profiles;

import org.springframework.stereotype.Component;

// Spring nesnesi olmasını sağlamak için
@Component
public interface IChooiseProfile {

    public String message(String name);
}