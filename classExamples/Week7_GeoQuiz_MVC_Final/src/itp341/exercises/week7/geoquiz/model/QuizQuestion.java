package itp341.exercises.week7.geoquiz.model;

public class QuizQuestion {
    private int question;	//resource
    private int answer;			//resource
    
	public QuizQuestion(int question,  int answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	/**
	 * @return the answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(int answer) {
		this.answer = answer;
	}

	/**
	 * @return the question
	 */
	public int getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(int question) {
		this.question = question;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "QuizQuestion [question=" + question + ", answer=" + answer
				+ "]";
	}


}
