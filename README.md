# BHServer
Not actually a server, more of an improved version of [BlockProxy](https://github.com/juanmuscaria/BlockProxy)
All you can find here is a working ENet proxy and packet analyzer with some progress on reverse engineering the network 
protocol and world formats

## Running stuff

Right now there's no proper release for the project, you must run everything from Gradle.
Gradle will take care of downloading the correct Java version.

* `./gradlew runInterceptorGui` - Runs the packet analyzer with GUI frontend
* `./gradlew runInterceptor -Pargs=''` - Runs the packet analyzer. Add CLI args inside the quotes
* `./gradlew runWorldInspector -Pargs=''` - Runs the world inspector, it dumps all world DBs. Add CLI args inside the
  quotes