package com.castelanjr.alunos;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class InserirAlunoWSTask extends AsyncTask<Void, Void, Boolean> {
    Aluno aluno;
    Context context;
    
    public InserirAlunoWSTask(Aluno aluno, Context context) {
        this.aluno = aluno;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            int code = HttpRequest.post("http://androidturmas.herokuapp.com/api/alunos")
                            .contentType("application/json").send(aluno.transformarEmJSON()
                            .toString()).code();
            if (code == 201) {
                return true;
            } else {
                return false;
            }
        } catch (HttpRequestException e) {
            e.printStackTrace();
            return false;
        }
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
