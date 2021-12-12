package de.hsba.bi.fahrradkurrier.user;

import de.hsba.bi.fahrradkurrier.Common.AddressEntity;
import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Comparable<User> {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;
    @Basic(optional = false)
    private String userName;
    @Basic(optional = false)
    private String firstName;
    @Basic(optional = false)
    private String lastName;
    @Basic(optional = false)
    private LocalDate birthday;
    @Basic(optional = false)
    private String password;
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
    @OneToOne(cascade = CascadeType.MERGE, optional = true)
    private AddressEntity address;

    @Builder
    public User(String userName, String firstName, String lastName, LocalDate birthday, String password, UserRoleEnum role, AddressEntity address) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    @Override
    public int compareTo(User other) {
        return this.userName.compareTo(other.userName);
    }

    @Override
    public String toString() {
        return userName;
    }
}