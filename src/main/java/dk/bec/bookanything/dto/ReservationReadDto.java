package dk.bec.bookanything.dto;

import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.model.UserEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationReadDto {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Long bookableObjectEntityId;
    private UserEntity userEntity;
    private int peopleNumber;

    public ReservationReadDto(ReservationEntity reservationEntity) {
        this.id = reservationEntity.getId();
        this.dateFrom = reservationEntity.getDateFrom();
        this.dateTo = reservationEntity.getDateTo();
        this.bookableObjectEntityId = reservationEntity.getBookableObjectEntity().getId();
        this.userEntity = reservationEntity.getUserEntity();
        this.peopleNumber = reservationEntity.getPeopleNumber();
    }
}
