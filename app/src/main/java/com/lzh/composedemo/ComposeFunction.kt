package com.lzh.composedemo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class ComposeFunction {
    @Composable
    fun Greet(string: String) {
        Text(text = string)
    }
    
    @Preview
    @Composable
    fun PreviewGreet() {
        Greet(string = "Good Morning!")
    }
}