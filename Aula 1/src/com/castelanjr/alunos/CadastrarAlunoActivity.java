package com.castelanjr.alunos;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.castelanjr.alunos.R;

public class CadastrarAlunoActivity extends Activity {
	EditText editNome, editIdade;
	RadioButton radioMasculino, radioFeminino;
	Button buttonSalvar, buttonDescartar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_aluno);
        
        //Seta os componentes usando os IDs definidos no layout
        editNome = (EditText) findViewById(R.id.edit_nome);
        editIdade = (EditText) findViewById(R.id.edit_idade);
        radioMasculino = (RadioButton) findViewById(R.id.radio_masculino);
        radioFeminino = (RadioButton) findViewById(R.id.radio_feminino);
        buttonSalvar = (Button) findViewById(R.id.button_salvar);
        buttonDescartar = (Button) findViewById(R.id.button_descartar);
        
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (editNome.getText().toString().trim().length() > 0 && editIdade.getText().toString().trim().length() > 0) {
					String mensagem = "Nome: " + editNome.getText().toString() + "\nIdade: " + editIdade.getText().toString() + "\nSexo: ";
					if (radioMasculino.isChecked()) {
						mensagem = mensagem + "Masculino";
					} else {
						mensagem = mensagem + "Feminino";
					}
					
					//Monta o AlertDialog usando o Builder (construtor):
					AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarAlunoActivity.this);
					builder.setTitle("Cadastrando aluno");
					builder.setMessage(mensagem);
					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					builder.show();
					
				} else {
					Toast.makeText(CadastrarAlunoActivity.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
				}
			}
		});
        
        buttonDescartar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editNome.setText("");
				editIdade.setText("");
				radioMasculino.setChecked(true);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastrar_aluno, menu);
        return true;
    }
}
