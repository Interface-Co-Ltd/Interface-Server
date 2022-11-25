package com.interfacesv.demo.component;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.image.Image;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileHandler {

    public List<Image> parseFileInfo(Board board, List<MultipartFile> multipartFiles) throws Exception{

        //반환할 파일 리스트
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
            String path = "src/main/resources/static/images/" + current_date;
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
                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;

                //dto 생성성
                Image image = Image.builder()
                        .uuid(UUID.randomUUID().toString())
                        .fileName(multipartFile.getOriginalFilename())
                        .uploadPath(path+"/"+new_file_name)
                        .board(board)
                        .build();

                imageList.add(image);

                //저장된 파일로 변경해 이를 보여줌
                file = new File(absolutePath + path + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }
        return imageList;
    }
}
