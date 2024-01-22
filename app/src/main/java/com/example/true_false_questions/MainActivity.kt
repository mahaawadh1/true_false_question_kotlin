package com.example.true_false_questions
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.true_false_questions.ui.theme.True_false_questionsTheme

class Greeting(s: String) {

}
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            True_false_questionsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column( verticalArrangement = Arrangement.SpaceBetween){
                        Text(text = "is Android an operating system ?",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            fontSize = 22.sp)

                        Box( modifier = Modifier
                            .size(190.dp)
                            .clip(CircleShape).background(color = Color.Green),
                            contentAlignment = Alignment.Center

                        ){
                            Text(text = "Correct Answer",textAlign = TextAlign.Center)

                        }


                        Row (modifier = Modifier.fillMaxWidth()){
                            Button(modifier = Modifier.width(200.dp),onClick = { /*TODO*/ }) {
                                Text(text = "True", fontSize = 20.sp)

                            }
                            Spacer(modifier = Modifier.width(16.dp))

                            Button(modifier = Modifier.width(200.dp),onClick = { /*TODO*/ }) {
                                Text(text = "False", fontSize = 20.sp)

                            }


                        }



                    }
                    Column( verticalArrangement = Arrangement.Bottom){
                        Button(modifier = Modifier.width(400.dp),onClick = { /*TODO*/ }) {
                            Text(text = "Next Question", fontSize = 17.sp)

                        }
                    }

                }

            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
        fun GreetingPreview() {
            True_false_questionsTheme {
                Greeting("Android")
            }
        }
