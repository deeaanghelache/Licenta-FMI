import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private privateHttpHeaders = {
    headers: new HttpHeaders({
      responseType: 'json',
      observe: 'response'
    }),
  };
  private baseUrl: string = environment.backendUrl;

  constructor(private http:HttpClient) { }

  getAllUsers(){
    return this.http.get(
      this.baseUrl + "user/findAllUsers",
      this.privateHttpHeaders
    )
  }

  getUserByEmail(email: string){
    return this.http.get(
      this.baseUrl + "user/findUserByEmail/" + email,
      this.privateHttpHeaders
    )
  }

  deleteUserByEmail(email: string){
    return this.http.delete(
      this.baseUrl + "user/deleteByEmail/" + email,
      this.privateHttpHeaders
    )
  }

  getAllUsersForAGivenRole(roleId: number){
    return this.http.get(
      this.baseUrl + "userRole/getAllUsersForGivenRole/" + roleId,
      this.privateHttpHeaders
    )
  }

  changePassword(userId:any, newPassword:any){
    return this.http.put(
      this.baseUrl + "user/changePassword/" + userId + "/" + newPassword,
      this.privateHttpHeaders
    )
  }
}
