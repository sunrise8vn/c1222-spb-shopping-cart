package com.cg.service.uploadMedia;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


@Service
public class UploadMediaServiceImpl implements IUploadMediaService{

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map uploadImage(MultipartFile multipartFile, Map options) throws IOException {
        return cloudinary.uploader().upload(multipartFile.getBytes(), options);
    }

    @Override
    public Map destroyImage(String publicId, Map options) throws IOException {
        return cloudinary.uploader().destroy(publicId, options);
    }
}
