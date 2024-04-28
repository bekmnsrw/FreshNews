package kfu.itis.freshnews.utils

fun <T> lazyUnsafe(block: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, block)
