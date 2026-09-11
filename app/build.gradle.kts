plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}


android {
    namespace = "com.example.mobileappclient"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.mobileappclient"
        minSdk = 24
        targetSdk = 37

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // --------------------------------------------------
    // Compose
    // --------------------------------------------------

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation(libs.androidx.compose.ui.tooling)

    // Compose Testing
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.test.manifest)


    // --------------------------------------------------
    // Android Core
    // --------------------------------------------------

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)


    // --------------------------------------------------
    // Navigation
    // --------------------------------------------------

    implementation(libs.androidx.navigation.compose)


    // --------------------------------------------------
    // Room
    // --------------------------------------------------

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)


    // --------------------------------------------------
    // Retrofit
    // --------------------------------------------------

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.kotlinx.serialization.converter)


    // --------------------------------------------------
    // Paging
    // --------------------------------------------------

    implementation(libs.androidx.paging.compose)


    // --------------------------------------------------
    // Kotlin Serialization
    // --------------------------------------------------

    implementation(libs.kotlinx.serialization.json)


    // --------------------------------------------------
    // DataStore
    // --------------------------------------------------

    implementation(libs.androidx.datastore.preferences)


    // --------------------------------------------------
    // Hilt
    // --------------------------------------------------

    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)

    implementation(libs.androidx.hilt.navigation.compose)


    // --------------------------------------------------
    // Coil
    // --------------------------------------------------

    implementation(libs.coil.compose)


    // --------------------------------------------------
    // Accompanist
    // --------------------------------------------------

    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.accompanist.swiperefresh)

    implementation(libs.accompanist.systemuicontroller)


    // --------------------------------------------------
    // Palette
    // --------------------------------------------------

    implementation(libs.androidx.palette)

    // --------------------------------------------------
    // Unit Testing
    // --------------------------------------------------

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)


    // --------------------------------------------------
    // Android Instrumentation Testing
    // --------------------------------------------------

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test:rules:1.7.0")
}
