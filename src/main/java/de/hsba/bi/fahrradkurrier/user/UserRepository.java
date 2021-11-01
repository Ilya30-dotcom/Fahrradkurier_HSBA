package de.hsba.bi.fahrradkurrier.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String name);

    List<User> findByRole(UserRoleEnum role);
}
