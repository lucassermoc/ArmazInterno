package com.example.lucas_pc.armazinterno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    //variáveis:
    EditText editText;
    Button btnSalvar;
    Button btnLer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnLer = (Button)findViewById(R.id.btnLer);
        textView = (TextView)findViewById(R.id.textView);
        textView.setVisibility(View.GONE);

    }

    public void salvarMensagem(View view){ //ação do botão salvar
        String Mensagem = editText.getText().toString(); //pegando a mensagem que o usuário informou e colocando em uma String
        String nome_arquivo = "mensagem"; //string armazena o nome do arquivo a ser criado
        Toast.makeText(getApplicationContext(), "Mensagem salva", Toast.LENGTH_LONG).show();
        try{
            FileOutputStream fileOutputStream = openFileOutput(nome_arquivo, MODE_PRIVATE); //chamando "openFileOutput()" com o nome do arquivo e o modo operacional (MODE PRIVATE = cria um arquivo ou substitui um já existente de mesmo nome e apenas o aplicativo tem acesso à ele, ou seja, se o aplicativo for desinstalado, consequentemente o arquivo será removido). Isso retorna um FileOutputStream.
            fileOutputStream.write(Mensagem.getBytes()); //gravando a mensagem
            fileOutputStream.close(); //fechando a stream
            Toast.makeText(getApplicationContext(), "Mensagem salva", Toast.LENGTH_LONG).show(); //exibindo uma mensagenzinha de sucesso
            editText.setText(""); //"limpando" o editText

        }catch (FileNotFoundException e) { //exceções:
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerMensagem(View view){ //ação do read
        try {
            String Mensagem; //criando um variável para no futuro
            FileInputStream fileInputStream = openFileInput("mensagem"); //passando o nome do arquivo a ser lido para o "openFileInput()" - que é chamado
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); //a classe "BufferedReader" serve para leitura de uma "InputStreamReader", ou seja, ela disponibiliza métodos para ocorrer essa leitura
            StringBuffer stringBuffer = new StringBuffer();
            while((Mensagem=bufferedReader.readLine())!=null){ //utilizando um dos métodos da classe "BufferedReader", o "readLine()"
                stringBuffer.append(Mensagem + "\n"); //o método "append"(acrescentar) sempre adiciona determinado caracter no final do buffer
            }
            textView.setText(stringBuffer.toString()); //exibindo a mensagem que está salva
            textView.setVisibility(View.VISIBLE);
        } catch (FileNotFoundException e) { //exceções:
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
