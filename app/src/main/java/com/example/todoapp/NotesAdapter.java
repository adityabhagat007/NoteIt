package com.example.todoapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NotesAdapter extends FirestoreRecyclerAdapter<Notes, NotesAdapter.NoteViewHolder> {

    Context context;

    public NotesAdapter(@NonNull FirestoreRecyclerOptions<Notes> options, Context context) {
        super(options);
        this.context = context;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Notes model) {
        holder.titleTextView.setText(model.title);
        holder.contentTextView.setText(model.content);
        holder.timestampTextView.setText(Utility.timestampToString(model.timestamp));
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView,contentTextView,timestampTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title);
            contentTextView = itemView.findViewById(R.id.note_content);
            timestampTextView = itemView.findViewById(R.id.note_date);
        }
    }
}
