a)	Mainacitvity.java code

package com.helloworld.calculatorkiit;
import androidx.appcompat.app.AppCompatActivity; import android.os.Bundle;
import android.widget.Button; import android.widget.TextView; import android.widget.Toast;

public class MainActivity extends AppCompatActivity { TextView display;
String currentInput = ""; String operator = "";
int firstNumber = 0;

@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
display = findViewById(R.id.display); int[] numberButtons = {
R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6,
R.id.btn7, R.id.btn8, R.id.btn9
};

for (int id : numberButtons) { Button btn = findViewById(id); btn.setOnClickListener(v -> {
currentInput += btn.getText().toString(); display.setText(currentInput);
});
}
findViewById(R.id.btnAdd).setOnClickListener(v -> setOperator("+")); findViewById(R.id.btnSub).setOnClickListener(v -> setOperator("-")); findViewById(R.id.btnMul).setOnClickListener(v -> setOperator("*")); findViewById(R.id.btnDiv).setOnClickListener(v -> setOperator("/"));
 
findViewById(R.id.btnClear).setOnClickListener(v -> { currentInput = "";
operator = ""; firstNumber = 0; display.setText("0");
});

findViewById(R.id.btnEqual).setOnClickListener(v -> calculate());
}

private void setOperator(String op) { if (currentInput.isEmpty()) return;

firstNumber = Integer.parseInt(currentInput); operator = op;
currentInput = "";
}
private void calculate() {
if (currentInput.isEmpty() || operator.isEmpty()) return;

int secondNumber = Integer.parseInt(currentInput); int result = 0;

switch (operator) { case "+":
result = firstNumber + secondNumber; break;
case "-":
result = firstNumber - secondNumber; break;
case "*":
result = firstNumber * secondNumber; break;
case "/":
if (secondNumber == 0) {
Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show(); return;
}
result = firstNumber / secondNumber; break;
}

display.setText(String.valueOf(result)); currentInput = String.valueOf(result); operator = "";
}
}
 /////////////////////////////////
b)	MainActivity.xml code
//////////////////////////////////////
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" xmlns:app="http://schemas.android.com/apk/res-auto" android:id="@+id/main" android:layout_width="match_parent" android:layout_height="match_parent" android:orientation="vertical" android:background="#1E1E2F"
android:padding="16dp">

<!-- Header -->
<LinearLayout android:layout_width="match_parent" android:layout_height="wrap_content" android:orientation="horizontal" android:gravity="center_vertical">

<ImageView android:layout_width="50dp" android:layout_height="50dp" android:src="@drawable/kiit"
android:contentDescription="KIIT Logo"/>

<LinearLayout android:layout_width="wrap_content" android:layout_height="wrap_content" android:orientation="vertical" android:layout_marginStart="12dp">

<TextView android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Developed by Shivesh Sinha" android:textColor="#FFFFFF" android:textSize="16sp" android:textStyle="bold" />

<TextView android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="2330047" android:textColor="#BBBBBB" android:textSize="14sp"/>
</LinearLayout>

</LinearLayout>

<!-- Display Screen -->
<TextView android:id="@+id/display" android:layout_width="354dp" android:layout_height="256dp" android:layout_marginTop="20dp" android:background="#2D2D44"
android:gravity="end|center_vertical" android:padding="16dp" android:text="0"
 
android:textColor="#FFD54F" android:textSize="32sp" />

<!-- Buttons Grid -->
<GridLayout android:layout_width="wrap_content" android:layout_height="wrap_content" android:layout_marginTop="20dp" android:columnCount="4">

<!-- Row 1 -->
<Button
android:id="@+id/btn7" android:text="7" />

<Button
android:id="@+id/btn8" android:text="8" />

<Button
android:id="@+id/btn9" android:text="9" />

<Button
android:id="@+id/btnDiv" android:text="÷" />

<!-- Row 2 -->
<Button
android:id="@+id/btn4" android:text="4" />

<Button
android:id="@+id/btn5" android:text="5" />

<Button
android:id="@+id/btn6" android:text="6" />

<Button
android:id="@+id/btnMul" android:text="×" />

<!-- Row 3 -->
<Button
android:id="@+id/btn1" android:text="1" />

<Button
android:id="@+id/btn2" android:text="2" />

<Button
android:id="@+id/btn3" android:text="3" />

<Button
android:id="@+id/btnSub"
 
android:text="-" />

<!-- Row 4 -->
<Button
android:id="@+id/btn0" android:text="0" />

<Button
android:id="@+id/btnClear" android:text="C" />

<Button
android:id="@+id/btnEqual" android:text="=" />

<Button
android:id="@+id/btnAdd" android:text="+" />

</GridLayout>

<ImageView android:id="@+id/imageView2" android:layout_width="match_parent" android:layout_height="wrap_content" app:srcCompat="@drawable/lovekiit" />

</LinearLayout>
