package dtalk.service;

import dtalk.domain.User;
import dtalk.dto.user.UserDetailDTO;
import dtalk.repository.FriendRepository;
import dtalk.repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    public void addFriend(User me,User user){

        friendRepository.addFriend(me,user);
    }
    public List<User> friendList(User me){
        friendRepository.friendList(me);
    }
}
