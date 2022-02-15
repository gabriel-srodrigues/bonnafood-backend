package br.com.bonnafood.app.common.domain.enums;


import br.com.bonnasys.common.domain.enums.EnumListable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ProblemType implements EnumListable<ProblemType> {
    INVALID_DATA("option_problem_type_invalid_data", "/invalid-data", "Dados inválidos",HttpStatus.BAD_REQUEST),
    FORBIDDEN("option_problem_type_forbidden", "/forbidden", "Acesso negado", HttpStatus.FORBIDDEN),
    SYSTEM_ERROR("option_problem_type_system_error", "/system-error", "Erro de sistema", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER("option_problem_type_invalid_parameter", "/invalid-parameter", "Parâmetro inválido", HttpStatus.BAD_REQUEST),
    INCOMPREHENSIBLE_MESSAGE("option_problem_type_incomprehensible_message", "/incomprehensible-message", "Mensagem incompreensível", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("option_problem_type_resource_not_found", "/resource-not-found", "Recurso não encontrado", HttpStatus.NOT_FOUND),
    ENTITY_IN_USE("option_problem_type_entity_in_use", "/entity-in-use", "Entidade em uso", HttpStatus.BAD_REQUEST),
    BUSINESS_ERROR("option_problem_type_business_error", "/business-error", "Violação de regra de negócio", HttpStatus.BAD_REQUEST);

    private final String i18nKey;
    private final String uri;
    private final String title;
    private final HttpStatus status;

    public static Optional<ProblemType> of(HttpStatus status) {
        return Arrays.stream(ProblemType.values()).filter(type -> type.status.equals(status)).findFirst();
    }

    @Override
    public String getI18nKey() {
        return i18nKey;
    }
}
