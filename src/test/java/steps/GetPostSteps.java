package steps;

import com.google.gson.Gson;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import model.Endereco;
import model.Pessoa;
import model.Telefone;
import utilities.RestAssuredExtension;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetPostSteps {

    private static ResponseOptions<Response> response;

    @Given("Eu executo operacao GET para {string}")
    public void euExecutoOperacaoGETPara(String url)  throws Throwable{

    }

    @And("Eu executo GET com o DDD {string} e numero {string}")
    public void euExecutoGETComODDDENumero(String postDDD, String postNumero) {
        BDDStyledMethod.SimpleGETPost(postDDD, postNumero);

    }

    @Then("Devo ver o nome {string} e o CPF {string}")
    public static void devoVerONomeEOCPF(String postNome, String postCPF) {
        /*assertThat(response.getBody().jsonPath().get("nome"), hasItem("Rafael Teixeira"));*/
    }

    @And("Eu executo GET com dados incorretos DDD {string} e numero {string}")
    public void euExecutoGETComDadosIncorretosDDDENumero(String postDDDE, String postNumeroE) {
        BDDStyledMethod.SimpleGetPostErro(postDDDE, postNumeroE);
    }

    @Then("DEvo ver a mensagem de erro")
    public void devoVerAMensagemDeErro() {
    }


    @Given("Eu executo a operacao POST para cadastrar uma pessoa com os dados")
    public void euExecutoAOperacaoPOSTParaCadastrarUmaPessoaComOsDados(DataTable table) {
        BDDStyledMethod.PerformPOSTWithBodyParameter(getPessoaMock(table));
    }

    @Then("Eu verifico se a pessoa cadastrada tem o nome de {string}")
    public void euVerificoSeAPessoaCadastradaTemONomeDe(String arg0)  throws Throwable{

    }


    public static Pessoa  getPessoaMock(DataTable table){
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(0);
        pessoa.setNome(table.cell(1,1));
        pessoa.setCpf(BDDStyledMethod.dadosFakerCpf());

        Endereco endereco = new Endereco();
        endereco.setBairro(table.cell(1,6));
        endereco.setCidade(table.cell(1,7));
        endereco.setEstado(table.cell(1,8));
        endereco.setLogradouro(table.cell(1,3));
        endereco.setComplemento(table.cell(1,5));
        endereco.setNumero(table.cell(1,4));

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        Telefone telefone = new Telefone();
        telefone.setDdd(table.cell(1,9));
        telefone.setNumero(BDDStyledMethod.generateFoneFake());

        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(telefone);

        pessoa.setEnderecos(enderecos);
        pessoa.setTelefones(telefones);

        return pessoa;
    }

    @Given("Eu executo a operacao POST para cadastrar a mesma pessoa com os dados")
    public void euExecutoAOperacaoPOSTParaCadastrarAMesmaPessoaComOsDados(DataTable table) {
        BDDStyledMethod.SalvarDuasPessoasCpf(getPessoaDuas(table));
        BDDStyledMethod.SalvarDuasPessoasCpf(getPessoaDuas(table));
    }

    @Then("Aperesenta a mensagem {string}")
    public void aperesentaAMensagem(String arg0) {
    }


    public static Pessoa  getPessoaDuas(DataTable table){
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(0);
        pessoa.setNome(table.cell(1,1));
        pessoa.setCpf(table.cell(1,2));;

        Endereco endereco = new Endereco();
        endereco.setBairro(table.cell(1,6));
        endereco.setCidade(table.cell(1,7));
        endereco.setEstado(table.cell(1,8));
        endereco.setLogradouro(table.cell(1,3));
        endereco.setComplemento(table.cell(1,5));
        endereco.setNumero(table.cell(1,4));

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        Telefone telefone = new Telefone();
        telefone.setDdd(table.cell(1,9));
        telefone.setNumero(BDDStyledMethod.generateFoneFake());

        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(telefone);

        pessoa.setEnderecos(enderecos);
        pessoa.setTelefones(telefones);

        return pessoa;
    }

}
