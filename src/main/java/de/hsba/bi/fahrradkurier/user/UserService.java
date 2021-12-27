package de.hsba.bi.fahrradkurier.user;

import de.hsba.bi.fahrradkurier.common.AddressRepository;
import de.hsba.bi.fahrradkurier.job.JobEntity;
import de.hsba.bi.fahrradkurier.web.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final AddressRepository addressRepository;
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
        addressRepository.save(user.getAddress());
        return userRepository.save(user);
    }

    public User findCurrentUser() {
        return userRepository.findByUserName(User.getCurrentUsername());
    }

    public boolean isUserNameUnique(String userName) {
        return userRepository.findByUserName(userName) == null;
    }

    /**
     * Check if the user is allowed to access the task
     *
     * @param jobEntity Job that should be checked
     * @param courier Shall the user be the courier of the job
     * @param customer Shall the user be a customer of the job
     */
    public void checkIfUserAllowed(JobEntity jobEntity, boolean courier, boolean customer) {

        Long currentUserId = findCurrentUser().getId();
        boolean isAllowed = false;
        if (courier) {
            isAllowed = jobEntity.getCourier().getId().equals(currentUserId);
        }
        if (customer) {
            isAllowed = isAllowed || jobEntity.getCustomer().getId().equals(currentUserId);
        }
        if (!isAllowed){
            throw new ForbiddenException();
        }
    }
}