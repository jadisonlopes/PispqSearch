package com.newproject.pispqsearch;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Lopes on 06/05/2018.
 */

public class Adapter extends ArrayAdapter<Object> {

    private final Context context;
    private final ArrayList<Object> elementos;

    public Adapter(Context context, ArrayList<Object> elementos) {
        super(context, R.layout.row_adapter, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_adapter,parent, false);

        TextView Prod   = (TextView) rowView.findViewById(R.id.idAText1);
        TextView Onu    = (TextView) rowView.findViewById(R.id.idAText2);
        TextView Classe = (TextView) rowView.findViewById(R.id.idAText3);
        TextView Risco  = (TextView) rowView.findViewById(R.id.idAText4);
        TextView Epi    = (TextView) rowView.findViewById(R.id.idAText5);

        Prod.setText(elementos.get(position).getProduto());
        Onu.setText(elementos.get(position).getOnu());
        Classe.setText(elementos.get(position).getClasse());
        Risco.setText(elementos.get(position).getRisco());
        Epi.setText(elementos.get(position).getEpi());

        return rowView;
    }
}
