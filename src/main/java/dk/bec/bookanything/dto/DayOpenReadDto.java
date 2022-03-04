package dk.bec.bookanything.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayOpenReadDto {
    private Long id;

    private String day;

    private LocalDateTime hourFrom;

    private LocalDateTime hourTo;
}
