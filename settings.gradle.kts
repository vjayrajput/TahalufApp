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
    }
}

rootProject.name = "Tahaluf"
include(":app")

include(":business:university:data:di")
include(":business:university:data:entity")
include(":business:university:data:main")
include(":business:university:di")
include(":business:university:domain:di")
include(":business:university:domain:main")
include(":business:university:domain:model")

include(":common:general:extensions")
include(":common:navigation")
include(":common:network")

include(":features:list")
include(":features:detail")
