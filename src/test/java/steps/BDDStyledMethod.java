package steps;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import model.Endereco;
import model.Pessoa;
import model.Telefone;
import org.hamcrest.core.Is;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.*;

public class BDDStyledMethod {
    public static void SimpleGETPost(String postDDD, String postNumero){
        given()
                .contentType(ContentType.JSON).
        when()
                .get(String.format("http://localhost:8080/pessoas/%s/%s", postDDD, postNumero)).
        then()
                .body("nome", is("Rafael Teixeira"), "cpf", is("12445678909")).statusCode(200);

    }

    public static void SimpleGetPostErro(String postDDDE, String postNumeroE) {
        given()
                .contentType(ContentType.JSON).
        when()
                .get(String.format("http://localhost:8080/pessoas/%s/%s", postDDDE, postNumeroE)).
        then()
                .body("nome", is(nullValue()), "cpf", is(nullValue()))
                .body("erro", is("Não existe pessoa com o telefone (" + postDDDE + ")" + postNumeroE))
                .statusCode(404);
    }

    public static void PerformPOSTWithBodyParameter(Pessoa pessoa) {
        given()
                .contentType(ContentType.JSON).
                with()
                .body(getJsonPessoa(pessoa)).
                when()
                .post("http://localhost:8080/pessoas").
                then()
                .body("nome", Is.is("Carlos")).statusCode(201);
    }
        public static void SalvarDuasPessoasCpf(Pessoa pessoa){
            given()
                    .contentType(ContentType.JSON).
                    with()
                    .body(getJsonPessoa(pessoa)).
                    when()
                    .post("http://localhost:8080/pessoas").
                    then()
                    .body("erro", is("Já existe pessoa cadastrada com o CPF " + pessoa.getCpf())).statusCode(400);

    }
    public static String getJsonPessoa(Pessoa pessoa){
        Gson gson = new Gson();
        String json = gson.toJson(pessoa);
        System.out.println(json);
        return json;
    }

    public static String dadosFakerCpf(){
        Faker faker = new Faker();
        String cpf = String.valueOf(faker.number().randomNumber(11, true));
        return cpf;
    }

    public static String generateFoneFake() {
        String fone = String.valueOf(Math.round(Math.random() * 1000000000D));
        return fone;
    }
}
