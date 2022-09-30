package com.example.meuprimeirobd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public SQLiteDatabase abrirbanco(String nomebd){
        try{
            SQLiteDatabase dbapp = openOrCreateDatabase(nomebd+".db",
                    Context.MODE_PRIVATE,null);
            return dbapp;

        }catch (SQLException ex){
            Log.d("Erro",ex.getMessage());
            return null;
        }

    }
    public void criatabela(){
        try{
            SQLiteDatabase db=abrirbanco("primbd");
            String SQL="CREATE TABLE IF NOT EXISTS aluno (" +
                    "id integer," +
                    "nome varchar(30))";
            db.execSQL(SQL);
            db.close();
        }catch (SQLException ex){
            Log.d("Erro",ex.getMessage());
        }
    }
    public void insere(String id,String nome){
        try{
            SQLiteDatabase db=abrirbanco("primbd");
            String SQL= "insert into aluno values ("+id+",'"+nome+"')";
            db.execSQL(SQL);
            db.close();
        }catch (SQLException ex){
            Log.d("Erro",ex.getMessage());
        }
    }
    public void alterar(String id,String nome){
        try{
            SQLiteDatabase db=abrirbanco("primbd");
            String SQL= "UPDATE aluno set nome ='"+nome+"' where id ="+id;
            db.execSQL(SQL);
            db.close();
        }catch (SQLException ex){
            Log.d("Erro",ex.getMessage());
        }
    }
    public void delete(String id){
        try{
            SQLiteDatabase db=abrirbanco("primbd");
            String SQL= "delete from aluno where id="+id;
            db.execSQL(SQL);
            db.close();
        }catch (SQLException ex){
            Log.d("Erro",ex.getMessage());
        }
    }

    public void mostraconsulta (){
        String aux="";
        String acumular="";
        try{
            SQLiteDatabase db=abrirbanco("primbd");
            Cursor c = db.rawQuery("Select * from aluno",null);
            while(c.moveToNext()){
               aux="Código :"+c.getInt(0)+" Nome :"+c.getString(1);
               acumular +=aux+"\n";
            }
        }catch(SQLException ex){}
        Toast.makeText(this, acumular, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        abrirbanco("primbd");
        criatabela();
        insere("4","daniel");
        mostraconsulta();
    }
}