package silva.marianne.diary.helpers;

import android.media.Rating;
import android.widget.EditText;
import android.widget.RatingBar;

import silva.marianne.diary.FormActivity;
import silva.marianne.diary.R;
import silva.marianne.diary.models.Student;

public class FormHelper {

    private EditText fieldName;
    private EditText fieldAddress;
    private EditText fieldPhone;
    private EditText fieldWebsite;
    private RatingBar fieldRating;

    public FormHelper(FormActivity formActivity){

        fieldName = (EditText) formActivity.findViewById(R.id.form_name);
        fieldAddress = (EditText) formActivity.findViewById(R.id.form_address);
        fieldPhone = (EditText) formActivity.findViewById(R.id.form_phone);
        fieldWebsite = (EditText) formActivity.findViewById(R.id.form_website);
        fieldRating = (RatingBar) formActivity.findViewById(R.id.form_rating);
    }

    public Student getStudent(){
        Student student = new Student();
        student.setName(fieldName.getText().toString());
        student.setAddress(fieldAddress.getText().toString());
        student.setPhone(fieldPhone.getText().toString());
        student.setWebsite(fieldWebsite.getText().toString());
        student.setRating(Double.valueOf(fieldRating.getProgress()));

        return student;
    }
}
