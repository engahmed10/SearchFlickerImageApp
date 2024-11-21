plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.searchflickrapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.searchflickrapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packagingOptions {
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/*.SF")
        exclude("META-INF/*.DSA")
        exclude("META-INF/*.RSA")
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/io.netty.versions.properties")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/maven/com.google.guava/guava/pom.properties")
        exclude("META-INF/maven/com.google.guava/guava/pom.xml")

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
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.converter.gson)
    //dependncy injection
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    //navigation
    implementation(libs.androidx.navigation.compose)
    //coil
    implementation(libs.coil.compose)

    //test dependencies for unit testing coverage
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    // Coroutines test dependencies
    testImplementation(libs.kotlinx.coroutines.test)
    // JUnit 5 for unit testing
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)


    //Moshi
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    //parcelable

}