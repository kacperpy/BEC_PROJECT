package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.DayOpenDto;
import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.service.DayOpenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DayOpenController {
    DayOpenService dayOpenService;

    public DayOpenController(DayOpenService dayOpenService) {
        this.dayOpenService = dayOpenService;
    }

    @GetMapping("/days-open/{id}")
    public ResponseEntity<DayOpenDto> getDayOpen(@PathVariable  Long id)
    {
        Optional<DayOpenEntity> dayOpenOptional = dayOpenService.getDayOpenById(id);
        return dayOpenOptional.map(dayOpenEntity -> new ResponseEntity<>(mapDayOpenEntityToDto(dayOpenEntity), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/days-open")
    public ResponseEntity<DayOpenEntity> addDayOpen(@RequestBody DayOpenDto dayOpenDto){
        Optional<DayOpenEntity> dayOpenEntityOptional = dayOpenService.addDayOpen(mapDayOpenDtoToEntity(dayOpenDto,null));
        return dayOpenEntityOptional.map(dayOpen -> new ResponseEntity<>(dayOpen, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
    @PutMapping("/days-open/{id}")
    public ResponseEntity<DayOpenEntity> modifyDayOpen(@RequestBody DayOpenDto dayOpenDto, @PathVariable Long id){

        Optional<DayOpenEntity> dayOpenEntityOptional = dayOpenService.modifyDayOpen(mapDayOpenDtoToEntity(dayOpenDto,id));
        return dayOpenEntityOptional.map(dayOpen -> new ResponseEntity<>(dayOpen, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/days-open/{id}")
    public ResponseEntity<Void> deleteDayOpen(@PathVariable("id") Long id){
        dayOpenService.deleteDayOpen(id);
        return  ResponseEntity.ok().build();
    }

    private DayOpenEntity mapDayOpenDtoToEntity(DayOpenDto dayOpenDto, Long id)
    {
       return DayOpenEntity.builder()
               .id(id)
               .day(dayOpenDto.getDay())
               .hourFrom(dayOpenDto.getHourFrom())
               .hourTo(dayOpenDto.getHourTo())
               .build();
    }

    private DayOpenDto mapDayOpenEntityToDto(DayOpenEntity dayOpenEntity){
        return DayOpenDto.builder()
                .day(dayOpenEntity.getDay())
                .hourFrom(dayOpenEntity.getHourFrom())
                .hourTo(dayOpenEntity.getHourTo()).build();
    }
}
