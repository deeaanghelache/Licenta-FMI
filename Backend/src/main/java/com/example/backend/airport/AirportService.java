package com.example.backend.airport;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AirportService {
    private final AirportInterface airportInterface;

    @Autowired
    public AirportService(AirportInterface airportInterface) {
        this.airportInterface = airportInterface;
    }

    // FIND (GET)
    public List<Airport> getAllAirports(){
        return airportInterface.findAll();
    }

    public Airport getAirportByAirportId(Integer airportId){
        return airportInterface.findAirportByAirportId(airportId).orElseThrow(() -> new AirportIdNotFoundException("Couldn't find any airport with the id: " + airportId));
    }

    public Airport getAirportByNameEng(String englishAirportName){
        return airportInterface.findAirportByNameEng(englishAirportName).orElseThrow(() -> new NameEngNotFoundException("Couldn't find any airport with the english name: " + englishAirportName));
    }

    public Airport getAirportByNameRom(String romanianAirportName){
        return airportInterface.findAirportByNameRom(romanianAirportName).orElseThrow(() -> new NameRomNotFoundException("Couldn't find any airport with the romanian name: " + romanianAirportName));
    }

    public List<Airport> findAirportByCityId(Integer cityId){
        return airportInterface.queryBy(cityId);
    }

    // POST
    public Airport addAirport(Airport airport){
        return airportInterface.save(airport);
    }

    // PUT
    // TODO: put

    // DELETE
    // TODO: delete airports by cityId

    void deleteAllAirports(){
        airportInterface.deleteAll();
    }

    void deleteAirportById(Integer airportId){
        airportInterface.deleteAirportByAirportId(airportId);
    }

    void deleteAirportByNameEng(String englishName){
        airportInterface.deleteAirportByNameEng(englishName);
    }

    void deleteAirportByNameRom(String romanianName){
        airportInterface.deleteAirportByNameRom(romanianName);
    }
}
