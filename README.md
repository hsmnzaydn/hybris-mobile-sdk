# Hybris Mobile SDK

Hybris mobile sdk contains all out of box services of Hybris base project. Only you have to  follow a few step for installation.

**Steps**

 1. Import "hybris_mobile_sdk.jar" file to your project (You can follow this tutorial https://www.youtube.com/watch?v=CiuqMu5UHMQ )
 2. Install Dependencies for SDK to your project
	 ` 
implementation "com.google.dagger:dagger:2.16"  
annotationProcessor "com.google.dagger:dagger-compiler:2.16"  
compileOnly 'javax.annotation:jsr250-api:1.0'  
implementation 'javax.inject:javax.inject:1'`
 3. Create a class extend from 'Application' ( example: MvpApp.class )


## UML diagrams

