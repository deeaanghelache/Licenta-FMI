import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserAuthenticationService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      'content-type': 'application/json',
      'responseType': 'json'
    }),
  };
  private baseUrl: string = environment.backendUrl;


  constructor(private http: HttpClient) { }

  register(user: any){
    return this.http.post(
      this.baseUrl + '/user/registerUser',
      user,
      this.privateHttpHeaders
    )
  }
}
