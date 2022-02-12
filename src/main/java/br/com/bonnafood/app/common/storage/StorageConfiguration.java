package br.com.bonnafood.app.common.storage;

import br.com.bonnafood.app.files.domain.service.StorageService;
import br.com.bonnafood.app.files.domain.service.S3StorageService;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StorageConfiguration {
    private final StorageProperties properties;

    @Bean
    @ConditionalOnProperty(name = "bonnafood.storage.type", havingValue = "S3")
    public AmazonS3 amazonS3() {

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider(properties.getS3().getProfile()))
                .withRegion(properties.getS3().getRegion())
                .build();
    }

    @Bean
    public StorageService fotoStorageService() {
        if (StorageProperties.StorageType.S3.equals(properties.getType())) {
            return new S3StorageService(properties.getS3().getBucket(), properties.getS3().getUserAvatarDirectory());
        } else {
            return null;
        }
    }

}
