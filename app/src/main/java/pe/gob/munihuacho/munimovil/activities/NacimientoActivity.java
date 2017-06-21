package pe.gob.munihuacho.munimovil.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.NacimientoAdapter;
import pe.gob.munihuacho.munimovil.model.Nacimiento;

public class NacimientoActivity extends AppCompatActivity {
    ArrayList<Nacimiento> nacimientos;
    RecyclerView rvNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacimiento);
        setTitle("Resultado de Nacimientos");
        nacimientos=getIntent().getParcelableArrayListExtra("nacimiento");
        rvNacimiento=(RecyclerView)findViewById(R.id.rvNacimiento);
        rvNacimiento.setLayoutManager(new LinearLayoutManager(this));
        if(nacimientos.size()==0){
            return;
        }
        NacimientoAdapter adapter=new NacimientoAdapter(this,nacimientos);
        rvNacimiento.setAdapter(adapter);
        adapter.setOnEntryClickListener(new NacimientoAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                Intent intent=new Intent(NacimientoActivity.this,NacimientoDetailActivity.class);
                intent.putExtra(NacimientoDetailActivity.DETALLE_NACIMIENTO,nacimientos.get(position));
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_child_activitys, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_back){
            if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }
        }
        if(id==R.id.action_screenshot){
            Toast.makeText(this, "Proximamente...!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
