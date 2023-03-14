import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  public message:string = ''; 
  public addForm!:FormGroup;
  public addCityTagForm!:FormGroup;

  constructor(private formBuilder:FormBuilder, private tagService:TagService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      tagNameEng : ['', Validators.required],
      tagNameRom : ['', Validators.required]
    });

    this.addCityTagForm = this.formBuilder.group({
      cityId : ['', Validators.required],
      tagId : ['', Validators.required]
    });
  }

  addTag(){
    console.log(this.addForm);
    
    if (this.addForm.valid){
      this.tagService.addTag(this.addForm.value).subscribe(
        (response:any) => {
          console.log(response);
          this.ok = true;
          this.message = "Tag had been added to database!";
          this.addForm.reset();
        }
      )
    } else {
      this.ok = true;
      this.message = "Something went wrong in the form! Try Again.";
      this.addForm.reset();
    }
  }

  addCityTag(){
    console.log(this.addCityTagForm);

    if (this.addCityTagForm.valid){
      this.tagService.addCityTag(this.addCityTagForm.value).subscribe(
        (response:any) => {
          console.log(response);
          this.ok = true;
          this.message = "Tag had been added to database!";
          this.addCityTagForm.reset();
        }
      )
    } else {
      this.ok = true;
      this.message = "Something went wrong in the form! Try Again.";
      this.addCityTagForm.reset();
    }
  }

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
