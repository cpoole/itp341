package itp341.exercises.week7.geoquiz;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer.TrackInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/*
 *	Functionality:
 *		True / False buttons
 *			Displays message based on cheating status and correct answer
 *
 *		Next button
 *			loads next question 
 *
 *		Cheat button
 *			launches CheatActivity
 *
 *		
 */
public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    
    private static final String KEY_INDEX = "itp341.exercises.week7.geoquiz.index";
	public static final String EXTRA_ANSWER_SHOWN = "itp341.exercises.week7.geoquiz.ANSWER_SHOWN";


    //View references
    Button buttonTrue;
    Button buttonFalse;
    Button buttonNext;
    Button buttonCheat;
    TextView textQuestion;
    
    //Instance variables
    boolean isCheater;		//flag which indicates if user cheated on current question
    String[] questions;		//array of questions
    int[] answers;			//array of answers (0 for false, 1 for true)
    int currentIndex;		//current question

    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.d(TAG, "onCreate() called");
	    setContentView(R.layout.activity_quiz);
	    
	
	  
	    //find view
	    textQuestion = (TextView)findViewById(R.id.text_question);
	    buttonTrue = (Button)findViewById(R.id.button_true);	
	    buttonFalse = (Button)findViewById(R.id.button_false);
	    buttonNext = (Button)findViewById(R.id.button_next);
	    buttonCheat = (Button)findViewById(R.id.button_cheat);
	    
	    //initialize variables
	    isCheater = false;		//assume user has NOT cheated
	    questions = getResources().getStringArray(R.array.array_questions);
	    answers = getResources().getIntArray(R.array.array_answers);
	    
	    //set up listeners
	    buttonTrue.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            checkAnswer(true);			//pass true because this is the True button
	        }
	    });
	
	    		
	    buttonFalse.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            checkAnswer(false);			//pass false because this is the False button
	        }
	    });
	
	    //TODO
	    /*
	     * setup buttonNext event handler
	     * 	
	     */
	    buttonNext.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            currentIndex = (currentIndex + 1) % questions.length;
	            isCheater = false;
	            updateQuestion();
	        }
	    });		
	    
	    //TODO
	    /*
	     * setup buttonCheat event handler
	     */
	    buttonCheat.setOnClickListener(new View.OnClickListener() {
	
	        @Override
	        public void onClick(View v) {
	            Log.d(TAG, "cheat button clicked");
	            Intent i = new Intent(QuizActivity.this, CheatActivity.class);
	            Log.d(TAG, "intent created");
	            boolean answerIsTrue = (answers[currentIndex] == 0) ? false : true;
	            i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
	            startActivityForResult(i, 0);
	        }
	    });
	
	    //TODO
	    /*
	     * restore any saved instance 
	     */
	    if (savedInstanceState != null) {
	        currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
	    }
	
	    updateQuestion();
	}
    
    //TODO 
    /*
     * 	updateQuestion
     * 		Uses the current index to update the text view with the current question
     */
	private void updateQuestion() {
       textQuestion.setText(questions[currentIndex]);
    }
	
	//TODO 
    /*
     * 	checkAnswer
     * 		input: boolean - indicates if user pressed true or false
     * 		side-effect: displays a Toast based on 1) user's answer and 2) whether they cheated or not
     * 
     * 		scenarios --> Toast message
     * 			User didn't cheat and answered incorrectly 	--> incorrect
     * 			User didn't cheat and answered correctly 	--> correct
     * 			User did cheat and answered incorrectly 	--> incorrect_judgment
     * 			User did cheat and answered correctly 		--> correct_judgment
     */
    private void checkAnswer(boolean userPressedTrue) {
    	
        boolean answerIsTrue = (answers[currentIndex] == 0) ? false : true;

        int messageResId = 0;
        
        if (isCheater) {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.toast_correct_judgment;
            } else {
                messageResId = R.string.toast_incorrect_judgment;
            }
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.toast_correct;
            } else {
                messageResId = R.string.toast_incorrect;
            }
        }
        
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show();
    }
    
    //TODO 
    /*
     * 	onActivityResult
     * 		check if user cheated and update isCheater
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(TAG, "onActivityResult");
        isCheater = data.getBooleanExtra(QuizActivity.EXTRA_ANSWER_SHOWN, false);
    }

    //TODO 
    /*
     * 	onSaveInstanceState
     * 		save currentIndex
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }
    

}
