package com.koatee.conjugator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// UI references.
	private EditText mAnswerEditText;
	private TextView mPersonTextView;
	private TextView mVerbTextView;
	private TextView mTenseTextView;
	private TextView mKOTextView;
	private TextView mOKTextView;
	private Button   mVerifyBtn;
	private int mOKcount = 0;
	private int mKOcount = 0;
	private int mCount   = 0;
	
	private final int CANCEL_DIALOG = 1;
	private Handler mHandler;
	private Dialog mDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		mAnswerEditText = (EditText) findViewById(R.id.edit_answer);
		mPersonTextView = (TextView) findViewById(R.id.label_person_value);
		mVerbTextView   = (TextView) findViewById(R.id.label_verb_value); 
		mTenseTextView  = (TextView) findViewById(R.id.label_tense_value);
		mKOTextView     = (TextView) findViewById(R.id.label_count_ko);
		mOKTextView     = (TextView) findViewById(R.id.label_count_ok);
		mVerifyBtn      = (Button)   findViewById(R.id.btn_verify);
		
		mHandler = new Handler(new Handler.Callback()
		{
		    @Override
			public boolean handleMessage(Message msg) {
				if(msg.what == CANCEL_DIALOG)
		        {
		            mDialog.cancel();
		        }

		        return false;
			}
		});
		
		loadGameInstance();
	}

	
	public void onButtonVerifyClick(View v) {
		mVerifyBtn.setEnabled(false);

		Context context = getApplicationContext();
		CharSequence text = "Hello toast!";
		
		/*mDialog = new AlertDialog.Builder(this)
											    .setTitle("Bien joué!")
											    .setMessage("Bonne réponse")
											    .setIcon(R.drawable.ic_ok_icon)
											    .show();
		*/
		mDialog = new AlertDialog.Builder(this)
											    .setTitle("Zut!")
											    .setMessage(":(")
											    .setIcon(R.drawable.ic_ko_icon)
											    .show();
		
		
		mHandler.sendEmptyMessageDelayed(CANCEL_DIALOG, 3000);	
		
		loadGameInstance();
    }
	
	private void loadGameInstance() {
		mAnswerEditText.setText("");
		mVerifyBtn.setEnabled(true);
	}
}
