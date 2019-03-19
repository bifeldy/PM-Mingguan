package id.ac.umn.project_uts_00000013536_v2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookRecyclerViewHolder> implements Filterable {

    private List<Book> books;
    private List<Book> booksFull;
    private Context recyclerContext;
    private int rowLayout;

    public BookRecyclerAdapter(Context recyclerContext, int rowLayout, List<Book> books) {
        this.recyclerContext = recyclerContext;
        this.rowLayout = rowLayout;
        this.books = books;
        this.booksFull = new ArrayList<>(books);
    }

    @NonNull
    @Override
    public BookRecyclerAdapter.BookRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.recyclerContext);
        View view = inflater.inflate(rowLayout, viewGroup, false);

        return new BookRecyclerAdapter.BookRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecyclerAdapter.BookRecyclerViewHolder bookRecyclerViewHolder, int i) {
        final Book book = books.get(i);

        bookRecyclerViewHolder.txtTitle.setText(book.getTitle());
        bookRecyclerViewHolder.txtAuthor.setText(book.getAuthor());
        bookRecyclerViewHolder.txtAsin.setText(book.getAsin());
        bookRecyclerViewHolder.imgBook.setImageResource(R.drawable.books);

        bookRecyclerViewHolder.favIcon.setOnCheckedChangeListener(null);
        bookRecyclerViewHolder.favIcon.setChecked(book.getFavorite() == 1);
        if(bookRecyclerViewHolder.favIcon.isChecked()) {
            bookRecyclerViewHolder.favIcon.setBackgroundResource(R.drawable.ic_favorite);
        }
        else {
            bookRecyclerViewHolder.favIcon.setBackgroundResource(R.drawable.ic_favorite_border);
        }

        bookRecyclerViewHolder.favIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BookHelper mDBHelper = new BookHelper(recyclerContext);
                if(book.getFavorite() == 0){
                    buttonView.setBackgroundResource(R.drawable.ic_favorite);
                    buttonView.setChecked(true);
                    book.setFavorite(1);
                    mDBHelper.setFavorite(book.getAsin(), 1);
                    Toast.makeText(recyclerContext, "Added to favorite~ :: " + book.getAsin(), Toast.LENGTH_SHORT).show();
                }
                else {
                    buttonView.setBackgroundResource(R.drawable.ic_favorite_border);
                    buttonView.setChecked(false);
                    book.setFavorite(0);
                    mDBHelper.setFavorite(book.getAsin(), 0);
                    Toast.makeText(recyclerContext, "Removed from favorite~ :: " + book.getAsin(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (books != null) ? books.size() : 0;
    }

    @Override
    public Filter getFilter() {
        // Buat Search Gan ..
        return booksFilter;
    }

    private Filter booksFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Book> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(booksFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Book item : booksFull) {
                    if(item.getTitle().toLowerCase().contains(filterPattern)) {
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
            books.clear();
            books.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class BookRecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtAuthor, txtAsin;
        private ToggleButton favIcon;
        private ImageView imgBook;

        public BookRecyclerViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.book_title);
            txtAuthor = itemView.findViewById(R.id.book_author);
            txtAsin = itemView.findViewById(R.id.book_asin);
            imgBook = itemView.findViewById(R.id.book_image);
            favIcon = itemView.findViewById(R.id.book_favorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(recyclerContext, DetailsActivity.class);
                    // Passing ID Buku
                    intent.putExtra("asin", books.get(position).getAsin());
                    recyclerContext.startActivity(intent);
                }
            });
        }
    }
}
