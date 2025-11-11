package com.example.uthsmarttasks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.ui.theme.Blue
import com.example.uthsmarttasks.ui.theme.DarkBlue
import com.example.uthsmarttasks.ui.theme.LightBlue
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.example.uthsmarttasks.ui.theme.WhiteBlue
import kotlin.String

@Composable
fun ProfileScreen(navController: NavController)
{
    val user = FirebaseAuth.getInstance().currentUser

    ProfileScreenUI(
        displayName = user?.displayName ?: "N/A",
        email = user?.email ?: "N/A",
        photoUrl = user?.photoUrl?.toString(),
        birthDate = "27/11/2005",
        onChangePhotoClick = {},
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun ProfileScreenUI(
    displayName: String,
    email: String,
    birthDate: String,
    photoUrl: String?,
    onChangePhotoClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WhiteBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Profile",
            style = MaterialTheme.typography.titleLarge,
            color = LightBlue
        )

        Spacer(modifier = Modifier.height(32.dp))


        Box(
            modifier = Modifier.size(125.dp)
        ) {
            val painter =
                if (photoUrl != null)
                    rememberAsyncImagePainter(model = photoUrl)
                else
                    painterResource(R.drawable.default_profile)

            Image(
                painter = painter,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopCenter)
                    .border(2.dp, DarkBlue, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(40.dp)
                    .border(2.dp, DarkBlue, CircleShape)
                    .background(WhiteBlue, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(LightBlue)
            ) {
                Icon(
                    painter = painterResource(R.drawable.camera),
                    contentDescription = "Change Photo",
                    tint = DarkBlue,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Name",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = DarkBlue,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = displayName,
                onValueChange = {},
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = DarkBlue),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedContainerColor = LightBlue.copy(alpha = 0.5f),
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = DarkBlue
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Email",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = DarkBlue,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {},
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = DarkBlue),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedContainerColor = LightBlue.copy(alpha = 0.5f),
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = DarkBlue
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Date of Birth",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = DarkBlue,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = birthDate,
                onValueChange = {},
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = DarkBlue),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = LightBlue,
                    unfocusedContainerColor = LightBlue.copy(alpha = 0.5f),
                    focusedBorderColor = DarkBlue,
                    unfocusedBorderColor = DarkBlue
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.down),
                        contentDescription = "Date Picker"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = onBackClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Back",
                    style = MaterialTheme.typography.titleLarge,
                    color = DarkBlue,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview()
{
    UTHSmartTasksTheme {
        ProfileScreenUI(
            displayName = "Kurokawa Izana",
            email = "kawaiza@tenjiku.yokohama.com",
            birthDate = "30/08/1987",
            photoUrl = null,
            onBackClick = {},
            onChangePhotoClick = {},
        )
    }
}