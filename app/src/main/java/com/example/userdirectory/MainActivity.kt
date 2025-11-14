package com.example.userdirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.userdirectory.ui.UserDirectoryScreen
import com.example.userdirectory.ui.UserDirectoryViewModel
import com.example.userdirectory.ui.theme.UserDirectoryTheme



class MainActivity : ComponentActivity() {

    private val viewModel: UserDirectoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserDirectoryTheme {
                UserDirectoryScreen(viewModel = viewModel)
            }
        }
    }
}
