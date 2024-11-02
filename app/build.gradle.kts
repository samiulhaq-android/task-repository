plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

android {
    namespace = "com.example.elearningapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.elearningapp"
        minSdk = 24
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    buildToolsVersion = "34.0.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Koin for dependency injection
    implementation (libs.koin.android)

    // Room for local database
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    //ksp
    ksp ("androidx.room:room-compiler:2.5.0")
    implementation ("androidx.room:room-runtime:2.5.0")
    implementation ("androidx.room:room-ktx:2.5.0")

    // JSON Parsing with Moshi
    implementation (libs.moshi)
    implementation (libs.moshi.kotlin)
    //navigation
    implementation (libs.androidx.navigation.fragment.ktx)
    implementation (libs.androidx.navigation.ui.ktx)
    //pixel density
    implementation (libs.sdp.android)
    implementation (libs.ssp.android)
    //picasso
    implementation (libs.picasso)
}