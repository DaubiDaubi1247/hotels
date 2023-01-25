package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String fcs;

    @Column(length = 12)
    @NotNull
    private String phone;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
}
