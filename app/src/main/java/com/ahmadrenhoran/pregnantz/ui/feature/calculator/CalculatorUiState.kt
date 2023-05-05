package com.ahmadrenhoran.pregnantz.ui.feature.calculator

import android.net.Uri
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.core.DueDateMenu
import com.ahmadrenhoran.pregnantz.core.PregnancyUtils
import com.ahmadrenhoran.pregnantz.core.Utils
import java.time.LocalDate

data class CalculatorUiState(
    val date: String = LocalDate.now().toString(),
    val dueDateMenu: DueDateMenu = Constants.DUE_DATE_MENU_LIST[0],
    val weekList: List<LocalDate> = PregnancyUtils.getWeeksList(
        PregnancyUtils.getFirstDayOfLastPeriodDueDate(
            LocalDate.parse(date)
        )
    ),
    val isDueDateMenuExpand: Boolean = false,
)