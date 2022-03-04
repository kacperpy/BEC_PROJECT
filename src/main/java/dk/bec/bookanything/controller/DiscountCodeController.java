package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.DiscountCodeCreateDto;
import dk.bec.bookanything.dto.DiscountCodeReadDto;
import dk.bec.bookanything.mapper.DiscountCodeMapper;
import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.service.DiscountCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@ResponseBody
@RequestMapping("/api")
public class DiscountCodeController {


    private final DiscountCodeService discountCodeService;
    private final DiscountCodeMapper discountCodeMapper;

    public DiscountCodeController(DiscountCodeService discountCodeService, DiscountCodeMapper discountCodeMapper) {
        this.discountCodeService = discountCodeService;
        this.discountCodeMapper = discountCodeMapper;
    }


    @GetMapping("/discount-codes")
    public ResponseEntity<List<DiscountCodeReadDto>> getDiscountCodes() {
        List<DiscountCodeReadDto> res = discountCodeService.getDiscountCodes().stream().map(discountCodeMapper::discountCodeEntityToDto).collect(Collectors.toList());
        if (res.size() > 0) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/discount-codes/{id}")
    public ResponseEntity<DiscountCodeReadDto> getDiscountCode(@Valid @PathVariable("id") Long id) {
        Optional<DiscountCodeReadDto> res = discountCodeService.getDiscountCode(id).map(discountCodeMapper::discountCodeEntityToDto);
        return res.map(discountCodeReadDto -> new ResponseEntity<>(discountCodeReadDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/discount-codes")
    public ResponseEntity<DiscountCodeReadDto> createDiscountCode(@Valid @RequestBody DiscountCodeCreateDto discountCodeCreateDto) {
        DiscountCodeEntity discountCodeEntity = discountCodeMapper.discountCodeDtoToEntity(discountCodeCreateDto);
        Optional<DiscountCodeReadDto> res = discountCodeService.createDiscountCode(discountCodeEntity).map(discountCodeMapper::discountCodeEntityToDto);
        return res.map(discountCodeReadDto -> new ResponseEntity<>(discountCodeReadDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/discount-codes/{id}")
    public ResponseEntity<Void> deleteDiscountCode(@PathVariable("id") Long id) {
        discountCodeService.deleteDiscountCode(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/discount-codes/{id}")
    public ResponseEntity<DiscountCodeCreateDto> updateDiscountCode(@PathVariable("id") Long id, @Valid @RequestBody DiscountCodeCreateDto discountCodeCreateDto) {
      try{
          discountCodeService.updateDiscountCode(id, discountCodeMapper.discountCodeDtoToEntityWhenModified(discountCodeCreateDto, id));
          return new ResponseEntity<>(discountCodeCreateDto, HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }


}
