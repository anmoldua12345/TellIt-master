package com.example.makroid.tellit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TITLE = "Tell It";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    ViewPagerAdapter adapter;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.navView)
    NavigationView navView;

    //Navigation views

    ImageView imageView;
    TextView textView;

    // Variables
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initViewPager();
        setRoundImage();
        navView.setNavigationItemSelectedListener(this);
        navView.getMenu().getItem(0).setChecked(true);
    }

    private void setRoundImage() {
        View view = navView.getHeaderView(0);
        imageView = (ImageView) view.findViewById(R.id.profile_image);
        textView = (TextView) view.findViewById(R.id.username);
        textView.setText("Jackson Parker");
        Picasso.with(this).load(R.drawable.image5).transform(new CircularImage()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    private void initViewPager() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecentStoryFragment().newInstance("hello"), "Recent Stories");
        adapter.addFragment(new RecentStoryFragment().newInstance("hello"), "Top Stories");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(TITLE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_hamburger);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int state = 0;
        switch (item.getItemId()) {
            case R.id.recent_fragment:
                state = 0;
                break;
            case R.id.top_fragment:
                state = 1;
                break;
            case R.id.log_fragment:
            //    startActivity(new Intent(MainActivity.this, LogInActivity.class));
           //     state = 2;
           //     break;
            case R.id.setting_fragment:
                state = 3;
                break;
            case R.id.share_fragment:
                state = 4;
                break;
            case R.id.rate_fragment:
                state = 5;
                break;
            case R.id.feedback_fragment:
                state = 6;
                break;
        }
        Toast.makeText(MainActivity.this, state + " clicked ", Toast.LENGTH_SHORT).show();
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Picasso.with(this).load(uri).transform(new CircularImage()).into(imageView);
                // Log.d(TAG, String.valueOf(bitmap));
        }
    }
}
