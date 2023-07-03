import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LandmarkService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getAllLandmarks(){
    return this.http.get(
      this.baseUrl + "landmark/getAllLandmarks",
      this.privateHttpHeaders
    )
  }

  getAllLandmarksForGivenCity(cityId:any){
    return this.http.get(
      this.baseUrl + "landmark/getAllLandmarksForGivenCity/" + cityId,
      this.privateHttpHeaders
    )
  }

  getAllLandmarksNamesForGivenCity(cityId:any){
    return this.http.get(
      this.baseUrl + "landmark/getAllLandmarksNamesForGivenCity/" + cityId,
      this.privateHttpHeaders
    )
  }

  getLandmarkByName(name:any){
    return this.http.get(
      this.baseUrl + "landmark/getLandmarkByName/" + name,
      this.privateHttpHeaders
    )
  }

  addLandmark(landmark:any, cityId:any){
    return this.http.post(
      this.baseUrl + "landmark/addLandmark/" + cityId,
      landmark,
      this.privateHttpHeaders
    )
  }

  addLandmarkByCityName(landmark:any, cityName:any){
    return this.http.post(
      this.baseUrl + "landmark/addLandmarkByCityName/" + cityName,
      landmark,
      this.privateHttpHeaders
    )
  }
}
