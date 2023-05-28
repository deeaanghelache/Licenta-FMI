import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CityRatingService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  addRating(cityId:any, rating:any){
    return this.http.post(
      this.baseUrl + "cityRating/addRating/" + cityId,
      rating, 
      this.privateHttpHeaders
    )
  }

  getTopDestinations(){
    return this.http.get(
      this.baseUrl + "cityRating/getTopDestinations",
      this.privateHttpHeaders
    )
  }
}
