package a40274665.calucaltor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridLayout gridLayout = (GridLayout)findViewById(R.id.Layout);
        int count = gridLayout.getChildCount();
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler();
        for(int i=0; i<count; i++){
            View v = gridLayout.getChildAt(i);
            if(v instanceof Button){
                v.setOnClickListener(buttonClickHandler);
            }
        }
    }

    private class ButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {

            TextView Output = (TextView)findViewById(R.id.Output);

            if(v instanceof Button){
                Button buttonClicked = (Button)v;

                if(v.getId() == R.id.CalC){
                    Output.setText("0");
                }

                else if(v.getId() == R.id.CalEquals){
                    try {
                        double Result = Utility.evaluate(Output.getText().toString());
                        Output.setText(Double.toString(Result));
                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        Output.setText("0");
                    }
                }

                else if(Output.getText().length() > 0 &&
                        Utility.isOperator(Output.getText().charAt(Output.getText().length() - 1)) &&
                        Utility.isOperator(buttonClicked.getText().charAt(0)))
                {
                    String errorMessage = "You cannot enter two math operators in a row. You entered " + Output.getText().charAt(Output.getText().length() - 1) + " and " + buttonClicked.getText() + ".";
                    Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                }

                else {
                    if (Output.getText().equals("0") &&
                            !Utility.isOperator(buttonClicked.getText().charAt(0))) {
                        Output.setText("");
                    }

                    Output.setText(Output.getText().toString() + buttonClicked.getText());
                }
            }

        }
    }
}
