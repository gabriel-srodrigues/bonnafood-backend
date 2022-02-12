package br.com.bonnafood.app.common.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("bonnafood.storage")
public class StorageProperties {
    private StorageType type = StorageType.LOCAL;
    private Local local = new Local();
    private S3 s3 = new S3();

    public enum StorageType { LOCAL, S3 }

    @Getter
    @Setter
    public static class Local {
        @NotNull
        private Path userAvatarDirectory;
    }

    @Getter
    @Setter
    public static class S3 {
        @NotBlank
        private String bucket;
        @NotNull
        private Regions region;
        @NotBlank
        private String userAvatarDirectory;
        @NotBlank
        private String profile;
    }
}
