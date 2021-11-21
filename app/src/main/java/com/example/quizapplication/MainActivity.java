package com.example.quizapplication;

import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_QUES;
import static com.example.quizapplication.data.QuizContract.MovieEntry.TABLE_QUEST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.quizapplication.data.DbHelper;
import com.example.quizapplication.model.Question;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> quesList;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb;
    Button butNext;
    RadioGroup grp;
    //private RefreshingThread refreshingThread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper db=new DbHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        butNext=(Button)findViewById(R.id.button1);
        setQuestionView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grp =(RadioGroup)findViewById(R.id.radioGroup1);
                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                grp.clearCheck();
                Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());

                db.addQuestion(currentQ);

                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    //db.addQuestion(currentQ);
                    score++;
                    grp.check(qid);
                    Log.d("score", "Your score"+score);
                }
                if(qid<5){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("question_id",qid);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getInt("question_id",1);
        grp.check(qid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        qid++;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        grp.check(qid);
    }
}