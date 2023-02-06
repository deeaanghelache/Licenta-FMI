import { Component, OnInit } from '@angular/core';
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

  loginUserFunction(){
    console.log(this.loginForm);

    if (this.loginForm.valid){
      this.userAuthentication.login(
        this.loginForm.value
      ).subscribe((response : any) => {
        if (response != null){
          this.goHome();
        }
        else {
          this.loginForm.reset();
          this.userNotFound = "Email or password incorrect";
        }
      })
    }
  }
}
