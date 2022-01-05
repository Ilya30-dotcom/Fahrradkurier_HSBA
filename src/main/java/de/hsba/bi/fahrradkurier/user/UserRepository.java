package de.hsba.bi.fahrradkurier.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String name);

    List<User> findByRole(UserRoleEnum role);
}
