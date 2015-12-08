# EndlessRecyclerViewOnScrollListener
Listener that alerts when the end of a RecyclerView is reached

[![Build Status](https://travis-ci.org/Commit451/EndlessRecyclerViewOnScrollListener.svg?branch=master)](https://travis-ci.org/Commit451/EndlessRecyclerViewOnScrollListener)

This library is derived from [this gist](https://gist.github.com/ssinss/e06f12ef66c51252563e) but we wanted to provide a way to include the dependency in Gradle.

# Gradle Dependency
Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```Gradle
dependencies {
    compile 'com.commit451:endlessrecyclerviewonscrolllistener:1.0.0'
}
```

# Usage
See sample project for usage.
```java
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
recyclerView.setLayoutManager(layoutmanager);
recyclerView.setAdapter(new Adapter());
recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                load();
            }
        });
```

License
--------

    Copyright 2015 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
