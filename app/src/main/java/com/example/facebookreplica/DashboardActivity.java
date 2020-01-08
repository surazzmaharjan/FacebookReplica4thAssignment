package com.example.facebookreplica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.facebookreplica.Adapater.FbRecyclerAdapater;
import com.example.facebookreplica.Api.Facebook;
import com.example.facebookreplica.Bll.LoginBll;
import com.example.facebookreplica.Model.ApiUser;
import com.example.facebookreplica.Model.TimelineData;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CircleImageView circleImageView;
    private Retrofit retrofit;
    private Facebook facebook;
    public static String imagePath = "uploads/" ;
    RecyclerView recyclerView;
    List<TimelineData> timeline = new ArrayList<>();
    private String fname,fgender,fdate,femail,fimgpath,ftoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        circleImageView = findViewById(R.id.imgProgileImg);

        loadCurrentUser();

        recyclerView=findViewById(R.id.rvfbtimeline);

        timelineList();


        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profile = new Intent(DashboardActivity.this,ProfileActivity.class);
                profile.putExtra("name",fname);
                profile.putExtra("gender",fgender);
                profile.putExtra("date",fdate);
                profile.putExtra("email",femail);
                profile.putExtra("path",fimgpath);
                profile.putExtra("token",ftoken);
                startActivity(profile);
            }
        });

    }

    private void loadCurrentUser() {

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        facebook = retrofit.create(Facebook.class);

        Call<ApiUser> userCall = facebook.getUserDetails(LoginBll.token);

        userCall.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = imagePath +  response.body().getProfileimage();
                    fname= response.body().getFirstname()+" "+response.body().getLastname();
                    fgender = response.body().getGender();
                    fdate= response.body().getDate();
                    femail=response.body().getEmail();
                    ftoken = response.body().getToken();
                    fimgpath = imgPath;


                Picasso.with(DashboardActivity.this)
                        .load("http://10.0.2.2:4000/"+imgPath)
                        .into(circleImageView);

            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {

                Toast.makeText(DashboardActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private  void timelineList(){
        Call<List<TimelineData>> timelineList = facebook.getTimelines(LoginBll.token);
        timelineList.enqueue(new Callback<List<TimelineData>>() {
            @Override
            public void onResponse(Call<List<TimelineData>> call, Response<List<TimelineData>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<TimelineData> timList = response.body();

                for (TimelineData tim: timList) {

                    timeline.add(new TimelineData(tim.getFullname(),tim.getStatus(),tim.getTime(),tim.getTimelineimage()));

                }

                FbRecyclerAdapater adapter = new FbRecyclerAdapater(timeline,DashboardActivity.this);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(DashboardActivity.this);


                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TimelineData>> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());

            }
        });

    }
}
