package com.example.backend.price;

import com.example.backend.landmark.LandmarkIdNotFoundException;
import com.example.backend.preference.Preference;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PriceService {
    private final PriceInterface priceInterface;

    @Autowired
    public PriceService(PriceInterface priceInterface) {
        this.priceInterface = priceInterface;
    }

    // FIND(GET)
    public List<Price> getAllPrices(){
        return priceInterface.findAll();
    }

    public Price getPriceByLandmarkId(Integer landmarkId){
        return priceInterface.findPriceByLandmarkId(landmarkId).orElseThrow(() -> new LandmarkIdNotFoundException("Couldn't find any landmark with the id: " + landmarkId));
    }

    // POST
    public Price addPrice(Price price){
        return priceInterface.save(price);
    }

    // PUT
    // TODO

    // DELETE
    public void deletePriceByLandmarkId(Integer landmarkId){
        priceInterface.deletePriceByLandmarkId(landmarkId);
    }

    public void deleteAllPrices(){
        priceInterface.deleteAll();
    }
}
