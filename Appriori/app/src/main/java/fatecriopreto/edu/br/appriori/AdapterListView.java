package fatecriopreto.edu.br.appriori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fatecriopreto.edu.br.appriori.model.Chamado;


public class AdapterListView extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Chamado> itens;

    public AdapterListView(Context context, List<Chamado> itens){
        this.layoutInflater = LayoutInflater.from(context);
        this.itens = itens;
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Chamado getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ItemAuxiliar{
        TextView txtDataInicio;
        TextView txtDescricao;
        TextView txtLocal;
        TextView txtEquipamento;
        TextView txtStatus;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemAuxiliar auxiliar;

        if(view == null){
            view = layoutInflater.inflate(R.layout.list_item,null);

            auxiliar = new ItemAuxiliar();

            auxiliar.txtDataInicio = (TextView) view.findViewById(R.id.txtDataInicio);
            auxiliar.txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
            auxiliar.txtLocal = (TextView) view.findViewById(R.id.txtLocal);
            auxiliar.txtEquipamento = (TextView) view.findViewById(R.id.txtEquipamento);
            auxiliar.txtStatus = (TextView) view.findViewById(R.id.txtStatus);

            view.setTag(auxiliar);
        }else{
            auxiliar = (ItemAuxiliar) view.getTag();
        }

        Chamado chamado = itens.get(i);

        SimpleDateFormat df = new SimpleDateFormat("EEEEE, dd 'de' MMMMM 'de' yyyy");
        Date data = new Date();

        auxiliar.txtDataInicio.setText(df.format(chamado.getDataInicio()));
        auxiliar.txtDescricao.setText(chamado.getDescricao());
        auxiliar.txtLocal.setText("Local: " + chamado.getEquipamento().getLocal().getNome());
        auxiliar.txtEquipamento.setText("Equipamento: " + chamado.getEquipamento().getDescricao());
        auxiliar.txtStatus.setText("Status do chamado: " + chamado.getStatusString());

        return view;
    }

}
