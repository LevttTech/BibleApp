plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("realm-android")
}

android {
    namespace = "com.levtttech.bibleapp"
    compileSdk = 33  // ДЛЯ AGP 7.4.2 используем compileSdk 33

    defaultConfig {
        applicationId = "com.levtttech.bibleapp"
        minSdk = 24
        targetSdk = 33  // И targetSdk 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("boolean", "USE_MOCKS", "false")
        }
        debug {
            buildConfigField("boolean", "USE_MOCKS", "false")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // Kapt настройки для Java 11
    kapt {
        javacOptions {
            option("-target", "11")
            option("-source", "11")
        }
    }
}

dependencies {
    // Realm - используем версию, совместимую с AGP 7.4.2
//    implementation("io.realm:realm-android-library:10.11.1")
//    implementation("io.realm:realm-android-kotlin-extensions:10.11.1")
//    kapt("io.realm:realm-annotations:10.11.1")
//    kapt("io.realm:realm-annotations-processor:10.11.1")
//    // Другие зависимости - используем версии, совместимые с AGP 7.4.2
    implementation("androidx.fragment:fragment-ktx:1.5.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}