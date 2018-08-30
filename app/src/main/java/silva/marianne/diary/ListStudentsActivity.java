package silva.marianne.diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import silva.marianne.diary.models.Student;
import silva.marianne.diary.repositories.StudentRepository;

//quando falamos de activity, nos referimos a tela da aplicação.
public class ListStudentsActivity extends AppCompatActivity {

    private ListView listStudents;

    @Override
    protected void onResume() {
        super.onResume();
        fillStudents();
    }

    private void fillStudents() {
        StudentRepository studentRepository = new StudentRepository(this);
        List<Student> students = studentRepository.search();
        studentRepository.close();

        //contexto serve para identificação e o que faremos é passar a propria activity
        //layout serve para identificar qual layout vamos utilizar (no caso só text view)
        //converte os alunos que são uma string em view para serem introduzidos na lista.
        ArrayAdapter<Student> adapter = new ArrayAdapter <Student> (this, android.R.layout.simple_list_item_1, students);
        listStudents.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deleteOption = menu.add("Delete");
        deleteOption.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) listStudents.getItemAtPosition(info.position);

                StudentRepository repository = new StudentRepository(ListStudentsActivity.this);

                Toast.makeText(ListStudentsActivity.this, "Aluno" + student.getName() + "deletado!", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    //android chama ele quando cria a tela do celular.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //de onde vem o conteúdo da tela e isso separa os dois itens: a parte visual e o comportamento.
        //classe R é gerada automaticamente pelo Android e serve para fazer referência a qualquer recurso
        //que está na pasta res -> de resources
        setContentView(R.layout.activity_list_students);
        //devolve uma instancia da view gerada no set content view
        listStudents = (ListView) findViewById(R.id.lista_alunos);

        Button newStudentButton = (Button) findViewById(R.id.newStudent);

        newStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instancia a intenção
                Intent intentForm = new Intent(ListStudentsActivity.this, FormActivity.class);
                //avisa ao android sua intenção
                startActivity(intentForm);
            }
        });

        registerForContextMenu(listStudents);

    }
}
