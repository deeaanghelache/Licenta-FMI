import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CityTagService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getAllCityTags(){
    return this.http.get(
      this.baseUrl + "cityTag/findAllCityTags",
      this.privateHttpHeaders
    );
  }

  getTagsForCurrentCity(cityId:any){
    return this.http.get(
      this.baseUrl + "cityTag/getAllTagsForGivenCity/" + cityId,
      this.privateHttpHeaders
    )
  }
}
