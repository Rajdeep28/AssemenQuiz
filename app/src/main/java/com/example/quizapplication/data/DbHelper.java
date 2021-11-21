package com.example.quizapplication.data;

import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_ANSWER;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_ID;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_OPTA;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_OPTB;
import static com.example.quizapplication.data.QuizContract.MovieEntry.KEY_QUES;
import static com.example.quizapplication.data.QuizContract.MovieEntry.TABLE_QUEST;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapplication.model.Question;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name

    private SQLiteDatabase dbase;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT)";
        db.execSQL(sql);
        addQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    private void addQuestions() {
        Question q1=new Question("Narendra Modi is the PM of India","Yes", "No","Yes");
        this.addQuestion(q1);
        Question q2=new Question("Mamata Banerjee is the CM of West Bengal", "Yes", "No","Yes");
        this.addQuestion(q2);
        Question q3=new Question("Kolkata is the city of joy", "Yes","No", "Yes" );
        this.addQuestion(q3);
        Question q4=new Question("Do you have Aadhar Card", "Yes", "No","Yes");
        this.addQuestion(q4);
        Question q5=new Question("Goat is a cow?","Yes","No","No");
        this.addQuestion(q5);
    }

    public void addQuestion(Question quest) {
       // dbase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
