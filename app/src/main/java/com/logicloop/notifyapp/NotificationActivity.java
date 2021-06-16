package com.logicloop.notifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        TextView textView = findViewById(R.id.text_view_ntfcn);
        textView.setText(getString(R.string.text_view_ntfcn_text));

        Button button = findViewById(R.id.btn_back);
        button.setOnClickListener(v -> {
            Intent intent =
                    new Intent(v.getContext(), MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(v.getContext());
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(intent);
            startActivity(intent);
        });
    }
}