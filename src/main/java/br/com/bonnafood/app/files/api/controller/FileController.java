package br.com.bonnafood.app.files.api.controller;

import br.com.bonnafood.app.files.domain.model.NewPhoto;
import br.com.bonnafood.app.files.domain.service.StorageService;
import br.com.bonnafood.app.users.domain.model.User;
import br.com.bonnafood.app.users.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Tag(name = "Files")
@RequestMapping("upload")
public class FileController {
    private final UserService userService;
    private final StorageService storageService;

    @PutMapping(value = "/users/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserAvatar(@PathVariable String id, @RequestPart MultipartFile file) throws IOException {
        User user = userService.findByIdOrThrows(id);

        String fileName = storageService.makeFileName(file.getOriginalFilename());

        NewPhoto newPhoto = new NewPhoto();
        newPhoto.setFileName(fileName);
        newPhoto.setContentType(file.getContentType());
        newPhoto.setInputStream(file.getInputStream());

        storageService.update(user.getAvatar(), newPhoto);

        user.setAvatar(fileName);
        userService.save(user);

        return ResponseEntity.ok().build();
    }
}
