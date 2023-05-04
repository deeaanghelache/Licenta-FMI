import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CityListService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  addCityListForGivenUser(cityId:any, userId:any){
    return this.http.post(
      this.baseUrl + "cityList/addCityList/" + cityId + "/" + userId,
      this.privateHttpHeaders
    )
  }

  deleteCityList(cityId:any, userId:any){
    return this.http.delete(
      this.baseUrl + "cityList/deleteCityList/" + cityId + "/" + userId,
      this.privateHttpHeaders
    )
  }
}
