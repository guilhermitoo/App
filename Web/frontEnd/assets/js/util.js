function getStatus(s) { // função para exibir o texto do status
    switch(s) {
        case '1': return '<b>Pendente</b>';
                break;
        case '2': return '<b style="color: #006400">Realizado</b>';
                break;
        case '3': return '<b style="color: #B22222">Cancelado</b>';
                break;
        default:return '';
    }
}

function getPeriodo(p){ // função para exibir o texto do período de trabalho
    var txt;
    switch(p){
        case 'M':
            txt = 'Manhã';
            break;
        case 'T':
            txt = 'Tarde';
            break;
        case 'N':
            txt = 'Noite';
            break;
        default:
            txt = '';
    }
    return txt;
}

function formataData(data){ // função que converte o formato Y-M-D da data do MySql para D/M/Y
    var dataFormatada;
    var dia;
    var mes;
    var ano;
    // pega o dia da data trazida em php, exemplo 2015/10/15
    // 15
    dia = data.substr(8,2);
    // 10
    mes = data.substr(5,2);
    // 2015
    ano = data.substr(0,4);
    
    dataFormatada = dia + ' / ' + mes + ' / ' + ano;
    return dataFormatada;
}