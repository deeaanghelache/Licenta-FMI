import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class TagService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http: HttpClient) { }

  getAllTags(){
    return this.http.get(this.baseUrl + 'tag/findAllTags',
    this.privateHttpHeaders
    );
  }

  getTagIdByName(name:string){
    return this.http.get(this.baseUrl + 'tag/findTagByName/' + name,
    this.privateHttpHeaders
    );
  }

  addTag(tag:any){
    return this.http.post(
      this.baseUrl + "tag/addTag",
      tag,
      this.privateHttpHeaders
    )
  }

  addCityTag(cityTag:any){
    return this.http.post(
      this.baseUrl + "cityTag/addCityTag",
      cityTag,
      this.privateHttpHeaders
    )
  }
}
