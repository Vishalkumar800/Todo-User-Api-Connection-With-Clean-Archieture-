package com.rach.firstnodejs.appUi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rach.firstnodejs.AppDimensions.AppDp
import com.rach.firstnodejs.AppPreview
import com.rach.firstnodejs.appUi.components.CustomTopAppBar
import com.rach.firstnodejs.appUi.viewmodels.MainViewModel
import com.rach.firstnodejs.ui.theme.FirstNodeJsTheme
import kotlinx.coroutines.launch


@Composable
fun ALLUsers(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {


    val state = viewModel.state
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.getAllUser()
    }

    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let { error ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = error,
                    actionLabel = "Dismiss"
                )
            }
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        modifier = modifier,
        topBar = {
            CustomTopAppBar(
                title = "All User",
                modifier = Modifier.fillMaxWidth()
            )

        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppDp.normalDp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.users.isNotEmpty()) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()

                ) {

                    items(state.users) { user ->

                        DescriptionScreen(
                            name = user.name,
                            email = user.email,
                            role = user.role
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                }

            } else {
                Text(
                    text = "User Not Found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }

    }


}


@Composable
fun DescriptionScreen(
    name: String,
    email: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "User Details",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Divider(color = Color.Gray, thickness = 1.dp)


                DetailRow(label = "Name", value = name)


                DetailRow(label = "Email", value = email)


                DetailRow(label = "Role", value = role)
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}


@AppPreview
@Composable
private fun Preview() {
    FirstNodeJsTheme {
        DescriptionScreen(
            name = "Vishal",
            email = "statusb30@gmail.com",
            role = "Andorid Developer"
        )
    }
}