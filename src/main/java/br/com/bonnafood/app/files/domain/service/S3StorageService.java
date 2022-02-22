package br.com.bonnafood.app.files.domain.service;

import br.com.bonnafood.app.files.domain.model.NewPhoto;
import br.com.bonnafood.app.files.domain.exception.StorageException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class S3StorageService implements StorageService {
    @Autowired
    private AmazonS3 amazonS3;
    private final String bucket;
    private final String userAvatarDirectory;

    public S3StorageService(String bucket, String userAvatarDirectory) {
        this.bucket = bucket;
        this.userAvatarDirectory = userAvatarDirectory;
    }

    @Override
    public void storage(NewPhoto newPhoto) {
        try {
            String filePath = getFilePath(newPhoto.getFileName());

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucket,
                    filePath,
                    newPhoto.getInputStream(),
                    objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
        }
    }

    @Override
    public void delete(String nomeArquivo) {
        try {
            String caminhoArquivo = getFilePath(nomeArquivo);

            var deleteObjectRequest = new DeleteObjectRequest(bucket, caminhoArquivo);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }


    private String getFilePath(String nomeArquivo) {
        return String.format("%s/%s", userAvatarDirectory, nomeArquivo);
    }
}
