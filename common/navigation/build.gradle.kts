plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.app.tahaluf.common.navigation"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int

    defaultConfig {
        minSdk = rootProject.ext.get("minSdkVersion") as Int
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = rootProject.ext.get("jvmTargetVersion") as String
    }
}

dependencies {
    implementation(libs.androidx.navigation.ui)
}
