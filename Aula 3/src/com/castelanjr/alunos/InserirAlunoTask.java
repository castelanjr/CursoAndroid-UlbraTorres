package com.castelanjr.alunos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class InserirAlunoTask extends AsyncTask<Void, Void, Boolean> {
    Aluno aluno;
    DatabaseHelper databaseHelper;
    Context context;
    
    public InserirAlunoTask(Aluno aluno, DatabaseHelper databaseHelper, Context context) {
        this.aluno = aluno;
        this.databaseHelper = databaseHelper;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        boolean result = databaseHelper.inserir(aluno);
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        String mensagem;
        if (result) {
            mensagem = "Aluno criado com sucesso";
        } else {
            mensagem = "Ocorreu um erro ao criar o aluno";
        }
        
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }
    
    

}
