package com.castelanjr.alunos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	
	public void inserir(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put(NOME, aluno.getNome());
		values.put(IDADE, aluno.getIdade());
		values.put(SEXO, String.valueOf(aluno.getSexo()));
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(TABELA_ALUNOS, null, values);
	}
	
	public List<Aluno> buscarAlunos() {
		Cursor c = this.getReadableDatabase().query(TABELA_ALUNOS, null, null, null, null, null, NOME);
		
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
		return alunos;
	}

}
