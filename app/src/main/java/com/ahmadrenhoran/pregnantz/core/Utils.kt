package com.ahmadrenhoran.pregnantz.core

import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import androidx.core.content.ContextCompat
import com.ahmadrenhoran.pregnantz.domain.model.User
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.firebase.auth.FirebaseUser
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.regex.Pattern
import kotlin.math.absoluteValue

object Utils {
    fun toUser(user: FirebaseUser): User {
        user.apply {
            return User(uid, displayName ?: "", email!!)
        }
    }

    fun isEmailValid(email: String) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String): Boolean {

        // minimal
        if (password.length < 8) return false

        // minimal ada satu angka
        var pattern = Pattern.compile("(?=.*[0-9])")
        if (!pattern.matcher(password).find()) return false

        return true
    }

    fun getFirstDayOfLastPeriodDueDate(localDate: LocalDate): String {
        val days = 7
        val months = 3
        val year = 1

        val expectedDueDate = localDate.plusDays(days.toLong())
            .minusMonths(months.toLong())
            .plusYears(year.toLong())

        return expectedDueDate.toString()
    }

    fun getDueWeeks(localDate: LocalDate) =
        ChronoUnit.WEEKS.between(localDate, LocalDate.now()).absoluteValue

    fun getDueDays(localDate: LocalDate) =
        ChronoUnit.DAYS.between(localDate, LocalDate.now()).absoluteValue % 7

    fun parsingDateToSimpleFormat(date: String): String {
        val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val parsedDate = inputDateFormat.parse(date)
        val outputDate = outputDateFormat.format(parsedDate)
        return outputDate
    }

    fun getIconWebsite(url: String): String {
        val domain = url.removePrefix("https://")

        return "https://s2.googleusercontent.com/s2/favicons?domain=$domain/%2020"
    }

    fun concatLocation(location: Location): String {
        return "${location.latitude}, ${location.longitude}"
    }

    fun bitmapDescriptor(
        context: Context,
        vectorResId: Int
    ): BitmapDescriptor? {

        // retrieve the actual drawable
        val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val bm = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )

        // draw it onto the bitmap
        val canvas = android.graphics.Canvas(bm)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }


}

