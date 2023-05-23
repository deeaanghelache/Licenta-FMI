import { Component, OnInit} from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public admin:boolean = false;
  public logged:boolean = false;
  public currentFirstName:string = '';
  public currentLastName:string = '';
  public currentId:number = 0;
  public currentEmail:string = '';
  public currentUsername: string = '';
  public currentPhotoPath:string = '';
  public displayGallery:boolean = true;
  public message: string = '';
  public displayChangePasswordForm: boolean = false;
  public displayChangeUsernameForm: boolean = false;
  public changePasswordForm!:FormGroup;
  public changeUsernameForm!:FormGroup;
  public language:any;
  public images = [
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
  ];

  constructor(public translate: TranslateService, private router: Router, private userService: UserService, private formBuilder:FormBuilder) { 
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
  }

  getUsername(){
    this.currentUsername = sessionStorage.getItem("username") as string;
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
  }

  ngOnInit(): void {
    this.getUsername();
    this.getEmail();
    this.checkIfLoggedIn();
    this.checkIfAdmin();
    this.getUserByEmail(this.currentEmail);
    this.changePasswordForm = this.formBuilder.group(
      {
        password : ['', Validators.required],
        confirmPassword : ['', Validators.required] 
      }, { validators: [this.passwordAndConfirmPasswordChecker]}
    );
    this.changeUsernameForm = this.formBuilder.group(
      {
        newUsername : ['', Validators.required]
      }
    );
  }
  
  passwordAndConfirmPasswordChecker : ValidatorFn = (group: AbstractControl):  ValidationErrors | null => { 
    let password = group.get('password')?.value;
    let confirmPassword = group.get('confirmPassword')?.value
    return password === confirmPassword ? null : { passwordsMismatched: true }
  }

  checkIfLoggedIn(){
    if ("loggedUserEmail" in sessionStorage){
      this.logged = true;
    }
  }

  checkIfAdmin(){
    if (sessionStorage.getItem("admin") === "admin"){
      this.admin = true;
    }
  }

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      console.log(response);
      this.currentId = response.userId;
      this.currentFirstName = response.firstName;
      this.currentLastName = response.lastName;
      this.currentPhotoPath = response.photo;
      console.log(this.currentPhotoPath);
      this.currentUsername = response.username;
    })
  }

  goToJournal(){
    this.router.navigateByUrl("/journal");
  }

  openChangePasswordForm(){
    this.displayChangePasswordForm = true;
    this.displayGallery = false;
    this.displayChangeUsernameForm = false;
  }

  openChangeUsernameForm(){
    this.displayChangeUsernameForm = true;
    this.displayGallery = false;
    this.displayChangePasswordForm = false;
  }

  closeChangePasswordForm(){
    this.displayGallery = true;
    this.displayChangePasswordForm = false;
    this.displayChangeUsernameForm = false;
  }

  closeChangeUsernameForm(){
    this.displayGallery = true;
    this.displayChangeUsernameForm = false;
    this.displayChangePasswordForm = false;
    window.location.reload();
  }

  changePassword(){
    this.userService.changePassword(this.currentId, this.changePasswordForm.get('password')?.value).subscribe((response:any) => {
      this.changePasswordForm.reset;
      this.message = "Your password has been successfully changed!"
    })
  }
  
  changeUsername(){
    this.userService.changeUsername(this.currentId, this.changeUsernameForm.get('newUsername')?.value).subscribe((response:any) => {
      this.changeUsernameForm.reset;
      this.message = "Your username has been successfully changed!"
    })
  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
    this.router.navigateByUrl("/homepage");
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
