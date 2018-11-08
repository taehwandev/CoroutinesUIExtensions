# Android Coroutine UI Extensions
[![License](https://img.shields.io/hexpm/l/plug.svg)]()

This is Android Coroutine UI Extensions libaray.
available for kotlin.


## Library version

### AndroidX package

[ ![Download](https://api.bintray.com/packages/taehwandev/thdev.tech/coroutines-extensions/images/download.svg) ](https://bintray.com/taehwandev/thdev.tech/coroutines-extensions/_latestVersion)

```
ext.kotlin_version = '1.3.0'
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
ext.coroutines_version = '1.0.0'
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

ext.supportLibraryVersion = '27.1.1'
implementation "com.android.support:appcompat-v7:$supportLibraryVersion"

ext.lifecycleVersion = '1.1.1'
implementation "android.arch.lifecycle:extensions:$lifecycleVersion"

implementation 'tech.thdev.coroutines:coroutines-extensions:$last_version'
```

### Legacy package

```
ext.kotlin_version = '1.3.0'
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
ext.coroutines_version = '1.0.0'
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

ext.supportLibraryVersion = '27.1.1'
implementation "com.android.support:appcompat-v7:$supportLibraryVersion"

ext.lifecycleVersion = '1.1.1'
implementation "android.arch.lifecycle:extensions:$lifecycleVersion"

implementation 'tech.thdev.coroutines:coroutines-extensions-legacy:1.0.0'
```

## Use api - Activity/Fragment/viewModel/LifecycleObservable

### Activity

```kotlin
class MainActivity : CoroutineScopeActivity() {

    // ...
    launch {
        // UI Thread
        // Thread change
        launch(Dispatchers.Default) {

        }
    }
    // ...
}
```

### Fragment

```kotlin
class HomeFragment : CoroutineScopeFragment() {

    // ...
    launch {
        // UI Thread
    }
    // ...
}
```

### ViewModel

```kotlin
class MainViewModel : CoroutineScopeViewModel() /* or CoroutineScopeAndroidViewModel() */ {

    // ...
    launch {
        // UI Thread
    }
    // ...
}
```

### LifecycleObservable

```kotlin
class MainLifecycleObservable : CoroutineScopeLifecycleObserver() {

    // ...
    launch {
        // UI Thread
    }
    // ...
}
```

## Use api - onClick

In a Inheritance from BaseCoroutineScope.

```kotlin
class MainViewModel : CoroutineScopeViewModel() /* or CoroutineScopeAndroidViewModel() */ {

    // ...
    fab.onClick(this) {
        gitHubService.contributors(tv_owner.text.toString(), tv_repo.text.toString())
                .onErrorReturn {
                    mutableListOf()
                }
                .await()
    }.runUi {
        if (it.isNotEmpty()) {
            for ((name, contributions) in it) {
                tv_message.text = "$name as $contributions contributions!"
            }
        } else {
            tv_message.text = "Not found"
        }
    }

    // or

    onClick(fab) {
        // background thread
    }.runUi {
        ui thread
    }
    // ...
}
```


## License

```
Copyright 2018 Tae-hwan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```