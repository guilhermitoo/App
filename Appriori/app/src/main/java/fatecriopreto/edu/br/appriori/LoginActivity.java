package fatecriopreto.edu.br.appriori;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    //componentes manipulados no layout
    EditText edtLogin;
    EditText edtSenhaL;
    Button   btnEntrarL;
    Button   btnCadastrarL;
    Button   btnEsqueciL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //"conectar" os elementos do layout com os atributos
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenhaL = (EditText) findViewById(R.id.edtSenhaL);
        btnEntrarL = (Button) findViewById(R.id.btnEntrarL);


        btnCadastrarL = (Button) findViewById(R.id.btnCadastrarL);
        btnEsqueciL = (Button) findViewById(R.id.btnEsqueciL);

        //evento para logar
        btnEntrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //conteudo da caixa de texto
                String login = edtLogin.getText().toString();
                String senha = edtSenhaL.getText().toString();

                //verifica se foi preenchido
                if (login.equals("") || senha.equals("")) {
                    Toast.makeText(LoginActivity.this, "Login e senha são obrigatórios", Toast.LENGTH_LONG).show();
                    return;
                }
                if (login.length() <= 1 || senha.length() <= 1) {
                    Toast.makeText(LoginActivity.this, "Usuario e senha devem ser maiores que 1 caractere", Toast.LENGTH_LONG).show();
                    return;
                }

                //logar



            }
        });

        btnCadastrarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrar = new Intent (LoginActivity.this, CadastrarActivity.class);
                startActivity(cadastrar);
            }

            //verificar o que falta para chamar a activity cadastrar
        });

        btnEsqueciL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent esqueci = new Intent (LoginActivity.this, EsqueciActivity.class);
                startActivity(esqueci);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
