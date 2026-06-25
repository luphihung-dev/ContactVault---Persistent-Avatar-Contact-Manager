package com.example.projectexercise3.ui

import androidx.annotation.DrawableRes
import com.example.projectexercise3.R

data class AvatarOption(
    val name: String,
    @DrawableRes val drawableRes: Int,
    val label: String
)

object AvatarCatalog {
    val avatars = listOf(
        AvatarOption("avatar_amber", R.drawable.avatar_amber, "Amber"),
        AvatarOption("avatar_blue", R.drawable.avatar_blue, "Blue"),
        AvatarOption("avatar_green", R.drawable.avatar_green, "Green"),
        AvatarOption("avatar_rose", R.drawable.avatar_rose, "Rose"),
        AvatarOption("avatar_slate", R.drawable.avatar_slate, "Slate"),
        AvatarOption("avatar_teal", R.drawable.avatar_teal, "Teal")
    )

    fun defaultName(): String = avatars.first().name

    @DrawableRes
    fun drawableFor(name: String): Int {
        return avatars.firstOrNull { it.name == name }?.drawableRes ?: avatars.first().drawableRes
    }
}
