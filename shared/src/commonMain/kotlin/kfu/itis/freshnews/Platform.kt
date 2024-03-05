package kfu.itis.freshnews

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform