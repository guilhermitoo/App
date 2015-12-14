package fatecriopreto.edu.br.appriori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import fatecriopreto.edu.br.appriori.model.Chamado;
import fatecriopreto.edu.br.appriori.util.StatusChamado;


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
        TextView txtLocalEquipamento;
        TextView txtDataInicio;
        TextView txtDescricao;
        TextView txtStatus;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemAuxiliar auxiliar;

        if(view == null){
            view = layoutInflater.inflate(R.layout.list_item,null);

            auxiliar = new ItemAuxiliar();

            auxiliar.txtLocalEquipamento = (TextView) view.findViewById(R.id.txtLocalEquipamento);
            auxiliar.txtDataInicio = (TextView) view.findViewById(R.id.txtDataInicio);
            auxiliar.txtDescricao = (TextView) view.findViewById(R.id.txtDescricao);
            auxiliar.txtStatus = (TextView) view.findViewById(R.id.txtStatus);

            view.setTag(auxiliar);
        }else{
            auxiliar = (ItemAuxiliar) view.getTag();
        }

        Chamado chamado = itens.get(i);

        auxiliar.txtLocalEquipamento.setText(chamado.getEquipamento().getLocal().getNome() +
                " - " + chamado.getEquipamento().getDescricao());
        SimpleDateFormat df = new SimpleDateFormat("EEEEE, dd 'de' MMMMM 'de' yyyy");
        auxiliar.txtDataInicio.setText(df.format(chamado.getDataInicio()));
        auxiliar.txtDescricao.setText(chamado.getDescricao());

        if (chamado.getStatus() == StatusChamado.PENDENTE) {
            auxiliar.txtStatus.setTextColor(0xff000000);
        }else if(chamado.getStatus() == StatusChamado.CANCELADO){
            auxiliar.txtStatus.setTextColor(0xffff0000);
        }else if(chamado.getStatus() == StatusChamado.REALIZADO){
            auxiliar.txtStatus.setTextColor(0xff0000ff);
        }

        auxiliar.txtStatus.setText(chamado.getStatusString());

        return view;
    }

}
