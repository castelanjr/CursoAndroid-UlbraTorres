package com.castelanjr.alunos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CadastrarAlunoActivity extends Activity {
	public static final String ID_DO_ALUNO = "idDoAluno";
	
	private static final int MODO_NOVO_ALUNO = 0;
	private static final int MODO_EDITAR_ALUNO = 1;
	
	private int modo, idAluno;
	private Aluno alunoSendoEditado;
	DatabaseHelper databaseHelper;
	
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
        
        databaseHelper = new DatabaseHelper(this);
        
        // Verifica a Intent, se foi passado algum ID como extra.
        // Se não for passado nenhum valor, é retornado -1 pelo método getIntExtra()
        // Ou seja: se for -1, é porque não estamos no modo de edição, e sim de criação de alunos
        Intent intent = getIntent();
        idAluno = intent.getIntExtra(ID_DO_ALUNO, -1);
        if (idAluno == -1) {
        	modo = MODO_NOVO_ALUNO;
        } else {
        	modo = MODO_EDITAR_ALUNO;
        	// Busca o aluno do banco de dados e reenche os campos com as informações dele
        	alunoSendoEditado = databaseHelper.buscarAlunoPeloId(idAluno);
        	editNome.setText(alunoSendoEditado.getNome());
        	editIdade.setText(String.valueOf(alunoSendoEditado.getIdade()));
        	if (alunoSendoEditado.getSexo() == 'M') {
        		radioMasculino.setChecked(true);
        	} else {
        		radioFeminino.setChecked(true);
        	}
        }
    }
    
    private void descartar() {
    	editNome.setText("");
		editIdade.setText("");
		radioMasculino.setChecked(true);
    }
    
    private void excluirAluno() {
    	databaseHelper.excluir(alunoSendoEditado);
    	finish();
    }
    
    private void salvar() {
    		if (editNome.getText().toString().trim().length() > 0 && editIdade.getText().toString().trim().length() > 0) {
    			
    			if (modo == MODO_NOVO_ALUNO) {
    				// alunoSendoEditado não existe, devemos criar um novo
    				alunoSendoEditado = new Aluno();
    			}
    			
    			alunoSendoEditado.setNome(editNome.getText().toString());
    			alunoSendoEditado.setIdade(Integer.valueOf(editIdade.getText().toString()));
    			char sexo;
    			if (radioMasculino.isChecked()) {
    				sexo = 'M';
    			} else {
    				sexo = 'F';
    			}
    			alunoSendoEditado.setSexo(sexo);
    			
    			if (modo == MODO_NOVO_ALUNO) {
	    			databaseHelper.inserir(alunoSendoEditado);
    			} else {
    				databaseHelper.alterar(alunoSendoEditado);
    			}
    			
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
    			// Ao selecionar a opção de Descartar, se estiver no modo de criar novo aluno, limpa os campos normalmente usando o método descartar()
    			// Caso contrário, se estiver no modo de edição (MODO_EDITAR_ALUNO), exclui o mesmo do banco de dados
    			if (modo == MODO_NOVO_ALUNO) {
    				descartar();
    			} else {
    				excluirAluno();
    			}
    		}
    		return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cadastrar_aluno, menu);
        return true;
    }
}
