package guet.experiment.code02;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowToast = findViewById(R.id.btnShowToast);
        btnShowToast.setOnClickListener(
                view -> Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_SHORT).show()
        );

        TextView tvCount = findViewById(R.id.tvCount);

        Button btnCount = findViewById(R.id.btnCount);
        btnCount.setOnClickListener(
                view -> tvCount.setText(Integer.toString(++count))
        );

    }
}