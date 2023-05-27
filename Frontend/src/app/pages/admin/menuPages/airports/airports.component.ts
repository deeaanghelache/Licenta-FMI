import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AirportService } from 'src/app/services/airport/airport.service';

@Component({
  selector: 'app-airports',
  templateUrl: './airports.component.html',
  styleUrls: ['./airports.component.scss']
})
export class AirportsComponent implements OnInit {
  public openAdd:boolean = false;
  public openDelete:boolean = false;
  public buttonOpen:boolean = false;
  public addForm!:FormGroup;
  public ok:boolean = false;
  public message:string = ''; 
  public dbAirports = [];
  public cityId:any;

  constructor(private formBuilder:FormBuilder, private airportService:AirportService) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      nameEng : ['', Validators.required],
      nameRom : ['', Validators.required],
      distanceToCity : ['', Validators.required],
      latitude : ['', Validators.required],
      longitude : ['', Validators.required]
    })
    this.getAllAirports();
  }

  getValue(value: string): void {
    console.log(value);
    this.cityId = value;
  }  

  getAllAirports(){
    this.airportService.getAllAirports().subscribe((response:any) => {
      console.log(response);
      this.dbAirports = response;
    })
  }

  addAirport(){
    console.log(this.addForm);
    console.log(this.cityId);

    if (this.addForm.valid){
      this.airportService.addAirport(this.addForm.value, this.cityId).subscribe((response:any) => {
        console.log(response);
        this.ok = true;
        this.message = "Airport had been added to database";
        this.addForm.reset();
      })
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
