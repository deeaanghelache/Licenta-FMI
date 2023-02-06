import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class UserAuthenticationService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'text',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;


  constructor(private http: HttpClient) { }

  register(user: any){
    return this.http.post(
      this.baseUrl + 'user/addUser',
      user,
      this.privateHttpHeaders
    )
  }

  login(user: any){
    return this.http.post(
      this.baseUrl + 'user/login',
      user, 
      this.privateHttpHeaders
    )
  }

  adminLoginCheck(email: any){
    return this.http.get(
      this.baseUrl + 'user/checkAdminRole/' + email,
      this.privateHttpHeaders
    )
  }
}
