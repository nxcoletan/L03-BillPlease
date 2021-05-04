package sg.edu.rp.c346.id20025835.l03_billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button Split;
    Button Reset;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    EditText amount;
    EditText numPax;
    EditText discount;
    RadioGroup rgMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.billamt);
        numPax = findViewById(R.id.pax);
        totalBill = findViewById(R.id.textView4);
        eachPays = findViewById(R.id.textView5);
        svs = findViewById(R.id.noSVS);
        gst = findViewById(R.id.GST);
        discount = findViewById(R.id. discount);
        Split = findViewById(R.id.Split);
        Reset = findViewById(R.id.Reset);
        rgMode = findViewById(R.id.rgMode);


        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().length()!=0 && numPax.getText().toString().length()!=0) {
                    double oriAmt = Double.parseDouble(amount.getText().toString());
                    double newAmt= 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = oriAmt;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = oriAmt*1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = oriAmt*1.07;
                    } else {
                        newAmt = oriAmt*1.17;
                    }

                    if (discount.getText().toString().length() !=0 ) {
                        newAmt*= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    String mode="in cash";
                    if (rgMode.getCheckedRadioButtonId()==R.id.paynow) {
                        mode="via Paynow to 912345678";
                    }

                    totalBill.setText("Total bill: $" + String.format("%.2f",newAmt));
                    int numPerson=Integer.parseInt(numPax.getText().toString());
                    if (numPerson!=1) {
                        eachPays.setText("Each pays: $" + String.format("%.2f", newAmt/numPerson) + mode);
                    } else {
                        eachPays.setText("Each pays: $" + newAmt + mode);
                    }
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                rgMode.check(R.id.cash);
            }
        });
    }
}