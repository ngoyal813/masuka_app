package com.example.masuka_app.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.caverock.androidsvg.SVG;
import com.example.masuka_app.R;
import com.example.masuka_app.models.country_model;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.List;

public class country_adapter extends RecyclerView.Adapter<country_adapter.viewholder> {

    private Context context;

    public country_adapter(Context context, List<country_model> countries_list) {
        this.context = context;
        this.countries_list = countries_list;
    }

    public void setCountries_list(List<country_model> countries_list) {
        this.countries_list = countries_list;
        notifyDataSetChanged();
    }
    private List<country_model> countries_list;
    @NonNull
    @NotNull
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {

        holder.name.setText(this.countries_list.get(position).getName());
        holder.capital.setText(this.countries_list.get(position).getCapital());
        holder.region.setText(this.countries_list.get(position).getRegion());
        holder.subregion.setText(this.countries_list.get(position).getSubregion());
        holder.population.setText(this.countries_list.get(position).getPopulation());
        holder.borders.setText(this.countries_list.get(position).getBorders().toString());
        /////// SETTING UP LANGUAGES ///////
        String lang = "";
        for (int i=0; i<this.countries_list.get(position).getLanguages().size(); i++){
            lang += ""+this.countries_list.get(position).getLanguages().get(i).getName()+"    ";
        }
        holder.languages.setText(lang);
        /////////SETTING UP IMAGE ///////////
        Uri IMAGE_URL = Uri.parse(this.countries_list.get(position).getFlag());
        GlideToVectorYou
                .init()
                .with(context)
                .load(IMAGE_URL, holder.flag);
    }

    @Override
    public int getItemCount() {
        if (this.countries_list != null){
            return this.countries_list.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder {

        private ImageView flag;
        private TextView name;
        private TextView capital;
        private TextView region;
        private TextView subregion;
        private TextView population;
        private TextView borders;
        private TextView languages;

        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subregion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);
            borders = itemView.findViewById(R.id.borders);
            languages = itemView.findViewById(R.id.languages);
        }
    }
}
