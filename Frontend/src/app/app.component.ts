// Code from: https://www.youtube.com/watch?v=0lD5P_z7EiI&t=225s

import { DOCUMENT } from '@angular/common';
import { Component, Inject, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit{
  title = 'Frontend';
  siteTheme : SiteTheme = 'light-theme';
  buttonName : String = '';

  constructor(@Inject(DOCUMENT) private document: Document,
  private renderer: Renderer2) {}
  ngOnInit(): void {
    this.setInitialTheme();
  }

  setInitialTheme(){
    this.renderer.addClass(this.document.body, this.siteTheme);
  }

  switchSiteTheme(){
    console.log(this.siteTheme);
    document.body.classList.toggle('dark-theme');
    this.document.body.classList.replace(this.siteTheme, this.siteTheme === 'light-theme' ? (this.siteTheme = 'dark-theme') : (this.siteTheme = 'light-theme'))
  }
}

export type SiteTheme = 'dark-theme' | 'light-theme';
