package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.DiscountCodeCreateDto;
import dk.bec.bookanything.dto.DiscountCodeReadDto;
import dk.bec.bookanything.mapper.DiscountCodeMapper;
import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.service.DiscountCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@ResponseBody
@RequestMapping("/api/discount-codes")
public class DiscountCodeController {


    private final DiscountCodeService discountCodeService;
    private final DiscountCodeMapper discountCodeMapper;

    public DiscountCodeController(DiscountCodeService discountCodeService, DiscountCodeMapper discountCodeMapper) {
        this.discountCodeService = discountCodeService;
        this.discountCodeMapper = discountCodeMapper;
    }


    @GetMapping
    public ResponseEntity<List<DiscountCodeReadDto>> getDiscountCodes() {
        List<DiscountCodeReadDto> res = discountCodeService.getDiscountCodes().stream().map(discountCodeMapper::discountCodeEntityToDto).collect(Collectors.toList());
        if (res.size() > 0) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountCodeReadDto> getDiscountCode(@PathVariable("id") Long id) {
        Optional<DiscountCodeReadDto> res = discountCodeService.getDiscountCode(id).map(discountCodeMapper::discountCodeEntityToDto);
        return res.isPresent() ? new ResponseEntity<DiscountCodeReadDto>(res.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<DiscountCodeReadDto> createDiscountCode(@RequestBody DiscountCodeCreateDto discountCodeCreateDto) {
        DiscountCodeEntity discountCodeEntity = discountCodeMapper.discountCodeDtoToEntity(discountCodeCreateDto);
        Optional<DiscountCodeReadDto> res = discountCodeService.createDiscountCode(discountCodeEntity).map(discountCodeMapper::discountCodeEntityToDto);
        return res.isPresent() ? new ResponseEntity<DiscountCodeReadDto>(res.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscountCode(@PathVariable("id") Long id) {
        discountCodeService.deleteDiscountCode(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountCodeReadDto> updateDiscountCode(@PathVariable("id") Long id, @RequestBody DiscountCodeCreateDto discountCodeCreateDto) {
        DiscountCodeEntity discountCodeEntity = discountCodeMapper.discountCodeDtoToEntityWhenModified(discountCodeCreateDto, id);
        Optional<DiscountCodeReadDto> res = discountCodeService.updateDiscountCode(discountCodeEntity).map(discountCodeMapper::discountCodeEntityToDto);
        return res.isPresent() ? new ResponseEntity<DiscountCodeReadDto>(res.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
