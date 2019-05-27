package id.ac.umn.projectuas_00000013536;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import id.ac.umn.projectuas_00000013536.Activities.DetailActivity;
import id.ac.umn.projectuas_00000013536.POJOs.SeasonalAnime;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder> implements Filterable {

    private List<SeasonalAnime> seasonalsAnime;
    private List<SeasonalAnime> seasonalsAnimeFull;
    private Context recyclerContext;
    private int rowLayout;

    public SeasonAdapter(Context recyclerContext, int rowLayout, List<SeasonalAnime> seasonalsAnime) {
        this.seasonalsAnime = seasonalsAnime;
        this.recyclerContext = recyclerContext;
        this.rowLayout = rowLayout;
        this.seasonalsAnimeFull = new ArrayList<>(seasonalsAnime);
    }

    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.recyclerContext);
        View view = inflater.inflate(rowLayout, viewGroup, false);

        return new SeasonAdapter.SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder seasonViewHolder, int i) {
        final SeasonalAnime seasonalAnime = seasonalsAnime.get(i);

        Glide.with(recyclerContext)
            .load(seasonalAnime.getImage_url())
            .transition(DrawableTransitionOptions.withCrossFade(250)) // Transition Effect Load Image
            .apply(
                new RequestOptions()
                .override(175, 247)
            ).into(seasonViewHolder.seasonal_image);

        seasonViewHolder.seasonal_title.setText(seasonalAnime.getTitle());

        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        if(seasonalAnime.getScore() == 0) {
            seasonViewHolder.seasonal_score.setText("--");
        }
        else {
            seasonViewHolder.seasonal_score.setText(decimalFormat.format(seasonalAnime.getScore()));
        }
        seasonViewHolder.seasonal_members.setText(decimalFormat.format(seasonalAnime.getMembers()));

        seasonViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Open Detail Activity And Passing ID
                Intent intent = new Intent(recyclerContext, DetailActivity.class);
                intent.putExtra("mal_id", seasonalAnime.getMal_id());
                recyclerContext.startActivity(intent);
            }
        });

        seasonViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                // TODO: Long Click Notif
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (seasonalsAnime != null) ? seasonalsAnime.size() : 0;
    }

    @Override
    public Filter getFilter() {

        // Filter Search
        return seasonalsFilter;
    }

    private Filter seasonalsFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SeasonalAnime> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(seasonalsAnimeFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(SeasonalAnime item : seasonalsAnimeFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            seasonalsAnime.clear();
            seasonalsAnime.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class SeasonViewHolder extends RecyclerView.ViewHolder {

        // UI Object
        private ImageView seasonal_image;
        private TextView seasonal_title, seasonal_score, seasonal_members;

        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);

            seasonal_image = itemView.findViewById(R.id.seasonal_image);
            seasonal_title = itemView.findViewById(R.id.seasonal_title);
            seasonal_score = itemView.findViewById(R.id.seasonal_score);
            seasonal_members = itemView.findViewById(R.id.seasonal_members);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    // Overrided By onBindViewHolder
                }
            });
        }
    }
}
