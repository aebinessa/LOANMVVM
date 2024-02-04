package com.binjesus.loantask

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoanViewModel : ViewModel() {

    var loanAmount by mutableStateOf(0f)

    var periodInMonths by mutableStateOf(0)

    var monthlyInstallment by mutableStateOf(0f)
        private set

    fun calculateMonthlyInstallment() {
        monthlyInstallment = if (periodInMonths != 0) {
            loanAmount / periodInMonths
        } else {
            0f
        }
    }

    fun loanAmount(fl: Float) {

    }

    fun periodInMonths(i: Int) {

    }

}

