package de.hsba.bi.fahrradkurrier.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findCouriers() {
        return userRepository.findByRole(UserRoleEnum.COURIER);
    }

    public List<User> findCustomers() {
        return userRepository.findByRole(UserRoleEnum.CUSTOMER);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findCurrentUser() {
        return userRepository.findByUserName(User.getCurrentUsername());
    }
}