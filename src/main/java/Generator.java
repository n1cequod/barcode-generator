import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Generator {

    public static ByteArrayOutputStream code(String barcode) {

        Code128Bean code128 = new Code128Bean();
        code128.setHeight(15f);
        code128.setModuleWidth(0.3);
        code128.setQuietZone(10);
        code128.doQuietZone(true);

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(byteOut, "image/x-png", 400, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        code128.generateBarcode(canvas, barcode);
        try {
            canvas.finish();
        }
        catch (IOException err) {
            err.printStackTrace();
        }

        return byteOut;
    }
}
