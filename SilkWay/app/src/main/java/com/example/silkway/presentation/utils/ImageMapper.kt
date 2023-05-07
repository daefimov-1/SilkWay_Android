package com.example.silkway.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream


fun String.toBitmap() : Bitmap {
    val imageDataBytes: String = this.substring(this.indexOf(",") + 1)

    val stream: InputStream =
        ByteArrayInputStream(Base64.decode(imageDataBytes.toByteArray(), Base64.DEFAULT))

    return BitmapFactory.decodeStream(stream)
}

fun Bitmap.likeString() : String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()

    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun Int.toBitmap(context: Context) : Bitmap {
    return BitmapFactory.decodeResource(
        context.resources,
        this
    )
}