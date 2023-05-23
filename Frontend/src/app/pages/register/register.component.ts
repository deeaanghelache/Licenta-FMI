import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UserAuthenticationService } from 'src/app/services/userAuthentication/user-authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public registerTitle:String = "Sign Up";
  public registerForm!:FormGroup;
  public userAlreadyExists:String = "";
  public ok:boolean = false;
  public tryAgain: String = "";
  public language:any;
  public uploadedPhoto: File | undefined;

  constructor(public translate: TranslateService, private formBuilder:FormBuilder, private router: Router, private userAuthentication:UserAuthenticationService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
   }

  passwordAndConfirmPasswordChecker : ValidatorFn = (group: AbstractControl):  ValidationErrors | null => { 
    let password = group.get('password')?.value;
    let confirmPassword = group.get('confirmPassword')?.value
    return password === confirmPassword ? null : { passwordsMismatched: true }
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group(
    {      
        firstName : ['', Validators.required],
        lastName : ['', Validators.required],
        email : ['', Validators.compose([Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])],
        username : ['', Validators.required],
        password: ['', Validators.required],
        // password : ['', [Validators.required, Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/)]],
        photo : [null, Validators.required],
        confirmPassword : ['', Validators.required]
      }, { validators: [this.passwordAndConfirmPasswordChecker] }
    );
  }

  goHome(){
    this.router.navigateByUrl('/homepage');
  }

  onFileSelected(event: any) {
    this.uploadedPhoto = event.target.files[0];
  }

  registerUserFunction(){
    const formData = new FormData();
    if (this.uploadedPhoto) {
      formData.append('photo', this.uploadedPhoto, this.uploadedPhoto.name);
    }
    formData.append('user', JSON.stringify({
      firstName: this.registerForm.get('firstName')?.value,
      lastName: this.registerForm.get('lastName')?.value,
      username: this.registerForm.get('username')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value
    }));

    if (this.registerForm.valid){
      this.ok = true;
      this.userAuthentication.register(
        formData
      ).subscribe((response : any) => {
          if (response != null){
             this.router.navigateByUrl('/login');
          }
          else{
             // User already exists
             this.registerForm.reset();
             this.userAlreadyExists = "Username or Email already exists";
          }
      })
    }
    else {
      this.ok = false;
      this.tryAgain = "Some of the data you typed didn't pass the validation tests. Please, be careful when completing this form.";
      this.registerForm.reset();
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
