package ru.alex.hotels.utils.room;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.alex.hotels.dto.RoomDto;
import ru.alex.hotels.entity.Hotel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class RoomCharacteristic extends RoomDto {
    private Hotel hotel;
}
