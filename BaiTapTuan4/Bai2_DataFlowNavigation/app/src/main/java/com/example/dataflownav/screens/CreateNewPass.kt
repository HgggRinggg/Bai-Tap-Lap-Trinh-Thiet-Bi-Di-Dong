package com.example.dataflownav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dataflownav.R
import com.example.dataflownav.ui.theme.DarkBlue
import com.example.dataflownav.ui.theme.DataFlowNavTheme
import com.example.dataflownav.ui.theme.LightBlue
import com.example.dataflownav.ui.theme.WhiteBlue

@Composable
fun CreateNewPassScreen(
    email: String,
    onNextClick: ((String) -> Unit)? = null,
    onBackClick: (() -> Unit)? = null
) {
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var isPassVisible by remember { mutableStateOf(false) }
    var isConfirmPassVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
    ) {
        SmartTasks(
            title = "Create New Password",
            subTitle = "Your new password must be different form previously used password.",
            showBackButton = onBackClick != null,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, 16.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = pass,
                onValueChange = { newValue -> pass = newValue },
                modifier = Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        "Password",
                        color = DarkBlue.copy(alpha = 0.7f)
                    )
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.lock),
                        contentDescription = null,
                        tint = DarkBlue,
                        modifier = Modifier.size(24.dp)
                    )
                },

                trailingIcon = {
                    val icon =
                        if (isPassVisible)
                            painterResource(R.drawable.visible)
                        else
                            painterResource(R.drawable.visible_off)

                    IconButton(
                        onClick = { isPassVisible = !isPassVisible }
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription =
                                if (isPassVisible)
                                    "Hide password"
                                else
                                    "Show password",
                            tint = DarkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },

                visualTransformation =
                    if (isPassVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                singleLine = true,

                textStyle = TextStyle(
                    color = DarkBlue,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                ),

                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedContainerColor = LightBlue.copy(alpha = 0.3f),
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = DarkBlue
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            OutlinedTextField(
                value = confirmPass,
                onValueChange = { newValue -> confirmPass = newValue },
                modifier = Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        "Confirm password",
                        color = DarkBlue.copy(alpha = 0.7f)
                    )
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.lock),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = DarkBlue
                    )
                },

                trailingIcon = {
                    val icon =
                        if (isConfirmPassVisible)
                            painterResource(R.drawable.visible)
                        else
                            painterResource(R.drawable.visible_off)

                    IconButton( onClick = { isConfirmPassVisible = !isConfirmPassVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription =
                                if (isConfirmPassVisible)
                                    "Hide password"
                                else
                                    "Show password",
                            tint = DarkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },

                visualTransformation =
                    if (isConfirmPassVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                singleLine = true,

                textStyle = TextStyle(
                    color = DarkBlue,
                    fontFamily = FontFamily.Serif,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                ),

                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedContainerColor = LightBlue.copy(alpha = 0.3f),
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = DarkBlue
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (pass == confirmPass && pass.isNotEmpty())
                        if (onNextClick != null)
                            onNextClick(pass)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)
            ) {
                Text(
                    "Next",
                    color = LightBlue,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateNewPassPreview()
{
    DataFlowNavTheme {
        CreateNewPassScreen("example@example.com")
    }
}