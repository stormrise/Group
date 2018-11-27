# Ninja Jump

> This is a course project for COMP7506
> 
> Game based on libgdx
> 
> @Copyright by: 
 

# Introduction 

MSc-CS program information based on https://www.msc-cs.hku.hk/


# Build

## Dependencies

This game is based on libGDX, Wiki: https://github.com/libgdx/libgdx

>libGDX is a cross-platform Java game development framework based on OpenGL (ES) that works on Windows, Linux, Mac OS X, Android, your WebGL enabled browser and iOS.

Get libGDX from the [official download site](http://libgdx.badlogicgames.com/download.html), run `gdx-setup.jar` to generate a new project. It will initial all necessary files. For this project we can skip this step.

Import project to Android Studio, everything is set and ready to go.




## Build with Gradle

Use latest Android Studio 3.2.1 and gradle 4.6 (Time: Nov 2018)

## Install it 

Choose the Piexl 2 XL as the AVD, because the larger screen is the trend, and provide better view for this app.

Also, we can choose to run in desktop use java runtime environment.

## Structures 
### Core

All game code in here.

`NinjaJump.java` is the main class, defines some constants, also contains backgroud, music, and include all others.

`sprites` folder contains `Ninja.java` and `Obstacle.java` which defines two sprites.

`states` folder: `PlayState.java` contains game logic and play screen, `MenuState.java` is a menu screen, `GameOverState.java` shows the score you got after ninja dead. `GameStateManager.java` controls different state in and out,`State.java` is a abstact class define the methods used in the previous class.

>All assets are locate in Android assets floder, which will mentioned later.

### Android 

`AndroidLauncher.java` is the one start android application, we can define some android unique configuration here, `AndroidManifest.xml` is the same as other android project.

!! important !!
`assets` folder is contains all assets like sprites/music/pictures 

### Desktop 

`DesktopLauncher.java`is the one start desktop  application, we can define some desktop unique configuration here.







# Contributors 

* Li Lingxiao
* Wang 
* Xiong 


# TODO

1. 


