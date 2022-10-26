# ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) http://ybob.instakg.site/
# ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) Team_YB-OB_InstaKG 인스타그램 클론 프로젝트
## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) 개발 프로그램
* IntelliJ
* SQLyog
* GitHuB
## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) 사용 기술

### 백엔드

### Spring boot

* JAVA 17
* Spring MVC
* Spring Boot Security
* Spring Boot Validation
* Spring Boot Jpa
* Spring Boot Lombok
* Spring boot thymeleaf

### Build tool

* Gradle

### DataBase

* Xampp
* MySql

### 프론트 엔드

* Thymeleaf
* Javascript
* jQuery
* Ajax
* BootStrap
* HTML5
* CSS3

## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) Description
* 스프링부트(JPA)와 Thymeleaf 를 이용한 인스타그램 클론 프로젝트

## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) 개발 환경
* CPU : Intel(R) Core(TM) i7-8700 CPU @ 3.20GHz 3.19GHz
* RAM : 16.0GB
* GRAPHIC : NVIDIA GeForce GTX 1660

## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) Prerequisite

* implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
*	implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
*	implementation 'org.springframework.boot:spring-boot-starter-security'
*	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
*	implementation 'org.springframework.boot:spring-boot-starter-web'
*	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
*	compileOnly 'org.projectlombok:lombok'
*	developmentOnly 'org.springframework.boot:spring-boot-devtools'
*	runtimeOnly 'mysql:mysql-connector-java'
*	annotationProcessor 'org.projectlombok:lombok'
*	testImplementation 'org.springframework.boot:spring-boot-starter-test'
*	testImplementation 'org.springframework.security:spring-security-test'
*	implementation 'org.springframework.boot:spring-boot-starter-security'
*	implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
*	implementation 'org.springframework.boot:spring-boot-starter-validation'
*	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
*	implementation 'org.springframework.boot:spring-boot-starter-web'
*	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
*	implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'
*	developmentOnly 'org.springframework.boot:spring-boot-devtools'
*	runtimeOnly 'mysql:mysql-connector-java'
*	annotationProcessor 'org.projectlombok:lombok'
*	testImplementation 'org.springframework.boot:spring-boot-starter-test'

## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) ERD 다이어그램

![](https://media.discordapp.net/attachments/993375184193077272/1024901054166478898/unknown.png?width=904&height=630)

## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) 메인 서비스

### 회원가입 / 로그인

##### 회원가입 화면(이메일 중복 시 화면)
![](https://media.discordapp.net/attachments/1021608475320651849/1024889526201630770/614eb2a0f86a6fd9.PNG?width=588&height=629)

##### 로그인 화면(로그인 실패 시 화면)
![](https://media.discordapp.net/attachments/1021608475320651849/1024889510158409809/10ae33da93b4d300.PNG?width=561&height=603)

##### 스토리 메인 화면
![스토리](https://user-images.githubusercontent.com/99649645/193164344-1e891999-043f-4dfd-90f8-d5715ac61694.JPG)

##### 스토리 좋아요 화면
![스토리 - 스토리 화면에서 댓글 등록](https://user-images.githubusercontent.com/99649645/193164499-655b83bc-371b-4aeb-9ce5-e40f1f602a74.JPG)

##### 스토리 게시물 수정/공유/삭제 댓글막기 기능 구현완료
![스토리 - 모달창](https://user-images.githubusercontent.com/99649645/193164534-c70b52de-2865-487d-a7d5-7cbe2c7db5b8.JPG)

##### 스토리 댓글에 댓글달기 화면
![상세페이지 - 대댓글](https://user-images.githubusercontent.com/99649645/193164682-0f4373eb-c2b9-4442-b439-19876db259fe.JPG)

##### 댓글 수정 화면
![댓글 수정화면](https://user-images.githubusercontent.com/99649645/194207445-9cd7ccc3-5ad8-425e-bde0-bf6957222f3f.JPG)

##### 
![스토리 - 댓글 막기](https://user-images.githubusercontent.com/99649645/194208166-cce71701-1827-45bf-8abb-6990a99804e0.JPG)

##### 게시물 업로드 화면
![업로드](https://user-images.githubusercontent.com/99649645/194207370-8b9a5f23-cd4a-431e-8982-55c56a06bdc7.JPG)

##### 게시물 업로드 사진 미리보기
![업로드 - 사진 미리보기](https://user-images.githubusercontent.com/99649645/194207055-c6b01f81-e739-47cb-9994-5f48f6647ea5.JPG)

##### 게시물 업로드 오류 화면
![업로드 오류 발생 화면](https://user-images.githubusercontent.com/99649645/194207103-ebd49d86-5888-4487-b43e-291d77eeb5e9.JPG)

##### 프로필 화면
![프로필](https://user-images.githubusercontent.com/99649645/193164624-226341a8-902a-48e9-be69-23047ddf3822.JPG)

##### 프로밀 화면 모달
![프로필 - 모달](https://user-images.githubusercontent.com/99649645/194207248-25e03075-cc43-472d-af2d-249df197ddb7.JPG)

##### 프로필 수정 페이지 화면
![프로필 설정](https://user-images.githubusercontent.com/99649645/193164727-13491bf9-8000-47ea-a0c7-859519b57142.JPG)

## ![](https://media.discordapp.net/attachments/986886165049262091/1024883450727112744/40x40.png) 기술 설명서
https://wiken.io/ken/10711


