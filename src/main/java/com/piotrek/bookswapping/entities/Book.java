package com.piotrek.bookswapping.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "id")
public class Book implements Serializable {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotBlank
    @NonNull
    @Size(min = 1, max = 200)
    private String title;

    //@NotBlank
    private String description;

    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private User user;


}
