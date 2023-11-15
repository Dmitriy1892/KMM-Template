package common.platform.navigation.compose

import android.os.Build
import android.os.Parcelable
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.navigation.NavBackStackEntry
import java.io.Serializable

fun NavBackStackEntry.getIntArg(key: String): Int? = arguments?.getInt(key)

fun NavBackStackEntry.getIntArgOrDefault(key: String, default: Int = 0): Int =
    arguments?.getInt(key, default) ?: default

fun NavBackStackEntry.getIntArrayArg(key: String): IntArray? = arguments?.getIntArray(key)

fun NavBackStackEntry.getIntArrayArgOrDefault(
    key: String,
    default: IntArray = IntArray(0)
): IntArray = arguments?.getIntArray(key) ?: default

fun NavBackStackEntry.getLongArg(key: String): Long? = arguments?.getLong(key)

fun NavBackStackEntry.getLongArgOrDefault(key: String, default: Long = 0L): Long =
    arguments?.getLong(key, default) ?: default

fun NavBackStackEntry.getLongArrayArg(key: String): LongArray? = arguments?.getLongArray(key)

fun NavBackStackEntry.getLongArrayArgOrDefault(
    key: String,
    default: LongArray = LongArray(0)
): LongArray = arguments?.getLongArray(key) ?: default

fun NavBackStackEntry.getFloatArg(key: String): Float? = arguments?.getFloat(key)

fun NavBackStackEntry.getFloatArgOrDefault(key: String, default: Float = 0.0F): Float =
    arguments?.getFloat(key, default) ?: default

fun NavBackStackEntry.getFloatArrayArg(key: String): FloatArray? = arguments?.getFloatArray(key)

fun NavBackStackEntry.getFloatArrayArgOrDefault(
    key: String,
    default: FloatArray = FloatArray(0)
): FloatArray = arguments?.getFloatArray(key) ?: default

fun NavBackStackEntry.getBooleanArg(key: String): Boolean? = arguments?.getBoolean(key)

fun NavBackStackEntry.getBooleanArgOrDefault(key: String, default: Boolean = false): Boolean =
    arguments?.getBoolean(key, default) ?: default

fun NavBackStackEntry.getBooleanArrayArg(key: String): BooleanArray? = arguments?.getBooleanArray(key)

fun NavBackStackEntry.getBooleanArrayArgOrDefault(
    key: String,
    default: BooleanArray = BooleanArray(0)
): BooleanArray = arguments?.getBooleanArray(key) ?: default

fun NavBackStackEntry.getStringArg(key: String): String? = arguments?.getString(key)

fun NavBackStackEntry.getStringArgOrDefault(key: String, default: String = ""): String =
    arguments?.getString(key, default) ?: default

fun NavBackStackEntry.getStringArrayArg(key: String): Array<String>? = arguments?.getStringArray(key)

fun NavBackStackEntry.getStringArrayArgOrDefault(
    key: String,
    default: Array<String> = emptyArray()
): Array<String> = arguments?.getStringArray(key) ?: default

inline fun <reified T: Parcelable> NavBackStackEntry.getParcelableArg(key: String): T? =
    if (isAtLeastTiramisuBuild()) arguments?.getParcelable(key, T::class.java)
    else arguments?.getParcelable(key)

inline fun <reified T: Parcelable> NavBackStackEntry.getParcelableArgOrDefault(
    key: String,
    default: T
): T = getParcelableArg(key) ?: default

@Suppress("UNCHECKED_CAST")
inline fun <reified T: Parcelable> NavBackStackEntry.getParcelableArrayArg(
    key: String
): Array<T>? = if (isAtLeastTiramisuBuild()) arguments?.getParcelableArray(key, T::class.java)
    else arguments?.getParcelableArray(key) as Array<T>?

inline fun <reified T: Parcelable> NavBackStackEntry.getParcelableArrayArgOrDefault(
    key: String,
    default: Array<T> = emptyArray()
): Array<T> = getParcelableArrayArg(key) ?: default

inline fun <reified T: Serializable> NavBackStackEntry.getSerializableArg(key: String): T? =
    if (isAtLeastTiramisuBuild()) arguments?.getSerializable(key, T::class.java)
    else arguments?.getSerializable(key) as T

inline fun <reified T: Serializable> NavBackStackEntry.getSerializableArgOrDefault(
    key: String,
    default: T
): T = getSerializableArg(key) ?: default

@Suppress("UNCHECKED_CAST")
inline fun <reified T: Serializable> NavBackStackEntry.getSerializableArrayArg(
    key: String
): Array<T>? =
    if (isAtLeastTiramisuBuild()) arguments?.getSerializable(key, T::class.java) as Array<T>
    else arguments?.getSerializable(key) as Array<T>?

inline fun <reified T: Serializable> NavBackStackEntry.getSerializableArrayArgOrDefault(
    key: String,
    default: Array<T> = emptyArray()
): Array<T> = getSerializableArrayArg(key) ?: default

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isAtLeastTiramisuBuild(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU