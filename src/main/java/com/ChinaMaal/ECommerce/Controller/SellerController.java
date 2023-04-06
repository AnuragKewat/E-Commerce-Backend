package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.Exception.SellerNotFound;
import com.ChinaMaal.ECommerce.Model.Seller;
import com.ChinaMaal.ECommerce.ReqDto.SellerReqDto;
import com.ChinaMaal.ECommerce.ResDto.SellerResDto;
import com.ChinaMaal.ECommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity<String> addSeller(@RequestBody SellerReqDto sellerReqDto) {
        sellerService.addSeller(sellerReqDto);
        return new ResponseEntity<>("Seller added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/get-all-seller")
    public ResponseEntity<List<SellerResDto>> getAllSeller() {
        List<SellerResDto> sellerResDtos = sellerService.getAllSeller();
        return new ResponseEntity<>(sellerResDtos, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-seller-by-panNo/{pan}")
    public ResponseEntity getSellerByPanNo(@PathVariable("pan") String panNo) {
        SellerResDto sellerResDto;
        try{
            sellerResDto = sellerService.getSellerByPanNo(panNo);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(sellerResDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getByName(@PathVariable("name") String name) {
        List<Seller> seller;
        try{
            seller = sellerService.getByName(name);
        }catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(seller, HttpStatus.ACCEPTED);
    }
}
