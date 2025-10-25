package com.example.dataflownav.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dataflownav.ui.theme.DarkBlue
import com.example.dataflownav.ui.theme.DataFlowNavTheme
import com.example.dataflownav.ui.theme.LightBlue
import com.example.dataflownav.ui.theme.WhiteBlue

@Composable
fun VerifyCodeScreen(
    email: String,
    onNextClick: (() -> Unit)? = null,
    onBackClick: (() -> Unit)? = null
) {
    var codes by remember { mutableStateOf(List(5) { "" }) }
    val verifyCodeState = remember { mutableStateOf((10000..99999).random().toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
    ) {
        SmartTasks(
            title = "Verify Code",
            subTitle = "Enter the code we just sent you on your registered Email.\n\nVerify Code: ${verifyCodeState.value}",
            showBackButton = onBackClick != null,
            onBackClick = onBackClick
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, 16.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(5) { index ->
                    OutlinedTextField(
                        value = codes[index],
                        onValueChange = { newValue ->
                            val newCode = codes.toMutableList()
                            newCode[index] = newValue.take(1)
                            codes = newCode
                        },
                        modifier = Modifier.size(60.dp),
                        singleLine = true,

                        textStyle = TextStyle(
                            color = DarkBlue,
                            fontFamily = FontFamily.Serif,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            textAlign = TextAlign.Center
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
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (codes.joinToString("") == verifyCodeState.value)
                        if (onNextClick != null)
                            onNextClick()
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
fun VerifyCodePreview()
{
    DataFlowNavTheme {
        VerifyCodeScreen(email = "example@example.com")
    }
}