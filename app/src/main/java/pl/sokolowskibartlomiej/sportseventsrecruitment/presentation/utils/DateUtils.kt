package pl.sokolowskibartlomiej.sportseventsrecruitment.presentation.utils

import android.content.Context
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

fun Date.getFormattedString(context: Context): String =
    if (abs(time - Date().time) < DateUtils.DAY_IN_MILLIS * 2)
        DateUtils.getRelativeDateTimeString(
            context,
            time,
            DateUtils.DAY_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS,
            0
        ).toString()
    else SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)