// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    /* Plugin Dagger */
    id("com.google.dagger.hilt.android") version "2.44" apply false
    /* Plugin Ksp */
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}
