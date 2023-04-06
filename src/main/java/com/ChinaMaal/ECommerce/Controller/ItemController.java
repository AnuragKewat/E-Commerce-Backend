package com.ChinaMaal.ECommerce.Controller;

import com.ChinaMaal.ECommerce.ResDto.ItemResDto;
import com.ChinaMaal.ECommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @GetMapping("/view/{item}")
    public ResponseEntity viewItem(@PathVariable("item") int productId) {
        ItemResDto itemResDto;
        try{
            itemResDto = itemService.viewItem(productId);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(itemResDto, HttpStatus.ACCEPTED);
    }
}
