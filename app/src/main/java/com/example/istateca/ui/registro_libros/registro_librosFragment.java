package com.example.istateca.ui.registro_libros;



import androidx.activity.result.ActivityResultLauncher;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.istateca.Clases.Autor;
import com.example.istateca.Clases.AutorLibro;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Tipo;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.AutorService;
import com.example.istateca.Utils.Autor_LibroService;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.Utils.TipoService;
import com.example.istateca.databinding.DialogoAutorBinding;
import com.example.istateca.databinding.DialogoTipoBinding;
import com.example.istateca.databinding.FragmentRegistroLibrosBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.istateca.ui.lista_libros.listar_librosFragment.validar;
import static com.example.istateca.ui.lista_libros.listar_librosFragment.idlibro;
import static com.example.istateca.ui.lista_libros.listar_librosFragment.libros;
import static com.example.istateca.V_principal.bibliotecario_ingresado;

public class registro_librosFragment extends Fragment {
    LibroService libroService;
    TipoService tipoService;
    AutorService autorService;
    Autor_LibroService autor_libroService;
    int a=0;
    private FragmentRegistroLibrosBinding binding;
    private DialogoTipoBinding binding1;
    private DialogoAutorBinding bindingautor;
    Dialog dialogo;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activitResultLauncher;
    List<Tipo> lista_tipos= new ArrayList<>();
    List<Autor> lista_autores= new ArrayList<>();
    List<Autor> lista_autoressincrono= new ArrayList<>();
    ArrayList<String> comboAutorList = new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentRegistroLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        combotipo();
        combodisponibilidad();
        getAutor();

        if(validar==1){
            System.out.println("Abriendo modificar");

            validar=0;
        }else{
            System.out.println("Abriendo Crear");
            ventanacrear();
        }



        return root;
    }

    private Boolean disponibilidad(){
        if(binding.comboDisponibilidad.getSelectedItem().toString().equalsIgnoreCase("si")){
            return true;
        }else{
            return false;
        }
    }

    private void combodisponibilidad(){
        ArrayList<String> comboDispoList = new ArrayList<String>();
        comboDispoList.add("Si");
        comboDispoList.add("No");

        binding.comboDisponibilidad.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboDispoList));
    }

    private void traerautores(){

    }



    private void ventanacrear(){
        activitylauncher();
        getAutor();
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


                String codigoDewey=binding.txtCodigodewey.getText().toString();
                String titulo=binding.txtTituloLlibro.getText().toString();
                String adquisicion= binding.txtAdquisicionLibro.getText().toString();
                int anio=0;
                if(binding.txtAnioPublicacion.getText().toString().equalsIgnoreCase("")){
                    anio=0;
                }else{
                    anio= Integer.parseInt(binding.txtAnioPublicacion.getText().toString());
                }

                String editor=binding.txtEditor.getText().toString();
                String ciudad= binding.txtCiudad.getText().toString();

                int numpaginas=0;
                if(binding.txtNumeroPaginas.getText().toString().equalsIgnoreCase("")) {
                    numpaginas=0;
                }else{
                    numpaginas = Integer.parseInt(binding.txtNumeroPaginas.getText().toString());
                }
                String area= binding.txtArea.getText().toString();
                String codisbn= binding.txtCodigoIsbn.getText().toString();
                String idioma=binding.txtIdioma.getText().toString();
                String descripcion= binding.txtDescripcion.getText().toString();
                String in1= binding.txtIndice1.getText().toString();
                String in2= binding.txtIndice2.getText().toString();
                String in3= binding.txtIndice3.getText().toString();
                String donante= binding.txtNombreDonante.getText().toString();
                String dimensiones=binding.txtDimensiones.getText().toString();
                String estadolibro=binding.txtEstadoLibro.getText().toString();
                Boolean activo=true;
                String url=  binding.txtUrl.getText().toString();
                byte[] documentodonacion= null;

                String tipo= (String) binding.comboTipo.getSelectedItem();
                System.out.println(tipo);




                //Libro li = new Libro(1,"Deweys","El chemas",objetotipo(tipo),"adquisicionqwe",1980,"Editort","Cuenca", 90, "Area", "Isbn123"
                //     , "Español", "Descripcion aasfa", "IUno", "IDos","Itres","Dimensiones", "Estado", true,byteArray,"asfasdURL",
                //    1,d,true,"Christian",null);


                Libro l = new Libro(a,codigoDewey,titulo,objetotipo(tipo),adquisicion,anio,editor,ciudad,numpaginas,area,codisbn,idioma,descripcion,
                        in1,in2,in3,dimensiones,estadolibro,activo,byteArray,url,bibliotecario_ingresado,d,disponibilidad(),donante,documentodonacion);



                CrearAutores();
                getAutor();
                create(l,0);
                limpiarcampos();





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
    }

    private void limpiarcampos(){
        binding.comboTipo.setSelection(0);

        //comboAutorList=null;
       // binding.comboAutores.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboAutorList));

        binding.imgFoto.setImageBitmap(null);
        binding.txtAdquisicionLibro.setText("");
        binding.txtAnioPublicacion.setText("");
        binding.txtArea.setText("");
        binding.txtCiudad.setText("");
        binding.txtArea.setText("");
        binding.txtCodigodewey.setText("");
        binding.txtCodigoIsbn.setText("");
        binding.txtDescripcion.setText("");
        binding.txtDimensiones.setText("");
        binding.txtCiudad.setText("");
        binding.txtEditor.setText("");
        binding.txtEstadoLibro.setText("");
        binding.txtIdioma.setText("");
        binding.txtIndice1.setText("");
        binding.txtIndice2.setText("");
        binding.txtIndice3.setText("");
        binding.txtNombreDonante.setText("");
        binding.txtNumeroPaginas.setText("");
        binding.txtTituloLlibro.setText("");
        binding.txtUrl.setText("");
    }

    private void CrearAutores(){
        for(int i=0; i<comboAutorList.size(); i++){
            int con=0;
            String nombre= comboAutorList.get(i);
            Autor au= new Autor(0,nombre);
            for(int j=0; j<lista_autores.size(); j++){
                if(lista_autores.get(j).getNombre().equalsIgnoreCase(au.getNombre())){
                    System.out.println("Autor Existente");
                    con=1;
                }
            }
            if(con!=1){
                //CrearAutor(au);
                CrearAutor(au);
                getAutor();

            }


        }




    }

    private void crearautor_libro(int idlibro){

        String nombreautor="";
        getAutor();
        for(int i=0; i<comboAutorList.size(); i++){
            nombreautor= comboAutorList.get(i);

            for (int j=0; j<lista_autores.size(); j++){
                if(lista_autores.get(j).getNombre().equalsIgnoreCase(nombreautor)){
                    Autor au1= new Autor(lista_autores.get(j).getId(),nombreautor);
                    Libro li = new Libro(idlibro,"Deweys","El chemas",null,"adquisicionqwe",1980,"Editort","Cuenca", 90, "Area", "Isbn123"
                            , "Español", "Descripcion aasfa", "IUno", "IDos","Itres","Dimensiones", "Estado", true,null,"asfasdURL",bibliotecario_ingresado
                            ,null,true,"Christian",null);
                    AutorLibro autorLibro = new AutorLibro(a, li, au1);
                    CrearAutor_Libro(autorLibro);
                    System.out.println("Autor "+ au1.getNombre() + " libro "+ li.getTitulo());

                }
            }


        }
    }

    private Tipo objetotipo(String nombre){
        Tipo t=null;
        for(int i=0; i<lista_tipos.size(); i++){
            if(lista_tipos.get(i).getNombre().equals(nombre)){
                t = new Tipo(lista_tipos.get(i).getId(), nombre);

            }
        }
        return t;
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

    private void getAutor(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        autorService= retrofit.create(AutorService.class);



        Call<List<Autor>> call= autorService.getAutor();

        call.enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if(!response.isSuccessful()){
                    Log.e("Response err: ", response.message());
                    return;
                }
                lista_autores = response.body();

                System.out.println("Actualizando Autores"+ lista_autores.size());
                combotipo();
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
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

        AutoCompleteTextView txtautor_autocomplete;
        TextView txtcerrar;
        EditText nombre;
        Button agregar;
        String agregar_tipo;
        dialogo.setContentView(R.layout.dialogo_autor);
        txtautor_autocomplete= (AutoCompleteTextView)dialogo.findViewById(R.id.txt_autor_autocomplete) ;
        ArrayAdapter adapter = new ArrayAdapter<Autor >(getActivity(), android.R.layout.simple_dropdown_item_1line, lista_autores);
        txtautor_autocomplete.setThreshold(3);
        txtautor_autocomplete.setAdapter(adapter);

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
                comboAutorList.add(txtautor_autocomplete.getText().toString());
                binding.comboAutores.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboAutorList));
                dialogo.dismiss();

            }
        });
    }




    private void create(Libro l, int v){
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
                if(v==0) {
                    Toast.makeText(getActivity(), l.getTitulo() + " Creado", Toast.LENGTH_LONG).show();
                    crearautor_libro(l.getId_libro());
                }else
                {
                    Toast.makeText(getActivity(), l.getTitulo() + " Modificado", Toast.LENGTH_LONG).show();
                }
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





       // Intent intent = new Intent(this, BackgroundService.class);




    private void CrearAutor(Autor A){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        autorService= retrofit.create(AutorService.class);
        Call<Autor> call= autorService.addAutor(A);
        call.enqueue(new Callback<Autor>() {
            @Override
            public void onResponse(Call<Autor> call, Response<Autor> response) {
                if(!response.isSuccessful()){
                    Log.e("Autor Creado", response.message());

                    return;
                }
                Autor l=response.body();
                Toast.makeText(getActivity(), l.getNombre()+" Autor creado correctamente", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Autor> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }

    private void CrearAutor_Libro(AutorLibro al){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        autor_libroService= retrofit.create(Autor_LibroService.class);
        Call<AutorLibro> call= autor_libroService.addAutor(al);
        call.enqueue(new Callback<AutorLibro>() {
            @Override
            public void onResponse(Call<AutorLibro> call, Response<AutorLibro> response) {
                if(!response.isSuccessful()){
                    Log.e("Autor Libro Creado", response.message());
                    return;
                }
                AutorLibro l=response.body();
                Toast.makeText(getActivity(), " Autor creado correctamente", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<AutorLibro> call, Throwable t) {
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