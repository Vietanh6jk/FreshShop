package iuh.dhktpm15.toancuc.xulyfile.iml;


import iuh.dhktpm15.toancuc.xulyfile.XuLyFile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class XuLyFileImpl implements XuLyFile {

    private final Path root = Paths.get("N:\\WWW_BTL\\Nhom6_WWW\\direct\\image");

    @Override
    public void init() {
        try {
            if(!Files.exists(root)){
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error create folder root");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        return null;
    }
}
