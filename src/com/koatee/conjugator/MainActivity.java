package com.koatee.conjugator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.koatee.conjugator.french.models.Parler;
import com.koatee.conjugator.game.ConjugatorGameFacade;
import com.koatee.conjugator.game.FrenchConjugatorGame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// UI references.
	private EditText mAnswerEditText;
	private TextView mPersonTextView;
	private TextView mPersonVerbTextView;
	private TextView mVerbTextView;
	private TextView mTenseTextView;
	private TextView mKOTextView;
	private TextView mOKTextView;
	
	private Button   mVerifyBtn;
	private Button   mNextBtn;
	private Button   mSolutionBtn;
	
	private ImageView mCheckImg;
	
	//Internal counters
	private Integer  mOKcount = 0;
	private Integer  mKOcount = 0;
	private Integer  mCount   = 0;
	
	//Game references
	private ConjugatorGameFacade mConjugator;
	
	//internal Listeners
	OnClickListener mNextListener;
	OnClickListener mTryAgainListener;
	OnClickListener mSolutionListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		//UI references
		mAnswerEditText     = (EditText) findViewById(R.id.edit_answer);
		
		mPersonTextView     = (TextView) findViewById(R.id.label_person_value);
		mPersonVerbTextView = (TextView) findViewById(R.id.label_person_answer);
		mVerbTextView       = (TextView) findViewById(R.id.label_verb_value); 
		mTenseTextView      = (TextView) findViewById(R.id.label_tense_value);
		mKOTextView         = (TextView) findViewById(R.id.label_count_ko);
		mOKTextView         = (TextView) findViewById(R.id.label_count_ok);
		
		mVerifyBtn          = (Button)   findViewById(R.id.btn_verify);
		mSolutionBtn        = (Button)   findViewById(R.id.btn_solution);
		mNextBtn            = (Button)   findViewById(R.id.btn_next);
		
		mCheckImg           = (ImageView)findViewById(R.id.img_check_ok);
		
		//Conjugator
		mConjugator = new FrenchConjugatorGame();
		
		
		mNextListener = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				gotoNextGameInstace();
			}
		};
		
		mTryAgainListener = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				setButtonStatus(true);
				focusEditAndShowKeyboard();
			}
		};
		
		mSolutionListener = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				setSolution();
				dialog.dismiss();
			}
		};
		
		refreshCounters();
		loadGameInstance();
	}

	private void setSolution() {
		setButtonStatus(false);
		mAnswerEditText.setText(mConjugator.getConjugation().getVerbalForm());		
	}
	
	private void setButtonStatus(boolean status){
		mVerifyBtn.setEnabled(status);
		mSolutionBtn.setEnabled(status);
		mAnswerEditText.setEnabled(status);
	}	
	
	private void addCounter(boolean wasCorrect){
		mCount++;
		if (wasCorrect)
			mOKcount++;
		else
			mKOcount++;
		refreshCounters();
	}
	
	private void refreshCounters(){
		String countOK = mOKcount.toString().concat("/").concat(mCount.toString());
		mOKTextView.setText(countOK);
		String countKO = mKOcount.toString().concat("/").concat(mCount.toString());
		mKOTextView.setText(countKO);
	}

	
	public void onButtonVerifyClick(View v) {
		setButtonStatus(false);
		
		String correctAnswer = mConjugator.getConjugation().getVerbalForm();
		String userAnswer    = mAnswerEditText.getText().toString();
		boolean isCorrect    = !userAnswer.equals("") && correctAnswer.toUpperCase().equals(userAnswer.toUpperCase());
		addCounter(isCorrect);
		if (isCorrect){
			//correct answer
			mCheckImg.setVisibility(ImageView.VISIBLE);
			
		} else {
			new AlertDialog.Builder(this)
   					       .setTitle("Zut! ")
						   .setMessage("La réponse n'est pas correcte!")
						   .setIcon(R.drawable.ic_ko_icon)
						   .setPositiveButton("Nouveau essai", mTryAgainListener)
						   .setNeutralButton("Solution", mSolutionListener)
						   .setNegativeButton("Suivant", mNextListener)
						   .show();
		}
	}

	public void onButtonNextClick(View v) {
		gotoNextGameInstace();
	}
	
	
	private void gotoNextGameInstace() {
		if (mSolutionBtn.isEnabled()) {
			addCounter(false);			
		} else {
			mCheckImg.setVisibility(ImageView.INVISIBLE);
			setButtonStatus(true);
		}
		loadGameInstance();
	}
	
	private void focusEditAndShowKeyboard(){
		mAnswerEditText.requestFocus();
		
		InputMethodManager m = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        if(m != null){
          m.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
        } 
	}

	public void onButtonSolutionClick(View v) {
		addCounter(false);
		setSolution();
	}


	private void loadGameInstance() {
		
		mConjugator.newQuestion();
		
		mAnswerEditText.setText("");
		mPersonTextView.setText(mConjugator.getPerson().toString());
		mVerbTextView.setText(mConjugator.getVerb().getInfinitive().toUpperCase());
		mTenseTextView.setText(mConjugator.getTense().getFrenchName());
		mPersonVerbTextView.setText(mConjugator.getConjugation().getPersonString());
		
		focusEditAndShowKeyboard();
	}
}
