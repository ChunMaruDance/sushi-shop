package com.chunmaru.sushishop.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream

fun Uri.readBytesFromUri(context: Context): ByteArray {
    val inputStream: InputStream? = context.contentResolver.openInputStream(this)
    return inputStream?.use { it.readBytes() } ?: byteArrayOf()
}

fun ByteArray.convertImageByteArrayToBitmap(): Bitmap =
     BitmapFactory.decodeByteArray(this, 0, this.size)


fun Double.format(digits: Int) = "%.${digits}f".format(this)
