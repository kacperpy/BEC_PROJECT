package dk.bec.bookanything.controller;


import dk.bec.bookanything.model.DiscountCodeEntity;
import dk.bec.bookanything.repository.DiscountCodeRepository;
import dk.bec.bookanything.service.DiscountCodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@ResponseBody
@AllArgsConstructor
@RequestMapping("/discount-codes")
public class DiscountCodeController {

    @Autowired
    private final DiscountCodeService discountCodeService;

    @GetMapping
    public List<DiscountCodeEntity> getDiscountCodes(){
        return discountCodeService.getDiscountCodes();
    }

    @GetMapping("/{uuid}")
    public DiscountCodeEntity getDiscountCode(@PathVariable("uuid") String uuid){
        return discountCodeService.getDiscountCode(uuid);
    }

    @PostMapping
    public void createDiscountCode(@RequestBody DiscountCodeEntity discountCode){
        discountCodeService.createDiscountCode(discountCode);
    }

    @DeleteMapping("/{uuid}")
    public void deleteDiscountCode(@PathVariable("uuid") String uuid){
        discountCodeService.deleteDiscountCode(uuid);
    }

    @PutMapping
    public void updateDiscountCode(@RequestBody DiscountCodeEntity discountCode){
        discountCodeService.updateDiscountCode(discountCode);
    }




}
