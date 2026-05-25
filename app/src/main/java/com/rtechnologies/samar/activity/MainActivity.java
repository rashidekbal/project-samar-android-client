package com.rtechnologies.samar.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.rtechnologies.samar.R;
import com.rtechnologies.samar.databinding.ActivityMainBinding;
import com.rtechnologies.samar.fragments.Fragment_chat;
import com.rtechnologies.samar.fragments.Fragment_drawer;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding viewBinding;
    FragmentManager fragmentManager;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewBinding=ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(viewBinding.getRoot());

        init();
        setupChatScreen();

    }
    private void init(){
        fragmentManager=this.getSupportFragmentManager();
    }
    public void setUpDrawerToggleWithToolBar(Toolbar toolbar){
        drawerToggle=new ActionBarDrawerToggle(this,viewBinding.main,toolbar,R.string.open_drawer,R.string.close_drawer);
        viewBinding.main.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
    private void setupChatScreen(){
        fragmentManager.beginTransaction().replace(viewBinding.mainContainer.getId(),new Fragment_chat())
                .commit();
        fragmentManager.beginTransaction().replace(viewBinding.sideBar.getId(),new Fragment_drawer()).commit();
    }
}