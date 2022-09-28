package com.interfacesv.demo.component;

import com.interfacesv.demo.domain.Cooperation.Cooperation;
import com.interfacesv.demo.domain.image.Image;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class FileHandler {
    public List<Image> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {
        List<Image> fileList = new ArrayList<>();
        if(multipartFiles.isEmpty()){
            return fileList;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String absolutePath = new File("").getAbsolutePath() + "/";

        String path = "src/main/resources/static/images/" + current_date;
        File file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }

        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                String contentType = multipartFile.getContentType();
                String originalFileExtension;

                if(ObjectUtils.isEmpty(contentType)){
                    break;
                }
                else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    } else {
                        break;
                    }
                }

                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;

                Image image = Image.builder()
                        .uuid(UUID.randomUUID().toString())
                        .fileName(multipartFile.getOriginalFilename())
                        .uploadPath(path+"/"+new_file_name)
                        .build();
                fileList.add(image);

                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);

            }
        }
        return fileList;
    }
}
