package com.r2s.springJPA.util;

import com.r2s.springJPA.entity.API;
import com.r2s.springJPA.entity.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class Constants {
    public static final String BASE_URL = "(http://localhost:8080)";

    public static final String BUYING = "paying";

    public static final String ORDERED = "ordered";

//    public static List<API> list = new ArrayList<>();
//
//    public static void setAPI(List<API> listAPI) {
//        list = listAPI;
//    }

//    public static final API GET_ALL_USERS = new API(BASE_URL + "/user", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "GET");
//
//    public static final API INSERT_USER = new API(BASE_URL + "/user", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "POST");
//
//    public static final API UPDATE_USER = new API(BASE_URL + "/user", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "PUT");
//
//    public static final API DELETE_USER = new API(BASE_URL + "/user", Arrays.asList(new SimpleGrantedAuthority("ADMIN")), "DELETE");

}
