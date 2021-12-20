package com.dev334.blood.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.dev334.blood.R;
import com.dev334.blood.database.TinyDB;
import com.dev334.blood.databinding.FragmentRequestBinding;
import com.dev334.blood.model.ApiResponse;
import com.dev334.blood.model.Blood;
import com.dev334.blood.util.retrofit.ApiClient;
import com.dev334.blood.util.retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestFragment extends Fragment {

    public static RequestFragment fragment=null;
    private View view;
    private String TAG="RequestFragment";
    private FragmentRequestBinding binding;
    private String blood,quantity,info;
    private Blood mBlood;
    private TinyDB tinyDB;

    public RequestFragment() {
        // Required empty public constructor
    }

    public static RequestFragment newInstance() {
        if(fragment==null) {
            fragment = new RequestFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentRequestBinding.inflate(getLayoutInflater());

        mBlood = new Blood();
        tinyDB=new TinyDB(getContext());

        disableAllButton();

        binding.button1.setOnClickListener(v->{
            blood=binding.button1.getText().toString();
            disableAllButton();
            binding.button1.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button2.setOnClickListener(v->{
            blood=binding.button2.getText().toString();
            disableAllButton();
            binding.button2.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button3.setOnClickListener(v->{
            blood=binding.button3.getText().toString();
            disableAllButton();
            binding.button3.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button4.setOnClickListener(v->{
            blood=binding.button4.getText().toString();
            disableAllButton();
            binding.button4.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button5.setOnClickListener(v->{
            blood=binding.button5.getText().toString();
            disableAllButton();
            binding.button5.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button6.setOnClickListener(v->{
            blood=binding.button6.getText().toString();
            disableAllButton();
            binding.button6.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button7.setOnClickListener(v->{
            blood=binding.button7.getText().toString();
            disableAllButton();
            binding.button7.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });

        binding.button8.setOnClickListener(v->{
            blood=binding.button8.getText().toString();
            disableAllButton();
            binding.button8.setBackground(getResources().getDrawable(R.drawable.primary_color_filled));
        });


        binding.buttonLocationNext.setOnClickListener(v->{

            if(binding.buttonLocationNext.getText().toString().equals("Done")){
                reqBlood();
                binding.buttonLocationNext.setText("Next");
                return;
            }

            info=binding.editInformation.getText().toString();
            quantity=binding.EditQuantity.getText().toString();

            if(quantity.isEmpty()){
                binding.EditQuantity.setError("Enter quantity");
            }else if(blood.isEmpty()){
                Toast.makeText(getContext(), "Select a blood type", Toast.LENGTH_SHORT).show();
            }else{
                //open location
                mBlood.setBlood(blood);
                mBlood.setQuantity(Integer.parseInt(quantity));
                mBlood.setInfo(info);
                mBlood.setLocation("Lucknow");
                mBlood.setUser("61bd9323f074c24a7140da57");
                ((HomeActivity)getActivity()).openMapActivity();

            }

        });


        return binding.getRoot();
    }

    private void disableAllButton() {
        binding.button1.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button2.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button3.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button4.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button5.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button6.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button7.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));
        binding.button8.setBackground(getResources().getDrawable(R.drawable.primary_grey_filled));

    }

    private void reqBlood() {
      Call<ApiResponse> call= ApiClient.getApiClient(getContext()).create(ApiInterface.class).reqBlood(mBlood);
      call.enqueue(new Callback<ApiResponse>() {
          @Override
          public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
              if(!response.isSuccessful()){
                    Log.i(TAG, "onResponse: "+response.code());
                    Log.i(TAG, "onResponse: "+response.toString());
                    Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i(TAG, "onResponse: "+response.body());
                if(response.body().getStatus()==200){
                    Log.i(TAG, "onResponse: Successful");
                    Toast.makeText(getContext(), "Blood requested", Toast.LENGTH_SHORT).show();
                }
          }

          @Override
          public void onFailure(Call<ApiResponse> call, Throwable t) {
              Log.i(TAG, "onFailure: "+t.getMessage());
          }
      });
    }

    @Override
    public void onResume() {
        super.onResume();
        mBlood.setLatitude(tinyDB.getDouble("Latitude"));
        mBlood.setLongitude(tinyDB.getDouble("Longitude"));
        tinyDB.putDouble("Latitude", 0);
        tinyDB.putDouble("Longitude", 0);
        if(mBlood.getLatitude()!=0){
            binding.buttonLocationNext.setText("Done");
        }
    }
}