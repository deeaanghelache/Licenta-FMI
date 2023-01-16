import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public loginTitle:String = "Log In";
  public loginForm!:FormGroup;

  constructor(private router:Router, private formBuilder:FormBuilder) { }

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
  }
}
