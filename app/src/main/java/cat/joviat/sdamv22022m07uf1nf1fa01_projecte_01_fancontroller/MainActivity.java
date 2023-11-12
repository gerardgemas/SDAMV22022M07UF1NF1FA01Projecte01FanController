package cat.joviat.sdamv22022m07uf1nf1fa01_projecte_01_fancontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private SeekBar sbSpeed1;
    private SeekBar sbSpeed2;
    private SeekBar sbSpeed3;
    private SeekBar sbSpeed4;
    private Switch swOnOff1;
    private Switch swOnOff2;
    private Switch swOnOff3;
    private Switch swOnOff4;
    private TextView tvSpeed1;
    private TextView tvSpeed2;
    private TextView tvSpeed3;
    private TextView tvSpeed4;
    private EditText etIPAddressA;
    private EditText etIPAddressB;
    private EditText etIPAddressC;
    private EditText etIPAddressD;
    private ImageButton ibtOk;
    private TextView tvIPAddressConfigurada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Hide the ActionBar (top toolbar)
        //getSupportActionBar().hide();
        // Set the app to run in fullscreen mode
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sbSpeed1 = findViewById(R.id.seekBar);
        sbSpeed2 = findViewById(R.id.seekBar2);
        sbSpeed3 = findViewById(R.id.seekBar3);
        sbSpeed4 = findViewById(R.id.seekBar4);
        swOnOff1 = findViewById(R.id.switch1);
        swOnOff2 = findViewById(R.id.switch2);
        swOnOff3 = findViewById(R.id.switch3);
        swOnOff4 = findViewById(R.id.switch4);
        tvSpeed1 = findViewById(R.id.textView2);
        tvSpeed2 = findViewById(R.id.textView4);
        tvSpeed3 = findViewById(R.id.textView6);
        tvSpeed4 = findViewById(R.id.textView8);
        etIPAddressA = findViewById(R.id.editTextA);
        etIPAddressB = findViewById(R.id.editTextB);
        etIPAddressC = findViewById(R.id.editTextC);
        etIPAddressD = findViewById(R.id.editTextD);
        ibtOk = findViewById(R.id.imageButton5);
        tvIPAddressConfigurada = findViewById(R.id.ipAddressTextView);
        sbSpeed1.setEnabled(false);
        sbSpeed2.setEnabled(false);
        sbSpeed3.setEnabled(false);
        sbSpeed4.setEnabled(false);
        tvSpeed1.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        tvSpeed2.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        tvSpeed3.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        tvSpeed4.setTextColor(ContextCompat.getColor(this, R.color.text_desactivat));
        etIPAddressA.addTextChangedListener(createTextWatcher(etIPAddressA, etIPAddressB));
        etIPAddressB.addTextChangedListener(createTextWatcher(etIPAddressB, etIPAddressC));
        etIPAddressC.addTextChangedListener(createTextWatcher(etIPAddressC, etIPAddressD));
        etIPAddressD.addTextChangedListener(createTextWatcher(etIPAddressD, null));

        // fem les coses necesaries que shan de fer quan sactiva un switch
        swOnOff1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sbSpeed1.setEnabled(true);
                    tvSpeed1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));


                    sendHttpRequest(getHttpRequestUrl(1, sbSpeed1.getProgress(), true));
                } else {
                    sbSpeed1.setEnabled(false);
                    tvSpeed1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_desactivat));

                    sendHttpRequest(getHttpRequestUrl(1, 0, false));
                }
            }
        });
        swOnOff2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Enable , change text color, and send an HTTP request if needed
                    sbSpeed2.setEnabled(true);
                    tvSpeed2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                    sendHttpRequest(getHttpRequestUrl(2, sbSpeed2.getProgress(), true));
                } else {
                    sbSpeed2.setEnabled(false);
                    tvSpeed2.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_desactivat));

                    sendHttpRequest(getHttpRequestUrl(2, sbSpeed2.getProgress(), false));
                }
            }
        });
        swOnOff3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sbSpeed3.setEnabled(true);
                    tvSpeed3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));

                    sendHttpRequest(getHttpRequestUrl(3, sbSpeed3.getProgress(), true));
                } else {
                    sbSpeed3.setEnabled(false);
                    tvSpeed3.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_desactivat));
                    sendHttpRequest(getHttpRequestUrl(3, sbSpeed3.getProgress(), false));

                }
            }
        });
        swOnOff4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sbSpeed4.setEnabled(true);
                    tvSpeed4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                    sendHttpRequest(getHttpRequestUrl(4, sbSpeed4.getProgress(), true));

                } else {
                    sbSpeed4.setEnabled(false);
                    tvSpeed4.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_desactivat));
                    sendHttpRequest(getHttpRequestUrl(4, sbSpeed4.getProgress(), false));

                }
            }
        });
        ibtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddressA = etIPAddressA.getText().toString();
                String ipAddressB = etIPAddressB.getText().toString();
                String ipAddressC = etIPAddressC.getText().toString();
                String ipAddressD = etIPAddressD.getText().toString();


                    String ipAddress = getFormattedIPAddress(); // Get the formatted IP address
                    tvIPAddressConfigurada.setText(ipAddress);
                etIPAddressA.setText("");
                etIPAddressB.setText("");
                etIPAddressC.setText("");
                etIPAddressD.setText("");

            }
        });
        sbSpeed1 = findViewById(R.id.seekBar);


        //fem els lsiteners que enviin https requests cada cop que l'usuari cambia les seek bars
        sbSpeed1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendHttpRequest(getHttpRequestUrl(1, sbSpeed1.getProgress(), true));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbSpeed2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendHttpRequest(getHttpRequestUrl(2, sbSpeed2.getProgress(), true));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbSpeed3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendHttpRequest(getHttpRequestUrl(3, sbSpeed3.getProgress(), true));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbSpeed4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sendHttpRequest(getHttpRequestUrl(4, sbSpeed4.getProgress(), true));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    private TextWatcher createTextWatcher(final EditText currentEditText, final EditText nextEditText) {
        return new TextWatcher() {
            //funcio per mirar si hi ha camvis als edit text de canviar la ip
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                int inputValue;
                try {
                    inputValue = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    inputValue = -1; // Invalid input
                }

                if (inputValue < 0 || inputValue > 255) {
                    currentEditText.setBackgroundColor(R.color.wrong_number);
                } else {
                    currentEditText.setBackgroundColor(Color.WHITE);
                }

                if (inputValue >= 0 && inputValue <= 255 && (input.length() >= 3 || (input.contains(".") && input.length() >= 4)) && nextEditText != null) {
                    nextEditText.requestFocus();
                }
            }
        };
    }


    private void sendHttpRequest(String url) {
        try {
            // fem la https request ( no ho entenc gaire, fet pel chat)
            URL requestUrl = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                String serverResponse = response.toString();

                reader.close();
            } else {
                Log.e("HTTP Request", "Error: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getFormattedIPAddress() {
        String ipAddressA = etIPAddressA.getText().toString();
        String ipAddressB = etIPAddressB.getText().toString();
        String ipAddressC = etIPAddressC.getText().toString();
        String ipAddressD = etIPAddressD.getText().toString();
        //mirem si els inputs estan buits
        if (ipAddressA.isEmpty() || ipAddressB.isEmpty() || ipAddressC.isEmpty() || ipAddressD.isEmpty()) {
            return "0.0.0.0"; //
        }

        int a = Integer.parseInt(ipAddressA);
        int b = Integer.parseInt(ipAddressB);
        int c = Integer.parseInt(ipAddressC);
        int d = Integer.parseInt(ipAddressD);
        //mirem que sigui una ip valida
        if (a < 0 || a > 255 || b < 0 || b > 255 || c < 0 || c > 255 || d < 0 || d > 255) {
            return "0.0.0.0";
        }

        return String.format("%d.%d.%d.%d", a, b, c, d);
    }
    private String getHttpRequestUrl(int fanNumber, int speed, boolean state) {
        //formatejem la url per fer requests
        String ipAddress = getFormattedIPAddress();
        return "http://" + ipAddress + "?fan=" + fanNumber + "&state=" + state + "&speed=" + speed;
    }
    private boolean isValidIPAddressComponent(String component) {
        try {
            int value = Integer.parseInt(component);
            return value >= 0 && value <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

