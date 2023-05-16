package com.example.fragmentassigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivHome, ivAdd, ivProfile;
    private ProfileFragment profileFragment = new ProfileFragment();
    private UploadFragment uploadFragment = new UploadFragment();
    private HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivHome = findViewById(R.id.iv_home);
        ivAdd = findViewById(R.id.iv_add);
        ivProfile = findViewById(R.id.iv_profile);

        ivHome.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        ivProfile.setOnClickListener(this);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof HomeFragment)) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameContainer, homeFragment,
                            HomeFragment.class.getSimpleName())
                    .commit();
        }

        getSupportActionBar().setTitle("Instaglamor");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                Bundle bundle = getIntent().getExtras();
                homeFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, homeFragment).commit();
                getSupportActionBar().setTitle("Instaglamor");
                break;
            case R.id.iv_add:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, uploadFragment).commit();
                getSupportActionBar().setTitle("Upload");
                break;
            case R.id.iv_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, profileFragment).commit();
                getSupportActionBar().setTitle("Profile");
                break;
        }
    }
}
