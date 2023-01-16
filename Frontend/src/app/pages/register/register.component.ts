import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  public registerTitle:String = "Sign Up";
  public registerForm!:FormGroup;

  constructor(private formBuilder:FormBuilder) { }

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

  registerUserFunction(){
    console.log(this.registerForm);
  }

}
