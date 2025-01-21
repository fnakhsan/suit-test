plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.dagger.hilt)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.akhsan.suittest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.akhsan.suittest"
        minSdk = 21
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    //    AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core.jvm)
    implementation(libs.androidx.swiperefreshlayout)

    //    Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //    Coroutine
    implementation(libs.bundles.coroutine)

    //    Lifecycle
    implementation(libs.bundles.lifecycle)

    //    Navigation
    implementation(libs.bundles.navigation)

    //    Serialization
    implementation(libs.kotlinx.serialization.json)

    //    Network
    implementation(libs.bundles.network)

    //    Pagination
    implementation(libs.androidx.paging.runtime)

    //    DI
    implementation(libs.android.dagger.hilt)
    ksp(libs.android.dagger.hilt.compiler)

    //    Presentation
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.glide)
    ksp(libs.glide.ksp)
}