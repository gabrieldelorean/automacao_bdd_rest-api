Feature:
  Verify different GET operations using REST-assured

  Scenario:  Procurar pessoa pelo DDD e telefone
    Given  Eu executo operacao GET para "/pessoas"
    And Eu executo GET com o DDD "11" e numero "995388877"
    Then Devo ver o nome "Rafael Teixeira" e o CPF "12445678909"

  Scenario: Procurar pessoa com dados incorretos
     Given  Eu executo operacao GET para "/pessoas"
     And Eu executo GET com dados incorretos DDD "12" e numero "99999999"
     Then Devo ver a mensagem de erro

  Scenario: Cadastrar Pessoa
    Given Eu executo a operacao POST para cadastrar uma pessoa com os dados
    |codigo|nome  |cpf       |logradouro   |numero|complemento|bairro               |cidade   |estado|ddd|numeroT  |
    |0     |Carlos|1245144975|Rua Alexandre|123   |Empresa    |Chacara Santo Antonio|Sao Paulo|SP    |11 |999799998|
    Then Eu verifico se a pessoa cadastrada tem o nome de "Carlos"

  Scenario: Não Salvar duas pessoas com mesmo CPF
    Given Eu executo a operacao POST para cadastrar a mesma pessoa com os dados
      |codigo|nome  |cpf       |logradouro   |numero|complemento|bairro               |cidade   |estado|ddd|numeroT  |
      |0     |Carlos|1245144975|Rua Alexandre|123   |Empresa    |Chacara Santo Antonio|Sao Paulo|SP    |11 |999799998|
    Then Aperesenta a mensagem "Já existe pessoa cadastrada com o CPF"
