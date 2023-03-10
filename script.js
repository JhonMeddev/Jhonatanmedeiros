////////////////////PROGRAMA NÚMEROS///////////////////////////////
$(document).ready(function() {
    var numeros = [];
    function atualizarLista() {
      numeros.sort(function(a, b) {
        return a - b;
      });

      $('#numeroAdicionado').text(numeros.join(', '));
    }

    $('#adicionarBtn').click(function() {
      var numero = $('#numeroInput').val(); 

      if (numero != '') { 
        numeros.push(parseInt(numero)); 
        atualizarLista(); 
        $('#numeroInput').val('');
      }
    });
});

function salvarNumeros() {    
    var numeros = $("#numeroAdicionado").text().split(", ").map(function(item) {
      return parseInt(item);
    });
    
    var data = {numeros: numeros};
    var json = JSON.stringify(data);
    
    // Criar um blob com o JSON
    var blob = new Blob([json], {type: "application/json"});
    
    // Salvar o arquivo usando FileSaver.js
    saveAs(blob, "numeros.json");
}

////////////////////PROGRAMA BUSCAR ENDEREÇO///////////////////////////////
$(document).ready(function() {
    $('#pesquisarCEPViacep').click(function() {
      var cep = $('#cepViacep').val();
      $.getJSON('https://viacep.com.br/ws/' + cep + '/json/', function(data) {
        if (data.erro) {
          $('#enderecoViacep').html('CEP não encontrado.');
        } else {
          var endereco = data.logradouro + ', ' + data.bairro + ', ' + data.localidade + ' - ' + data.uf;
          $('#enderecoViacep').html(endereco);
        }
      });
    });
});

////////////////////PROGRAMA NÚMEROS PERFEITOS ///////////////////////////////
function encontrarDivisores(numero) {
    let divisores = [];
    for (let i = 1; i < numero; i++) {
      if (numero % i === 0) {
        divisores.push(i);
      }
    }

    return divisores;
}

function verificarNumeroPerfeito(numero) {
    let soma = 0;
    const divisores = encontrarDivisores(numero);
    divisores.forEach(function(divisor) {
      soma += divisor;
    });
    
    if (soma === numero) {
      return true;
    } else {
      return false;
    }
}
  
$(document).ready(function() {
    $('#perguntarNumero').click(function() {
      const numero = parseInt($('#numero').val());
  
      if (verificarNumeroPerfeito(numero)) {
        $('#numeroPerfeito').text('Sim, este número é perfeito');
      } else {
        $('#numeroPerfeito').text('Não, este número não é perfeito');
      }
    });
});

////////////////////PROGRAMA TABUADA ///////////////////////////////
$(document).ready(function() {
    $('#tabuada').click(function() {
      const numero = parseInt($('#numeroTabuada').val());

      let resultado = "";
      for (let i = 1; i <= 10; i++) {
        resultado += `${numero} x ${i} = ${numero * i} <br>`;
      }
      document.getElementById("mostraTabuada").innerHTML = resultado;

    });
});


