package itp431.poole.connor.finals.project.app.views;

import itp431.poole.connor.finals.project.app.userManager;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class drawView extends View{
	Paint paint= new Paint();
	
	public drawView(Context context){
		super(context);
	}
	
	@Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        paint.setAlpha(200);
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        double vertOffset= (height*.35);
        double horizOffset = ((width*.05)); 
        //drawRect(topX, topY, bottomX, bottomY)
        canvas.drawRect( (int)horizOffset, (int)vertOffset, width-(int)horizOffset, (int)(vertOffset+(5*width*.1)), paint);
        
        double squareSide = (width*.1);
        
        for(int i=0; i<userManager.getNumPunches(); i++){
        	if(i <4){
            	paint.setColor(Color.RED);
            	paint.setStrokeWidth(10);
            	canvas.drawRect((int)(horizOffset+squareSide*(2*i+1)), (int)(vertOffset+squareSide), (int)(horizOffset+squareSide*(2*i+2)), (int)(vertOffset+squareSide*2), paint );
            }else if (i>=4){
            	int j = i-4;
            	paint.setColor(Color.RED);
            	paint.setStrokeWidth(10);
            	canvas.drawRect((int)(horizOffset+squareSide*(2*j+1)), (int)(vertOffset+squareSide*3), (int)(horizOffset+squareSide*(2*j+2)), (int)(vertOffset+squareSide*4), paint );
            }
        }
        for(int i=userManager.getNumPunches(); i< 8; i++){
        	if(i <4){
            	paint.setColor(Color.GRAY);
            	paint.setStrokeWidth(10);
            	canvas.drawRect((int)(horizOffset+squareSide*(2*i+1)), (int)(vertOffset+squareSide), (int)(horizOffset+squareSide*(2*i+2)), (int)(vertOffset+squareSide*2), paint );
            }else if (i>=4){
            	int j = i -4;
            	paint.setColor(Color.GRAY);
            	paint.setStrokeWidth(10);
            	canvas.drawRect((int)(horizOffset+squareSide*(2*j+1)), (int)(vertOffset+squareSide*3), (int)(horizOffset+squareSide*(2*j+2)), (int)(vertOffset+squareSide*4), paint );
            }
        }
        

    }
}
