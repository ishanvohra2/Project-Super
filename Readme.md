# Project Super

Project super is an android application which uses Marvel's APIs to fetch details about our favorite characters
and comic books. 

![](https://github.com/ishanvohra2/Project-Super/blob/master/iron-man.png?raw=true) 

I am working on getting the application to the play store. You can download the apk from [this link](https://drive.google.com/file/d/1IZT48AcjIEmtz0gXAa8hZMSZUb-WMzKD/view?usp=sharing).

[Demo Video](https://github.com/ishanvohra2/Project-Super/blob/master/video.mp4?raw=true) 

## Development Setup
Before you begin, you should have already downloaded the Android Studio SDK and set it up correctly.
You can find a guide on how to do this here: Setting up Android Studio.

## Building the code
1. Clone the repository using: git clone https://github.com/ishanvohra2/Project-Super.git
2. Open Android Studio.
3. Click on 'Open an existing Android Studio project'
4. Browse to the directory where you cloned the project-super repo and click OK.
5. Let Android Studio import the project.
6. Build the application in your device by clicking run button.

Note: Login to the Marvel Developer portal and add public and private api keys to your strings.xml file. 

## Branch Policy
We have the following branches:

1. develop : All the contributions should be pushed to this branch. If you're making a contribution, you are supposed to make a merge request to develop.
It is advisable to keep the branch you're currently working on updated with develop.

2. master: The master branch contains all the stable and bug-free working code. The develop branch once complete will be merged with this branch.

Once the develop branch is merged with master, a tag needs to be included to bookmark the current stable version of the project.

## Understanding the code
### Architecture
The project is built in Kotlin using [Model View View Model (MVVM)](https://www.section.io/engineering-education/implementing-mvvm-architecture-in-android-using-kotlin/) UI architecture. 

### Libraries Used
Following are the libraries being used :
1. [Material Design](https://material.io/)
2. [Lifecycle Extensions](https://developer.android.com/jetpack/androidx/releases/lifecycle)
3. [GSON](https://github.com/google/gson)
4. [Glide - Image Loading Library](https://github.com/bumptech/glide)
5. [Okhttp](https://square.github.io/okhttp/)
6. [Coroutines](https://developer.android.com/kotlin/coroutines)
7. [Jetpack Navigation Component](https://developer.android.com/guide/navigation)
8. [Marvel API](https://developer.marvel.com/)
