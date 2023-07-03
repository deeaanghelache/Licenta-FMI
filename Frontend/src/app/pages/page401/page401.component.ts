import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-page401',
  templateUrl: './page401.component.html',
  styleUrls: ['./page401.component.scss']
})
export class Page401Component implements OnInit {
  public language:any;
  
  constructor(public translate: TranslateService, private router:Router) { 
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
  }

  ngOnInit(): void {
  }

  goHome(){
    this.router.navigateByUrl('/homepage');
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
