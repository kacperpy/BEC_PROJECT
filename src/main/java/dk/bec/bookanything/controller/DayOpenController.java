package dk.bec.bookanything.controller;

import dk.bec.bookanything.dto.DayOpenCreateDto;
import dk.bec.bookanything.dto.DayOpenReadDto;
import dk.bec.bookanything.mapper.DayOpenMapper;
import dk.bec.bookanything.model.DayOpenEntity;
import dk.bec.bookanything.service.DayOpenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DayOpenController {
    private final DayOpenService dayOpenService;
    private final DayOpenMapper dayOpenMapper;

    public DayOpenController(DayOpenService dayOpenService, DayOpenMapper dayOpenMapper) {
        this.dayOpenService = dayOpenService;
        this.dayOpenMapper = dayOpenMapper;
    }

    @GetMapping("/days-open/{id}")
    public ResponseEntity<DayOpenReadDto> getDayOpen(@PathVariable Long id) {
        Optional<DayOpenEntity> dayOpenOptional = dayOpenService.getDayOpenById(id);
        return dayOpenOptional.map(dayOpenEntity -> new ResponseEntity<>(dayOpenMapper.mapDayOpenEntityToReadDto(dayOpenEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/days-open")
    public ResponseEntity<DayOpenReadDto> addDayOpen(@Valid @RequestBody DayOpenCreateDto dayOpenCreateDto) {
        Optional<DayOpenEntity> dayOpenEntityOptional = dayOpenService.addDayOpen(dayOpenMapper.mapDayOpenDtoToEntity(dayOpenCreateDto, null));
        return dayOpenEntityOptional.map(dayOpen -> new ResponseEntity<>(dayOpenMapper.mapDayOpenEntityToReadDto(dayOpen), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/days-open/{id}")
    public ResponseEntity<DayOpenReadDto> modifyDayOpen(@Valid @RequestBody DayOpenCreateDto dayOpenCreateDto, @PathVariable Long id) {

        Optional<DayOpenEntity> dayOpenEntityOptional = dayOpenService.modifyDayOpen(dayOpenMapper.mapDayOpenDtoToEntity(dayOpenCreateDto, id));
        return dayOpenEntityOptional.map(dayOpen -> new ResponseEntity<>(dayOpenMapper.mapDayOpenEntityToReadDto(dayOpen), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/days-open/{id}")
    public ResponseEntity<Void> deleteDayOpen(@PathVariable("id") Long id) {
        dayOpenService.deleteDayOpen(id);
        return ResponseEntity.ok().build();
    }
}
