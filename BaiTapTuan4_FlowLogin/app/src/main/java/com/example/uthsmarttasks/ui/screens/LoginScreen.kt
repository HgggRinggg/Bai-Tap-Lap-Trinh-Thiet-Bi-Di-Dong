package com.example.uthsmarttasks.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.ui.theme.Blue
import com.example.uthsmarttasks.ui.theme.DarkBlue
import com.example.uthsmarttasks.ui.theme.LightBlue
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.example.uthsmarttasks.ui.theme.WhiteBlue
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun SignInWithGoogle(navController: NavController)
{
    val context = LocalContext.current
    val activity = context as? Activity
    val auth = FirebaseAuth.getInstance()

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("430627192009-shj89ja4q1mbr8micqppabkt8kbvch2h.apps.googleusercontent.com")
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

        if (task.isSuccessful) {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { taskResult ->
                    if (taskResult.isSuccessful) {
                        activity?.let {
                            Toast.makeText(it, "Đăng nhập thành công!", Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate("profile") {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    }
                    activity?.let {
                        Toast.makeText(it, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        activity?.let {
            Toast.makeText(it, "Lỗi đăng nhập Google!", Toast.LENGTH_SHORT).show()
        }
    }

    SignInWithGoogleUI(
        onSignInClick = {
            val signIn = googleSignInClient.signInIntent
            launcher.launch(signIn)
        }
    )
}

@Composable
fun SignInWithGoogleUI(
    onSignInClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteBlue)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "UTH_logo",
            modifier = Modifier.size(120.dp)
        )

        Text(
            "SmartTasks",
            style = MaterialTheme.typography.titleLarge,
            color = Blue
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "A simple and efficient to-do app",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            color = Blue,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "Welcome",
            style = MaterialTheme.typography.titleSmall,
            color = DarkBlue
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ready to explore? Log in to get started",
            style = MaterialTheme.typography.bodyLarge,
            color = DarkBlue.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(38.dp))

        Button(
            onClick = onSignInClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Google",
                    tint = DarkBlue,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(modifier = Modifier.width(18.dp))

                Text(
                    "SIGN IN WITH GOOGLE",
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 17.sp),
                    color = DarkBlue,
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.copyright),
                    contentDescription = "Copyright Icon",
                    tint = DarkBlue.copy(alpha = 0.7f),
                    modifier = Modifier.size(14.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    "UTHSmartTasks",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp),
                    color = DarkBlue.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview()
{
    UTHSmartTasksTheme {
        SignInWithGoogleUI(onSignInClick = {})
    }
}