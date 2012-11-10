package com.castelanjr.alunos;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Task extends AsyncTask<Void, Void, Boolean> {
    DatabaseHelper databaseHelper;
    ListView listviewAlunos;
    List<Aluno> alunos;
    Context context;
    
    public Task(DatabaseHelper databaseHelper, ListView listview, Context context) {
        this.databaseHelper = databaseHelper;
        this.listviewAlunos = listview;
        this.context = context;
    }
    
    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(context, 
                    android.R.layout.simple_list_item_1, alunos);
            listviewAlunos.setAdapter(adapter);
            listviewAlunos.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> container, View view, int position, long id) {
                    int idDoAluno = alunos.get(position).getId();
                    Intent intent = new Intent(context, CadastrarAlunoActivity.class);
                    intent.putExtra(CadastrarAlunoActivity.ID_DO_ALUNO, idDoAluno);
                    context.startActivity(intent);
                }
            });
        } else {
            Toast.makeText(context, "Não há nenhum aluno cadastrado.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        alunos = databaseHelper.buscarAlunos();
        
        if (alunos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
