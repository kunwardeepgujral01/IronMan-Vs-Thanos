package com.example.avengerstictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int active=0;
    boolean activeGame=true;
    int[] position={2,2,2,2,2,2,2,2,2,};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    ImageView ironmanuser,thanosuser;

    public void viewClicked(View view) {

        //0= ironman    1=thanos  2=Empty

        ImageView imageView=(ImageView) view;
        int tagPosition=Integer.parseInt(imageView.getTag().toString());
        if(position[tagPosition]==2 && activeGame){
            position[tagPosition]=active;

            if(active==0){
                imageView.setImageResource(R.drawable.ironman);

                ironmanuser.animate().alpha((float) 0.4).setDuration(100);
                ironmanuser.getLayoutParams().height = 50;
                ironmanuser.getLayoutParams().width = 50;
                ironmanuser.requestLayout();

                thanosuser.animate().alpha(1).setDuration(100);
                thanosuser.getLayoutParams().height=190;
                thanosuser.getLayoutParams().width=190;
                thanosuser.requestLayout();
                active=1;
            }
            else{
                ironmanuser.animate().alpha(1).setDuration(100);
                ironmanuser.getLayoutParams().height=190;
                ironmanuser.getLayoutParams().width=190;
                ironmanuser.requestLayout();

                thanosuser.animate().alpha((float) 0.4).setDuration(100);
                thanosuser.getLayoutParams().height=50;
                thanosuser.getLayoutParams().width=50;
                thanosuser.requestLayout();

                imageView.setImageResource(R.drawable.thanos);
                active=0;
            }
            imageView.animate().alpha(1).setDuration(500);
            String winner="";


            //To check after every click who is winner
            for(int[] winningPosition:winningPositions){
                if(position[winningPosition[0]]!=2 && position[winningPosition[0]]==position[winningPosition[1]] && position[winningPosition[1]]==position[winningPosition[2]]){

                    activeGame=false;

                    if(position[winningPosition[0]]==0){
                        winner="Iron Man Won";
                    }
                    else{
                        winner="Thanos Won";
                    }
                    gameFinish(winner);
                }
                }

             if(isDraw() && activeGame){
                winner="Match is Drawn";
                gameFinish(winner);

            }
        }


    }

    boolean isDraw(){
        for (int item:position
             ) {
            if(item==2){
                return false;
            }
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ironmanuser=findViewById(R.id.ironmanUser);
        thanosuser=findViewById(R.id.thanosUser);

    }

    public void gameFinish(String winner){

        Button btnPlayAgain = (Button) findViewById(R.id.play_again_btn);
        btnPlayAgain.setVisibility(View.VISIBLE);
        Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
        ironmanuser.animate().alpha(0).setDuration(100);
        thanosuser.animate().alpha(0).setDuration(100);
    }

    public void playAgain(View view) {
        Button btnPlayAgain = (Button) findViewById(R.id.play_again_btn);

        btnPlayAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(i);

            imageView.animate().alpha(0).setDuration(500);
            imageView.setImageDrawable(null);

        }

        for(int i=0; i<position.length; i++){
            position[i] = 2;
        }

        activeGame = true;

        //for starting again, set top users images as before
        active = 0;
        ironmanuser.animate().alpha(1).setDuration(100);
        ironmanuser.getLayoutParams().height=190;
        ironmanuser.getLayoutParams().width=190;
        ironmanuser.requestLayout();

        thanosuser.animate().alpha(0.4f).setDuration(100);
        thanosuser.getLayoutParams().height=50;
        thanosuser.getLayoutParams().width=50;
        thanosuser.requestLayout();
    }
}