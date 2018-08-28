package silva.marianne.diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import silva.marianne.diary.helpers.FormHelper;
import silva.marianne.diary.models.Student;
import silva.marianne.diary.repositories.StudentRepository;

public class FormActivity extends AppCompatActivity {

    private FormHelper formHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        formHelper = new FormHelper(this);
    }

    //método invocado quando algum item do menu é selecionado.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_form_ok:
                //instancia do Student
                Student student = formHelper.getStudent();
                StudentRepository repository = new StudentRepository(this);
                repository.insert(student);
                repository.close();

                Toast.makeText(FormActivity.this,  student.getName()+" criado!", Toast.LENGTH_SHORT).show();
                Intent listStudent = new Intent(FormActivity.this, ListStudentsActivity.class);
                //terminar a activity do formulario
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //renderiza o menu com novas opções
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);

    }
}
