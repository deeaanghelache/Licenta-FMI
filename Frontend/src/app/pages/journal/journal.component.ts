import { Component, OnInit } from '@angular/core';
import { JournalPostService } from 'src/app/services/journalPost/journal-post.service';
import { UserService } from 'src/app/services/user/user.service';
import { DatePipe } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-journal',
  templateUrl: './journal.component.html',
  styleUrls: ['./journal.component.scss']
})
export class JournalComponent implements OnInit {
  public journalPostForm!:FormGroup;
  public logged:boolean = false;
  public admin:boolean = false;
  public currentId: number = 0;
  public currentEmail: string = "";
  public display:boolean = false;
  public currentPost!:any;
  public images = [
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
  ];
  // public posts = [];
  public posts = [
    {
      journalPostId: 1,
      name: "post name 1",
      post: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor risus in enim consectetur, in mattis lorem efficitur. Nulla sit amet consectetur massa. Sed tincidunt vitae odio vel pretium. Fusce non ipsum in risus cursus congue. Nunc id euismod nisi. Nulla ornare eros magna, id dignissim nulla interdum in. Sed fringilla convallis mauris, vitae suscipit elit faucibus vel. Nam eu tortor vel nisi iaculis tincidunt a in dui. Praesent eu tellus a eros porttitor facilisis. Praesent euismod nibh vitae risus eleifend, in maximus odio elementum. Nam malesuada dolor ac libero facilisis, vel hendrerit ipsum tristique. Maecenas sit amet metus non massa bibendum pulvinar.",
      photo: "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
      dateWritten: "10 may 2023"
    },
    {
      journalPostId: 2,
      name: "post name 2",
      post: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor risus in enim consectetur, in mattis lorem efficitur. Nulla sit amet consectetur massa. Sed tincidunt vitae odio vel pretium. Fusce non ipsum in risus cursus congue. Nunc id euismod nisi. Nulla ornare eros magna, id dignissim nulla interdum in. Sed fringilla convallis mauris, vitae suscipit elit faucibus vel. Nam eu tortor vel nisi iaculis tincidunt a in dui. Praesent eu tellus a eros porttitor facilisis. Praesent euismod nibh vitae risus eleifend, in maximus odio elementum. Nam malesuada dolor ac libero facilisis, vel hendrerit ipsum tristique. Maecenas sit amet metus non massa bibendum pulvinar.",
      photo: "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
      dateWritten: "11 may 2023"
    },
    {
      journalPostId: 3,
      name: "post name 3",
      post: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed auctor risus in enim consectetur, in mattis lorem efficitur. Nulla sit amet consectetur massa. Sed tincidunt vitae odio vel pretium. Fusce non ipsum in risus cursus congue. Nunc id euismod nisi. Nulla ornare eros magna, id dignissim nulla interdum in. Sed fringilla convallis mauris, vitae suscipit elit faucibus vel. Nam eu tortor vel nisi iaculis tincidunt a in dui. Praesent eu tellus a eros porttitor facilisis. Praesent euismod nibh vitae risus eleifend, in maximus odio elementum. Nam malesuada dolor ac libero facilisis, vel hendrerit ipsum tristique. Maecenas sit amet metus non massa bibendum pulvinar.",
      photo: "../../../assets/photos/pexels-nicolas-2925146.jpg",
      dateWritten: "12 may 2023"
    }
  ];
  public mainPhotos = [
    "../../../assets/photos/pexels-anastasiya-vragova-6791741.jpg",
    "../../../assets/photos/pexels-esrageziyor-7473041.jpg",
    "../../../assets/photos/pexels-guillaume-hankenne-2792025.jpg",
    "../../../assets/photos/pexels-nicolas-2925146.jpg",
    "../../../assets/photos/pexels-spencer-davis-4353813.jpg",
  ]
  public image1:any;
  public image2:any;
  public image3:any;

  constructor(private userService:UserService, private journalPostService:JournalPostService, private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.journalPostForm = this.formBuilder.group({
      name : ['', Validators.required],
      post : ['', Validators.required],
      photo : ['']
    })

    this.checkIfLoggedIn();
    this.checkIfAdmin();
    //this.getAllJournalPosts();
    this.randomlyDisplayImages();
  }

  checkIfLoggedIn(){
    if ("loggedUserEmail" in sessionStorage){
      this.logged = true;
      this.getEmail();
    }
  }

  getEmail(){
    this.currentEmail = sessionStorage.getItem("loggedUserEmail") as string;
    this.getUserByEmail(this.currentEmail);
  }

  getUserByEmail(email:string){
    this.userService.getUserByEmail(email).subscribe((response:any) => {
      this.currentId = response.userId;
    })
  }

  checkIfAdmin(){
    if (sessionStorage.getItem("admin") === "admin"){
      this.admin = true;
    }
  }

  getAllJournalPosts(){
    this.journalPostService.getAllJournalPostsForGivenUser(this.currentId).subscribe((response:any) => {
      console.log(response);
      this.posts = response;
    })
  }

  randomlyDisplayImages(){
    let randomImagesIndexes:any[] = [];

    while (randomImagesIndexes.length < 3) {
      let currentRandomIndex = Math.floor(Math.random() * this.mainPhotos.length);
      if (!randomImagesIndexes.includes(currentRandomIndex)) {
        randomImagesIndexes.push(currentRandomIndex);
      }
    }

    this.image1 = this.mainPhotos[randomImagesIndexes[0]];
    this.image2 = this.mainPhotos[randomImagesIndexes[1]];
    this.image3 = this.mainPhotos[randomImagesIndexes[2]];
  }

  showPost(post:any){
    this.display = true;
    this.currentPost = post;
  }

  closePost(){
    this.display = false;
  }

  addPost(){

  }

  logout(){
    sessionStorage.clear();
    this.admin = false;
    this.logged = false;
  }
}
