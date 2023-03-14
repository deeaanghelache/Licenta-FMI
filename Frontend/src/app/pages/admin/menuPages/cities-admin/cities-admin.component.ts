import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CityService } from 'src/app/services/city/city.service';

@Component({
  selector: 'app-cities-admin',
  templateUrl: './cities-admin.component.html',
  styleUrls: ['./cities-admin.component.scss']
})
export class CitiesAdminComponent implements OnInit {
  public openAdd:boolean = false;
  public openDelete:boolean = false;
  public buttonOpen:boolean = false;
  public addForm!:FormGroup;
  public deleteForm!:FormGroup;
  public ok:boolean = false;
  public message:string = ''; 
  public dbCities = [];

  constructor(private formBuilder:FormBuilder, private cityService:CityService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      nameEng : ['', Validators.required],
      nameRom : ['', Validators.required],
      countryEng : ['', Validators.required],
      countryRom : ['', Validators.required],
      briefHistoryEng : ['', Validators.required],
      briefHistoryRom : ['', Validators.required],
      currencyName : ['', Validators.required]
    })
    this.getAllCities();
  }

  getAllCities(){
    this.cityService.getAllCities().subscribe((response:any) => {
      console.log(response);
      this.dbCities = response;
    })
  }

  addCity(){
    console.log(this.addForm);

    if (this.addForm.valid){
      this.cityService.addCity(this.addForm.value).subscribe(
        (response:any) => {
          console.log(response);
          this.ok = true;
          this.message = "City had been added to database!";
          this.addForm.reset();
          window.location.reload();
        }
      )
    } else {
      this.ok = true;
      this.message = "Something went wrong in the form! Try Again.";
      this.addForm.reset();
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
