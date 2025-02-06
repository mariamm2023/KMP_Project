package org.example.kmp_project

import Nexus
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Nexus Meetings"
    ) {
        // تطبيق السمة (Theme) مع واجهة المستخدم الرئيسية Nexus
        MaterialTheme {
            Nexus()
        }
    }
}
