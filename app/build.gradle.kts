plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "uk.ac.ltu.hms"
    compileSdk = 36

    defaultConfig {
        applicationId = "uk.ac.ltu.hms"
        minSdk = 26
        targetSdk = 36
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

    // Java 17 once
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Needed for Robolectric to load resources
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    // UI
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    // Optional AndroidX core for Java (remove if not needed)
    implementation("androidx.core:core:1.13.1")

    // Room (Java uses annotationProcessor)
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // Unit tests (JVM)
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("androidx.test:core:1.5.0")        // ApplicationProvider
    testImplementation("androidx.room:room-testing:2.6.1") // Test helpers (optional)

    // Instrumented tests (device/emulator)
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
