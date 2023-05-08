package com.example.backend.database.airport;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/findAllAirports")
    public ResponseEntity<List<Airport>> getAllAirports(){
        List<Airport> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @GetMapping("/findByAirportId/{id}")
    public ResponseEntity<Airport> getAirportByAirportId(@PathVariable("id") Integer airportId){
        Airport airport = airportService.getAirportByAirportId(airportId);
        return new ResponseEntity<>(airport, HttpStatus.OK);
    }

    @GetMapping("/getAllAirportsForGivenCity/{id}")
    public ResponseEntity<List<Airport>> getAirportsByCityId(@PathVariable("id") Integer cityId){
        List<Airport> airportsByCity = airportService.findAirportByCityId(cityId);
        return new ResponseEntity<>(airportsByCity, HttpStatus.OK);
    }

    @DeleteMapping("/deleteByAirportId/{id}")
    public ResponseEntity<?> deleteAirportByAirportId(@PathVariable("id") Integer airportId){
        airportService.deleteAirportById(airportId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: alte mappings
}
