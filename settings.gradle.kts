pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        maven (
            url = "/Users/ankitmaurya/AndroidStudioProjects/flutter_demo_module/build/host/outputs/repo"
        )

        maven (
            url = "https://storage.googleapis.com/download.flutter.io"
        )
    }
}

rootProject.name = "NativeAndroidUsingFlutter"
include(":app")
 