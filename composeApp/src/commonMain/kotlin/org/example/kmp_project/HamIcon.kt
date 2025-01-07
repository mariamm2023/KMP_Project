import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun Nexus() {
    var showSchedule by remember { mutableStateOf(false) }
    var showPermissionCard by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf("English") }

    Column(modifier = Modifier.fillMaxSize()) {
        TopNavBar(selectedLanguage) { newLanguage ->
            selectedLanguage = newLanguage
        }
        Spacer(modifier = Modifier.weight(1f))
        MeetingSection(
            onScheduleClick = { showSchedule = true },
            onStartMeetingClick = { showPermissionCard = true }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Footer()
    }

    // Schedule Meeting Page
    if (showSchedule) {
        ScheduleMeetingScreen(onClose = { showSchedule = false })
    }

    // Permission Card
    if (showPermissionCard) {
        PermissionCard(onClose = { showPermissionCard = false })
    }
}

@Composable
fun TopNavBar(selectedLanguage: String, onLanguageChange: (String) -> Unit) {
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
            listOf("Products", "Solutions", "Resources", "Plans & Pricing").forEach { title ->
                ClickableText(
                    text = AnnotatedString(title),
                    onClick = {},
                    style = LocalTextStyle.current.copy(color = Color.White, fontSize = 14.sp)
                )
            }
        }

        // اختيار اللغة
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DropdownMenu(selectedLanguage, onLanguageChange)
        }
    }
}

@Composable
fun DropdownMenu(selectedLanguage: String, onLanguageChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }, colors = ButtonDefaults.buttonColors(Color(0xFF1E90FF))) {
            Text(selectedLanguage, color = Color.White)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
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
fun MeetingSection(onScheduleClick: () -> Unit, onStartMeetingClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEFF3F6))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Meetings", fontSize = 20.sp, color = Color(0xFF001F3F))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onStartMeetingClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF1E90FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Start Meeting", color = Color.White)
            }
            Button(
                onClick = onScheduleClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF61A8FF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Schedule Meeting", color = Color.White)
            }
        }
    }
}

@Composable
fun ScheduleMeetingScreen(onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Schedule Meeting", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Enter meeting details here...", fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onClose, colors = ButtonDefaults.buttonColors(Color.Red)) {
                Text("Close", color = Color.White)
            }
        }
    }
}

@Composable
fun PermissionCard(onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Permission Required", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Allow access to your camera and microphone?", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onClose, colors = ButtonDefaults.buttonColors(Color.Green)) {
                    Text("Allow", color = Color.White)
                }
                Button(onClick = onClose, colors = ButtonDefaults.buttonColors(Color.Red)) {
                    Text("Deny", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun Footer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF121212))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            FooterSection("About", listOf("Zoom Blog", "Customers", "Our Team", "Careers", "Partners"))
            FooterSection("Download", listOf("Zoom App", "Zoom Rooms Client", "Browser Extension", "Android App"))
            FooterSection("Sales", listOf("Contact Sales", "Plans & Pricing", "Request a Demo", "Webinars"))
            FooterSection("Support", listOf("Test Zoom", "Account", "Support Center", "Learning Center", "Contact Us"))
        }
    }
}

@Composable
fun FooterSection(title: String, items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, color = Color.White, fontSize = 14.sp)
        items.forEach { item ->
            ClickableText(
                text = AnnotatedString(item),
                onClick = {},
                style = LocalTextStyle.current.copy(color = Color.Gray, fontSize = 12.sp)
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Nexus Meetings") {
        MaterialTheme {
            Nexus()
        }
    }
}
