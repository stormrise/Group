Ninja Jump
============

> This is a course project for COMP7506
> 
> Game based on libgdx
> 
> @Copyright by:
> 
> Li, Lingxiao   
> Wang,Xichen     
> Xiong, Shanyu
 
 

Introduction 
============

The Ninja Jump App is an Android-based application for phones, which is an adventure game.


It is a fast-paced climbing game that can be used on Android, desktop and other devices. The player's goal is to climb over obstacles and keep climbing. 

You only need to use one finger to click anywhere on the screen. The ninja will jump from the left to the right or jump from the right to the left. There are various obstacles encountered on the way up. Avoid obstacles and live as long as you can!

Features: cartoon style, small, easy to operate and game difficulty increases with time.


Build
=======
Dependencies
--------
This game is based on libGDX, Wiki: https://github.com/libgdx/libgdx

>libGDX is a cross-platform Java game development framework based on OpenGL (ES) that works on Windows, Linux, Mac OS X, Android, your WebGL enabled browser and iOS.

Get libGDX from the [official download site](http://libgdx.badlogicgames.com/download.html), run `gdx-setup.jar` to generate a new project. It will initial all necessary files. For this project we can skip this step.

Import project to Android Studio, everything is set and ready to go.




Build with Gradle
-------

Use latest Android Studio 3.2.1 and gradle 4.6 (Time: Nov 2018)

Install it 
-------

After make project successfully in Android Studio, you can run it directly by the 'Run' butthon. 

Remember to choose the Piexl 2 XL as the AVD, because the larger screen is the trend, and provide better view for this app.

Also, we can choose to run in desktop use java runtime environment.

Structures 
-------
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

Game Overview 
=======
After the game is launched (our game can be used on various platforms such as Android, desktop, etc.), players can directly enter the waiting interface of the game. Just click the button in the middle to start the game.


  ![图片 1.png](https://i.loli.net/2018/11/28/5bfe4e37a0edb.png)
  
  
The operation of the game is very simple. There are left and right sides of the screen. You only need to use one finger. Click anywhere on the screen. The ninja will jump from the left to the right or jump from the right to the left.


 ![图片 2.png](https://i.loli.net/2018/11/28/5bfe4e373c251.png)  

We set up different kind of ninja images that are randomly generated when the game is loaded. We also provided a wonderful background music to let players can focus on the game and get relax.

![图片 3.png](https://i.loli.net/2018/11/28/5bfe4e373d886.png)
  
  
On the top of the screen, the numbers represent the obstacles that the ninja has turned over.


![图片 4.png](https://i.loli.net/2018/11/28/5bfe4e373d453.png)
  
  
When the ninja touches the obstacle, the game will end, and you will enter the end screen. The settle scores show the obstacles that the ninja has turned over.Players can also click the screen anywhere to start a new game again.



Contributors 
======

* Li Lingxiao
* Wang Xichen
* Xiong Shanyu

