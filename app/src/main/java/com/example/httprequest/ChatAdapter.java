package com.example.httprequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatMessage> chatMessages;

    public ChatAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        ChatMessage chatMessage = chatMessages.get(viewType); // Get the chat message at this position

        // Inflate different layouts based on the sender of the chat message
        if (chatMessage.getSender().equals("You")) {
            Toaster.show(parent.getContext(), "ME");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sender, parent, false);
        } else if (chatMessage.getSender().equals("Chatbot")) {
            Toaster.show(parent.getContext(), "chat");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_receiver, parent, false);
        } else {
            // Default to the original layout if the sender is neither "You" nor "ChatBot"
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
            Toaster.show(parent.getContext(), "else");
        }

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        holder.textViewSender.setText(chatMessage.getSender());
        holder.textViewMessage.setText(chatMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position; // Return the position as the view type, assuming it's unique for each message
    }

    public void addMessage(ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
        notifyItemInserted(chatMessages.size() - 1);
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSender;
        TextView textViewMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSender = itemView.findViewById(R.id.textViewSender);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
        }
    }
}
