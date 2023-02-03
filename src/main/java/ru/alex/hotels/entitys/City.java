package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "index", length = 6)
    private String index;

    @ManyToMany(mappedBy = "cities")
    @Column(name = "hotel")
    private List<Hotel> hotels = new ArrayList<>();
}
