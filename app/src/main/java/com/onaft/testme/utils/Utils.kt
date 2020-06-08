package com.onaft.testme.utils

object Utils {
    fun generateLessonId(): String{val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..10)
            .map { allowedChars.random() }
            .joinToString("")
    }
}