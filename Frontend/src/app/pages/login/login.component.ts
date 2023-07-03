import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
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
  public currentUserId:string = "";
  public language:any;

  constructor(public translate: TranslateService, private router:Router, private formBuilder:FormBuilder, private userAuthentication: UserAuthenticationService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email : ['', Validators.compose([Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
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
    sessionStorage.setItem("userId", this.currentUserId);
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
            this.currentUserId = response.userId;
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

  getLanguageFromSessionStorage(){
    if ("language" in sessionStorage){
      this.language = sessionStorage.getItem("language");
    }
  }

  switchAppsLanguage(language: string) {
    console.log(language);
    sessionStorage.setItem("language", language);
    this.translate.use(language);
  }
}
