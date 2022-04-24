package com.example.cardock.ui.sellingProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardock.R;
import com.example.cardock.backend.car;
import com.example.cardock.carRegister;
import com.example.cardock.databinding.FragmentSellingProfileBinding;
import com.example.cardock.landingPage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
public class SellingProfileFragment extends Fragment {
    private FloatingActionButton floattingButton ;
    private FragmentSellingProfileBinding binding;
    public static String logins;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SellingProfileViewModel sellingProfileViewModel =
                new ViewModelProvider(this).get(SellingProfileViewModel.class);

        binding = FragmentSellingProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        logins = landingPage.logins;

        final TextView textView = binding.textView;
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
        if (car.registeredCarsArray.containsKey(logins)) {
            car carThis = car.registeredCarsArray.get(logins);
            String[] carImage = carThis.getCarImageArray();
            ImageView carView = root.findViewById(R.id.carview);
            ImageView carView1 = root.findViewById(R.id.carview1);
            ImageView carView2 = root.findViewById(R.id.carview2);
            ImageView carView3 = root.findViewById(R.id.carview3);
            carView.setImageURI(Uri.parse(carImage[0]));
            carView1.setImageURI(Uri.parse(carImage[1]));
            carView2.setImageURI(Uri.parse(carImage[2]));
            carView3.setImageURI(Uri.parse(carImage[3]));

            TextView model = root.findViewById(R.id.modelView);
            TextView year = root.findViewById(R.id.year);
            TextView fuel = root.findViewById(R.id.fueltype);
            TextView millage = root.findViewById(R.id.millage);
            TextView price = root.findViewById(R.id.price);
            TextView finance = root.findViewById(R.id.finance);
            TextView description = root.findViewById(R.id.description);

            model.setText("Model : "+carThis.getModel());
            year.setText("Year : "+carThis.getYear());
            fuel.setText("Fuel Type : " +carThis.getFuelType() );
            millage.setText("Millage up to now : "+carThis.getMillage()+"km");
            price.setText("Price : Rs: "+carThis.getPrice()+"/=");
            finance.setText((carThis.getFinance().equals("yes")) ? ("Car Financed"):("Car Not Financed"));
            description.setText("Description : " +carThis.getDescription());

        }
        HorizontalScrollView carImagesScroll = root.findViewById(R.id.horizontalScrollView);
        carImagesScroll.fullScroll(View.FOCUS_RIGHT);

        ((landingPage) getActivity()).setActionBarTitle("Seller's View");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}