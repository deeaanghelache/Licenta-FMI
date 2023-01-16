import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from 'src/app/services/userAuthentication/user-authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public registerTitle:String = "Sign Up";
  public registerForm!:FormGroup;

  constructor(private formBuilder:FormBuilder, private router: Router, private userAuthentication:UserAuthenticationService) { }

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
        email : ['', Validators.required],
        username : ['', Validators.required],
        password : ['', Validators.required],
        photo : [''],
        confirmPassword : ['', Validators.required]
      }, { validators: this.passwordAndConfirmPasswordChecker }
    );
  }

  goHome(){
    this.router.navigateByUrl('/homepage');
  }

  registerUserFunction(){
    console.log(this.registerForm);

    if (this.registerForm.valid){
      this.userAuthentication.register(
        this.registerForm.value
      ).subscribe((response : any) => {
          console.log(response);
          // un navigate la o pagina din site!
      })
    }
  }

}
