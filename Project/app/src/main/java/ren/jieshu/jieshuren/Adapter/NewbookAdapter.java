package ren.jieshu.jieshuren.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import ren.jieshu.jieshuren.R;
import ren.jieshu.jieshuren.activity.BookDetailsActivity;
import ren.jieshu.jieshuren.entity.BooksBean;
import ren.jieshu.jieshuren.widget.StarBar;

/**
 * Created by laomaotao on 2017/7/4.
 */

public class NewbookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BooksBean> booksList;
    private Context context;
    public NewbookAdapter(Context context,  List<BooksBean> booksList){
        this.context = context;
        this.booksList = booksList;

    } public NewbookAdapter(Context context){
        this.context = context;

    }
    public void setList(List<BooksBean> listList){
        this.booksList = listList;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_newbook,parent,false);
        RecyclerView.ViewHolder newbookHolder = new NewbookAdapter.NewbookHolder(view);
        return newbookHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((NewbookAdapter.NewbookHolder)holder).item_newbook_simpledraweeview.setImageURI(Uri.parse(booksList.get(position).getBookImage()));
        ((NewbookAdapter.NewbookHolder)holder).item_newbook_bookname.setText(booksList.get(position).getBookName());
        ((NewbookAdapter.NewbookHolder)holder).item_newbook_average.setText(booksList.get(position).getAverage());
        ((NewbookAdapter.NewbookHolder)holder).item_newbook_bookAuthor.setText(booksList.get(position).getBookAuthor());
        ((NewbookAdapter.NewbookHolder)holder).item_newbook_average_star.setStarMark(Float.parseFloat(booksList.get(position).getAverage())/2);
        if (booksList.get(position).getFreeDb()!= null) {
            if (booksList.get(position).getFreeDb() == 0) {
                ((NewbookAdapter.NewbookHolder) holder).item_newbook_dbSize.setText("2-5天发货");
            } else {
                ((NewbookAdapter.NewbookHolder) holder).item_newbook_dbSize.setText("24小时发货");
            }
        }

    }

    @Override
    public int getItemCount() {
        if (booksList != null) {
            return booksList.size();
        }else {
            return 0;
        }
    }

    class NewbookHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView item_newbook_simpledraweeview;
        TextView item_newbook_bookname;
        TextView item_newbook_average;
        TextView item_newbook_bookAuthor;
        TextView item_newbook_dbSize;
        StarBar item_newbook_average_star;
        public NewbookHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context,BookDetailsActivity.class);
                    intent.putExtra("bookID",booksList.get(getAdapterPosition()).getBookid());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });
            item_newbook_simpledraweeview = (SimpleDraweeView) itemView.findViewById(R.id.item_newbook_simpledraweeview);
            item_newbook_bookname = (TextView) itemView.findViewById(R.id.item_newbook_bookname);
            item_newbook_average = (TextView) itemView.findViewById(R.id.item_newbook_average);
            item_newbook_bookAuthor = (TextView) itemView.findViewById(R.id.item_newbook_bookAuthor);
            item_newbook_dbSize = (TextView) itemView.findViewById(R.id.item_newbook_dbSize);
            item_newbook_average_star = (StarBar) itemView.findViewById(R.id.item_newbook_average_star);
        }

    }


}
