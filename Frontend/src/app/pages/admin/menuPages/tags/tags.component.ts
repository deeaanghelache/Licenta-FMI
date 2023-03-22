import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CityTagService } from 'src/app/services/cityTag/city-tag.service';
import { TagService } from 'src/app/services/tag/tag.service';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.scss']
})
export class TagsComponent implements OnInit {
  public openAdd:boolean = false;
  public openDelete:boolean = false;
  public buttonOpen:boolean = false;
  public ok:boolean = false;
  public messageTag:string = ''; 
  public messageCityTag:string = '';
  public addForm!:FormGroup;
  // public addCityTagForm!:FormGroup;
  public dbTags = [];
  public dbCityTags = [];

  constructor(private router:Router, private formBuilder:FormBuilder, private tagService:TagService, private cityTagService:CityTagService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      tagNameEng : ['', Validators.required],
      tagNameRom : ['', Validators.required]
    });

    // this.addCityTagForm = this.formBuilder.group({
    //   cityId : ['', Validators.required],
    //   tagId : ['', Validators.required]
    // });

    this.getAllTags();
    this.getAllCityTags();
  }

  getAllTags(){
    this.tagService.getAllTags().subscribe((response:any) => {
      console.log(response);
      this.dbTags = response;
    })
  }

  getAllCityTags(){
    this.cityTagService.getAllCityTags().subscribe((response:any) => {
      console.log(response);
      this.dbCityTags = response;
    })
  }

  addTag(){
    console.log(this.addForm);
    
    if (this.addForm.valid){
      this.tagService.addTag(this.addForm.value).subscribe(
        (response:any) => {
          console.log(response);
          this.ok = true;
          this.messageTag = "Tag had been added to database!";
          this.addForm.reset();

          // TODO: jquery
          // $("#refresh").load(window.location.href + " refresh");
        }
      )
    } else {
      this.ok = true;
      this.messageTag = "Something went wrong in the form! Try Again.";
      this.addForm.reset();
    }
  }

  // addCityTag(){
  //   console.log(this.addCityTagForm);

  //   if (this.addCityTagForm.valid){
  //     this.tagService.addCityTag(this.addCityTagForm.value).subscribe(
  //       (response:any) => {
  //         console.log(response);
  //         this.ok = true;
  //         this.messageCityTag = "Tag had been added to database!";
  //         this.addCityTagForm.reset();
  //       }
  //     )
  //   } else {
  //     this.ok = true;
  //     this.messageCityTag = "Something went wrong in the form! Try Again.";
  //     this.addCityTagForm.reset();
  //   }
  // }

  openAddDataEntry(){
    this.openAdd = true;
    this.buttonOpen = true;
  }

  closeAddForm(){
    this.openAdd = false;
    this.buttonOpen = false;
  }

  openDeleteDataEntry(){
    this.openDelete = true;
    this.buttonOpen = true;
  }

  closeDeleteForm(){
    this.openDelete = false;
    this.buttonOpen = false;
  }
}
