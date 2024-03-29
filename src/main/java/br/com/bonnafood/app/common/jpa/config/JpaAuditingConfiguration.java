package br.com.bonnafood.app.common.jpa.config;

import br.com.bonnafood.app.common.security.BonnafoodSecurity;
import br.com.bonnafood.app.users.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@AllArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditingDateTimeProvider")
public class JpaAuditingConfiguration {
    private final BonnafoodSecurity bonnafoodSecurity;

    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

    @Bean
    public AuditorAware<User> auditorAware() {
        return bonnafoodSecurity::getUserAuthenticated;
    }


}
