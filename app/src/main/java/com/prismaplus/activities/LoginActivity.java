package com.prismaplus.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prismaplus.R;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.loginUser)
    Button loginUser;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    public  LoginActivity(){

    }

    //ConnectionInterface connetionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        //connetionService = ConnetionService.obtenerServicio();
    }


    @OnClick(R.id.loginUser)
    public void login(){
        Toast.makeText(getApplicationContext(), username.getText().toString(), Toast.LENGTH_LONG).show();
        Log.d("usuario", password.getText().toString());
        /*connetionService.eliminarAveriaPorId(f).enqueue(new Callback<Failure>() {
            @Override
            public void onResponse(Call<Failure> call, Response<Failure> response) {

            }

            @Override
            public void onFailure(Call<Failure> call, Throwable t) {

            }
        });*/
    }

}
