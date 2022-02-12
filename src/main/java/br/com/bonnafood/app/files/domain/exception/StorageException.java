package br.com.bonnafood.app.files.domain.exception;

public class StorageException extends RuntimeException{
    public StorageException(String message, Exception e) {
        super(message, e);
    }
}
