pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven ( url = "https://oss.sonatype.org/content/repositories/snapshots/")
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven ( url = "https://oss.sonatype.org/content/repositories/snapshots/")
  }
}

rootProject.name = "MyFlix"
include(":app")
include(":core")
include(":feature-auth:api")
include(":feature-auth:impl")
include(":feature-home:api")
include(":feature-home:impl")
include(":feature-favorite:api")
include(":feature-favorite:impl")
include(":feature-profile:api")
include(":feature-profile:impl")
include(":design-system")
