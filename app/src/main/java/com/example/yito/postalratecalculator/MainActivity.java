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
                int depth = Integer.parseInt(editDepth.getText().toString());
                int width = Integer.parseInt(editWidth.getText().toString());
                int height = Integer.parseInt(editHeight.getText().toString());
                int weight = Integer.parseInt(editWeight.getText().toString());
                boolean standard = isStandard(depth, width, height, weight);
                boolean nonStandard = isNonStandard(depth, width, height, weight);
                int weightType = checkWeightType(standard, nonStandard, weight);
                int price = checkPrice(country, standard, nonStandard, weightType);
                double decimalPrice = price;
                DecimalFormat form = new DecimalFormat("0.00");
                String displayPrice = form.format(decimalPrice/100);
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

    public static int checkPrice(String country, boolean standard, boolean nonStandard, int weightType) {
        if (country.equals("Canada")) {
            if (standard) {
                if (weightType == 0) {
                    return 100;
                } else if (weightType == 1) {
                    return 120;
                }
            } else if (nonStandard) {
                if (weightType == 2) {
                    return 180;
                } else if (weightType == 3) {
                    return 295;
                } else if (weightType == 4) {
                    return 410;
                } else if (weightType == 5) {
                    return 470;
                } else if (weightType == 6) {
                    return 505;
                }             }
        } else if (country.equals("United States")) {
            if (standard) {
                if (weightType == 0) {
                    return 120;
                } else if (weightType == 1) {
                    return 180;
                }
            } else if (nonStandard) {
                if (weightType == 2) {
                    return 295;
                } else if (weightType == 3) {
                    return 515;
                } else if (weightType >= 4 && weightType <= 6) {
                    return 1030;
                }
            }

        } else if (country.equals("International")) {
            if (standard) {
                if (weightType == 0) {
                    return 250;
                } else if (weightType == 1) {
                    return 360;
                }
            } else if (nonStandard) {
                if (weightType == 2) {
                    return 590;
                } else if (weightType == 3) {
                    return 1030;
                } else if (weightType >= 4 && weightType <= 6) {
                    return 2060;
                }
            }
        }
        return 0;
    }
}