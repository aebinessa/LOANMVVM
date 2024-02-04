package com.binjesus.loantask
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun LoanCalculator(viewModel: LoanViewModel = viewModel()) {
    var loanAmount by remember { mutableStateOf("") }
    var periodInMonths by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = loanAmount,
            onValueChange = {
                loanAmount = it
                viewModel.loanAmount(it.toFloatOrNull() ?: 0f)
            },
            label = { Text("Loan Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = periodInMonths,
            onValueChange = {
                periodInMonths = it
                viewModel.periodInMonths(it.toIntOrNull() ?: 0)
            },
            label = { Text("Period in Months") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                viewModel.calculateMonthlyInstallment()
                keyboardController?.hide()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Calculate")
        }

        Text(
            text = "Monthly Installment: $${viewModel.monthlyInstallment}",
            modifier = Modifier.padding(16.dp)
        )
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoanCalculatorApp() {
    val viewModel: LoanViewModel = viewModel()
    MaterialTheme {
        LoanCalculator(viewModel)
    }
}