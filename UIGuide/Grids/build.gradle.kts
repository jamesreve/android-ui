plugins {
    alias(libs.plugins.ui.guide.example)
}

android {
    namespace = "com.develou.grids"

    defaultConfig {
        applicationId = "com.develou.grids"
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