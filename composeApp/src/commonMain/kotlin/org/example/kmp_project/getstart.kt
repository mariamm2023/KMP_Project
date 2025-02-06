import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun Nexus() {
    var selectedLanguage by remember { mutableStateOf("English") }
    var isLoginScreen by remember { mutableStateOf(false) }

    if (isLoginScreen) {
        LoginScreen { isLoginScreen = false } // الانتقال إلى تسجيل الدخول
    } else {
        Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
            TopNavBar(selectedLanguage, { selectedLanguage = it }) { isLoginScreen = true }
            Spacer(modifier = Modifier.height(24.dp))
            HeroSection()
            Spacer(modifier = Modifier.height(32.dp))
            FeaturesSection()
            Spacer(modifier = Modifier.height(16.dp))
            Footer()
        }
    }
}

@Composable
fun TopNavBar(selectedLanguage: String, onLanguageChange: (String) -> Unit, onLoginClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF001F3F))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Nexus", color = Color(0xFF61A8FF), fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf("Home", "Features", "Pricing", "Contact").forEach { title ->
                ClickableText(
                    text = AnnotatedString(title),
                    onClick = {},
                    style = LocalTextStyle.current.copy(color = Color.White, fontSize = 14.sp)
                )
            }
            Button(
                onClick = onLoginClick, // هنا يتم التغيير إلى شاشة تسجيل الدخول
                colors = ButtonDefaults.buttonColors(Color(0xFF1E90FF))
            ) {
                Text("Get Started", color = Color.White)
            }
        }

        DropdownMenu(selectedLanguage, onLanguageChange)
    }
}

@Composable
fun DropdownMenu(selectedLanguage: String, onLanguageChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }, colors = ButtonDefaults.buttonColors(Color(0xFF1E90FF))) {
            Text(selectedLanguage, color = Color.White)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = { onLanguageChange("English"); expanded = false }) {
                Text("English")
            }
            DropdownMenuItem(onClick = { onLanguageChange("العربية"); expanded = false }) {
                Text("العربية")
            }
        }
    }
}

@Composable
fun HeroSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                "Seamless Virtual Meetings",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF001F3F)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Experience high-quality meetings with AI-powered enhancements.",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FeaturesSection() {
    Column(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
        Text(
            "Why Choose Nexus?",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001F3F)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            FeatureCard("AI-Powered Summaries", "Get automatic meeting summaries with key highlights.")
            FeatureCard("Focus Tracking", "Ensure engagement with real-time attention monitoring.")
            FeatureCard("Smart Chat", "Instantly get AI-driven responses to your questions.")
        }
    }
}

@Composable
fun FeatureCard(title: String, description: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF001F3F)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF121212))
            .padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            FooterSection("About", listOf("Our Team", "Careers", "Partners"))
            FooterSection("Resources", listOf("Blog", "Support", "Community"))
            FooterSection("Contact", listOf("Email Us", "Live Chat", "FAQ"))
        }
    }
}

@Composable
fun FooterSection(title: String, items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        items.forEach { item ->
            ClickableText(
                text = AnnotatedString(item),
                onClick = {},
                style = LocalTextStyle.current.copy(color = Color.Gray, fontSize = 12.sp)
            )
        }
    }
}

@Composable
fun LoginScreen(function: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF8F6FF)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "WELCOME",
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Back!",
                fontSize = 24.sp,
                color = Color(0xFF0000FF) // أزرق
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter your email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            // Password Field with Toggle Visibility
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter password") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
                trailingIcon = {
                    /*Icon(
                        imageVector = if (passwordVisible) Icons.Filled.visibility else Icons.Filled.visibility_off,
                        contentDescription = "Toggle password visibility",
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )*/
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button with Gradient
            Button(
                onClick = { /* تنفيذ تسجيل الدخول */ },
                modifier = Modifier.fillMaxWidth().padding(8.dp).height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color.Black, Color(0xFF1E90FF))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Login", color = Color.White, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password & Sign Up
            Text(
                text = "Forgot password?",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.clickable { /* استعادة كلمة المرور */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't have an account?",
                fontSize = 12.sp,
                color = Color.Black
            )
            Text(
                text = "SIGN UP NOW",
                fontSize = 14.sp,
                color = Color(0xFF0000FF),
                modifier = Modifier.clickable { /* تسجيل حساب جديد */ }
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Nexus Meetings") {
        MaterialTheme { Nexus() }
    }
}