package com.example.true_false_questions
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.true_false_questions.ui.theme.True_false_questionsTheme
import kotlin.reflect.KProperty
data class Question(val text: String, val isCorrect: Boolean)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            True_false_questionsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrueFalseGame()
                }
            }
        }
    }
}

@Composable
fun TrueFalseGame() {
    var showNextButton by remember { mutableStateOf(false) }
    var questions by remember { mutableStateOf<List<Question>>(emptyList()) }
    var currentQuestionIndex = remember { mutableStateOf(0) }
    var AnsrCorrect by remember { mutableStateOf(false) }
    var UserAnsr by remember { mutableStateOf(false) }
    var ButtonAnsr by remember { mutableStateOf(false) }
    var showFeedback by remember { mutableStateOf(false) }
    var userScore = remember { mutableStateOf(0) }

    if (questions.isEmpty()) {
        questions = listOf(
            Question("my name is maha ", true),
            Question("is the sky blue ?", true),
            Question("all animals have 4 legs ?", false),
        )

    }
    AnsrCorrect = UserAnsr == questions[currentQuestionIndex.value].isCorrect

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = questions[currentQuestionIndex.value].text,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        var showResetButton = currentQuestionIndex.value == questions.size - 1
        if (showResetButton) {
            ResetButton(currentQuestionIndex, userScore)
        }

        if (AnsrCorrect && showFeedback && !showResetButton) {
            AnswerFeedback("Correct!", MaterialTheme.colorScheme.secondary)
            Text(text = "Score: ${userScore.value}")
            Image(
                painter = painterResource(id = R.drawable.correct_answer ),
                contentDescription = null, // Provide appropriate content description
            )}

        if (showNextButton) {
            Button(
                onClick = {
                    ButtonAnsr = false
                    showFeedback = false
                    showNextButton = false
                    if (currentQuestionIndex.value < questions.size - 1) {
                        currentQuestionIndex.value++
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(10.dp),
            ) {
                Text(text = "Next Question")
            }
        }
        if (!showNextButton && !AnsrCorrect && showFeedback) {
            ButtonAnsr = true
            AnswerFeedback("Wrong!", MaterialTheme.colorScheme.error)
        }
        if (!showNextButton) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TrueFalseButton("True") {
                    UserAnsr = true
                    ButtonAnsr = true
                    AnsrCorrect = UserAnsr == questions[currentQuestionIndex.value].isCorrect
                    userScore.value += 1
                    showFeedback = true
                    showNextButton = true
                }
                TrueFalseButton("False") {
                    UserAnsr = false
                    ButtonAnsr = true
                    AnsrCorrect = UserAnsr == questions[currentQuestionIndex.value].isCorrect
                    showFeedback = true
                    showNextButton = true
                }
            }
        }
    }
}
private operator fun Any.setValue(nothing: Nothing?, property: KProperty<*>, any: Any) {
}

@Composable
fun ResetButton(currentQuestionIndex: MutableState<Int>, userScore: MutableState<Int>) {
    Button(onClick = {
        currentQuestionIndex.value = 0
        userScore.value = 0
    }) {
        Text(text = "Reset Game")
    }
}
@Composable
fun TrueFalseButton(text: String, UserAnsr: () -> Unit) {
    Button(
        onClick = {
            UserAnsr()
        },
        modifier = Modifier
            .width(150.dp)
            .height(40.dp)
    ) {
        Text(text = text)
    }
}
@Composable
fun AnswerFeedback(message: String, backgroundColor: androidx.compose.ui.graphics.Color) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .clip(MaterialTheme.shapes.large)
            .background(backgroundColor)
            .padding(16.dp),
        Alignment.Center
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
@Preview(showBackground = true)
@Composable
fun TrueFalseGamePreview() {
    TrueFalseGame()
}