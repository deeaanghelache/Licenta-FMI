import { Component, Input, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { LandmarkService } from 'src/app/services/landmark/landmark.service';
import { UserService } from 'src/app/services/user/user.service';
import { CityListService } from 'src/app/services/cityList/city-list.service';
import { ListOfLandmarksService } from 'src/app/services/listOfLandmarks/list-of-landmarks.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-plan-landmarks',
  templateUrl: './plan-landmarks.component.html',
  styleUrls: ['./plan-landmarks.component.scss']
})
export class PlanLandmarksComponent implements OnInit {
  public currentLandmarks = [];
  public currentUserLandmarksNames:string[] = [];
  public currentLandmarksNames:string[] = [];
  public currentLandmark: any;
  public displayLandmarkInfo: boolean = false;
  public currentUserId:number = 0;
  public currentEmail: string = '';
  public currentCityList: any;
  public currentCityListId: any;
  public landmarkToAdd: any;
  public landmarkToDelete: any;
  public language:any;

  @Input() chosenCity: any;

  constructor(public translate: TranslateService, private landmarkService:LandmarkService, private userService:UserService, private cityListService:CityListService, private listOfLandmarksService:ListOfLandmarksService) {
    this.translate.addLangs(['en', 'ro'])
    this.translate.setDefaultLang('en');
    this.getLanguageFromSessionStorage();
    this.translate.use(this.language);
   }

  ngOnInit(): void {
    this.getAllLandmarksNamesForGivenCity();
    this.getEmail();
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
    this.getUserByEmail(this.currentEmail);
  }

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      this.currentUserId = response.userId;
      this.getCurrentCityList(this.currentUserId);
    })
  }

  getAllLandmarksForGivenCity(){
    var cityId = this.chosenCity['cityId'];
    this.landmarkService.getAllLandmarksForGivenCity(cityId).subscribe((response:any) => {
      console.log(response);
    })
  }

  getAllLandmarksNamesForGivenCity(){
    var cityId = this.chosenCity['cityId'];
    this.landmarkService.getAllLandmarksNamesForGivenCity(cityId).subscribe((response:any) => {
      console.log(response);
      this.currentLandmarksNames = response;
    })
  }

  getLandmarkByName(name:any){
    this.landmarkService.getLandmarkByName(name).subscribe((response:any) => {
      console.log(response);
      this.currentLandmark = response;
      this.displayLandmarkInfo = true;
    })
  }

  openLandmarkInfo(name:any){
    this.getLandmarkByName(name);
    this.displayLandmarkInfo = true;
  }

  closeLandmarkInfo(){
    this.displayLandmarkInfo = false;
  }

  getCurrentCityList(userId:any){
    this.cityListService.getCityListByUserAndCity(userId, this.chosenCity['cityId']).subscribe((response:any) => {
      console.log(response);
      this.currentCityList = response;
      this.currentCityListId = this.currentCityList['cityListId'];
      console.log(this.currentCityListId);
      this.getAllLandmarksForGivenCityList(this.currentCityListId);
    })
  }

  getAllLandmarksForGivenCityList(cityListId:any){
    this.listOfLandmarksService.getAllLandmarksForGivenCityList(cityListId).subscribe((response:any) => {
      console.log(response);
      this.currentLandmarks = response;
      this.mapLandmarksToLandmarkNames();
    })
  }

  mapLandmarksToLandmarkNames(){
    var newList = [];
    for (var landmark of this.currentLandmarks){
      var name: string = landmark['name'];
      newList.push(name);
    }
    this.currentUserLandmarksNames = newList;
  }

  addListOfLandmarks(cityListId:any, landmarkId: any, priority:any){
    this.listOfLandmarksService.addListOfLandmarks(cityListId, landmarkId, priority).subscribe((response:any) => {
      console.log(response);
    })
  }

  deleteListOfLandmarks(cityListId:any, landmarkdId:any){
    this.listOfLandmarksService.deleteListOfLandmarks(cityListId, landmarkdId).subscribe((response:any) => {
      console.log(response);
    })
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      // moving elements inside the same container
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
      const containerBoxName = event.container.id;
      const landmarkName = event.container.data[event.currentIndex];
      const priority = event.currentIndex + 1;
      
      // cdk-drop-list-0 is the city landmark container
      if (containerBoxName === 'cdk-drop-list-0'){
          // should delete list of landmarks for current moved landmark
          this.landmarkService.getLandmarkByName(landmarkName).subscribe((response:any) => {
            console.log(response);
            this.landmarkToDelete = response;
            this.deleteListOfLandmarks(this.currentCityListId, this.landmarkToDelete['landmarkId']);
          })
      } else {
        // cdk-drop-list-1 is my landmark planning container
        if (containerBoxName == 'cdk-drop-list-1'){
          // const indexToDelete: number = this.currentLandmarksNames.findIndex(item => item === landmarkName);

          // if (indexToDelete !== -1) {
          //   this.currentLandmarksNames.splice(indexToDelete, 1);
          // }

          // should add list of landmarks for current moved landmark
          this.landmarkService.getLandmarkByName(landmarkName).subscribe((response:any) => {
            console.log(response);
            this.landmarkToAdd = response;
            this.addListOfLandmarks(this.currentCityListId, this.landmarkToAdd['landmarkId'], priority);
          })
        }
      }
    }
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

