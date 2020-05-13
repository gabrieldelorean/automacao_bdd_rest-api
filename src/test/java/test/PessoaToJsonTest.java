package test;

import com.google.gson.Gson;
import model.Endereco;
import model.Pessoa;
import model.Telefone;

import java.util.ArrayList;
import java.util.List;

public class PessoaToJsonTest {

    public static  void  main(String ... args){
        Pessoa pessoa = new Pessoa();

        pessoa.setCodigo(0);
        pessoa.setNome("Rafael Teixeira");
        pessoa.setCpf("12345678909");

        Endereco endereco = new Endereco();
        endereco.setBairro("Chacara Santo Antonio");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setLogradouro("Rua Alexandre Dumas");
        endereco.setComplemento("Empresa");
        endereco.setNumero("123");

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        Telefone telefone = new Telefone();
        telefone.setDdd("11");
        telefone.setNumero("23456234");

        List<Telefone> telefones = new ArrayList<Telefone>();
        telefones.add(telefone);

        pessoa.setEnderecos(enderecos);
        pessoa.setTelefones(telefones);

        System.out.println(pessoa);

        Gson gson = new Gson();
        String json = gson.toJson(pessoa);

        System.out.println(json);
    }
}
