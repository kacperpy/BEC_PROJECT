package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOpenReadDto {
    private Integer day;

    private LocalDateTime hourFrom;

    private LocalDateTime hourTo;
}
