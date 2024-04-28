plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.jvm.target.get()
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.ktorClientCommon)
            implementation(libs.bundles.serialization)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kodein.di)
            implementation(libs.bundles.sqlDelightCommon)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.okhttp3.logging.interceptor)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.timber)
            implementation(libs.kodein.di.framework.android)
            implementation(libs.sql.delight.android.driver)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
            implementation(libs.sql.delight.native.driver)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    task("testClasses")
}

sqldelight {
    databases {
        create("FreshNews") {
            packageName.set("kfu.itis.freshnews")
        }
    }
}

android {
    namespace = "kfu.itis.freshnews"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = false
    }
}
