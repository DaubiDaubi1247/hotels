package ru.alex.hotels.entitys;

import jakarta.annotation.Nullable;
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
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String fcs;

    @Column(length = 11)
    @NotNull
    private String phone;

    @NotNull
    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;
}
