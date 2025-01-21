package com.akhsan.suittest.util

fun isPalindrome(value: String): Boolean {
    val text = value.replace("\\p{Zs}+".toRegex(), "")
    for (i in 0..text.length / 2) {
        if (text[i] != text[text.length - i - 1]) return false
    }
    return true
}