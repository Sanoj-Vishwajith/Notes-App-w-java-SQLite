package com.example.todolist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Database.Note;
import com.example.todolist.R;
import com.example.todolist.ViewNoteActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Note> NotesData;
    private Context context;
    private ExecutorService executorService;

    public RecyclerAdapter(Context context){
        this.context=context;
    }

    public  void  setData(List<Note> data){
        this.NotesData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.note_item_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(NotesData.get(position).getTitle());
        holder.txtDate.setText(NotesData.get(position).getDate());

        //View the note in new activity
        holder.cvCard.setOnClickListener(v->{
            Intent intent = new Intent(context, ViewNoteActivity.class);
            intent.putExtra("title",NotesData.get(position).getTitle());
            intent.putExtra("body",NotesData.get(position).getBody());
            intent.putExtra("date",NotesData.get(position).getDate());
            intent.putExtra("id",NotesData.get(position).getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return NotesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtDate, txtTitle;
        private CardView cvCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDate=itemView.findViewById(R.id.date);
            txtTitle=itemView.findViewById(R.id.Title);
            cvCard = itemView.findViewById(R.id.noteCard);
        }
    }
}
