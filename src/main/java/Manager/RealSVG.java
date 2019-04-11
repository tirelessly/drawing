package Manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class RealSVG implements SVG{
    private String canvas;

    public RealSVG(String canvas) {
        this.canvas = canvas;
    }

    @Override
    public void saveSVG() {
        try {
            PrintWriter writer = null;
            writer = new PrintWriter("myfile.svg", "UTF-8");
            writer.println(this.canvas + "</svg>");
            writer.close();
        }
        catch(IOException i) {
            i.printStackTrace();
        }
    }


    public void downloadSVG(){

    }


}
