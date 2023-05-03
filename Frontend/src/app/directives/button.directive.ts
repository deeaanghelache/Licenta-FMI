// Keep a button pressed 
// Code from: https://stackoverflow.com/questions/56176811/how-do-i-make-a-button-stay-pressed-after-clicking

import { Directive, ElementRef, HostListener, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appButton]'
})
export class ButtonDirective {

  constructor(private elRef: ElementRef, private renderer: Renderer2) {}

  ngOnInit() {
  }

  @HostListener('mousedown') onmousedown(eventData: Event) {
    if (this.elRef.nativeElement.style.backgroundColor === 'white') {
      this.renderer.setStyle(this.elRef.nativeElement, 'background-color', '#ffc533');
    } else {
      this.renderer.setStyle(this.elRef.nativeElement, 'background-color', 'white');
    }
  }
}
