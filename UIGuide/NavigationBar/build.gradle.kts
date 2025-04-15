plugins {
    alias(libs.plugins.ui.guide.example)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.develou.navigationbar"

    defaultConfig {
        applicationId = "com.develou.navigationbar"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
}