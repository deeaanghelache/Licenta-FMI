import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ListOfLandmarksService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getAllLandmarksForGivenCityList(cityListId:any){
    return this.http.get(
      this.baseUrl + "listOfLandmarks/getAllLandmarksForGivenCityList/" + cityListId,
      this.privateHttpHeaders
    )
  }

  addListOfLandmarks(cityListId:any, landmarkId: any, priority:any){
    return this.http.post(
      this.baseUrl + "listOfLandmarks/addListOfLandmarks/" + cityListId + "/" + landmarkId + "/" + priority,
      this.privateHttpHeaders
    )
  }
}
