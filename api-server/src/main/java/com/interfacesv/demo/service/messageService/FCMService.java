package com.interfacesv.demo.service.messageService;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.interfacesv.demo.dao.FCMTokenDao;
import com.interfacesv.demo.domain.user.User;
import com.interfacesv.demo.domain.user.UserRepository;
import com.interfacesv.demo.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FCMService {

    private final FCMTokenDao fcmTokenDao;
    private final UserRepository userRepository;

    public void sendNewNoticePosted(String title, String content) {
        List<User> userList = userRepository.findAll();

        for(int i=0;i<userList.size();i++) {
            String studentId = userList.get(i).getStudentId();
            if (!fcmTokenDao.hasKey(studentId)) {
                continue;
            }
            title = "[공지] " + title;

            String fcmToken = fcmTokenDao.getToken(studentId);
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(content)
                    .build();
            System.out.println("FcmToken : " + fcmToken);
            Message message = Message.builder()
                    .setNotification(notification)
//                    .putData("title", title)
//                    .putData("content", content)
                    .setToken(fcmToken)
                    .build();

            send(message);
        }
    }

    public void send(Message message) {
//        try {
            FirebaseMessaging.getInstance().sendAsync(message);
            //String response = FirebaseMessaging.getInstance().send(message);
            //System.out.println(response);
//        } catch (FirebaseMessagingException e) {
//            System.out.println("messaging failed!");
//            return;
//        }
    }

    public void saveToken(LoginUserDto loginUserDto) {
        if(fcmTokenDao.hasKey(loginUserDto.getStudentId())) {
            fcmTokenDao.deleteToken(loginUserDto.getStudentId());
            fcmTokenDao.saveToken(loginUserDto);
            return;
        }
        else {
            fcmTokenDao.saveToken(loginUserDto);
        }
    }
}
