package ru.alex.hotels.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private Integer roomNumber;

    @Column(
            columnDefinition = "boolean default false"
    )
    private Boolean hasTV;

    @Column(
            columnDefinition = "integer default 1"
    )
    private Integer countBeds;
}
