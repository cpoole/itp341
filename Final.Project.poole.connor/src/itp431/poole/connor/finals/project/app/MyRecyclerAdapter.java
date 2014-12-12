package itp431.poole.connor.finals.project.app;

import itp431.poole.connor.finals.project.app.models.FruitModel;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>{
	private FruitModel[] fruitsData;
	
	public MyRecyclerAdapter(FruitModel[] fruitsData){
		this.fruitsData = fruitsData;
	}
	
	@Override
	public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		//create view and viewholder
		View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, null);
		ViewHolder viewHolder = new ViewHolder(itemLayoutView);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position){
		viewHolder.rowNumberTextView.setText(fruitsData[position].getFruitName());
		viewHolder.nameTextView.setText(fruitsData[position].getFruitName());
	}
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView rowNumberTextView;
		public TextView nameTextView;
		
		public ViewHolder(View itemLayoutView){
			super(itemLayoutView);
//			rowNumberTextView = (TextView) itemLayoutView.findViewById(R.id.rowNumberTextView);
//			nameTextView = (TextView) itemLayoutView.findViewById(R.id.nameTextView);
		}
	}
	
	@Override
	public int getItemCount(){
		return fruitsData.length;
	}
	

}
