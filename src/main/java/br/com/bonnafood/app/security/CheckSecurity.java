package br.com.bonnafood.app.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {
    @PreAuthorize("@bonnafoodSecurity.hasAdminRole()")
    @Retention(RUNTIME)
    @Target(METHOD)
    @interface Admin { }

    @interface Users {
        @PreAuthorize("@bonnafoodSecurity.isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanView {
        }
    }

}
