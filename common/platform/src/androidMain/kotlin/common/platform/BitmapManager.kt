package common.platform

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface

object BitmapManager {

    fun getRotatedBitmap(context: Context, imageUri: Uri): Bitmap {
        val contentResolver = context.contentResolver
        val bitmap = getBitmap(contentResolver, imageUri)
        val currentRotation = getRotation(context, imageUri)

        return rotateBitmap(bitmap, currentRotation)
    }

    private fun getBitmap(contentResolver: ContentResolver, uri: Uri): Bitmap {
        return contentResolver.openInputStream(uri).use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    }

    private fun getRotation(
        context: Context,
        imageUri: Uri
    ): Int = getRotationFromCamera(context, imageUri)

    private fun getRotationFromCamera(context: Context, imageUri: Uri): Int {
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(imageUri)

        val exif = ExifInterface(inputStream!!)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }.also { inputStream.close() }
    }

    private fun rotateBitmap(bitmap: Bitmap, rotation: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotation.toFloat())

        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}