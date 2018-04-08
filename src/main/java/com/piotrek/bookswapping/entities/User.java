package com.piotrek.bookswapping.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = "createdAt", allowGetters = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "id")
public class User implements Serializable, UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name")
    @NotBlank
    @Size(min = 1, max = 30)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 1, max = 30)
    private String lastName;

    @Column(unique = true, nullable = false, updatable = false)
    @NotBlank
    @Size(min = 2, max = 16)
    private String username;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    @Email
    @Column(unique = true, nullable = false)
    @NotBlank
    private String email;

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<BookForExchange> booksForExchange = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<WantedBook> wantedBooks = new ArrayList<>();

    public User(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
