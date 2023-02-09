# Licenta - FMI

- __Backend__: Java & Spring Boot
- __Frontend__: Angular
- __Database__: MySql

# __Frontend React & Angular Course__

## __[Link Demo](https://drive.google.com/drive/folders/1qGExb-Q57jocCD6LYgDEbR9vsNXU9kfm?usp=share_link)__

Barem de notare proiect:
 - [x] Sa aiba mai multe rute 2p 
 - [x] Sa se foloseasca componente reutilizabile 2p 
 - [x] Sa se comunice intre componente 2p 
 - [x] Rute publice si private 1p 
 - [x] Sa fie cel putin o pagina cu un form ( login/register) 2p 
 - [x] Firebase sau orice alt mediu de backend 1p 

## Mai multe rute

![](/FrontendCourseImages/RutePubliceSiPrivate.png)

## Componente reutilizabile

- In Pagina adminului vreau sa afisez 2 tabele (unul care contine toti utilizatorii care au rolul de __user__ si unul pentru toti utilizatorii care au rolul de __admin__)
- Pentru asta, folosesc o componenta reutilizabila _User_
- In _admin.component.html_, apelez componenta reutilizabila cu:

```html
    <app-user [userData]="usersThatAreAdmins"></app-user>
    <app-user [userData]="usersThatAreNotAdmins"></app-user>
```
unde _userData_ este lista din componenta reutilizabila pe care o folosesc si _usersThatAreAdmins_ si _usersThatAreNotAdmins_ sunt cele 2 liste pe care vreau sa le afisez folosind componenta reutilizabila

### __admin.component.html__
![](/FrontendCourseImages/adminHtml.png)

### __user.component.html__
![](/FrontendCourseImages/UserReusHtml.png)

### __user.component.ts__
![](/FrontendCourseImages/UserReuseTs.png)

## Comunicare intre componente

- Lista _userData_ este primita ca input din componenta adminului (folosind decoratorul _@Input()_)

- Decoratorul _@Output()_ este folosit pentru stergerea unui user, atunci cand adminul apasa pe butonul __DELETE__ din pagina Admin

### __user.component.ts__
![](/FrontendCourseImages/UserReuseTs.png)

### __shared-info.module.ts__
![](/FrontendCourseImages/SharedModule.png)

## Rute publice si private

- Pagina adminului este protejata de un guard (daca user-ul logat nu este admin, el nu poate accesa pagina)
- Celelalte rute sunt publice

![](/FrontendCourseImages/RutePubliceSiPrivate.png)

## Pagini cu form

### Register Page

![](/FrontendCourseImages/RegisterForm.png)

### Login Page

![](/FrontendCourseImages/LogInForm.png)

#### login.component.html

![](/FrontendCourseImages/LoginHtml.png)

#### login.component.ts

![](/FrontendCourseImages/LoginTs.png)

## Backend 

### Java & Spring Boot with a MySql Database

#### Java Backend

![](/FrontendCourseImages/JavaBackend.png)

#### MySql Database

![](/FrontendCourseImages/MySqlDatabase.png)

