package com.example.tester.ui.home;

import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.tester.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView product_list;
    List<ProductInformation> productInformation;
    FirebaseDatabase firebaseDatabase;
    private StorageReference mStorageRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        product_list = root.findViewById(R.id.recyclerViewProduct);
        //final TextView textView = root.findViewById(R.id.text_home);

       productInformation = new ArrayList<>();
      DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");
        mStorageRef = FirebaseStorage.getInstance().getReference();
//        String id  = reference.push().getKey();
//        ProductInformation productInformations = new ProductInformation("https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fsunrise&psig=AOvVaw2aFf_7pGKHBn-e3OUe4JMR&ust=1585713697378000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPjP0v_pw-gCFQAAAAAdAAAAABAD", "Dummy","Dummy Value");
//        reference.child(id).setValue(productInformations);
//        reference.setValue("GDVDFVDFFDvfd");
//        System.out.println(reference);
//        System.out.println(reference.child("Posts"));


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                System.out.println(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        System.out.println("TTTTTTT");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println(dataSnapshot);
               productInformation.clear();

                for (DataSnapshot tutorSnapshot : dataSnapshot.getChildren()) {

                    ProductInformation user = tutorSnapshot.getValue(ProductInformation.class);
                    System.out.println(user + "jdsnjkndsjkdn");
                    productInformation.add(user);
                    System.out.println(productInformation + "dfff");


                }
                ProductListAdapter adapter = new ProductListAdapter(getActivity(),(ArrayList<ProductInformation>) productInformation);
                product_list.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}