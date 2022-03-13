package br.com.bonnafood.app.common.security;

import br.com.bonnafood.app.recipes.domain.repository.RecipeRepository;
import br.com.bonnafood.app.users.domain.exception.UserNotFoundException;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public abstract class BonnafoodSecurity {
    private static final String CREATE_RECIPES = "CREATE_RECIPES";
    private static final String EDIT_RECIPE = "EDIT_RECIPE";
    private static final String ADMIN = "ADMIN";
    private static final String VIEW_USERS = "VIEW_USERS";
    private static final String DELETE_RECIPES = "DELETE_RECIPES";
    private static final String EDIT_USERS = "EDIT_USERS";
    private static final String CREATE_USERS = "CREATE_USERS";


    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public abstract String getUserId();

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public Optional<User> getUserAuthenticated() {
        String userId = getUserId();

        if (userId == null)
            return Optional.empty();

        return userRepository.findById(userId);
    }

    public boolean canCreateUser() {
        return hasAdminRole() || hasCreateUserRole();
    }


    public boolean canUpdateUser() {
        return hasAdminRole();
    }

    public boolean canUpdatePassword(String id) {
        return hasAdminRole() || isUserAuthenticated(id);
    }

    public User getUserAuthentatedOrThrows() {
        return this.getUserAuthenticated().orElseThrow(() -> new UserNotFoundException(getUserId()));
    }

    public boolean canCreateRecipe() {
        return isAuthenticated() && hasCreateRecipeRole();
    }

    public boolean canEditRecipe(String recipeId) {
        return hasAdminRole() || hasEditRecipeRole() || isRecipeOwner(recipeId);
    }

    public boolean canDeleteRecipe(String recipeId) {
        return hasAdminRole() || hasDeleteRecipeRole() || isRecipeOwner(recipeId);
    }

    private boolean hasDeleteRecipeRole() {
        return hasAuthority(DELETE_RECIPES);
    }

    public boolean canViewAllUsers() {
        return hasAdminRole() || hasViewUserRole();
    }

    public boolean canSeeSpecUser(String userId) {
        return hasAdminRole() || isUserAuthenticated(userId);
    }

    private boolean isUserAuthenticated(String userId) {
        return userId.equals(getUserId());
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean hasAnyAuthority(String... authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> Arrays.asList(authorityName).contains(authority.getAuthority()));
    }

    private boolean isRecipeOwner(String recipeId) {
        return recipeRepository.isRecipeOwner(recipeId, getUserId());
    }

    public boolean canUpdateAnyPassword() {
        return this.hasAdminRole();
    }

    public boolean hasAdminRole() {
        return hasAuthority(ADMIN);
    }

    private boolean hasCreateRecipeRole() {
        return hasAuthority(CREATE_RECIPES);
    }

    private boolean hasEditRecipeRole() {
        return hasAuthority(EDIT_RECIPE);
    }

    private boolean hasViewUserRole() {
        return hasAuthority(VIEW_USERS);
    }

    private boolean hasCreateUserRole() {
        return hasAuthority(CREATE_USERS);
    }

}
