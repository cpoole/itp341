package itp341.exercises.week6.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
 * 	Saving state:
 *		If the activity is re-created, the current state should be restored
 *		Implement this behavior  (hint: what data needs to be saved?)
 *
 *		
 */
public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    public static final String EXTRA_IS_ANSWER_SHOWN = "itp.341.exercises.week6.geoquiz.is_answer_shown";
    private static final String KEY_INDEX = "itp.341.exercises.week6.geoquiz.index";
    private static final String KEY_IS_CHEATER = "itp.341.exercises.week6.geoquiz.is_cheater";

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

    //TODO
    /*
     * onCreate - method is not complete
     */
    @Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.d(TAG, "onCreate() called");
	    setContentView(R.layout.activity_quiz);
	    
	    //restore if screen is rotated
	    
	    if(savedInstanceState != null){
	    	currentIndex = savedInstanceState.getInt(KEY_INDEX);
	    	isCheater = savedInstanceState.getBoolean(KEY_IS_CHEATER);
	    }
	
	  
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
	
	    buttonNext.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v)
	    	{
	    		currentIndex = (currentIndex + 1) % questions.length; //in case we go back to q 0
	    		isCheater = false;
	    		updateQuestion();
	    	}
	    });
	    //TODO
	    /*
	     * setup buttonNext event handler
	     * 	
	     */
	    
	    
	    //TODO
	    /*
	     * setup buttonCheat event handler
	     */
	    buttonCheat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//launch cheat
				//send answer
				Intent i = new Intent(getApplicationContext(), CheatActivity.class);//check what this does
				boolean answerIsTrue = (answers[currentIndex]== 0) ? false : true;
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
				startActivityForResult(i, 0);
			}
	    	
	    });
	   
	
  
	
	    updateQuestion();
	}
    
    //TODO 
    /*
     * 	updateQuestion
     * 		Uses the current index to update the text view with the current question
     */
	private void updateQuestion() {
		
		textQuestion.setText(questions[currentIndex]); //moves the current index + 1 to update new q
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
		
		int messageResourceID = 0; //store resource id of the string, not actual string
		
		if(isCheater){
			if(userPressedTrue == answerIsTrue){
				messageResourceID = R.string.toast_correct_judgment; // youre correct but cheated
			}
			else{
				messageResourceID = R.string.toast_incorrect_judgment;
			}
		}
		else{
			if(userPressedTrue == answerIsTrue){
				messageResourceID = R.string.toast_correct;
			}
			else{
				messageResourceID = R.string.toast_incorrect;
			}
		}
		Toast.makeText(getApplicationContext(),  messageResourceID, Toast.LENGTH_LONG).show();
		
	}
    
    //TODO 
    /*
     * 	onActivityResult
     * 		check if user cheated and update isCheater
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.d(TAG, "onActivityResult");
        
    	if(data != null){
    		isCheater = data.getBooleanExtra(EXTRA_IS_ANSWER_SHOWN, false);
    	}
    }

    //TODO 
    /*
     * 	onSaveInstanceState
     * 		
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        
        savedInstanceState.putBoolean(KEY_IS_CHEATER, isCheater);
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
        
    }
    

}
