package com.example.parcial_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //William Alexander Aparicio Zelaya USSS011223
    // Jouse Gilberto Castro Zelaya USSS013023
    TabHost tbh;
    TextView tempVal;
    Button btn;
    Spinner spn;
    conversores objConversor = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbh = findViewById(R.id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("Area").setContent(R.id.tabArea).setIndicator("Area+", null));
        tbh.addTab(tbh.newTabSpec("Metros").setContent(R.id.tabMetros).setIndicator("Metros", null));

        btn = findViewById(R.id.btnMetrosConvertir);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtMetrosCantidad);
                double num1 = Double.parseDouble(tempVal.getText().toString());


                double Calculo = 0;

                if(num1 <= 18 ){
                    Calculo = 6;
                }else if (num1 <=28){
                    Calculo = (num1 - 18) * 0.45 + 6;
                }else if (num1 >=29) {
                    Calculo = (num1 - 28) * 0.65 + 4.5 + 6;


                }
                tempVal = findViewById(R.id.lblrespuesta);
                tempVal.setText("Precio: "+ Calculo +"$");
            }
        });

        btn = findViewById(R.id.btnAreaConvertir);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spn = findViewById(R.id.spnAreaDe);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(R.id.spnAreaA);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(R.id.txtAreaCantidad);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = objConversor.convertir(0, de, a, cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+
                        resp, Toast.LENGTH_LONG).show();
            }
        });
    }
}
class conversores{
    double[][] valores = {
            {1,0.1329421,0.111111,0.0929,0.00014774656489,0.0000132,0.0000092}
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a] / valores[opcion][de] * cantidad;
    }
}