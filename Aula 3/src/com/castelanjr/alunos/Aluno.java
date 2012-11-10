package com.castelanjr.alunos;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Aluno {
	private int id;
	private String nome;
	private char sexo;
	private int idade;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static List<Aluno> buscarAlunoDoJSON(String response) throws JSONException {
	    List<Aluno> alunos = new ArrayList<Aluno>();
	    
	    JSONArray jsonArray = new JSONArray(response);
	    for (int i = 0; i < jsonArray.length(); i++) {
	        JSONObject jsonObject = jsonArray.getJSONObject(i);
	        Aluno aluno = new Aluno();
	        aluno.setId(jsonObject.getInt("id"));
	        aluno.setNome(jsonObject.getString("nome"));
	        aluno.setIdade(jsonObject.getInt("idade"));
	        aluno.setSexo(jsonObject.getString("sexo").charAt(0));
	        
	        alunos.add(aluno);
	    }
	    return alunos;
	}
	
	public JSONObject transformarEmJSON() {
	    JSONObject jsonObject = new JSONObject();
	    try {
        	    jsonObject.put("id", this.getId());
        	    jsonObject.put("nome", this.getNome());
        	    jsonObject.put("idade", this.getIdade());
        	    jsonObject.put("sexo", String.valueOf(this.getSexo()));
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	    return jsonObject;
	}

}
