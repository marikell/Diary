package silva.marianne.diary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//quando falamos de activity, nos referimos a tela da aplicação.
public class ListStudentsActivity extends AppCompatActivity {

    //android chama ele quando cria a tela do celular.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //de onde vem o conteúdo da tela e isso separa os dois itens: a parte visual e o comportamento.
        //classe R é gerada automaticamente pelo Android e serve para fazer referência a qualquer recurso
        //que está na pasta res -> de resources
        setContentView(R.layout.activity_list_students);

        String [] students= {"Daniel","Ronaldo", "Jeferson", "Felipe"};
        //devolve uma instancia da view gerada no set content view
        ListView listStudents = (ListView) findViewById(R.id.lista_alunos);
        //contexto serve para identificação e o que faremos é passar a propria activity
        //layout serve para identificar qual layout vamos utilizar (no caso só text view)
        //converte os alunos que são uma string em view para serem introduzidos na lista.
        ArrayAdapter<String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_list_item_1, students);
        listStudents.setAdapter(adapter);
    }
}
