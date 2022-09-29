package com.example.istateca.ui.listado_bibliotecario;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.BibliotecarioService;
import com.example.istateca.databinding.FragmentBibliotecarioBinding;
import com.example.istateca.databinding.FragmentListadoBibliotecarioBinding;
import com.example.istateca.ui.registro_bibliotecario.BibliotecarioFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listado_bibliotecarioFragment extends Fragment {


    BibliotecarioService bibliotecarioService;
    public static List<Bibliotecario> bibliotecarios;
    ListView recyclerView ;
    public static int id=0;
    TextView txtced,txtusu,txtcel,txtema,txtcont;
    private FragmentListadoBibliotecarioBinding binding;
    Dialog dialogo;
    ImageView editar;
    public static int validar=0;
    public static int idbibliotecario=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListadoBibliotecarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView= binding.listaBibliotecarios;
        listarBibliotecario();
        dialogo=new Dialog(getActivity());
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogo.setContentView(R.layout.dialogo_bibliotecario);
                editar=(ImageView) dialogo.findViewById(R.id.img_editar);
                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        idbibliotecario=bibliotecarios.get(i).getId();
                        abrirEditar();
                    }
                });
                datos(dialogo,i);
                dialogo.show();
            }
        });
        return root;
    }

    public void abrirEditar(){
        BibliotecarioFragment homeFragment = new BibliotecarioFragment();
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.listarbilbiotecarioss,homeFragment);
        fragmentTransaction.commit();

        dialogo.dismiss();
        validar =1;
    }

    public void listarBibliotecario(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bibliotecarioService=retrofit.create(BibliotecarioService.class);
        Call<List<Bibliotecario>> call= bibliotecarioService.getBibliotecario();
        call.enqueue(new Callback<List<Bibliotecario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Bibliotecario>> call, Response<List<Bibliotecario>> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());
                    return;
                }
                bibliotecarios=response.body();
                lista_bibliotecariosAdapter lista_bibliotecariosAdapter= new lista_bibliotecariosAdapter(bibliotecarios,getActivity());
                recyclerView.setAdapter(lista_bibliotecariosAdapter);
                bibliotecarios.forEach(p-> System.out.println(bibliotecarios.toString()));
            }
            @Override
            public void onFailure(Call<List<Bibliotecario>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void datos(Dialog dialog,int i){
        txtced=dialog.findViewById(R.id.text_cedulaa);
        txtusu=dialog.findViewById(R.id.text_usuarioo);
        txtcel=dialog.findViewById(R.id.text_celularr);
        txtema=dialog.findViewById(R.id.text_emaill);
        txtcont=dialog.findViewById(R.id.text_contraa);

        txtced.setText(bibliotecarios.get(i).getPersona().getCedula());
        txtusu.setText(bibliotecarios.get(i).getPersona().getUsuario());
        txtcel.setText(bibliotecarios.get(i).getPersona().getCelular());
        txtema.setText(bibliotecarios.get(i).getPersona().getCorreo());
        txtcont.setText(bibliotecarios.get(i).getPersona().getClave());

    }


}