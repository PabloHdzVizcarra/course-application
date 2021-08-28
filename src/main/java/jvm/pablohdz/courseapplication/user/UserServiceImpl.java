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
        // TODO: 8/28/21 throw custom exception when email is duplicated
        // TODO: 8/28/21  response with error and message to client
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
