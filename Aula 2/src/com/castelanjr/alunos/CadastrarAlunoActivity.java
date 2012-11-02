package com.castelanjr.alunos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CadastrarAlunoActivity extends Activity {
	
	EditText editNome, editIdade;
	RadioButton radioMasculino, radioFeminino;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);
        
        //Seta os componentes usando os IDs definidos no layout
        editNome = (EditText) findViewById(R.id.edit_nome);
        editIdade = (EditText) findViewById(R.id.edit_idade);
        radioMasculino = (RadioButton) findViewById(R.id.radio_masculino);
        radioFeminino = (RadioButton) findViewById(R.id.radio_feminino);
    }
    
    private void descartar() {
    	editNome.setText("");
		editIdade.setText("");
		radioMasculino.setChecked(true);
    }
    
    private void salvar() {
    		if (editNome.getText().toString().trim().length() > 0 && editIdade.getText().toString().trim().length() > 0) {
    			
    			//Criar um novo aluno, adiciona-o à lista e fecha a activity
    			Aluno aluno = new Aluno();
    			aluno.setNome(editNome.getText().toString());
    			aluno.setIdade(Integer.valueOf(editIdade.getText().toString()));
    			char sexo;
    			if (radioMasculino.isChecked()) {
    				sexo = 'M';
    			} else {
    				sexo = 'F';
    			}
    			aluno.setSexo(sexo);
    			
    			//Adiciona o aluno à lista
    			DatabaseHelper databaseHelper = new DatabaseHelper(this);
    			databaseHelper.inserir(aluno);
    			
    			//Método para fechar a activity
    			finish();
			
		} else {
			Toast.makeText(CadastrarAlunoActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
		}
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    		if (item.getItemId() == R.id.menu_adicionar) {
    			salvar();
    		} else {
    			descartar();
    		}
    		return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastrar_aluno, menu);
        return true;
    }
}
