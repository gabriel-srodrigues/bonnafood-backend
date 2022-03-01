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
        @PreAuthorize("@bonnafoodSecurity.canViewAllUsers()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanView {
        }

        @PreAuthorize("@bonnafoodSecurity.canSeeSpecUser(#userId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanSeeHimself {
        }

        @PreAuthorize("@bonnafoodSecurity.canCreateUser()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanCreate {
        }


        @PreAuthorize("@bonnafoodSecurity.canUpdateUser()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdate {
        }
        @PreAuthorize("@bonnafoodSecurity.canUpdatePassword(#id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanUpdatePassword {
        }
    }

    @interface Recipes {
        @PreAuthorize("@bonnafoodSecurity.canCreateRecipe()")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanCreate {
        }

        @PreAuthorize("@bonnafoodSecurity.canEditRecipe(#recipeId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanEdit {
        }

        @PreAuthorize("@bonnafoodSecurity.canDeleteRecipe(#recipeId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        @interface CanDelete {
        }
    }
}
