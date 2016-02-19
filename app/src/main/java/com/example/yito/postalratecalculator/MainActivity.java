package com.example.yito.postalratecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editWidth;
    EditText editDepth;
    EditText editHeight;
    EditText editWeight;
    String country;
    Button button;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        dropdown = (Spinner) findViewById(R.id.location);
        String[] items = new String[]{"Canada", "United States", "International"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        addListenerOnSubmit();

    }

    public void addListenerOnSubmit() {
        button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                editDepth = (EditText) findViewById(R.id.depth);
                editWidth = (EditText) findViewById(R.id.width);
                editHeight = (EditText) findViewById(R.id.height);
                editWeight = (EditText) findViewById(R.id.weight);
                country = dropdown.getSelectedItem().toString();
                int depth = Integer.parseInt(editDepth.getText().toString());
                int width = Integer.parseInt(editWidth.getText().toString());
                int height = Integer.parseInt(editHeight.getText().toString());
                int weight = Integer.parseInt(editWeight.getText().toString());
                boolean standard = isStandard(depth, width, height);
                boolean nonStandard = isNonStandard(depth, width, height);
                int weightType = checkWeightType(standard, nonStandard, weight);
                int price = checkPrice(country, standard, nonStandard, weightType);
                double displayPrice = price / 100;
                if (price != 0) {
                    Toast.makeText(MainActivity.this, "The cost of sending letter mail will be: " + displayPrice, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Your letter mail does not meet the specifications", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int checker() {
        return 0;
    }

    public static boolean isStandard(int depth, int width, int height) {

        if (width <= 245 && width >= 140 && height <= 156 && height >= 90 && depth <= 5) {
            return true;
        }

        return false;
    }

    public static int checkWeightType(boolean standard, boolean nonStandard, int weight) {
        if (standard) {
            if (weight <= 30) {
                return 0;
            } else if (weight <= 50) {
                return 1;
            }
        } else if (nonStandard) {
            if (weight <= 100) {
                return 2;
            } else if (weight <= 200) {
                return 3;
            } else if (weight <= 300) {
                return 4;
            } else if (weight <= 400) {
                return 5;
            } else if (weight <= 500) {
                return 6;
            }
        }
        return 9;

    }

    public static boolean isNonStandard(int depth, int width, int height) {
        if (width <= 380 && width >= 140 && height <= 270 && height >= 90 && depth <= 20) {
            return true;
        }
        return false;
    }

    public static int checkPrice(String country, boolean standard, boolean nonStandard, int overWeight) {
        if (country.equals("Canada")) {
            if (standard) {
                if (overWeight == 0) {
                    return 100;
                } else if (overWeight == 1) {
                    return 120;
                } else {
                    return 0;
                }
            } else if (nonStandard) {
                if (overWeight == 2) {
                    return 180;
                } else if (overWeight == 3) {
                    return 295;
                } else if (overWeight == 4) {
                    return 410;
                } else if (overWeight == 5) {
                    return 470;
                } else if (overWeight == 6) {
                    return 505;
                } else {
                    return 0;
                }
            }
        } else if (country.equals("United States")) {
            if (standard) {
                if (overWeight == 0) {
                    return 120;
                } else if (overWeight == 1) {
                    return 180;
                } else {
                    return 0;
                }
            } else if (nonStandard) {
                if (overWeight == 2) {
                    return 295;
                } else if (overWeight == 3) {
                    return 515;
                } else if (overWeight >= 4 && overWeight <= 6) {
                    return 1030;
                } else {
                    return 0;
                }
            }

        } else if (country.equals("International")) {
            if (standard) {
                if (overWeight == 0) {
                    return 250;
                } else if (overWeight == 1) {
                    return 360;
                } else {
                    return 0;
                }
            } else if (nonStandard) {
                if (overWeight == 2) {
                    return 590;
                } else if (overWeight == 3) {
                    return 1030;
                } else if (overWeight >= 4 && overWeight <= 6) {
                    return 2060;
                } else {
                    return 0;
                }
            }

        }
        return 0;
    }
}