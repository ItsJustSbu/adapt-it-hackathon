package com.example.httprequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private EditText editTextMessage;
    private ImageView buttonSend;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        List<ChatMessage> chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages);
        recyclerViewChat.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerViewChat.setLayoutManager(layoutManager);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String userMessage = editTextMessage.getText().toString().trim();
        if (!userMessage.isEmpty()) {
            // Add the user's message to the RecyclerView
            addMessageToChat("You", userMessage);

            // TODO: Implement chatbot communication and get the response
            // For now, we'll use a placeholder response
            String responseMessage = "This is a response from the chatbot.";

            // Add the chatbot's response to the RecyclerView
            addMessageToChat("Chatbot", responseMessage);

            // Clear the input field
            editTextMessage.getText().clear();
        }
    }

    private void addMessageToChat(String sender, String message) {
        ChatMessage chatMessage = new ChatMessage(sender, message);
        chatAdapter.addMessage(chatMessage);
        scrollToBottom();
    }

    private void scrollToBottom() {
        recyclerViewChat.scrollToPosition(chatAdapter.getItemCount() - 1);
    }
}
