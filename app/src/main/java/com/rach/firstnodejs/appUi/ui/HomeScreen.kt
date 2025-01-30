package com.rach.firstnodejs.appUi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rach.firstnodejs.AppDimensions.AppDp
import com.rach.firstnodejs.AppPreview
import com.rach.firstnodejs.appUi.components.CustomOutLinedTextField
import com.rach.firstnodejs.appUi.components.CustomTopAppBar
import com.rach.firstnodejs.appUi.viewmodels.MainViewModel
import com.rach.firstnodejs.models.HomeUiEvent
import com.rach.firstnodejs.ui.theme.FirstNodeJsTheme
import com.rach.firstnodejs.ui.theme.Purple80
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToAllUsers: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = message
                )
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        topBar = {
            CustomTopAppBar(
                title = "Create User",
                modifier = Modifier.fillMaxWidth()
            )

        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppDp.normalDp)
                .padding(top = 10.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            CustomOutLinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.enteredName,
                onValueChange = {
                    viewModel.onEvent(
                        HomeUiEvent.EnterUserNameChanged(it)
                    )
                },
                label = "Enter Name"
            )

            Spacer(modifier = Modifier.height(AppDp.normalSpacing))

            CustomOutLinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.enterRole,
                onValueChange = {
                   viewModel.onEvent(
                       HomeUiEvent.EnterRoleChanged(it)
                   )
                },
                label = "Enter Role"
            )

            Spacer(modifier = Modifier.height(AppDp.normalSpacing))

            CustomOutLinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.enteredEmail,
                onValueChange = {
                   viewModel.onEvent(
                       HomeUiEvent.EnterEmailChanged(it)
                   )
                },
                label = "Enter Email"
            )


            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = { viewModel.createUser() },
                colors = ButtonDefaults.buttonColors(
                    Purple80
                )
            ) {

                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Save as",
                        color = Color.Black
                    )

                }
            }

            if (!state.isLoading) {
                Button(onClick = onNavigateToAllUsers) {
                    Text("See ALL Userss")
                }
            }


        }
    }

}

@AppPreview
@Composable
private fun Preview() {
    FirstNodeJsTheme {
        HomeScreen(
            onNavigateToAllUsers = {}
        )
    }
}