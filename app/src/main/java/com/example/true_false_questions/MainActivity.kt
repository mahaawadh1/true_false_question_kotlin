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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                    TrueFalseGame(

                    )
                }
            }
        }
    }
}

@Composable
fun TrueFalseGame() {
    var isCorrect by remember { mutableStateOf(false) }
    var isSelectedAnsr by remember { mutableStateOf(false) }
    var isButtonSelect by remember { mutableStateOf(false) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val questions = listOf(
        Question("my name is maha ?",true),
        Question(" is the sky blue ?",true),
        Question("all animals have 4 legs ?",false)
    )
    val currentQuestion = questions.getOrNull(currentQuestionIndex)

    var selectedAnswer by remember { mutableStateOf<Boolean?>(null) }
    var userScore by remember { mutableStateOf(0) }

    if (isSelectedAnsr){ isCorrect = selectedAnswer == currentQuestion?.answer
    }

    Column(
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxSize()
            .width(200.dp)
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        // Step 1
        Text(text = currentQuestion?.text ?: "GAME OVER!!",
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Step 5
        if (isCorrect) {
            AnswerFeedback("Correct!", MaterialTheme.colorScheme.secondary)
        } else {
            AnswerFeedback("Wrong!", MaterialTheme.colorScheme.error)
        }
        if (isButtonSelect){
            Button(
                onClick = { isButtonSelect = false
                    selectedAnswer = null
                    if (currentQuestionIndex < questions.size -1){                             if (isCorrect) userScore++
                        currentQuestionIndex++} else {
                        userScore=0
                        currentQuestionIndex=0
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .align(Alignment.End)
                    .width(200.dp)
                    .padding(10.dp),
            ) {
                Text(text = if (currentQuestionIndex < questions.size -1 ) "Next Question" else "RESTART GAME")
            }
        }else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                // Step 3
                TrueFalseButton("True") {
                    isSelectedAnsr = true
                    isButtonSelect = true
                }
                TrueFalseButton("False") {
                    isSelectedAnsr = true
                    selectedAnswer = false}
            }
        }
    }

}




@Composable
fun TrueFalseButton(text: String, onSelected: () -> Unit) {
    Button(
        onClick = {
            onSelected()
        },
        modifier = Modifier
            .width(120.dp)
            .height(40.dp)
    ) {
        Text(text = text)
    }
}


@Composable
fun AnswerFeedback(message: String, backgroundColor: androidx.compose.ui.graphics.Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .clip(MaterialTheme.shapes.large)
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(

                text = message,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary

            )
        }
    }
}

@Composable
        fun GreetingPreview() {
            True_false_questionsTheme {
                Greeting("Android")
            }
        }
data class Question(val text: String, val answer: Boolean)
@Preview(showBackground = true)
@Composable
fun TrueFalseGamePreview() {

    True_false_questionsTheme {
        TrueFalseGame()
    }
}