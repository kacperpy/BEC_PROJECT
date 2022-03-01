package dk.bec.bookanything.controller;


import dk.bec.bookanything.dto.DiscountCodeCreateDto;
import dk.bec.bookanything.dto.DiscountCodeReadDto;
import dk.bec.bookanything.mapper.DiscountCodeMapper;
import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.service.DiscountCodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@ResponseBody
@AllArgsConstructor
@RequestMapping("/discount-codes")
public class DiscountCodeController {

    @Autowired
    private final DiscountCodeService discountCodeService;

    @GetMapping
    public ResponseEntity<List<DiscountCodeReadDto>> getDiscountCodes(){
        List<DiscountCodeReadDto> res = discountCodeService.getDiscountCodes().stream().map(DiscountCodeMapper::discountCodeEntityToDto).collect(Collectors.toList());
        if(res.size()>0) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountCodeReadDto> getDiscountCode(@PathVariable("id") Long id){
        Optional<DiscountCodeReadDto> res = discountCodeService.getDiscountCode(id).map(DiscountCodeMapper::discountCodeEntityToDto);
        return res.isPresent()? new ResponseEntity<DiscountCodeReadDto>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<DiscountCodeReadDto> createDiscountCode(@RequestBody DiscountCodeCreateDto discountCodeCreateDto){
        DiscountCodeEntity discountCodeEntity = DiscountCodeMapper.discountCodeDtoToEntity(discountCodeCreateDto);
        Optional<DiscountCodeReadDto> res = discountCodeService.createDiscountCode(discountCodeEntity).map(DiscountCodeMapper::discountCodeEntityToDto);
        return res.isPresent()? new ResponseEntity<DiscountCodeReadDto>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscountCode(@PathVariable("id") Long id){
        discountCodeService.deleteDiscountCode(id);
    }

    @PutMapping
    public ResponseEntity<DiscountCodeReadDto> updateDiscountCode( @RequestBody DiscountCodeEntity discountCodeEntity){
        Optional<DiscountCodeReadDto> res =discountCodeService.updateDiscountCode(discountCodeEntity).map(DiscountCodeMapper::discountCodeEntityToDto);
        return res.isPresent()? new ResponseEntity<DiscountCodeReadDto>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
