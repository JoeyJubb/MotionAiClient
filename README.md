# MotionAiClient

This app allows you to interact with [motion.ai](https://www.motion.ai/) bots that you've created.

[![](https://jitpack.io/v/BubblebearAppsLtd/MotionAiClient.svg)](https://jitpack.io/#BubblebearAppsLtd/MotionAiClient)

## Using this library

- Add in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Add the dependency:
```gradle
dependencies {
    compile 'com.github.BubblebearAppsLtd.MotionAiClient:presentation:v1.0.0-beta'
}
```

- Add the following to your onCreate() method in your Application class
```java
@Override
public void onCreate() {
    super.onCreate();
    ...
    Injection.initializeInjector(this); // add this line
}
```

- Make sure your application class is mentioned in the manifest
