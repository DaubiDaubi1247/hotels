package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany
    @JoinTable(
            name = "hotels_in_citys",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<CityEntity> cities;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private List<RoomEntity> rooms;

}
