package com.example.omkar.retrapstep3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public boolean checking;
    public int s;
    public int firstmove = 0;
    public int sure;
    public static double x;
    public static double y;
    public double height;
    public double width;
    public char[] match = new char[3];
    public int[] match2 = new int[3];
    public int[] xoffset = new int[3];
    public int[] yoffset = new int[3];
    public int resets;
    public int[][] database = new int[3][100];
    public int universal;
    public int score;
    public static int p1;
    public static int p2;
    public int duration = Toast.LENGTH_SHORT;
    public String player = "P1";
    public int indices;
    public static char symb;

    public static String sym;
    String output;
    // globally
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.button03);
        //textView1.setText(sym);
        textView1.setText(getString(R.string.days, ""));

    }

    public int get1() {
        return p1;
    }

    public int get2() {
        return p2;
    }

    public void sendMessage(View view) {
        resets = 0;
        CharSequence text = "You selected 'S'!";
        symb = 'S';
        sym = "S";
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }


    public void areyousure(View view) {
        sure++;
        if (sure >= 3) {
            textView1 = (TextView) findViewById(view.getId());

            Log.e("before: ", Integer.toString(p1) + " and " + Integer.toString(p2));
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, WinScreen.class));
                }
            });
        } else {

            Toast toast = Toast.makeText(getApplicationContext(), "Press thrice to confirm endgame", duration);
            toast.show();
        }


    }


    @SuppressLint("StringFormatInvalid")
    public void setscore(View view) {
        resets = 0;

//add a case to not overwriter players!
        if (player.equals("P1")) {
            //p1+=score;score=0;
            if (Integer.toString(view.getId()).charAt((Integer.toString(view.getId())).length() - 2) == '2') {
                p2 += score;
            }

            textView1 = (TextView) findViewById(view.getId());
            String s = textView1.getText().toString();
            if (textView1.getText().charAt(7) == '1') {
                textView1.setText(getString(R.string.player1, Integer.toString(p1)));
                Toast toast = Toast.makeText(getApplicationContext(), "Score updated...", Toast.LENGTH_SHORT);
                toast.show();
            }//exploits the characterstics of the button name to update the right score

        }
        if (player.equals("P2")) {
            //p2+=score;score=0;
            if (Integer.toString(view.getId()).charAt((Integer.toString(view.getId())).length() - 2) == '1') {
                p1 += score;
            }

            //  Log.e("socre", Integer.toString(p2) + " player 2's score");
            textView1 = (TextView) findViewById(view.getId());
            String s = textView1.getText().toString();
            Log.i("tryle", textView1.toString());
            if (textView1.getText().charAt(7) == '2') {
                textView1.setText(getString(R.string.player2, Integer.toString(p2)));
                Toast toast = Toast.makeText(getApplicationContext(), "Score Updated...", Toast.LENGTH_SHORT);
                toast.show();
            }
// oh, what was fun, defiantly the sms api

        }
    }

    public void switchturn(View view) {

        if (player.equals("P1")) {
            player = "P2";
            if (score > 0) {
                p1 += score;
            }
        } else {
            player = "P1";
            if (score > 0) {
                p2 += score;
            }
        }// simple stuff, if player 1 ends his turn, it's now player 2's turn.
        Toast toast = Toast.makeText(getApplicationContext(), "TURN SWITCH.", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void recolourshit() {
        TextView t2 = textView1 = (TextView) findViewById(match2[0]);
        t2.setTextColor(Color.parseColor("#14a895"));
        TextView t3 = textView1 = (TextView) findViewById(match2[1]);
        t3.setTextColor(Color.parseColor("#14a895"));
        TextView t4 = textView1 = (TextView) findViewById(match2[2]);
        t4.setTextColor(Color.parseColor("#14a895"));
        //Creative function name. this recolors the grid button after they're matched successfully
    }


    public void sendMessage2(View view) {
        CharSequence text = "You selected 'O'!";
        symb = 'O';
        sym = "O";
//Separate functions, why not. This selects 'O' for you to place.
        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
    }

    public void resetgame(View view) {
        resets++;
        if (resets >= 1) {
            //resets all variables to their default value to prevent overlap between games
            textView1 = (TextView) findViewById(view.getId());
            p1 = 0;
            player = "P1";
            p2 = 0;
            score = 0;
            sym = "";
            symb = 'F';
            checking = false;
            s = 0;
            firstmove = 0;
            sure = 0;
            x = 0.0;
            y = 0;
            height = 0;
            width = 0.0;
            match = new char[3];
            match2 = new int[3];
            xoffset = new int[3];
            yoffset = new int[3];
            resets = 0;
            database = new int[3][100];
            universal = 0;


            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
            });

        } else {
//Simple stuff, if you trigger a reset, it loads the same activity again from scratch.
            Toast toast = Toast.makeText(getApplicationContext(), "Press 4X to confirm reset", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void showrules(View view) {
        textView1 = (TextView) findViewById(view.getId());
//Show the rules
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ruleset.class));
            }
        });
    }


    public void checkmatch(View view) {
        checking = true;

        symb = 'C';
        String text = "";
        if (indices > 3) {
            indices = 0;
        }
        //This activates a flag and makes sure to check for a match in method placesymbol
        // rly tho, matchmaking
        Toast toaster = Toast.makeText(getApplicationContext(), "Choose 3 tiles to match!", duration);
        toaster.show();

    }

    public boolean checkhorizontal() {
        boolean ok = false;
        if (xoffset[0] + 1 == xoffset[1] && xoffset[1] + 1 == xoffset[2] && xoffset[0] + 2 == xoffset[2]) {
            ok = true;
        }
        if (!ok) {
            if (xoffset[0] - 1 == xoffset[1] && xoffset[1] - 1 == xoffset[2] && xoffset[0] - 2 == xoffset[2]) {
                ok = true;
            }
        }
        if (!ok) {
            if (xoffset[0] == xoffset[1] && xoffset[1] == xoffset[2]) {
                ok = true;
            }
        }
        return ok;

    }

    //Above and below functions could really be combined into one...
// The check if a match is valid horizontally and vertically.
    public boolean checkvertical() {
        boolean ok = false;
        if (yoffset[0] + 1 == yoffset[1] && yoffset[1] + 1 == yoffset[2] && yoffset[0] + 2 == yoffset[2]) {
            ok = true;
        }
        if (!ok) {
            if (yoffset[0] - 1 == yoffset[1] && yoffset[1] - 1 == yoffset[2] && yoffset[0] - 2 == yoffset[2]) {
                ok = true;
            }
        }
        if (!ok) {
            if (yoffset[0] == yoffset[1] && yoffset[1] == yoffset[2]) {
                ok = true;
            }
        }
        return ok;

    }


    public void placesymbol(View view) {
        resets = 0;

        Log.e("height and width=", Double.toString(height) + "  " + Double.toString(width));

        String text = "Match failed.";
        if (symb == 'C') {
            //Special code to check for matches
            if (indices < 3) {
//p.s. one person 2
                textView1 = (TextView) findViewById(view.getId());
                if (textView1 == null || textView1.getText().length() <= 0) {
                    return;
                }
                match[indices] = textView1.getText().charAt(0);
                String test = "" + match[indices];
                Log.d("buttonid=", textView1.toString());
                Log.d("tag", test);
                match2[indices] = view.getId();
                String holder = "" + textView1.toString().charAt(textView1.toString().length() - 2);
                String holder2 = "" + textView1.toString().charAt(textView1.toString().length() - 3);
                //This also exploits the naming convention of grid buttons
                //wherin each button is named according to it's grid position
                int i = Integer.parseInt(holder);
                int j = Integer.parseInt(holder2);
                xoffset[indices] = i;
                yoffset[indices] = j;
                Log.e("offset", holder + " " + holder2);
                database[indices][universal] = view.getId();
                indices++;
                //ain't this the cite of cites? nah, not here. you'll find that elsewhere.
            } else {
                indices = 0;
                String abs = "" + match[0] + match[1] + match[2];
                //This logs a sting of 3 matched tiles for reference
                Log.d("symbols,", (abs));
                if (match[0] == 'S' && match[2] == 'S' && match[1] == 'O') {
                    int already = 0;
                    for (int i = 0; i < 100; i++) {
                        //This checks if you've made a match before, hence the name.
                        if ((match2[0] == database[0][i] || match2[0] == database[2][i])
                                && match2[1] == database[1][i] && (match2[2] == database[2][i]
                                || match2[2] == database[0][i])) {
                            already++;
                        }// have we matched before?
                    }
                    int count = 1;
                    //what you should do here:
                    // you've combined all criterai. break them into
                    // vert, hor and dia. that solves this ;)

                    if (checkhorizontal() && checkvertical()) {
                        //if count is 0, it means the match was valid both vertically and horizontally.

                        count = 0;
                    }

                /*
                for (int i = 0; i < 2; i++) {
                    if ((xoffset[i] - 1 != xoffset[i+1] && xoffset[i] + 1 != xoffset[i+1] && xoffset[i] != xoffset[i+1])) {
                        if ((yoffset[i] - 1 != yoffset[1] && yoffset[i] + 1 != yoffset[1] && yoffset[i] != yoffset[i+1])) {
                           count++;
                        }// are you straight?
                    }
                }//lmao these jokes
                */
                    //  if (count<=1) {text="WORX!"; Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    //    toast.show();}
                    Log.i("count=", Integer.toString(count));
                    Log.i("already=", Integer.toString(already));
                    if (count != 0) {
                        text = "Invalid tile(s) chosen";
                    }
                    if (already != 1) {
                        text = "Match Repeated.";
                    }

                    if (already == 1 && count == 0) {

                        text = "You just matched!";
                        if (player.charAt(1) == '1') {
                            p1++;
                        } else p2++;
                        universal++;
                        recolourshit();
                    }
                    Log.i("test is ", text);
                    indices = 0;

                }
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        } else if (symb != 'O' && symb != 'S') {

            Toast toast = Toast.makeText(getApplicationContext(), "Select a letter first.", duration);
            toast.show();
            symb = 'G';
        } else {
            textView1 = (TextView) findViewById(view.getId());
            //textView1.setText(sym);
            if (!textView1.getText().equals("S") && !textView1.getText().equals("O")) {
                textView1.setText(getString(R.string.days, sym));
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully placed!", duration);
                toast.show();
            } else {
                Toast toasty = Toast.makeText(getApplicationContext(), "Tile already full", duration);
                toasty.show();
            }
            //Places a tile as per S/O selection and toasts to the screen.
            symb = 'G';


        }
    }


}
//finito! that was shorter than expected...and took longer to type ;P