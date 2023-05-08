package com.example.backend.database.price;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/findAllPrices")
    public ResponseEntity<List<Price>> getAllPrices(){
        List<Price> prices = priceService.getAllPrices();
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }

    @GetMapping("/findPriceByLandmarkId/{id}")
    public ResponseEntity<Price> getPriceByLandmarkId(@PathVariable("id") Integer landmarkId){
        Price price = priceService.getPriceByLandmarkId(landmarkId);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    // TODO: put si post

    @DeleteMapping("/deleteAllPrices")
    public ResponseEntity<?> deleteAllPrices(){
        priceService.deleteAllPrices();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletePriceByLandmarkId/{id}")
    public ResponseEntity<?> deletePriceByLandmarkId(@PathVariable("id") Integer landmarkId){
        priceService.deletePriceByLandmarkId(landmarkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
