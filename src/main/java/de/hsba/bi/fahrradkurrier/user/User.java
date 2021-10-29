package de.hsba.bi.fahrradkurrier.user;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements Comparable<User> {

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return null;
    }

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    private String userName;

    @Basic(optional = false)
    private String password;

    private UserRoles role;

    public User(String userName) {
        this.userName = userName;
    }

    public User(String userName, String password, UserRoles role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
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