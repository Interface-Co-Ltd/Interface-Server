package com.interfacesv.demo.domain.image;

import com.interfacesv.demo.domain.BaseTimeEntity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    @Builder
    public Image(String uuid, String uploadPath, String fileName) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
//        this.product = product; //조인하는 테이블의 객체를 생성자로 받아야함
    }


    public void update(String uuid, String uploadPath, String fileName) {
        this.uuid = uuid;
        this.uploadPath = uploadPath;
        this.fileName = fileName;
    }


}