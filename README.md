# Android support libraries
[![](https://jitpack.io/v/tuankhaiit/android-support-lib.svg)](https://jitpack.io/#tuankhaiit/android-support-lib)

Some utilities, kotlin extensions useful for Android development

## Installation

#### Step 1: Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### Step 2: Add the dependency

```
dependencies {
	implementation 'com.github.tuankhaiit:android-support-lib:latest_version'
}
```

## Features

### 1. Animation

#### FadeIn
```Kotlin
anyView.animFadeIn(delayMillis = 500L, fadeDuration = 2000L)
```

### 2. Context Extensions

#### Get current Activity from context
```Kotlin
context.getNearestActivity()
```

#### Hide keyboard from context
```Kotlin
context.hideKeyboard()
```

#### Hide keyboard when touch outside EditText
```Kotlin
rootView.hideKeyboardWhenTouchOutside()
```

### 3. LiveData Extensions

#### Debounce for LiveData
```Kotlin
liveData.debounce(1000L)
```

### 4. View Extensions

#### Spreading out work across multiple frames
```Kotlin
UIJobScheduler.submitJob {
    // update view
}
```

#### EditText: Request focus and show keyboard
```Kotlin
anyEditText.requestFocusAndShowKeyboard()
```

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
