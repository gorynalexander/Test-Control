package com.onaft.testme

object AppData {
    var isStudent: Boolean = true

    @JvmStatic
    fun updateRole(isStudent: Boolean) {
        this.isStudent = isStudent
    }
}