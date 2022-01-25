package br.com.bonnafood.app.security.authorizationserver;

import br.com.bonnafood.app.users.domain.model.BonnaUser;
import br.com.bonnafood.app.users.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BonnaUser bonnaUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));

        return new AuthUser(bonnaUser, getAuthorities(bonnaUser));
    }

    private Collection<GrantedAuthority> getAuthorities(BonnaUser usuario) {
        return usuario.getGroups().stream()
                .flatMap(grupo -> grupo.getPermissions().stream())
                .map(permissao -> new SimpleGrantedAuthority(permissao.getName().toUpperCase()))
                .collect(Collectors.toSet());
    }

}
