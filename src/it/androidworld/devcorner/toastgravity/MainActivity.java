package it.androidworld.devcorner.toastgravity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	Button makeToastBtn;
	Spinner pos1;
	Spinner pos2;
	Spinner dur;
	EditText toastText;
	EditText xOffsetText;
	EditText yOffsetText;
	int gravity1;
	int gravity2;
	int xOffset;
	int yOffset;
	int duration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		makeToastBtn = (Button) findViewById(R.id.makeToast);
		makeToastBtn.setOnClickListener(this);
		toastText = (EditText) findViewById(R.id.toastText);
		pos1 = (Spinner) findViewById(R.id.spinnerPosition1);
		pos2 = (Spinner) findViewById(R.id.spinnerPosition2);
		dur = (Spinner) findViewById(R.id.durationSpinner);
		xOffsetText = (EditText) findViewById(R.id.xOffsetEditText);
		yOffsetText = (EditText) findViewById(R.id.yOffsetEditText);
		ArrayAdapter<CharSequence> positionAdapter = ArrayAdapter
				.createFromResource(this, R.array.positions,
						android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter
				.createFromResource(this, R.array.duration,
						android.R.layout.simple_spinner_item);
		positionAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		durationAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		pos1.setAdapter(positionAdapter);
		pos2.setAdapter(positionAdapter);
		dur.setAdapter(durationAdapter);
		pos1.setOnItemSelectedListener(this);
		pos2.setOnItemSelectedListener(this);
		dur.setOnItemSelectedListener(this);

	}

	public void makeToast() {
		String text = toastText.getText().toString();
		xOffset = parseString(xOffsetText.getText().toString());
		yOffset = parseString(yOffsetText.getText().toString());
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, duration);
		Log.e("Gravity", gravity1+", "+gravity2);
		toast.setGravity(gravity1|gravity2, xOffset, yOffset);
		toast.show();

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == makeToastBtn.getId()) {
			makeToast();
			
		}

	}



	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		if(parent.getId()==dur.getId())
		{
			Log.e("Click su", "Durata");
			switch (position) {
			case 0:
				duration=Toast.LENGTH_LONG;
				break;

			case 1:
				duration=Toast.LENGTH_SHORT;
				break;
			default:
				break;
			}
		}
		
		else
			
		{
			Log.e("Click item", "Posizione");
			int gravity=0;
			switch (position) {
			case 0:
				gravity = Gravity.LEFT;
				break;
			case 1:
				gravity = Gravity.RIGHT;
				break;
			case 2:
				gravity = Gravity.TOP;
				break;
			case 3:
				gravity = Gravity.BOTTOM;
				break;
			case 4:
				gravity = Gravity.CENTER;
				break;
			default:
				break;
			}
			if(parent.getId()==pos1.getId())
			{
				gravity1 = gravity;
			}
			else if(parent.getId()==pos2.getId())
			{
				gravity2 = gravity;
			}
		}

		}
	
	public Integer parseString(String text) {
		  try {
		    return new Integer(text);
		  } catch (NumberFormatException e) {
		    return 0;
		  }
		}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
