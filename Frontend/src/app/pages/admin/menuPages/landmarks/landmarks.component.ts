import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LandmarkService } from 'src/app/services/landmark/landmark.service';

@Component({
  selector: 'app-landmarks',
  templateUrl: './landmarks.component.html',
  styleUrls: ['./landmarks.component.scss']
})
export class LandmarksComponent implements OnInit {
  public openAdd:boolean = false;
  public openDelete:boolean = false;
  public buttonOpen:boolean = false;
  public addForm!:FormGroup;
  public ok:boolean = false;
  public message:string = ''; 
  public dbLandmarks = [];
  public cityId:any;

  constructor(private formBuilder:FormBuilder, private landmarkService:LandmarkService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      name : ['', Validators.required],
      descriptionRom : ['', Validators.required],
      descriptionEng : ['', Validators.required],
      typeRom : ['', Validators.required],
      typeEng : ['', Validators.required],
      latitude : ['', Validators.required],
      longitude : ['', Validators.required],
      photo : ['', Validators.required]
    })
    this.getAllLandmarks();
  }

  getAllLandmarks(){
    this.landmarkService.getAllLandmarks().subscribe((response:any) => {
      this.dbLandmarks = response;
    })
  }

  addLandmark(){
    console.log(this.addForm);
    console.log(this.cityId);

    if (this.addForm.valid){
      this.landmarkService.addLandmark(this.addForm.value, this.cityId).subscribe((response:any) => {
        console.log(response);
        this.ok = true;
        this.message = "Landmark had been added to database";
        this.addForm.reset();
      })
    } else {
      this.ok = true;
      this.message = "Something went wrong in the form! Try Again.";
      this.addForm.reset();
    }
  }

  getValue(value: string): void {
    console.log(value);
    this.cityId = value;
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
