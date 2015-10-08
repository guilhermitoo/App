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