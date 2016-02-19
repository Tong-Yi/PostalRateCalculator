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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText editWidth;
    EditText editDepth;
    EditText editHeight;
    EditText editWeight;
    String country;
    Button button;
    Spinner dropdown;
    int height;
    int depth;
    int width;
    int weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                try {
                    depth = Integer.parseInt(editDepth.getText().toString());
                    width = Integer.parseInt(editWidth.getText().toString());
                    height = Integer.parseInt(editHeight.getText().toString());
                    weight = Integer.parseInt(editWeight.getText().toString());
                } catch (NumberFormatException nfe) {

                }
                boolean standard = isStandard(depth, width, height, weight);
                boolean nonStandard = isNonStandard(depth, width, height, weight);
                int price = checkPrice(country, standard, nonStandard, weight);
                double decimalPrice = price;
                DecimalFormat form = new DecimalFormat("0.00");
                String displayPrice = form.format(decimalPrice / 100);
                if (price != 0) {
                    Toast.makeText(MainActivity.this, "The cost of sending letter mail will be: $" + displayPrice, Toast.LENGTH_LONG).show();
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

    public static boolean isStandard(int depth, int width, int height, int weight) {
        if (width <= 245 && width >= 140 && height <= 156 && height >= 90 && depth <= 5 && weight <= 50) {
            return true;
        }
        return false;
    }

    public static boolean isNonStandard(int depth, int width, int height, int weight) {
        if (width <= 380 && width >= 140 && height <= 270 && height >= 90 && depth <= 20 && weight <= 500) {
            return true;
        }
        return false;
    }


    public static int checkPrice(String country, boolean standard, boolean nonStandard, int weight) {
        if (country.equals("Canada")) {
            if (standard) {
                if (weight <= 30) {
                    return 100;
                } else if (weight <= 50) {
                    return 120;
                }
            } else if (nonStandard) {
                if (weight <= 100) {
                    return 180;
                } else if (weight > 100 && weight <= 200) {
                    return 295;
                } else if (weight > 200 && weight <= 300) {
                    return 410;
                } else if (weight > 300 && weight <= 400) {
                    return 470;
                } else if (weight > 400 && weight <= 500) {
                    return 505;
                }
            }
        } else if (country.equals("United States")) {
            if (standard) {
                if (weight <= 30) {
                    return 120;
                } else if (weight <= 50) {
                    return 180;
                }
            } else if (nonStandard) {
                if (weight <= 100) {
                    return 295;
                } else if (weight > 100 && weight <= 200) {
                    return 515;
                } else if (weight > 200 && weight <= 500) {
                    return 1030;
                }
            }
        } else if (country.equals("International")) {
            if (standard) {
                if (weight <= 30) {
                    return 250;
                } else if (weight <= 50) {
                    return 360;
                }
            } else if (nonStandard) {
                if (weight <= 100) {
                    return 590;
                } else if (weight > 100 && weight <= 200) {
                    return 1030;
                } else if (weight > 200 && weight <= 500) {
                    return 2060;
                }
            }
        }
        return 0;
    }
}