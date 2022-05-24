package guet.experiment.code04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE_STRING = "com.experiment.code04.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etMessage = findViewById(R.id.message);
        Button btSend = findViewById(R.id.send_message);

        btSend.setOnClickListener(view -> {
            String message = etMessage.getText().toString();

            Intent intent = new Intent(MainActivity.this, MessageActivity.class);
            intent.putExtra(MESSAGE_STRING, message);
            startActivity(intent);
        });
    }
}