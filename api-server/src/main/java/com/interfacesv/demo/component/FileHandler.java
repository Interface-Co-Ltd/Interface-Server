package com.interfacesv.demo.component;

import com.interfacesv.demo.domain.Cooperation.Cooperation;
import com.interfacesv.demo.domain.image.Image;
import com.interfacesv.demo.sevice.ImageService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class FileHandler {
    private final ImageService imageService;

    public FileHandler(ImageService imageService){
        this.imageService = imageService;
    }

    public List<Image> parseFileInfo(
            List<MultipartFile> multipartFiles
    ) throws Exception{
        List<Image> imageList = new ArrayList<>();

        if(!CollectionUtils.isEmpty(multipartFiles)){
            //파일명을 업로드 날짜로 변환해 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dateTimeFormatter);

            //프로젝트 디렉터리 내외 저장 절대경로 설정
            //File.separator = 경로 구분자
            String absolutePath = new File("").getAbsolutePath() + File.separator;

            //세부경로
            String path = "iamges" + File.separator + current_date;
            File file = new File(path);

            //디렉터리 존재 X
            if(!file.exists()){
                boolean wasSuccessful = file.mkdirs();

                //디렉터리 생성 실패
                if(!wasSuccessful) System.out.println("file : was not successful");
            }

            //다중파일 처리
            for(MultipartFile multipartFile : multipartFiles){
                //파일 확장자
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                //확장자명 존재X
                if(ObjectUtils.isEmpty(contentType)) break;
                else{
                    //확장자  jpeg, png 파일 처리
                    if(contentType.contains("image/jpeg")) originalFileExtension = ".jpg";
                    else if(contentType.contains("image/png")) originalFileExtension = ".png";
                    else break;
                }

                //파일 중복 피하기 : 나노초 처리
                String new_file_name = System.nanoTime() + originalFileExtension;

                //dto 생성성
            }
        }
    }
}
