package com.chunmaru.sushishop.presentation.screens.add_render_ingredient.elements

import androidx.compose.runtime.Composable
import com.airbnb.lottie.compose.LottieConstants
import com.chunmaru.sushishop.data.models.dishes.Ingredient
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.DefaultAlertDialog
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.DefaultAlertDialogWithConfirm
import com.chunmaru.sushishop.presentation.screens.defaults.alerts.DefaultAlertsState

@Composable
fun IngredientsAlertHandler(
    state: DefaultAlertsState<Ingredient>,
    onCancel: () -> Unit,
    onConfirm: (Ingredient) -> Unit,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {

    when (state) {
        is DefaultAlertsState.Initial -> Unit
        is DefaultAlertsState.Confirm -> {
            DefaultAlertDialogWithConfirm(
                showDialog = true,
                animAssets = "confirmed_anim.json",
                message = "Are you sure you\n" +
                        "want to create this ingredient?",
                onDismiss = onCancel,
                onConfirm = { onConfirm(state.data) }
            )
        }

        is DefaultAlertsState.Error -> {
            DefaultAlertDialog(
                showDialog = true,
                animAssets = "error_anim.json",
                message = "Something went wrong",
                onDismiss = onError
            )
        }

        is DefaultAlertsState.Loading -> {
            DefaultAlertDialog(
                iteration = LottieConstants.IterateForever,
                animAssets = "loading_anim.json",
                message = "Loading\nPlease wait",
                showDialog = true,
                onDismiss = {}
            )
        }

        is DefaultAlertsState.Success -> {
            DefaultAlertDialog(
                iteration = 1,
                animAssets = "success_anim.json",
                message = "The ingredient has been created\n" +
                        "successfully processed",
                showDialog = true,
                onDismiss = onSuccess
            )
        }
    }

}