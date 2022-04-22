package com.example.cardock.ui.sellingProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardock.R;
import com.example.cardock.carRegister;
import com.example.cardock.databinding.FragmentSellingProfileBinding;
import com.example.cardock.landingPage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class SellingProfileFragment extends Fragment {
    private FloatingActionButton floattingButton ;
    private FragmentSellingProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SellingProfileViewModel sellingProfileViewModel =
                new ViewModelProvider(this).get(SellingProfileViewModel.class);

        binding = FragmentSellingProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        sellingProfileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        floattingButton = (FloatingActionButton) ( root.findViewById(R.id.floatingAddActionButton));
        floattingButton.setImageResource(R.drawable.add);
        floattingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "add new ad", Toast.LENGTH_SHORT).show();
                Intent lunch = new Intent(getActivity(), carRegister.class);
                startActivity(lunch);
//                Navigation.findNavController(view).navigate(R.id.action_sellingProfile_to_carRegister);
            }
        });

        ((landingPage) getActivity()).setActionBarTitle("Seller's View");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}