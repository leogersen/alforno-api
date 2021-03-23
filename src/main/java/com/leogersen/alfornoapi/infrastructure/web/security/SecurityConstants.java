package com.leogersen.alfornoapi.infrastructure.web.security;

public class SecurityConstants {

    public static final String SECRET_KEY = "MeDMaRiA";
    public static final long EXPIRATION_TIME = 86400000; // 1 Day
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
