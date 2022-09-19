package com.example.istateca.ui.registro_libros;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.istateca.Clases.Autor;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Tipo;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.Utils.TipoService;
import com.example.istateca.databinding.DialogoAutorBinding;
import com.example.istateca.databinding.DialogoTipoBinding;
import com.example.istateca.databinding.FragmentRegistroLibrosBinding;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registro_librosFragment extends Fragment {
    LibroService libroService;
    TipoService tipoService;
    int a=0;
    private FragmentRegistroLibrosBinding binding;
    private DialogoTipoBinding binding1;
    private DialogoAutorBinding bindingautor;
    Dialog dialogo;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activitResultLauncher;
    List<Tipo> lista_tipos= new ArrayList<>();
    ArrayList<String> comboAutorList = new ArrayList<String>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentRegistroLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        activitylauncher();



        binding.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrircamara();
            }
        });
        binding.imgGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] byteArray;

                //Imagen
                if(bitmap!=null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 1, stream);
                    byteArray = stream.toByteArray();
                    bitmap.recycle();
                }else{
                    byteArray= null;
                }

                //Fecha
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());




                /*
                String codigoDewey=binding.txtCodigodewey.getText().toString();
                String titulo=binding.txtTituloLlibro.getText().toString();
                int tipo= Integer.parseInt(binding.txtTipo.getText().toString());
                String adquisicion= binding.txtAdquisicionLibro.getText().toString();
                int anio= Integer.parseInt(binding.txtAnioPublicacion.getText().toString());
                String editor=binding.txtEditor.getText().toString();
                String ciudad= binding.txtCiudad.getText().toString();
                int numpaginas=Integer.parseInt(binding.txtNumeroPaginas.getText().toString());
                String area= binding.txtArea.getText().toString();
                String codisbn= binding.txtCodigoIsbn.getText().toString();
                String idioma=binding.txtIdioma.getText().toString();
                String descripcion= binding.txtDescripcion.getText().toString();
                String in1= "Indice 1";
                String in2= "Indice 2";
                String in3= "Indice 3";
                String dimensiones=binding.txtDimensiones.getText().toString();
                String estadolibro=binding.txtEstadoLibro.getText().toString();
                Boolean activo=true;
                byte[] imagen = null;
                String url=  binding.txtUrl.getText().toString();
                int idBibliotecario= Integer.parseInt(binding.txtBibliotecarioRegistra.getText().toString());
                Timestamp fecha= null;
                boolean disponibilidad= false;
                String donante= binding.txtAnadirDonante.getText().toString();
                byte[] documentodonacion= null;

                 */

                Tipo t= new Tipo(2,"Var");

                Libro l = new Libro(a,"Deweys","El chemas",t,"adquisicionqwe",1980,"Editort","Cuenca", 90, "Area", "Isbn123"
                        , "Español", "Descripcion aasfa", "IUno", "IDos","Itres","Dimensiones", "Estado", false,byteArray,"asfasdURL",
                        1,d,true,"Christian",null);


                //Libro l = new Libro(5,codigoDewey,titulo,tipo,adquisicion,anio,editor,ciudad,numpaginas,area,codisbn,idioma,descripcion,
                //    in1,in2,in3,dimensiones,estadolibro,activo,imagen,url,idBibliotecario,fecha,disponibilidad,donante,documentodonacion);

                create(l);

            }
        });


        dialogo=new Dialog(getActivity());
        binding.btnAgregarTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
            }
        });
        binding.btnAgregarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoautor();
            }
        });
        getTipo();
        combotipo();

        return root;
    }

    private void combotipo(){

        ArrayList<String> comboTiposList = new ArrayList<String>();
        comboTiposList.add("Seleccione: ");
        for (int i=0; i< lista_tipos.size(); i++){
            comboTiposList.add(lista_tipos.get(i).getNombre());
        }
        binding.comboTipo.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboTiposList));

    }

    private void getTipo(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        tipoService= retrofit.create(TipoService.class);



        Call<List<Tipo>> call= tipoService.getTipos();

        call.enqueue(new Callback<List<Tipo>>() {
            @Override
            public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response) {
                if(response.isSuccessful()){
                    Log.e("Response err: ", response.message());
                    lista_tipos = response.body();
                    System.out.println(lista_tipos.size());
                    combotipo();
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<Tipo>> call, Throwable t) {
                Log.e("Response err: ", t.getMessage());
            }
        });


    }


    public void dialogo(){
        TextView txtcerrar;
        EditText nombre;
        Button agregar;
        String agregar_tipo;
        dialogo.setContentView(R.layout.dialogo_tipo);
        txtcerrar=(TextView) dialogo.findViewById(R.id.txt_cerrar);
        nombre=(EditText) dialogo.findViewById(R.id.txt_agregar);
        agregar=(Button) dialogo.findViewById(R.id.btn_agregar);
        txtcerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tipo t= new Tipo(a,nombre.getText().toString());
                CrearTipo(t);
                nombre.setText("");
                System.out.println("tipo creadooooooo");
                dialogo.dismiss();
            }
        });
    }
    public void dialogoautor(){


        TextView txtcerrar;
        EditText nombre;
        Button agregar;
        String agregar_tipo;
        dialogo.setContentView(R.layout.dialogo_autor);
        txtcerrar=(TextView) dialogo.findViewById(R.id.txt_cerrar);
        nombre=(EditText) dialogo.findViewById(R.id.txt_agregar);
        agregar=(Button) dialogo.findViewById(R.id.btn_agregar);
        txtcerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
        dialogo.show();
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comboAutorList.add(nombre.getText().toString());
                binding.comboAutores.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboAutorList));
                dialogo.dismiss();

            }
        });
    }




    private void create(Libro l){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        libroService= retrofit.create(LibroService.class);
        Call<Libro> call= libroService.addlibro(l);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText("Se agrego con exito", Toast.LENGTH_LONG).show();
                    Log.e("Response erra", response.message());
                    return;
                }
                Libro l=response.body();
               // Toast.makeText(registro_librosFragment.this,l.getCodigoDewey()+" created!", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), l.getTitulo()+" Creado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }

    public void abrircamara(){
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activitResultLauncher.launch(camaraIntent);
    }

    public void activitylauncher(){
        activitResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if(result.getData() != null){
                    bitmap=result.getData().getExtras().getParcelable("data");
                    binding.imgFoto.setImageBitmap(bitmap);

                }

            }
        });
    }


    private void CrearTipo(Tipo l){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        tipoService= retrofit.create(TipoService.class);
        Call<Tipo> call= tipoService.addTipo(l);
        call.enqueue(new Callback<Tipo>() {
            @Override
            public void onResponse(Call<Tipo> call, Response<Tipo> response) {
                if(!response.isSuccessful()){
                    Log.e("Response erra", response.message());
                    return;
                }
                Tipo l=response.body();
                getTipo();
                combotipo();
                Toast.makeText(getActivity(), " Tipo creado correctamente", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Tipo> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}