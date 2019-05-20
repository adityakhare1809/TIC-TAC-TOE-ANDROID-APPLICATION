package com.aditya.myfirst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] [] buttons=new Button[3][3];
    private boolean player1 = true;
    private int count;

    private int player1points;
    private int player2points;

    private TextView textViewplayer1;
    private TextView textViewplayer2;
    //private String defType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewplayer1= (TextView)findViewById(R.id.text_view_p1);
        textViewplayer2= (TextView) findViewById(R.id.text_view_p2);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonId="button_"+i+j;
                int resid=getResources().getIdentifier(buttonId,"id",getPackageName());
                buttons[i][j]= (Button) findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset= (Button) findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                resetgame();

            }
        });


    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals(""))
        {
            return;
        }
        if(player1)
        {
            ((Button)v).setText("X");
        }
        else
        {
            ((Button)v).setText("O");
        }
        count++;

        if(checkwin())
        {
            if(player1)
            {
                player1wins();
            }
            else
            {
                player2wins();
            }

        } else if(count==9)
        {
            draw();
        }
        else
        {
            player1=!player1;
        }
    }

    public boolean checkwin()
    {
        String [][] field=new String[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1])
                    &&field[i][0].equals(field[i][2])
                    &&!field[i][0].equals(""))
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i])
                    &&field[0][i].equals(field[2][i])
                    &&!field[0][i].equals(""))
            {
                return true;
            }
        }
            if(field[0][0].equals(field[1][1])
                    &&field[0][0].equals(field[2][2])
                    &&!field[0][0].equals(""))
            {
                return true;
            }

            if(field[0][2].equals(field[1][1])
                    &&field[0][2].equals(field[2][0])
                    &&!field[0][2].equals(""))
            {
                return true;
            }
            return false;

    }

    private void player1wins()
    {
        player1points++;
        Toast.makeText(this,"PLAYER 1 WINS",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetboard();

    }

    private void player2wins()
    {
        player2points++;
        Toast.makeText(this,"PLAYER 2 WINS",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetboard();

    }

    private void draw()
    {
        //player1points++;
        //player2points++;
        Toast.makeText(this,"MATCH DRAW",Toast.LENGTH_SHORT).show();
        //updatePointsText();
        resetboard();

    }

    private void updatePointsText()
    {
        textViewplayer1.setText("PLAYER 1 : "+player1points);
        textViewplayer2.setText("PLAYER 2 : "+player2points);
    }

    private void resetboard()
    {
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        count=0;
        player1=true;
    }

    private void resetgame()
    {
        player1points=0;
        player2points=0;
        updatePointsText();
        resetboard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count",count);
        outState.putInt("player1points",player1points);
        outState.putInt("player2points",player2points);
        outState.putBoolean("player1",!player1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        count=savedInstanceState.getInt("count");
        player1points=savedInstanceState.getInt("player1points");
        player2points=savedInstanceState.getInt("player2points");
        player1=savedInstanceState.getBoolean("player");

    }
}
