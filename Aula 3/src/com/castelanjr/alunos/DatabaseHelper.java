package com.castelanjr.alunos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String TAG = DatabaseHelper.class.getSimpleName();
	
	private static final String DATABASE_NAME = "database.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABELA_ALUNOS = "alunos";
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String IDADE = "idade";
	public static final String SEXO = "sexo";
	
	private static final String SCRIPT_TABELA_ALUNOS = "CREATE TABLE " +
			TABELA_ALUNOS + " ( " +
			ID + " integer primary key, " +
			NOME + " text not null, " +
			IDADE + " integer not null, " +
			SEXO + " text not null); ";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "Script da tabela alunos: " + SCRIPT_TABELA_ALUNOS);
		db.execSQL(SCRIPT_TABELA_ALUNOS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Deleta a tabela e cria de novo
		db.execSQL("drop table if exists " + TABELA_ALUNOS);
		this.onCreate(db);
	}
	
	public boolean inserir(Aluno aluno) {
	    boolean result;
	    SQLiteDatabase db = this.getWritableDatabase();
	    try {
        		ContentValues values = new ContentValues();
        		values.put(ID, aluno.getId());
        		values.put(NOME, aluno.getNome());
        		values.put(IDADE, aluno.getIdade());
        		values.put(SEXO, String.valueOf(aluno.getSexo()));
        		
        		db.insert(TABELA_ALUNOS, null, values);
        		
        		result = true;
	    } catch (SQLException e) {
	        result = false;
	    } finally {
	        db.close();
	    }
	    
	    return result;
	}
	
	public void alterar(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put(ID, aluno.getId());
		values.put(NOME, aluno.getNome());
		values.put(IDADE, aluno.getIdade());
		values.put(SEXO, String.valueOf(aluno.getSexo()));
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.update(TABELA_ALUNOS, values, ID + "=" + aluno.getId(), null);
		db.close();
	}
	
	public void excluir(Aluno aluno) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABELA_ALUNOS, ID + "=" + aluno.getId(), null);
		db.close();
	}
	
	public void excluirTodos() {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABELA_ALUNOS, null, null);
	    db.close();
	}
	
	public List<Aluno> buscarAlunos() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABELA_ALUNOS, null, null, null, null, null, NOME);
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		while (c.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(c.getInt(c.getColumnIndex(ID)));
			aluno.setNome(c.getString(c.getColumnIndex(NOME)));
			aluno.setIdade(c.getInt(c.getColumnIndex(IDADE)));
			aluno.setSexo(c.getString(c.getColumnIndex(SEXO)).charAt(0));
			alunos.add(aluno);
		}
		c.close();
		db.close();
		return alunos;
	}
	
	public Aluno buscarAlunoPeloId(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABELA_ALUNOS, null, ID + "=" + id, null, null, null, NOME);
		Aluno aluno = null;
		if (c.moveToFirst()) {
			aluno = new Aluno();
			aluno.setId(c.getInt(c.getColumnIndex(ID)));
			aluno.setNome(c.getString(c.getColumnIndex(NOME)));
			aluno.setIdade(c.getInt(c.getColumnIndex(IDADE)));
			aluno.setSexo(c.getString(c.getColumnIndex(SEXO)).charAt(0));
		}
		c.close();
		db.close();
		return aluno;
	}

}
