package com.castelanjr.alunos;

import java.util.List;

import org.json.JSONException;

import com.github.kevinsawicki.http.HttpRequest;
import com.github.kevinsawicki.http.HttpRequest.HttpRequestException;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BuscarAlunosWSTask extends AsyncTask<Void, Void, Boolean> {
    ProgressBar progressbar;
    ListView listviewAlunos;
    Context context;
    List<Aluno> alunos;
    
    public BuscarAlunosWSTask(ProgressBar progressbar, ListView listview, Context context) {
        this.progressbar = progressbar;
        this.listviewAlunos = listview;
        this.context = context;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        progressbar.setVisibility(View.GONE);
        if (result) {
            ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(context, 
                    android.R.layout.simple_list_item_1, alunos);
            listviewAlunos.setAdapter(adapter);
            listviewAlunos.setVisibility(View.VISIBLE);
            
            listviewAlunos.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> container, View view, int position, long id) {
                    Intent intent = new Intent(context, CadastrarAlunoActivity.class);
                    Aluno aluno = alunos.get(position);
                    intent.putExtra(CadastrarAlunoActivity.ID_DO_ALUNO, aluno.getId());
                    context.startActivity(intent);
                }
            });
        } else {
            Toast.makeText(context, "Ocorreu um erro ao buscar os alunos", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPreExecute() {
        progressbar.setVisibility(View.VISIBLE);
        listviewAlunos.setVisibility(View.GONE);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            String response = HttpRequest.get("http://androidturmas.herokuapp.com/api/alunos")
                    .connectTimeout(1).body();
            alunos = Aluno.buscarAlunoDoJSON(response, context);
            return true;
            
        } catch (HttpRequestException e) {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            alunos = databaseHelper.buscarAlunos();
            if (alunos.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

}
