package dtalk.service;

import dtalk.domain.Quiz;
import dtalk.domain.Record;
import dtalk.domain.User;
import dtalk.dto.quiz.QuizSaveDto;
import dtalk.dto.user.UserDetailDTO;
import dtalk.repository.QuizRepository;
import dtalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    public Long save(QuizSaveDto quizSaveDto){
        Quiz quiz = QuizSaveDto.createQuiz(quizSaveDto);
        UserDetailDTO u = (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = new User();
        user.setIdx(u.getIdx());
        System.out.println("퀴즈 세이브 유저" + u.getUser().getIdx());
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }

    public List<Quiz> myList(User user){
        return quizRepository.list(user);
    };
    public Long listCount(User user){
        return quizRepository.countList(user);
    };
    public void quizSend(Record record){

        quizRepository.quizSend(record);
    };
    public Quiz findQuiz(Long idx){
        return quizRepository.findQuiz(idx);
    }
    public List<Quiz> rankQuizList(LocalDateTime prev , LocalDateTime next){

        return quizRepository.rankQuizList(prev, next);
    }
}
