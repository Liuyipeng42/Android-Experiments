package guet.experiment.code02_1;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String COUNT_VALUE = "count_value";
    private int count = 0;
    TextView tvCount;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowToast = findViewById(R.id.btnShowToast);
        btnShowToast.setOnClickListener(
                view -> Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show()
        );

        tvCount = findViewById(R.id.tvCount);

        Button btnCount = findViewById(R.id.btnCount);
        btnCount.setOnClickListener(
                view -> tvCount.setText(Integer.toString(++count))
        );

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_VALUE, count);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt(COUNT_VALUE);

        if (tvCount != null) {
            tvCount.setText(Integer.toString(count));
        }
    }
}