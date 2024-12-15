
package com.rafid.rect_connect;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends BaseActivity {

    private EditText inputEditText;
    private TextView chatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        inputEditText = findViewById(R.id.input);
        chatTextView = findViewById(R.id.chat);

        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String input = inputEditText.getText().toString();
                    chatTextView.append("\n" + input);
                    inputEditText.setText("");
                    return true;
                }
                return false;
            }
        });
        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputEditText.getText().toString();
                chatTextView.append("\n" + input);
                inputEditText.setText("");
            }
        });

    }
}
