package dk.bec.bookanything.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dk.bec.bookanything.model.FacilityEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOpenDto {

    private Integer day;

    private LocalDateTime hourFrom;

    private LocalDateTime hourTo;
}
