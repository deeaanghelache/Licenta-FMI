import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/services/userAuthentication/user-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public loginTitle:String = "Log In";
  public loginForm!:FormGroup;
  public userNotFound:String = "";
  public userRole:string = "";
  public currentUsername:string = "";

  constructor(private router:Router, private formBuilder:FormBuilder, private userAuthentication: UserAuthenticationService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email : ['', Validators.required],
      password : ['', Validators.required]
    })
  }

  goHome(){
    this.router.navigateByUrl('/homepage');
  }

  userCurrentSessionUpdate(){
    sessionStorage.setItem("loggedUserEmail", this.loginForm.value.email);
    sessionStorage.setItem("username", this.currentUsername);
    console.log(sessionStorage.getItem("username"));
    sessionStorage.setItem("admin", this.userRole);  // value: user or admin
    sessionStorage.setItem("logged", "true");
  }

  loginUserFunction(){
    console.log(this.loginForm);

    if (this.loginForm.valid){
      this.userAuthentication.login(
        this.loginForm.value
      ).subscribe((response : any) => {
        this.currentUsername = response.username;
        if (response != null){
          this.userAuthentication.adminLoginCheck(this.loginForm.value.email).subscribe((response:any) => {
            if (response === false){
              this.userRole = "user";
            }
            else{
              this.userRole = "admin";
            } 

            this.userCurrentSessionUpdate();
            this.goHome();
          })
        }
        else {
          this.loginForm.reset();
          this.userNotFound = "Email or password incorrect";
        }
      })
    }
  }
}
