package dk.bec.bookanything.mapper;

import dk.bec.bookanything.dto.ReservationCreateDto;
import dk.bec.bookanything.dto.ReservationReadDto;
import dk.bec.bookanything.model.ReservationEntity;
import dk.bec.bookanything.service.BookableObjectService;
import dk.bec.bookanything.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    private final UserService userService;
    private final BookableObjectService bookableObjectService;


    public ReservationMapper(UserService userService, BookableObjectService bookableObjectService) {
        this.userService = userService;
        this.bookableObjectService = bookableObjectService;
    }

    public ReservationEntity mapReservationCreateDtoToReservationEntity(ReservationCreateDto reservationCreateDto, Long id) {
        return ReservationEntity.builder()
                .id(id)
                .dateFrom(reservationCreateDto.getDateFrom())
                .dateTo(reservationCreateDto.getDateTo())
                .peopleNumber(reservationCreateDto.getPeopleNumber())
                .userEntity(userService.getUserById(reservationCreateDto.getUserId()).get())
                .bookableObjectEntity(bookableObjectService.getBookableObjectById(reservationCreateDto.getBookableObjectId()).get()).build();
    }

    public ReservationReadDto mapReservationEntityToReservationReadDto(ReservationEntity reservationEntity) {
        return ReservationReadDto.builder()
                .dateFrom(reservationEntity.getDateFrom())
                .dateTo(reservationEntity.getDateTo())
                .bookableObjectEntityId(reservationEntity.getBookableObjectEntity().getId())
                .userId(reservationEntity.getUserEntity().getId())
                .peopleNumber(reservationEntity.getPeopleNumber()).build();
    }
}
