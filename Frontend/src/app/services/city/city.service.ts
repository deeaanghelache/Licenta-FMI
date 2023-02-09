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
    return this.http.get(this.baseUrl + '/city/findAllCities',
    this.privateHttpHeaders
    );
  }
}
