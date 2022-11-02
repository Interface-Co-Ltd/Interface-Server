package com.interfacesv.demo.domain.image;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.board.BoardRepository;
import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRepositoryTest {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUP() {
        Long id = 1L;
        String studentId = "20011572";
        String name = "이름";
        String password = "1234";
        String email = "email";
        String auth = "1";
        String phone = "000-1111-1111";
        String birthday = "02-02-25";

        User user = User.builder()
                .id(id)
                .studentId(studentId)
                .name(name)
                .password(password)
                .email(email)
                .auth(auth)
                .phone(phone)
                .birthday(birthday)
                .build();

        userRepository.save(user);

        String title = "제목입니다";
        String content = "내용입니다";
        String type = "공지사항";

        Board board = Board.builder()
                .title(title)
                .content(content)
                .type(type)
                .user(userRepository.findByStudentId(studentId).get())
                .build();

        boardRepository.save(board);

        String uuid = "234124-dfsfeds-12323";
        String uploadPath = "/static/image";
        String fileName = "image.jpeg";

        Image image = Image.builder()
                .uuid(uuid)
                .uploadPath(uploadPath)
                .fileName(fileName)
                .board(board)
                .build();

        imageRepository.save(image);
    }

    @After
    public void cleanUp(){
        imageRepository.deleteAll();
    }

    @Test
    public void 저장조회() {

        List<Image> imageList = imageRepository.findAll();
        Image image = imageList.get(0);

        assertThat(image.getUuid()).isEqualTo("234124-dfsfeds-12323");
        assertThat(image.getFileName()).isEqualTo("image.jpeg");
        assertThat(image.getUploadPath()).isEqualTo("/static/image");

        Board board = image.getBoard();

        System.out.println(">>>>> createDate="+board.getCreated_date()+", modifiedDate="+board.getModified_date());

        assertThat(board.getTitle()).isEqualTo("제목입니다");
        assertThat(board.getContent()).isEqualTo("내용입니다");
        assertThat(board.getType()).isEqualTo("공지사항");

    }

}