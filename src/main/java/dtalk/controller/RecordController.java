package dtalk.controller;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.dto.quiz.QuizListDTO;
import dtalk.dto.quiz.ReceiveQuizDTO;
import dtalk.dto.record.RecordDTO;
import dtalk.dto.user.UserDetailDTO;
import dtalk.dto.user.UserResponseDTO;
import dtalk.service.QuizService;
import dtalk.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController {
    private final QuizService quizService;
    private final RecordService recordService;

    @GetMapping
    @Operation(description = "유저의 좋아요 수")
    public Long recommendQuiz(@RequestParam Long userIdx){
        User user = new User();
        user.setIdx(userIdx);
        return recordService.userRecommendCount(user);
    }
    @PatchMapping("/recommend")
    @Operation(description = "퀴즈 좋아요")
    public void quizRecommend(@RequestBody FindRecord findRecord){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Quiz quiz = new Quiz();
        quiz.setIdx(findRecord.quizIdx);
        recordService.quizRecommend(me,quiz);

    }
    @GetMapping("/receive")
    @Operation(description = "내가 받은 퀴즈 리스트")
    public List<ReceiveQuizDTO> receiveQuizList(){
        User me = ((UserDetailDTO) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        List<ReceiveQuizDTO> responseList = new ArrayList<>();
        for (Quiz quiz: recordService.receiveQuizList(me)) {
            responseList.add(ReceiveQuizDTO.getQuizListDTO(quiz));
        }
        return responseList;
    }
    @Data
    static class FindRecord{
        private Long quizIdx;


    }
}
