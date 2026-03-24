ACTIVITY MAIN.JAVA Code:-
///////////////////////////////

package com.helloworld.quizapp; import android.app.Activity; import android.os.Bundle;
import android.os.CountDownTimer; import android.view.View;
import android.widget.Button; import android.widget.LinearLayout; import android.widget.RadioButton; import android.widget.RadioGroup; import android.widget.TextView; import android.widget.Toast;

public class MainActivity extends Activity {

// UI
private TextView tvWelcome, tvTimer, tvQuestion, tvProgress, tvPoints, tvTotalScore; private LinearLayout quizContent, pointsTable;
private RadioGroup rgOptions;
private RadioButton rb1, rb2, rb3, rb4; private Button btnStart, btnNext, btnRestart;

// {question, opt1, opt2, opt3, opt4, correctIndex (1–4)} private final String[][] questions = {
{"What is 2+2?", "3", "4", "5", "6", "2"},
{"India's capital?", "Mumbai", "Delhi", "Kolkata", "Chennai", "2"},
{"Largest planet?", "Earth", "Mars", "Jupiter", "Saturn", "3"}
};

private int currentQ = 0; private int totalPoints = 0; private int questionPoints = 0;
private final int totalQ = questions.length;

private CountDownTimer timer; private long startTimeMillis; private boolean quizActive = false;
private static final int QUESTION_TIME_SEC = 40; @Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
 
setContentView(R.layout.activity_main); initAll();
}

private void initAll() {

tvWelcome = findViewById(R.id.tvWelcome); tvTimer = findViewById(R.id.tvTimer); tvQuestion = findViewById(R.id.tvQuestion); tvProgress = findViewById(R.id.tvProgress); tvPoints = findViewById(R.id.tvPoints); tvTotalScore = findViewById(R.id.tvTotalScore); quizContent = findViewById(R.id.quizContent); pointsTable = findViewById(R.id.pointsTable); rgOptions = findViewById(R.id.rgOptions);
rb1 = findViewById(R.id.rb1); rb2 = findViewById(R.id.rb2); rb3 = findViewById(R.id.rb3); rb4 = findViewById(R.id.rb4);
btnStart = findViewById(R.id.btnStart); btnNext = findViewById(R.id.btnNext); btnRestart = findViewById(R.id.btnRestart);

btnStart.setOnClickListener(v -> startQuiz()); btnNext.setOnClickListener(v -> nextQuestion()); btnRestart.setOnClickListener(v -> resetQuiz());

// Initial UI state
tvTimer.setVisibility(View.VISIBLE);	// <-- TIMER AB SHURU SE DIKHEGA quizContent.setVisibility(View.GONE); pointsTable.setVisibility(View.GONE); tvWelcome.setVisibility(View.VISIBLE);  btnStart.setVisibility(View.VISIBLE);

tvTimer.setText(String.valueOf(QUESTION_TIME_SEC)); tvTimer.setTextColor(0xFF10B981);

preloadFirstQuestion();
}

private void preloadFirstQuestion() { String[] q = questions[0]; tvQuestion.setText("1. " + q[0]); rb1.setText(q[1]);
rb2.setText(q[2]);
rb3.setText(q[3]);
rb4.setText(q[4]); tvProgress.setText("Q 1/" + totalQ); rgOptions.clearCheck();
}

private void startQuiz() { currentQ = 0;
totalPoints = 0; quizActive = true;

tvWelcome.setVisibility(View.GONE); btnStart.setVisibility(View.GONE);
tvTimer.setVisibility(View.VISIBLE); quizContent.setVisibility(View.VISIBLE);
 
startTimer(); updateCurrentQuestion();
}

private void updateCurrentQuestion() { String[] q = questions[currentQ];
tvQuestion.setText((currentQ + 1) + ". " + q[0]); rb1.setText(q[1]);
rb2.setText(q[2]);
rb3.setText(q[3]);
rb4.setText(q[4]);
tvProgress.setText("Q " + (currentQ + 1) + "/" + totalQ); rgOptions.clearCheck();
}

private void startTimer() {

tvTimer.setText(String.valueOf(QUESTION_TIME_SEC)); tvTimer.setTextColor(0xFF10B981);
startTimeMillis = System.currentTimeMillis(); if (timer != null) timer.cancel();
timer = new CountDownTimer(QUESTION_TIME_SEC * 1000L, 1000) { @Override
public void onTick(long millisLeft) { int sec = (int) (millisLeft / 1000);
tvTimer.setText(String.valueOf(sec)); tvTimer.setTextColor(sec <= 5 ? 0xFFFF0000 : 0xFF10B981);
}

@Override
public void onFinish() { tvTimer.setText("TIME UP!"); tvTimer.setTextColor(0xFFFF0000); handleAnswerTimeout();
}
}.start();
}

private void handleAnswerTimeout() { if (!quizActive) return; calculatePoints(0, false);
Toast.makeText(this, "⏰ Time up! +0 pts", Toast.LENGTH_SHORT).show();
goToNextOrFinish();
}

private void nextQuestion() { if (!quizActive) return;
if (timer != null) timer.cancel();

int selectedId = rgOptions.getCheckedRadioButtonId(); boolean correct = false;

if (selectedId != -1) {
RadioButton selected = findViewById(selectedId);

int answerIndex = (selected == rb1) ? 0 :
(selected == rb2) ? 1 :
 
(selected == rb3) ? 2 : 3;

int correctIndex = Integer.parseInt(questions[currentQ][5]) - 1; correct = (answerIndex == correctIndex);
}

long timeTaken = (System.currentTimeMillis() - startTimeMillis) / 1000; int secondsLeft = QUESTION_TIME_SEC - (int) timeTaken;
if (secondsLeft < 0) secondsLeft = 0;

calculatePoints(secondsLeft, correct);

if (correct) {
Toast.makeText(this, "✅ Correct! +" + questionPoints + " pts", Toast.LENGTH_SHORT).show();
} else {
Toast.makeText(this, "❌ Wrong! +" + questionPoints + " pts", Toast.LENGTH_SHORT).show();
}

goToNextOrFinish();
}

private void goToNextOrFinish() { currentQ++;
if (currentQ < totalQ) { startTimer(); updateCurrentQuestion();
} else {
showLeaderboard();
}
}

private void calculatePoints(int secondsLeft, boolean correct) { if (!correct) {
questionPoints = 0;
} else if (secondsLeft >= 30) { questionPoints = 10;
} else if (secondsLeft >= 20) { questionPoints = 8;
} else if (secondsLeft >= 10) { questionPoints = 5;
} else {
questionPoints = 2;
}
totalPoints += questionPoints;
}

private void showLeaderboard() { quizActive = false;
if (timer != null) timer.cancel();

quizContent.setVisibility(View.GONE); pointsTable.setVisibility(View.VISIBLE); tvTotalScore.setText("TOTAL: " + totalPoints + " points");

String rank;
if (totalPoints >= 25) {
rank = " #1 KIIT RANK!";
} else if (totalPoints >= 18) { rank = " #2 RANK!";
} else if (totalPoints >= 12) { rank = " #3 RANK!";
 
} else {
rank = " TOP 10!";
}

Toast.makeText(this, " " + rank, Toast.LENGTH_LONG).show();
}

private void resetQuiz() { if (timer != null) {
timer.cancel(); timer = null;
}
quizActive = false; currentQ = 0;
totalPoints = 0;

tvTimer.setVisibility(View.VISIBLE); quizContent.setVisibility(View.GONE); pointsTable.setVisibility(View.GONE); tvWelcome.setVisibility(View.VISIBLE); btnStart.setVisibility(View.VISIBLE);

tvTimer.setText(String.valueOf(QUESTION_TIME_SEC)); tvTimer.setTextColor(0xFF10B981);

preloadFirstQuestion();
}
}
/////////////////////////////
1.	ACTIVITY MAIN.XML CODE
/////////////////////////////
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent" android:layout_height="match_parent" android:background="#0F0F23">

<LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="vertical" android:padding="25dp" android:gravity="center">

<!-- KIIT Logo -->
<ImageView android:id="@+id/ivKiitLogo" android:layout_width="120dp" android:layout_height="120dp" android:src="@drawable/kiit_logo" android:layout_marginBottom="20dp"
android:background="@drawable/header_circle" />

<!-- Title -->
<TextView android:layout_width="match_parent" android:layout_height="wrap_content"
 
android:text="QUIZ\nMASTER" android:textSize="28sp" android:textColor="#E879F9" android:textStyle="bold" android:gravity="center" android:lineSpacingExtra="5dp" android:layout_marginBottom="10dp" />

<!-- Timer HAMESHA SCREEN PE -->
<TextView android:id="@+id/tvTimer" android:layout_width="90dp" android:layout_height="90dp" android:text="40" android:textSize="26sp" android:textColor="#10B981" android:textStyle="bold" android:gravity="center"
android:background="@drawable/timer_circle" android:layout_marginBottom="20dp" />

<!-- Welcome Text -->
<TextView android:id="@+id/tvWelcome" android:layout_width="match_parent" android:layout_height="wrap_content"
android:text="Welcome to KIIT Quiz Challenge!\nTest your knowledge and compete for top ranks!" android:textSize="16sp"
android:textColor="#9CA3AF" android:gravity="center" android:layout_marginBottom="40dp" />

<!-- START Button -->
<Button
android:id="@+id/btnStart" android:layout_width="match_parent" android:layout_height="65dp"
android:text="	START QUIZ (40s per question)" android:textSize="18sp" android:textColor="#FFFFFF" android:textStyle="bold" android:background="@drawable/start_btn" android:layout_marginBottom="30dp" />

<!-- Quiz Content -->
<LinearLayout android:id="@+id/quizContent" android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="vertical" android:visibility="gone">
 
<!-- Question -->
<TextView android:id="@+id/tvQuestion" android:layout_width="match_parent" android:layout_height="wrap_content" android:text="Q1. Question goes here" android:textSize="18sp" android:textColor="#FFFFFF" android:lineSpacingExtra="6dp"
android:background="@drawable/question_bg" android:padding="25dp" android:layout_marginBottom="25dp" />

<!-- Progress -->
<TextView android:id="@+id/tvProgress" android:layout_width="match_parent" android:layout_height="wrap_content" android:text="Q 1/3" android:textSize="16sp" android:textColor="#9CA3AF" android:gravity="center"
android:layout_marginBottom="25dp" />

<!-- Options -->
<RadioGroup android:id="@+id/rgOptions" android:layout_width="match_parent" android:layout_height="wrap_content">

<RadioButton android:id="@+id/rb1"
android:layout_width="match_parent" android:layout_height="wrap_content" android:minHeight="55dp" android:textSize="16sp" android:textColor="#FFFFFF" android:buttonTint="#E879F9" android:background="@drawable/option_bg" android:paddingLeft="20dp" android:paddingRight="20dp" android:paddingTop="12dp" android:paddingBottom="12dp" android:gravity="center_vertical" android:layout_marginBottom="12dp" />

<RadioButton android:id="@+id/rb2"
android:layout_width="match_parent" android:layout_height="wrap_content"
 
android:minHeight="55dp" android:textSize="16sp" android:textColor="#FFFFFF" android:buttonTint="#E879F9" android:background="@drawable/option_bg" android:paddingLeft="20dp" android:paddingRight="20dp" android:paddingTop="12dp" android:paddingBottom="12dp" android:gravity="center_vertical" android:layout_marginBottom="12dp" />

<RadioButton android:id="@+id/rb3"
android:layout_width="match_parent" android:layout_height="wrap_content" android:minHeight="55dp" android:textSize="16sp" android:textColor="#FFFFFF" android:buttonTint="#E879F9" android:background="@drawable/option_bg" android:paddingLeft="20dp" android:paddingRight="20dp" android:paddingTop="12dp" android:paddingBottom="12dp" android:gravity="center_vertical" android:layout_marginBottom="12dp" />

<RadioButton android:id="@+id/rb4"
android:layout_width="match_parent" android:layout_height="wrap_content" android:minHeight="55dp" android:textSize="16sp" android:textColor="#FFFFFF" android:buttonTint="#E879F9" android:background="@drawable/option_bg" android:paddingLeft="20dp" android:paddingRight="20dp" android:paddingTop="12dp" android:paddingBottom="12dp" android:gravity="center_vertical" />
</RadioGroup>

<!-- Next Button -->
<Button
android:id="@+id/btnNext" android:layout_width="match_parent" android:layout_height="55dp" android:text="NEXT " android:textSize="16sp"
 
android:textColor="#FFFFFF" android:textStyle="bold" android:background="@drawable/next_btn" android:layout_marginTop="20dp" />
</LinearLayout>

<!-- Points Table -->
<LinearLayout android:id="@+id/pointsTable" android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="vertical" android:layout_marginTop="20dp" android:visibility="gone">

<TextView android:layout_width="match_parent" android:layout_height="wrap_content" android:text="	KIIT POINTS TABLE" android:textSize="22sp" android:textColor="#E879F9" android:textStyle="bold" android:gravity="center" android:layout_marginBottom="20dp" />

<TextView android:id="@+id/tvPoints"
android:layout_width="match_parent" android:layout_height="wrap_content"
android:text="30–40s: 10pts | 20–30s: 8pts | 10–20s: 5pts | 0–10s: 2pts" android:textSize="14sp"
android:textColor="#9CA3AF" android:gravity="center" android:layout_marginBottom="25dp" />

<TextView android:id="@+id/tvTotalScore" android:layout_width="match_parent" android:layout_height="wrap_content" android:text="TOTAL: 0 points" android:textSize="28sp" android:textColor="#10B981" android:textStyle="bold" android:gravity="center" android:layout_marginBottom="15dp" />

<Button
android:id="@+id/btnRestart" android:layout_width="match_parent" android:layout_height="55dp" android:text="	PLAY AGAIN"
 
android:textSize="16sp" android:textColor="#FFFFFF" android:background="@drawable/start_btn" />
</LinearLayout>
</LinearLayout>
</ScrollView>
