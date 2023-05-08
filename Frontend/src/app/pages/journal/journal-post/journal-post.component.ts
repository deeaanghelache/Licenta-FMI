import { Component, Input, OnInit } from '@angular/core';
import * as pdfMake from "pdfmake/build/pdfmake";
import * as pdfFonts from 'pdfmake/build/vfs_fonts';

(<any>pdfMake).vfs = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-journal-post',
  templateUrl: './journal-post.component.html',
  styleUrls: ['./journal-post.component.scss']
})
export class JournalPostComponent implements OnInit {
  @Input() post:any;

  constructor() { }

  ngOnInit(): void {
  }

  generateJournalPostPdf() {
    var img = new Image();
    img.src = this.post['photo'];
    img.onload = () => {
      var canvas = document.createElement('canvas');
      canvas.width = img.width;
      canvas.height = img.height;
      var canvasContext = canvas.getContext('2d');
      if (canvasContext){
        canvasContext.drawImage(img, 0, 0, img.width, img.height);
        var imageUrl = canvas.toDataURL('image/png');
        var docDefinition = {
          content: [
            { text: this.post['dateWritten'], fontSize: 12, italics: true, color: "gray" },
            { text: " " },
            { text: this.post['name'], fontSize: 15, bold: true },
            { text: " " },
            {
              image: imageUrl,
              width: 150
            },
            { text: " " },
            { text: this.post['post'], fontSize: 12 },
          ]
        };
        var pdfName = "journalPost_" + this.post['dateWritten'] + ".pdf";
        pdfMake.createPdf(docDefinition).download(pdfName);
      }
    };
  }
  
}
