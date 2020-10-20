# Android support libraries
[![](https://jitpack.io/v/tuankhaiit/android-support-lib.svg)](https://jitpack.io/#tuankhaiit/android-support-lib)

Some utilities, kotlin extensions useful for Android development

## Installation

### Step 1: Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### Step 2: Add the dependency

```
dependencies {
	implementation 'com.github.tuankhaiit:android-support-lib:latest_version'
}
```

## Features

### Animation

#### FadeIn
```Kotlin
anyView.animFadeIn(delayMillis = 500L, fadeDuration = 2000L)
```

### Extensions

#### Debounce with Coroutines
```Kotlin
coroutinesDebounce(delayMillis = delayMillis, scope = scope) {
	// any action here
}
```

#### OnClickListener with debounce
```Kotlin
anyView.setDebounceOnClickListener(delayMillis = 1000L) {
	// any action here
}
```

Updating...
