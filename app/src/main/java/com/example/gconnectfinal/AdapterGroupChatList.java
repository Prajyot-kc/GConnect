package com.example.gconnectfinal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterGroupChatList extends RecyclerView.Adapter<AdapterGroupChatList.HolderGroupChatList> {

    private Context context;
    private ArrayList<ModelGroupChatList> groupChatLists;
    private FirebaseAuth firebaseAuth;

    public AdapterGroupChatList(Context context, ArrayList<ModelGroupChatList> groupChatLists) {
        this.context = context;
        this.groupChatLists = groupChatLists;
    }

    @NonNull
    @Override
    public HolderGroupChatList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_groupchats_list, parent,false);

        return new HolderGroupChatList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderGroupChatList holder, int position) {

        ModelGroupChatList model = groupChatLists.get(position);
        final String groupId = model.getGroupId();
        String groupIcon = model.getGroupIcon();
        String groupTitle = model.getGroupTitle();
        String groupDescription = model.getGroupDescription();

        holder.groupTitleTv.setText(groupTitle);
        holder.descriptionTv.setText(groupDescription);

        try {
            Picasso.get().load(groupIcon).placeholder(R.drawable.ic_insert_photo_black_24dp).into(holder.groupIconIv);
        }
        catch (Exception e){
            holder.groupIconIv.setImageResource(R.drawable.ic_insert_photo_black_24dp);
        }

        holder.mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(context,RetrieveMapActivity.class);
                mapIntent.putExtra("groupId", groupId);
                context.startActivity(mapIntent);
            }
        });

        holder.joinTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context,GroupChatActivity.class);
                intent.putExtra("groupId", groupId);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return groupChatLists.size();
    }

    class HolderGroupChatList extends RecyclerView.ViewHolder{

        private ImageView groupIconIv;
        private TextView groupTitleTv, descriptionTv, joinTV;
        private ImageButton mapBtn;

        public HolderGroupChatList(@NonNull View itemView) {
            super(itemView);

            groupIconIv = itemView.findViewById(R.id.groupIconIv);
            groupTitleTv = itemView.findViewById(R.id.groupTitleTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            joinTV = itemView.findViewById(R.id.joinTV);
            mapBtn = itemView.findViewById(R.id.mapBtn);

        }
    }
}
