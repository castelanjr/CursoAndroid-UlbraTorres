package com.castelanjr.alunos;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

public class StartActivity extends Activity {
	ListView lista;
	List<Aluno> alunos;
	ProgressBar progressbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		lista = (ListView) findViewById(R.id.lista);
		progressbar = (ProgressBar) findViewById(R.id.progressbar);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
//		alunos = databaseHelper.buscarAlunos();
//		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
//		lista.setAdapter(adapter);
//		
//		lista.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> container, View view, int position, long id) {
//				int idDoAluno = alunos.get(position).getId();
//				Intent intent = new Intent(StartActivity.this, CadastrarAlunoActivity.class);
//				intent.putExtra(CadastrarAlunoActivity.ID_DO_ALUNO, idDoAluno);
//				startActivity(intent);
//			}
//		});
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		Task task = new Task(databaseHelper, lista, this);
		task.execute();
	}

	//Cria o menu, relacionando um resource (R.menu.)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}

	//Trata a seleção de algum item do menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_adicionar) {
			startActivity(new Intent(this, CadastrarAlunoActivity.class));
		}
		return true;
	}
	
	

}
