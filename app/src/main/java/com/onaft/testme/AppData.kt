package com.onaft.testme

object AppData {
    var isStudent: Boolean = false

    @JvmStatic
    fun updateRole(isStudent: Boolean) {
        this.isStudent = isStudent
    }
}