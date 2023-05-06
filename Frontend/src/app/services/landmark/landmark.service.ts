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

  getAllLandmarksForGivenCity(cityId:any){
    return this.http.get(
      this.baseUrl + "landmark/getAllLandmarksForGivenCity/" + cityId,
      this.privateHttpHeaders
    )
  }
}
