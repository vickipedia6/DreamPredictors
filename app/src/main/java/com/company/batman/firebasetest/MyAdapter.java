package com.company.batman.firebasetest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Batman on 4/2/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    Context context;
    ArrayList<ImageTask> tasks;
    int mExpandedPosition=-1;
    public MyAdapter(ArrayList<ImageTask> tasks, Context context) {

        this.context = context;
        this.tasks=tasks;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.card,parent,false);


        return new Holder(view);



    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
       TextView t1,t2;
       ImageView imageView;


        public Holder(View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.descr);
            t2=itemView.findViewById(R.id.desc);
            imageView=(ImageView)itemView.findViewById(R.id.screenshot);



        }

        public void bind(final int position) {

            final boolean isExpanded = position==mExpandedPosition;
            t1.setText(tasks.get(position).getTitle());
            t2.setText(tasks.get(position).getDesc());
            t2.setVisibility(isExpanded?View.VISIBLE:View.GONE);

            Picasso.get().load(tasks.get(position).getUrl()).into(imageView);
            imageView.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            itemView.setActivated(isExpanded);
            itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                mExpandedPosition = isExpanded ? -1:position;
                                                notifyItemChanged(position);
                                            }
                                        }
            );
        }
    }
}
