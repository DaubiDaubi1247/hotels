package ru.alex.hotels.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "director")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "fcs")
    private String fcs;

    @NotNull
    @Length(max = 11)
    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "director", cascade = CascadeType.PERSIST)
    private List<Hotel> hotel;
}
