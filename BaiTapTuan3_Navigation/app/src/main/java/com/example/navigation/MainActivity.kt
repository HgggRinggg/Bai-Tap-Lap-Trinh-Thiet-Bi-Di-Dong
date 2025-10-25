package com.example.navigation

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.Brown
import com.example.navigation.ui.theme.BrownChoco
import com.example.navigation.ui.theme.DarkGreen
import com.example.navigation.ui.theme.LightGreen
import com.example.navigation.ui.theme.MiddlePurpLe
import com.example.navigation.ui.theme.NavigationTheme
import com.example.navigation.ui.theme.QueenPink
import com.example.navigation.ui.theme.Red
import com.example.navigation.ui.theme.White

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavController)
{
    Column (
        modifier = Modifier
            .background(LightGreen)
            .fillMaxSize()
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier
                .size(216.dp, 233.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        Text(
            "Jetpack Compose",
            style = MaterialTheme.typography.titleLarge,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = {navController.navigate("list")},
            modifier = Modifier
                .width(250.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DarkGreen)
        ) {
            Text(
                "I'm ready",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Normal)
            )
        }
    }
}

data class ComponentItem(
    val title: String,
    val subtitle: String
)

@Composable
fun TopAppBar(
    title: String,
    backgroundColor: Color,
    titleColor: Color,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(35.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 20.dp)
        ) {
            if (showBackButton && onBackClick != null)
            {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.chevron_left),
                        contentDescription = "Back",
                        tint = titleColor,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Text(
                text = title,
                color = titleColor,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(2.dp),
                textAlign = TextAlign.Center
            )

            if (showBackButton) {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }
    }
}

@Composable
fun UIComponentsListScreen (navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = "UI Components List",
                backgroundColor = MiddlePurpLe,
                titleColor = White,
                showBackButton = false,
                onBackClick = {}
            )
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(QueenPink)
                .padding(22.dp, 22.dp, 22.dp, 0.dp)
        ) {
            item {
                CategoryItem(
                    "Display",
                    navController,
                    listOf(
                        ComponentItem("Text", "Displays text."),
                        ComponentItem("Image", "Displays an image."),
                        ComponentItem("ProgressIndicator", "Shows loading progress."),
                        ComponentItem("Card", "Displays content inside a card container.")
                    ),
                )

                CategoryItem(
                    "Input",
                    navController,
                    listOf(
                        ComponentItem("TextField", "Input field for text."),
                        ComponentItem("PasswordField", "Input field for passwords."),
                        ComponentItem("Button", "Triggers an action when clicked."),
                        ComponentItem("IconButton", "Button that displays an icon."),
                        ComponentItem("CheckBox", "Allows selection of multiple options."),
                        ComponentItem("RadioButton", "Allows selection of one option from a group."),
                        ComponentItem("Switch", "Toggles between on and off states."),
                        ComponentItem("Slider", "Selects a value from a continuous range."),
                        ComponentItem("DropdownMenu", "Shows a list of selectable items."),
                        ComponentItem("RatingBar", "Lets users rate using stars."),
                        ComponentItem("SearchBar", "Input field for searching."),
                        ComponentItem("Spinner", "Displays a list of selectable items in a dropdown.")
                    ),
                )

                CategoryItem(
                    "Layout",
                    navController,
                    listOf(
                        ComponentItem("Column", "Arranges elements vertically."),
                        ComponentItem("Row", "Arranges elements horizontally."),
                        ComponentItem("Box", "Stacks elements on top of each other."),
                        ComponentItem("LazyVerticalGrid", "Displays items in a scrollable grid."),
                        ComponentItem("Scroll", "Enables scrolling for overflowing content.")
                    ),
                )
            }
        }
    }
}

@Composable
fun CategoryItem(title: String, navController: NavController, componentRoutes: List<ComponentItem>)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(QueenPink),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = BrownChoco
        )

        Spacer(modifier = Modifier.height(12.dp))

        for (item in componentRoutes) {
            Card(
                onClick = { navController.navigate(item.title) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MiddlePurpLe)
                        .padding(start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.title,
                        color = Red,
                        style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(
                        text = item.subtitle,
                        color = White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun MultipleStylesInText()
{
    Text(
        buildAnnotatedString {
            append("The ")

            withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough))
            {
                append("quick ")
            }

            withStyle(style = SpanStyle(color = Brown, fontSize = 50.sp, fontWeight = FontWeight.Bold))
            {
                append("B")
            }

            withStyle(style = SpanStyle(color = Brown))
            {
                append("rown ")
            }

            append("fox j u m p s ")

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic))
            {
                append("over ")
            }

            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline))
            {
                append("the ")
            }

            withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, fontFamily = FontFamily.Cursive))
            {
                append("lazy ")
            }

            append("dog.")
        },

        fontSize = 35.sp,
        lineHeight = 64.sp
    )
}

@Composable
fun TextDetailScreen(navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Text Detail",
                backgroundColor = Red,
                titleColor = QueenPink,
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(QueenPink)
                .padding(45.dp, 22.dp, 22.dp, 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MultipleStylesInText()
        }
    }
}

@Composable
fun ImagesScreen(navController: NavController)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Images",
                backgroundColor = Red,
                titleColor = QueenPink,
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(QueenPink)
                .padding(22.dp, 22.dp, 22.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /* AsyncImage(
                model = "https://diemthi.tuyensinh247.com/images/simages/GSA.jpg",
                contentDescription = "Image from URL",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            ) */

            Image(
                painter = painterResource(id = R.drawable.uth_2),
                contentDescription = "in internet",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(Modifier.height(16.dp))
            Text(
                "https://diemthi.tuyensinh247.com/images/simages/GSA.jpg",
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
                color = BrownChoco
            )

            Spacer(Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "in internet",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "in app",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = BrownChoco
            )
        }
    }
}

@Composable
fun TextFieldScreen(navController: NavController)
{
    var textValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "TextField",
                backgroundColor = Red,
                titleColor = QueenPink,
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(QueenPink)
                .padding(22.dp, 22.dp, 22.dp, 120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { newValue ->
                    textValue = newValue
                },
                label = { Text("Thông tin nhập") },
                textStyle = TextStyle(color = BrownChoco),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Tự động cập nhật dữ liệu theo textfield",
                color = Red,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Dữ liệu hiện tại: $textValue",
                color = Red,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun ColumnLayoutScreen(navController: NavController)
{
    Scaffold (
        topBar = {
            TopAppBar(
                title = "Column Layout",
                backgroundColor = Red,
                titleColor = QueenPink,
                showBackButton = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(QueenPink)
                .padding(22.dp, 22.dp, 22.dp, 22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            for (i in 1..3) {
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp)
                ){

                }
            }
        }
    }
}


@Composable
fun MyApp()
{
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome")
    {
        composable("welcome") { WelcomeScreen(navController) }
        composable("list") { UIComponentsListScreen(navController) }
        composable("Text") { TextDetailScreen(navController) }
        composable("Image") { ImagesScreen(navController) }
        composable("TextField") { TextFieldScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview()
{
    val navController = rememberNavController()

    NavigationTheme {
        MyApp()
    }
}