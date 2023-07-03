import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CityService } from 'src/app/services/city/city.service';
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
  public allCities = [];

  constructor(private http: HttpClient, private cityService:CityService, private formBuilder:FormBuilder, private landmarkService:LandmarkService) { }

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

  getAllCityCoordinates(){
    this.cityService.getCityCoordinates().subscribe((response:any) => {
      this.allCities = response;
    })
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

  fetchLandmarksData(){
    this.getAllCityCoordinates();
    const overpassApiUrl = 'https://overpass-api.de/api/interpreter';

    for (let city of this.allCities){
      var latitude = city['second']['first'];
      var longitude = city['second']['second'];
      var numberOfLandmarks = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];
      const overpassQuery = `[out:json];
      (
        node(around:5000, ${latitude}, ${longitude})["tourism"="museum"];
        way(around:5000, ${latitude}, ${longitude})["tourism"="museum"];
        relation(around:5000, ${latitude}, ${longitude})["tourism"="museum"];
      );
      out center;`;

      fetch(`${overpassApiUrl}?data=${encodeURIComponent(overpassQuery)}`)
        .then(response => response.json())
        .then(data => {
          // console.log(data.elements[0].tags.name);
          for (let index in numberOfLandmarks){
            let currentLandmark = data.elements[index];
            let name = currentLandmark.tags.name + " " + currentLandmark.tags.tourism;
            let latitude = currentLandmark.lat;
            let longitude = currentLandmark.lon;
            
            let landmarkData = {
              name: name,
              latitude: latitude,
              longitude: longitude,
            };
        
            this.landmarkService.addLandmark(landmarkData, city['first']).subscribe((response:any) => {
              console.log(response);
            })
          }
        })
        .catch(error => {
          // Handle any errors
          console.error('Error:', error);
        });
    }
  }
}
