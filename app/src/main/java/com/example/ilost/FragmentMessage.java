package com.example.ilost;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilost.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentMessage extends Fragment implements MessageAdapter.MessageClickListener {


    RecyclerView recyclerView;

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    MessageAdapter messageAdapter;
    List<User> users = new ArrayList<>();


    public FragmentMessage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView = root.findViewById(R.id.message_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        loadMessage();
        messageAdapter = new MessageAdapter(getContext(), users);
        messageAdapter.setMessageClickListener(this);
        recyclerView.setAdapter(messageAdapter);

        return root;
    }


    private void loadMessage(){
        databaseReference.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("app", dataSnapshot.toString());
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    User user = dataSnapshot1.getValue(User.class);
                    users.add(user);
                    Log.d("app", users.size() + " - - -");
                }
                messageAdapter.setUsers(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onMessageClick(View view, int position) {
        User user = users.get(position);
        Log.d("app", user.getEmail());
    }

}
