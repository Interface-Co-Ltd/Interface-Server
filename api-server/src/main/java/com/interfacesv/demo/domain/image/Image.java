package com.interfacesv.demo.domain.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interfacesv.demo.domain.BaseTimeEntity.BaseTimeEntity;
import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.dto.ImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Image extends BaseTimeEntity {

    //@NotNull
    @Id
    /* uuid */
    private String uuid; //uuid

    //@NotNull
    /* 경로 */
    private String uploadPath; //upload_path
    /* 파일 이름 */
    //@NotNull
    private String fileName; //file_name

//    //@Nullable
//    @ManyToOne
//    @JoinColumn(name="product") // name은 Image 테이블에서의 칼럼명을 지정하는 것임 Build할때 product객체를 넣으면 자동으로 product객체의 PK가 들어가면서 생성됨
//    private Product product; // 대상 테이블은 매핑된 Entity의 오브젝트형을 보고 자동으로 정함 (여기선 Product)

    //@Nullable
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="board") // name은 Image 테이블에서의 칼럼명을 지정하는 것임 Build할때 board객체를 넣으면 자동으로 board객체의 PK가 들어가면서 생성됨
    private Board board; // 대상 테이블은 매핑된 Entity의 오브젝트형을 보고 자동으로 정함 (여기선 Board)

    @Builder
    public Image(String uuid, String uploadPath, String fileName, Board board) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.board = board; //조인하는 테이블의 객체를 생성자로 받아야함
    }

    public void update(String uuid, String uploadPath, String fileName, Board board) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
        this.board = board;
    }


    public List<ImageDto> toDtoList(List<Image> imageList) {
        List<ImageDto> imageDtoList = new ArrayList<>();

        for(Image image: imageList){
            imageDtoList.add(new ImageDto(image));
        }

        return imageDtoList;
    }
}
