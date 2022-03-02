package dk.bec.bookanything.controller;


import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.service.DiscountCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@ResponseBody
@AllArgsConstructor
@RequestMapping("/discount-codes")
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    @GetMapping
    public ResponseEntity<List<DiscountCodeEntity>> getDiscountCodes(){
        List<DiscountCodeEntity> res = discountCodeService.getDiscountCodes();
        if(res.size()>0) return new ResponseEntity<>(res, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountCodeEntity> getDiscountCode(@PathVariable("id") Long id){
        Optional<DiscountCodeEntity> res = discountCodeService.getDiscountCode(id);
        return res.isPresent()? new ResponseEntity<DiscountCodeEntity>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<DiscountCodeEntity> createDiscountCode(@RequestBody DiscountCodeEntity discountCode){
        Optional<DiscountCodeEntity> res = discountCodeService.createDiscountCode(discountCode);
        return res.isPresent()? new ResponseEntity<DiscountCodeEntity>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscountCode(@PathVariable("id") Long id){
        discountCodeService.deleteDiscountCode(id);
    }

    @PutMapping
    public ResponseEntity<DiscountCodeEntity> updateDiscountCode(@RequestBody DiscountCodeEntity discountCode){
        Optional<DiscountCodeEntity> res =discountCodeService.updateDiscountCode(discountCode);
        return res.isPresent()? new ResponseEntity<DiscountCodeEntity>(res.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
