package com.example.dataflownav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dataflownav.R
import com.example.dataflownav.ui.theme.DarkBlue
import com.example.dataflownav.ui.theme.DataFlowNavTheme
import com.example.dataflownav.ui.theme.LightBlue
import com.example.dataflownav.ui.theme.WhiteBlue

@Composable
fun ConfirmScreen(
    email: String,
    pass: String,
    onBackClick: (() -> Unit)? = null,
    onFinishClick: (() -> Unit)? = null
) {
    var inputEmail by remember { mutableStateOf("") }
    var inputPass by remember { mutableStateOf("") }
    var inputCode by remember { mutableStateOf("") }
    var isPassVisible by remember { mutableStateOf(false) }

    val verifyCode = remember { mutableStateOf((10000..99999).random().toString()) }

    val isEmailValid = inputEmail == email
    val isCodeValid = inputCode == verifyCode.value
    val isPassValid = inputPass == pass

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
    ) {
        SmartTasks(
            title = "Confirm",
            subTitle = "We are here to help you!.",
            showBackButton = onBackClick != null,
            onBackClick = onBackClick
        )

        if (isEmailValid)
        {
            Text(
                text = "Verify Code: ${verifyCode.value}",
                color = DarkBlue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, 16.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = inputEmail,
                onValueChange = { inputEmail = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Your Email", color = DarkBlue.copy(alpha = 0.7f)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.user),
                        contentDescription = "Email",
                        tint = DarkBlue,
                        modifier = Modifier.size(24.dp)
                    )
                },
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

            if (isEmailValid) {
                Spacer(modifier = Modifier.height(22.dp))

                OutlinedTextField(
                    value = inputCode,
                    onValueChange = { inputCode = it.filter { ch -> ch.isDigit() }.take(5) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            "Enter Verify Code",
                            color = DarkBlue.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.email),
                            contentDescription = "Verify Code",
                            tint = DarkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    },
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
            }

            Spacer(modifier = Modifier.height(22.dp))

            OutlinedTextField(
                value = inputPass,
                onValueChange = { inputPass = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Password", color = DarkBlue.copy(alpha = 0.7f)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.lock),
                        contentDescription = null,
                        tint = DarkBlue,
                        modifier = Modifier.size(24.dp)
                    )
                },
                trailingIcon = {
                    val icon = if (isPassVisible)
                        painterResource(R.drawable.visible)
                    else
                        painterResource(R.drawable.visible_off)

                    IconButton(onClick = { isPassVisible = !isPassVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription = if (isPassVisible) "Hide password" else "Show password",
                            tint = DarkBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                visualTransformation = if (isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                    if (isEmailValid && isCodeValid && isPassValid)
                        if (onFinishClick != null)
                            onFinishClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)
            ) {
                Text(
                    "Summit",
                    color = LightBlue,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmScreenPreview() {
    DataFlowNavTheme {
        ConfirmScreen(
            email = "example@example.com",
            pass = "123456"
        )
    }
}
