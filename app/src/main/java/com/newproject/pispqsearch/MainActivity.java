package com.newproject.pispqsearch;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ImageView IconAjuda;
    private EditText EditPesquisa;
    private TextView Insta;
    private FrameLayout FrameAjuda;
    private ListView List;
    private ArrayList<Object> Array;
    public static SQLiteDatabase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IconAjuda    = (ImageView) findViewById(R.id.idImage2);
        Insta        = (TextView) findViewById(R.id.idTextPropaganda2);
        FrameAjuda   = (FrameLayout) findViewById(R.id.idFrame1);
        List         = (ListView) findViewById(R.id.idList1);
        EditPesquisa = (EditText) findViewById(R.id.idEdit1);

        dataBase = CreateDataBase();
        IconAjuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FrameAjuda.getVisibility() == View.GONE) {
                    FrameAjuda.setVisibility(View.VISIBLE);
                }else{
                    FrameAjuda.setVisibility(View.GONE);
                }
            }
        });

        Insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com/jadisonlopess/"));
                startActivity(i);
            }
        });

        EditPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                PreencheList();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                PreencheList();
            }

            @Override
            public void afterTextChanged(Editable s) {
                PreencheList();
            }
        });
    }

    private SQLiteDatabase CreateDataBase(){
        SQLiteDatabase bancoDados = openOrCreateDatabase("Fispq",MODE_APPEND,null);
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS produto (produto VARCHAR(80)," +
                "onu VARCHAR(80), classe VARCHAR(80), risco VARCHAR(80), epi VARCHAR(300));");
        dataBase = bancoDados;
        if (SQL("A").getCount()<=0) {
            bancoDados.execSQL("INSERT INTO produto (produto,onu,classe,risco,epi) VALUES ('Acetona ou Cetona','1090','03','33','Óculos de proteção hermético para produtos químicos, luvas, aventais e botas impermeáveis.\nRespirar com filtro para vapores orgânicos.');");
            bancoDados.execSQL("INSERT INTO produto (produto,onu,classe,risco,epi) VALUES ('Álcool etílico hidratado','1987','02','33','Máscara para vapores orgânicos ou equipamentos de proteção autônoma.\nLuvas impermeáveis-neoprene, nitrílica, viton.\nÓculos com proteção facial.');");
            bancoDados.execSQL("INSERT INTO produto (produto,onu,classe,risco,epi) VALUES ('Etileno','1962','01','223','Óculos de segurança.\nLuvas de segurança.\nMáscara com filtro contra gases.');");
            bancoDados.execSQL("INSERT INTO produto (produto,onu,classe,risco,epi) VALUES ('Polipropileno','1705','-','9003-07-0','Óculos de segurança');");
            bancoDados.execSQL("INSERT INTO produto (produto,onu,classe,risco,epi) VALUES ('Cloreto de venila','1086','2.1','23','Óculos de segurança com proteção lateral e lentes incolores, luvas de raspa.\nRespirador de ar com filtro químico.');");
        }
        return bancoDados;
    }

    public void PreencheList(){
        Array = adicionar();
        ArrayAdapter adapter = new Adapter(MainActivity.this, Array);
        List.setAdapter(adapter);
    }

    private ArrayList<Object> adicionar() {
        ArrayList<Object> ListArray = new ArrayList<Object>();
        Object e = null;

        Cursor Consulta = SQL(EditPesquisa.getText().toString());

        while (Consulta.getCount() > Consulta.getPosition() ){
            e = new Object(Consulta.getString(Consulta.getColumnIndex("produto")),
                    Consulta.getString(Consulta.getColumnIndex("onu")),
                    Consulta.getString(Consulta.getColumnIndex("classe")),
                    Consulta.getString(Consulta.getColumnIndex("risco")),
                    Consulta.getString(Consulta.getColumnIndex("epi")));
            Consulta.moveToNext();
            ListArray.add(e);
        }
        return ListArray;
    }

    public static Cursor SQL(String produto){
        String S = "";
        if (!produto.equals("")){
            S=S+" AND lower(produto) like lower('%"+produto+"%') ";
        }
        else{
            S=S+" AND 1=2 ";
        }
        S="SELECT * FROM produto WHERE 1=1 "+S+" ORDER BY produto; ";
        Cursor cursor = dataBase.rawQuery(S,null);
        cursor.moveToFirst();
        return cursor;
    }
}
