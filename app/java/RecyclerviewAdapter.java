package com.example.sqllite;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.viewholder> {
    TextView textinrecyclerview;
    ArrayList<String> n=new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View photoView =LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview, parent, false);
        viewholder viewHolder = new viewholder(photoView,parent);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        System.out.println("size"+n.size());
        return n.size();
    }
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        textinrecyclerview.setText(n.get(position));

    }
    public void setarraylist(ArrayList<String> n)
    {
        this.n=n;
    }
    public class viewholder extends RecyclerView.ViewHolder{
        public viewholder(@NonNull View itemView,ViewGroup parent) {
            super(itemView);
            textinrecyclerview= itemView.findViewById(R.id.textinrecyclerview);
            MainActivity deletevar=new MainActivity();
            RecyclerviewAdapter adapter=new RecyclerviewAdapter();
            DBMS abc=new DBMS(parent.getContext());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(parent.getContext(), "deleted", LENGTH_SHORT).show();

                    if(abc.addnotes("apple",n.get(getAdapterPosition())))
                        System.out.println("updated sucess");
                    else
                        System.out.println("not updated");
                    n.clear();
                    n=abc.getEveryThing();
                    notifyItemRemoved(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(parent.getContext(), "deleted", LENGTH_SHORT).show();
                    System.out.println("deleted");
                    if(abc.delete(n.get(getAdapterPosition())))
                        System.out.println("confirm");
                    System.out.println(n.size());
                    int pos=getAdapterPosition();
                    n.remove(pos);
                    notifyItemRemoved(pos);
                    //notifyItemRangeChanged(pos,n.size());
                    System.out.println(n.size());
                    Toast.makeText(parent.getContext(), "Long pressed", LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }
}
