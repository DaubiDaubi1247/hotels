package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    private DirectorEntity director;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotel_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    @Column(name = "city")
    private List<City> city = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private List<RoomEntity> rooms;

}
