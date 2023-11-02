function generateList(path, listIdName) {
    const url = "http://localhost:8080/v1/bancodesangue" + path;
    fetch(url)
    .then(response=>{
        return response.json()
    })
    .then(data => {
        const dataContainer = document.getElementById(listIdName);
        counter = 1;
        for (const key in data) {
            if (data.hasOwnProperty(key)) {
                const value = data[key];
                
                const row = dataContainer.insertRow();
                const cell1 = row.insertCell(0);
                cell1.textContent = counter;
                const cell2 = row.insertCell(1);
                cell2.textContent = key;
                const cell3 = row.insertCell(2);
                cell3.textContent = value;
                counter++;
            }
        }
    })
    .catch(error => {
        console.error("Erro ao buscar os dados JSON:" + url, error);
    });
}

function generatePessoasList(path, listIdName) {
    const url = "http://localhost:8080/v1/bancodesangue" + path;
    fetch(url)
    .then(response=>{
        return response.json()
    })
    .then(pessoas => {
        const dataContainer = document.getElementById(listIdName);
        counter = 1;
        for (const pessoa of pessoas) {
            const row = dataContainer.insertRow();
            const cell1 = row.insertCell(0);
            cell1.textContent = counter;
            const cell2 = row.insertCell(1);
            cell2.textContent = pessoa.nome;
            const cell3 = row.insertCell(2);
            cell3.textContent = pessoa.cpf;
            const cell4 = row.insertCell(3);
            cell4.textContent = pessoa.rg;
            const cell5 = row.insertCell(4);
            cell5.textContent = pessoa.dataNascimento;
            const cell6 = row.insertCell(5);
            cell6.textContent = pessoa.sexo;
            const cell7 = row.insertCell(6);
            cell7.textContent = pessoa.mae;
            const cell8 = row.insertCell(7);
            cell8.textContent = pessoa.pai;
            const cell9 = row.insertCell(8);
            cell9.textContent = pessoa.email;
            const cell10 = row.insertCell(9);
            cell10.textContent = pessoa.cep;
            const cell11 = row.insertCell(10);
            cell11.textContent = pessoa.endereco;
            const cell12 = row.insertCell(11);
            cell12.textContent = pessoa.numero;
            const cell13 = row.insertCell(12);
            cell13.textContent = pessoa.bairro;
            const cell14 = row.insertCell(13);
            cell14.textContent = pessoa.cidade;
            const cell15 = row.insertCell(14);
            cell15.textContent = pessoa.estado;
            const cell16 = row.insertCell(15);
            cell16.textContent = pessoa.telefoneFixo;
            const cell17 = row.insertCell(16);
            cell17.textContent = pessoa.celular;
            const cell18 = row.insertCell(17);
            cell18.textContent = pessoa.altura;
            const cell19 = row.insertCell(18);
            cell19.textContent = pessoa.peso;
            const cell20 = row.insertCell(19);
            cell20.textContent = pessoa.tipoSanguineo;

            counter++;
        }
    })
    .catch(error => {
        console.error("Erro ao buscar os dados JSON:" + url, error);
    });
}

generateList('/quantidade/receptores/tiposanguineo/doador', "receptores-tipo-doador")
generateList('/quantidade/doadores/tiposanguineo/receptor', "doadores-tipo-receptor")
generateList('/pessoas/estados', "candidatos-estados")
generateList('/percentual/obesos/sexo', "obesos-sexo")
generateList('/media/imc/decada', "imc-decada-list")
generateList('/media/idade/tiposanguineo', "idade-media-tipo-sanguineo")
generatePessoasList('/pessoas', "pessoas")




const pessoas = [];

function cadastrarPessoa(nome, cpf, rg, dataNascimento, sexo, mae, pai, email, cep, endereco, numero, bairro, cidade, estado, telefoneFixo, celular, altura, peso, tipoSanguineo) {
    const pessoa = {
      nome,
      cpf,
      rg,
      dataNascimento,
      sexo,
      mae,
      pai,
      email,
      cep,
      endereco,
      numero,
      bairro,
      cidade,
      estado,
      telefoneFixo,
      celular,
      altura,
      peso,
      tipoSanguineo,
      formatedDate: "string"
    };
  
    pessoas.push(pessoa);
  }

  document.addEventListener("DOMContentLoaded", function () {
    const formulario = document.getElementById("formularioPessoa");
  
    formulario.addEventListener("submit", function (event) {
      event.preventDefault();
  
      // Captura os valores preenchidos nos campos do formulário
      const nome = document.getElementById("nome").value;
      const cpf = document.getElementById("cpf").value;
      const rg = document.getElementById("rg").value;
      const dataNascimento = document.getElementById("data-nascimento").value
      const sexo = document.getElementById("sexo").value
      const mae = document.getElementById("mae").value
      const pai = document.getElementById("pai").value
      const email = document.getElementById("email").value
      const cep = document.getElementById("cep").value
      const endereco = document.getElementById("endereco").value
      const numero = document.getElementById("numero").value
      const bairro = document.getElementById("bairro").value
      const cidade = document.getElementById("cidade").value
      const estado = document.getElementById("estado").value
      const telefoneFixo = document.getElementById("telefone").value
      const celular = document.getElementById("celular").value
      const altura = document.getElementById("altura").value
      const peso = document.getElementById("peso").value
      const tipoSanguineo = document.getElementById("tipo-sanguineo").value
  
      cadastrarPessoa(nome, cpf, rg, dataNascimento, sexo, mae, pai, email, cep, endereco, numero, bairro, cidade, estado, telefoneFixo, celular, altura, peso, tipoSanguineo);
      enviaRequisicaoPost('/envia/pessoas', pessoas)
  
      formulario.reset();
    });
  });
  
  function enviaRequisicaoPost(path, pessoas) {
    const url = "http://localhost:8080/v1/bancodesangue" + path;

    const option = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(pessoas)
    };

    fetch(url, option)
    .then(response=>{
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Falha na solicitação POST');
        }
    })
    .then(data => {
        console.log('Resposta da URL:', data);
    })
      .catch(error => {
        console.error('Erro na solicitação POST:', error);
    });
}