package iuh.dhktpm15.toancuc.xulyfile;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface XuLyFile {
    public void init();

    public void save(MultipartFile file);

    public Resource load(String filename);
}