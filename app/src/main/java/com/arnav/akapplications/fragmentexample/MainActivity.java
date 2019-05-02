package com.arnav.akapplications.fragmentexample;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SimpleFragment.OnFragmentInteractionListener {

    private Button actionButton;
    private boolean isFragmentDisplayed = false;

    static final String StateOfFragement = "State of Fragment";

    private int mRadioButtonChoice = 2;
    public String[] songsValuesArray = {"Cry for a Shadow","My Bonnie-Ain't she sweet","Searching","Love Me Do","She Loves You","Leave Me Kitten Alone","One After 909"};
    public int item_count = songsValuesArray.length;

    public SongRecyclerView songRecyclerView;
    public RecyclerView songView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songView = findViewById(R.id.songNameRecyclerView);
        songRecyclerView = new SongRecyclerView(songsValuesArray,this,item_count);
        songView.setAdapter(songRecyclerView);
        songView.setLayoutManager(new LinearLayoutManager(this));

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
                    removeFragment();
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
        SimpleFragment simpleFragment = SimpleFragment.newInstance(mRadioButtonChoice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment,simpleFragment).addToBackStack(null).commit();

        actionButton.setText(R.string.close);

        isFragmentDisplayed = true;
    }

    public void removeFragment()
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

    @Override
    public void onRadioButtonChoice(int choice) {

        mRadioButtonChoice = choice;
        String radioChoice = Integer.toString((mRadioButtonChoice));
        String userChoice = "My choice :" + " " + radioChoice;

        Toast.makeText(this,userChoice,Toast.LENGTH_LONG).show();

    }
}
