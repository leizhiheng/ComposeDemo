package com.lzh.composedemo

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lzh.composedemo.ui.dao.Book
import com.lzh.composedemo.ui.theme.ComposeDemoTheme
import com.lzh.composedemo.ui.viewmodel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var messages = getMessages()


        setContent {
            ComposeDemoTheme {
                var bookViewModel: BookViewModel = viewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Conversation(messages)
                    ComposeFunction().Greet("hello paul")
                }
            }
        }
    }
}

@Composable
fun BookList(viewModel: BookViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)) {
            Text(text = "添加书本", fontSize = 18.sp, modifier = Modifier.padding(20.dp, 10.dp).clickable {
                viewModel.insertBook(Book(System.currentTimeMillis(), "lzh-${Math.random()}", "leizhiheng"))
            })
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)) {
            Text(text = "书籍列表", fontSize = 18.sp, modifier = Modifier.padding(20.dp, 10.dp).clickable {
                viewModel.
            })
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "icon",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        var isExpanded by remember { mutableStateOf(false) }
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            AnimatedVisibility(visible = isExpanded) {
                Text(text = "${message.author}", color = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.height(5.dp))

            Surface(
                shadowElevation = 1.dp,
            ) {
                Log.d("zhiheng", "isExpanded is : $isExpanded, message is : ${message.message}")
                Text(
                    text = message.message,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

fun getMessages(): MutableList<Message> {
    val messages: MutableList<Message> = mutableListOf()
    messages.apply {
        var name = ""
        for (i in 0..10) {
            name = if (i % 2 == 0) "lzh" else "Blue"
            this.add(Message("$name", "$name's ${(i/2) + 1} message content message content message content message content message content message content message content message content message content message content message content message content message content message content message content message content message content"))
        }
    }
    return messages
}

@Preview(name = "Conversation")
@Composable
fun PreviewConversation() {
    ComposeDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Conversation(getMessages())
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) {
            MessageCard(message = it)
        }
    }
}


@Preview(name = "Light Mode")
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewMessageCard() {
    ComposeDemoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MessageCard(Message("Lzh", "Hello world!"))
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Greeting("Android")
    }
}

data class Message(val author: String, var message: String)