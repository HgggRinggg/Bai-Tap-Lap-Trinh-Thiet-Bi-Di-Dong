package com.example.dataflownav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dataflownav.ui.theme.DataFlowNavTheme
import com.example.dataflownav.R
import com.example.dataflownav.ui.theme.DarkBlue
import com.example.dataflownav.ui.theme.LightBlue
import com.example.dataflownav.ui.theme.WhiteBlue

@Composable
fun ForgetPassScreen(
    onNextClick: ((String) -> Unit)? = null,
    onBackClick: (() -> Unit)? = null
) {
    var email by remember { mutableStateOf("") }
    val mockEmails = listOf("hggg2610@gmail.com", "kawaiiiiizana@example.com", "trinhhdm2798@ut.edu.vn")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
    ) {
        SmartTasks(
            title = "Forget Password?",
            subTitle = "Enter your Email, we will send you a verification code.",
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
                value = email,
                onValueChange = { newValue -> email = newValue },
                modifier = Modifier.fillMaxWidth(),

                placeholder = {
                    Text(
                        "Your Email",
                        color = DarkBlue.copy(alpha = 0.7f)
                    )
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.email),
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

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (mockEmails.contains(email))
                        if (onNextClick != null)
                            onNextClick(email)

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
fun ForgerPassPreview()
{
    DataFlowNavTheme {
        ForgetPassScreen()
    }
}