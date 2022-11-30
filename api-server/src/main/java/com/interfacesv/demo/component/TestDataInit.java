package com.interfacesv.demo.component;

import com.interfacesv.demo.domain.board.Board;
import com.interfacesv.demo.domain.schedule.Schedule;
import com.interfacesv.demo.dto.BoardDto;
import com.interfacesv.demo.dto.CooperationDto;
import com.interfacesv.demo.dto.ScheduleDto;
import com.interfacesv.demo.service.BoardService;
import com.interfacesv.demo.service.CooperationService;
import com.interfacesv.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TestDataInit {

    private final BoardService boardService;
    private final ScheduleService scheduleService;
    private final CooperationService cooperationService;

    public void boardInitial() {
        List<BoardDto> boardDtoList = new ArrayList<>();

        boardDtoList.add(new BoardDto(0L, "2학기 동방 사물함 배정", "안녕하세요! 소통부장 동기창입니다!\n\n저희 동아리방에 있는 사물함을 배정할려고 합니다!\n\n✅ 배정 가능한 사물함 갯수는 정리 후 추후 말씀 드리겠습니다!\n✅ 사물함 배정은 2학기 회비 납부하신 분들에 한해 신청 가능하십니다!\n✅ 신청은 선착순으로 진행 됩니다!\n✅ 사물함 신청폼은 이번주 목요일(9월 8일) 오후 8시에 열립니다! 신청폼은 공지된 시간에 알려드리겠습니다!\n✅ 사물함 배정 결과는 추석 후 개인적으로 공지해 드리겠습니다!\n✅ 사물함 사용은 2학기 종강(12월 21일)까지 가능합니다!\n\n추가적으로 궁금한 점들은 바로바로 갠톡 보내주세요!\n\n감사합니다!", "NOTICE", "20003201", "", ""));
        boardDtoList.add(new BoardDto(0L, "2022-2학기 인터페이스 개강총회", "안녕하세요! 소통부장 동기창입니다!\n\n오늘 드디어 개강했네요 꺄르륵 ㅠ\n\n내일(9월 2일 금요일) 인터페이스 2학기 개강총회 개최합니다!\n\n✅장소 : 대양 AI센터 B116호 입니다!\n✅날짜 : 9월 2일(금) 오후 6시\n✅회비 : 신입생, 재학생 상관없이 20000원\n\n뒷풀이 장소도 변경이 되었습니다!\n\n✅장소 : 화심\n✅날짜 : 9월 2일(금) 개강총회 끝난 후 바로\n✅요금 : 회비납부자 - 20000원  회비미납부자 - 30000원", "NOTICE", "20003201", "", ""));
        boardDtoList.add(new BoardDto(0L, "인터페이스 돕바 공구", "안녕하세요! 소통 부장 동기창입니다.\n\n이제 곧 겨울이 다가와서 저희 동아리에서 굿즈(?)를 준비했습니다!\n\n바로바로\n\n돕바(롱페딩)!!\n\n✅ 가격 : 70,000원\n✅ 조사 : 22.11.12(토) 23:59 까지\n✅ 색상 : 검정색, 흰색\n\n보다 정확한 디자인을 확인 하실라면 신청폼을 통해 확인해 보세요!\n\n✅ 신청폼 :https://forms.gle/8Pqp1S9ovgeRSvQh8\n\n⚠️ 조사가 마감된 후 주문취소(환불), 인원추가 및 사이즈변경이 불가능합니다!\n\n기타 문의 사항은 소통 부장 동기창한테 카톡 주세요!\n감사합니다!", "NOTICE", "20003201", "", ""));
        boardDtoList.add(new BoardDto(0L, "2022년 인터페이스 창립제", "안녕하세요! 소통부장 동기창입니다.\n\n이번 11월 19일(토요일)에 인터페이스 주요 행사 중 하나인 “창립제”가 열립니다!👏👏👏\n\n창립제란, 저희 동아리가 1년동안 진행했던 활동들을 졸업하신 선배님들에게 소개하며, 선후배들의 교류를 마련하는 자리입니다.\n\n지난 3년동안 코로나로 인해 진행하지 못했던 행사인 만큼 많은 분들의 참가 부탁드립니다!\n\n✳️ 2022년 창립제 ✳️\n\n✅ 일시 : 11월 19일(토요일) 오후 4시 30분\n✅ 장소 : 대양 AI센터 B205 (변동가능)\n✅ 대상 : 인터페이스 동아리 부원이라면 누구나\n✅ 기타 : 창립제 진행 후 선배님들과의 뒷풀이가 있을 예정입니다!\n		단, 이번 뒷풀이는 반드시! 창립제를 참석하셔야 참석 가능 하십니다!\n		\n✅ 참여폼 : https://forms.gle/wkmwtAznxiB8RWoE8\n\n기타 궁금하신 부분이 계신 분들은 기장 @홍지섭, 부기장 @공민성 에게 문의 부탁드립니다!\n감사합니다!", "NOTICE", "20003201", "", ""));
        boardDtoList.add(new BoardDto(0L, "36기 인터페이스 회장 선거", "안녕하세요! 소통부장 동기창입니다!\n\n어느덧 22년 2학기도 얼마 안남았네요… 디귿\n그래서 2023년도 회장 선거를 하는 시기가 왔습니다!\n\n⭐️ 36기 회장 후보 등록\n✅ 후보자 등록 기간 : 11월 24일(목) ~ 11월 29일(화)\n✅ 신청 자격 : 인터페이스 회칙 제 13조(임원 선출) 제1항을 충족하는 자\n    제1항. 회장은 2학기 이상 수료 예정자로 회원 7인 이상의 추천으로 후보로 등록되며 신입회원,\n정회원 과반 수 이상의 출석 중 출석 회원 과반수의 찬성으로 선출한다.\n(단 당선자가 없을 경우는 득표가 높은 후보 2명을 재투표하여 선출하며 그래도 없을 경우 최고득표자를 당선자로 한다).\n✅ 회장 후보 등록폼 : https://forms.gle/tu5WtV8mWiRUobs36\n\n⭐️ 36기 회장 선출 투표\n✅ 투표 일시 : 12월 2일(금) 18시 30분\n✅ 장소 : 대양AI센터 B103호\n✅ 참가 신청 기간 : 11월 24일(목) ~ 11월 29일(화)\n✅ 참가 신청 폼 : https://forms.gle/YUg8oNcWZb2fcMbd9\n\n⭐️회장 선출 투표 끝나고 뒷풀이가 있습니다!\n✅ 참가비 : 15,000원\n✅ 참가 신청 폼은 회장 선출 투표 참가 폼에 같이 있습니다.", "NOTICE", "20003201", "", ""));

        for(int i=0;i<boardDtoList.size();i++) {
            boardService.save(boardDtoList.get(i));
        }
    }

    public void scheduleInitial() {
        List<ScheduleDto> scheduleDtoList = new ArrayList<>();

        scheduleDtoList.add(new ScheduleDto(0L, "세종대학교", "중간고사", "2022-10-20T00:00:00+09:00", "2022-10-26T23:59:59+09:00", 1L));
        scheduleDtoList.add(new ScheduleDto(0L, "세종대학교", "기말고사", "2022-12-15T00:00:00+09:00", "2022-12-21T23:59:59+09:00", 1L));
        scheduleDtoList.add(new ScheduleDto(0L, "인터페이스", "2022창립제", "2022-10-19T16:30:00+09:00", "2022-10-19T18:30:00+09:00", 0L));
        scheduleDtoList.add(new ScheduleDto(0L, "인터페이스", "프전DAY1", "2022-12-01T10:00:00+09:00", "2022-12-01T17:00:00+09:00", 0L));
        scheduleDtoList.add(new ScheduleDto(0L, "인터페이스", "프전DAY2", "2022-12-02T10:00:00+09:00", "2022-12-02T17:00:00+09:00", 0L));

        for(int i=0;i<scheduleDtoList.size();i++) {
            scheduleService.save(scheduleDtoList.get(i));
        }
    }

    public void cooperationInitial() {
        List<CooperationDto> cooperationDtoList = new ArrayList<>();

        cooperationDtoList.add(new CooperationDto(0L, "공민성", "address", "https://m.place.naver.com/my/place/detailList/d4d3e1a444664b69a58548af8d5b8c1b?close%27"));
        cooperationDtoList.add(new CooperationDto(0L, "이유진", "address", "https://naver.me/FxXkdUiC%27"));

        for(int i=0;i<cooperationDtoList.size();i++) {
            cooperationService.save(cooperationDtoList.get(i));
        }
    }

    public void deleteAll() {
        List<BoardDto> boardDtoList = boardService.findAll();
        List<ScheduleDto> scheduleDtoList = scheduleService.findAll();
        List<CooperationDto> cooperationDtoList = cooperationService.findAll();

        for(int i=0;i<boardDtoList.size();i++) {
            boardService.deleteById(boardDtoList.get(i).getId());
        }

        for(int i=0;i<scheduleDtoList.size();i++) {
            scheduleService.deleteById(scheduleDtoList.get(i).getId());
        }

        for(int i=0;i<cooperationDtoList.size();i++) {
            cooperationService.delete(cooperationDtoList.get(i).getId());
        }
    }
}
