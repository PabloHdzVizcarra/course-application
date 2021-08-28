package jvm.pablohdz.courseapplication.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User userSaved;
        try {
            userSaved = userRepository.save(user);
        } catch (Exception e) {
            throw new EmailUserDuplicatedException(user.getEmail());
        }
        log.info("New user created with the username name is: {}", user.getUsername());
        return userSaved;
    }

}
