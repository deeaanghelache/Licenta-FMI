import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AirportService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getAllAirportsForGivenCity(cityId:any){
    return this.http.get(
      this.baseUrl + "airport/getAllAirportsForGivenCity/" + cityId,
      this.privateHttpHeaders
    )
  }

  getAllAirports(){
    return this.http.get(
      this.baseUrl + "airport/getAllAirports",
      this.privateHttpHeaders
    )
  }

  addAirport(airport:any, cityId:any){
    return this.http.post(
      this.baseUrl + "airport/addAirport/" + cityId,
      airport,
      this.privateHttpHeaders
    )
  }
}
