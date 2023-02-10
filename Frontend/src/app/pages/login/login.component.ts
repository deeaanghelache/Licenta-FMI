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
  public ok:boolean = false;
  public tryAgain: String = "";

  constructor(private router:Router, private formBuilder:FormBuilder, private userAuthentication: UserAuthenticationService) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email : ['', Validators.required, Validators.email],
      password : ['', Validators.required]
    })
  }

  goHome(){
    this.router.navigateByUrl('/homepage');
  }

  userCurrentSessionUpdate(){
    sessionStorage.setItem("loggedUserEmail", this.loginForm.value.email);
    sessionStorage.setItem("username", this.currentUsername);
    sessionStorage.setItem("admin", this.userRole);  // value: user or admin
    sessionStorage.setItem("logged", "true");
  }

  loginUserFunction(){
    console.log(this.loginForm);

    if (this.loginForm.valid){
      this.userAuthentication.login(
        this.loginForm.value
      ).subscribe((response : any) => {
        if (response != null){
          this.userAuthentication.adminLoginCheck(this.loginForm.value.email).subscribe((response:any) => {
            if (response === false){
              this.userRole = "user";
            }
            else{
              this.userRole = "admin";
            } 
            
            this.currentUsername = response.username;
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
    else {
      this.ok = false;
      this.tryAgain = "Some of the data you typed didn't pass the validation tests. Please, be careful when completing this form.";
      this.loginForm.reset();
    }
  }
}
