import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class CityService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getAllCities(){
    return this.http.get(this.baseUrl + 'city/findAllCities',
    this.privateHttpHeaders
    );
  }

  getAllCitiesNames(){
    return this.http.get(
      this.baseUrl + "city/findAllCitiesNames", 
      this.privateHttpHeaders
    )
  }

  getCityCoordinates(){
    return this.http.get(
      this.baseUrl + "city/getAllCityCoordinates",
      this.privateHttpHeaders
    )
  }

  getAllCitiesForAGivenTag(tagId:any){
    console.log(tagId);
    return this.http.get(this.baseUrl + 'cityTag/getAllCitiesForGivenTag/' + tagId,
    this.privateHttpHeaders
    )
  }

  addCity(city:any){
    return this.http.post(
      this.baseUrl + "city/addCity",
      city, 
      this.privateHttpHeaders
    )
  }

  getFavouriteCities(userId:any){
    return this.http.get(
      this.baseUrl + "cityList/getAllCitiesForGivenUser/" + userId,
      this.privateHttpHeaders
    )
  }

  searchCitiesByNameContainsWord(searchName:any){
    return this.http.get(
      this.baseUrl + "city/getCityByNameContainsWord/" + searchName,
      this.privateHttpHeaders
    )
  }

  getDistanceMatrix(){
    return this.http.get(
      this.baseUrl + "city/getDistanceMatrix",
      this.privateHttpHeaders
    )
  }
}
