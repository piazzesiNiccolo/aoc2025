plugins {
    kotlin("jvm") version "2.2.21"
    id("com.diffplug.spotless") version "8.1.0"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

spotless {
    kotlin {
        ktfmt()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}
