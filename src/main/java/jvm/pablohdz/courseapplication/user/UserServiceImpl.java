package jvm.pablohdz.courseapplication.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User userSaved = userRepository.save(user);
        log.info("New user created with the username name is: {}", user.getUsername());
        return userSaved;
    }
}
