package silva.marianne.diary.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import silva.marianne.diary.models.Student;

public class StudentRepository extends SQLiteOpenHelper{


    public StudentRepository(Context context) {
        super(context,"Diary", null, 1);
    }

    //usado assim que o banco é criado.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Students (id INTEGER PRIMARY KEY, name TEXT NOT NULL, address TEXT, phone TEXT, website TEXT, rating REAL);";
        db.execSQL(sql);
    }

    public void remove(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM Students where id =" + id;
        db.execSQL(sql);
    }

    public List<Student> search(){
        String sql = "SELECT * FROM Students";
        SQLiteDatabase db = getReadableDatabase();
        //ponteiro que mostra os dados da busca.
        Cursor c = db.rawQuery(sql, null);

        List<Student> students = new ArrayList<Student>();
        while(c.moveToNext()){
            Student student = new Student();
            student.setId(c.getLong(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setAddress(c.getString(c.getColumnIndex("address")));
            student.setPhone(c.getString(c.getColumnIndex("phone")));
            student.setWebsite(c.getString(c.getColumnIndex("website")));
            student.setRating(c.getDouble(c.getColumnIndex("rating")));

            students.add(student);
        }

        c.close();
        return students;
    }

    public void insert(Student student){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("address", student.getAddress());
        values.put("phone", student.getPhone());
        values.put("website", student.getWebsite());
        values.put("rating", student.getRating());

        db.insert("Students", null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Students";
        db.execSQL(sql);
        onCreate(db);
    }
}
