package com.arnav.akapplications.fragmentexample;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button actionButton;
    private boolean isFragmentDisplayed = false;

    static final String StateOfFragement = "State of Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = findViewById(R.id.actionButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFragmentDisplayed)
                {
                    displayFragment();
                }
                else
                {
                    removeFragement();
                }
            }
        });

        if(savedInstanceState != null)
        {
            isFragmentDisplayed = savedInstanceState.getBoolean(StateOfFragement);

            if( isFragmentDisplayed)
            {
                actionButton.setText(R.string.close);
            }
        }
    }

    public void displayFragment()
    {
        SimpleFragment simpleFragment = SimpleFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment,simpleFragment).addToBackStack(null).commit();

        actionButton.setText(R.string.close);

        isFragmentDisplayed = true;
    }

    public void removeFragement()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        SimpleFragment simpleFragment = (SimpleFragment)fragmentManager.findFragmentById(R.id.fragment);

        if(simpleFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }

        actionButton.setText(R.string.open);

        isFragmentDisplayed = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putBoolean(StateOfFragement,isFragmentDisplayed);
        super.onSaveInstanceState(outState, outPersistentState);


    }
}
