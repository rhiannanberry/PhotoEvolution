/**
 * Created by STARR on 4/9/2016.
 */

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Photo {
    private File file;
    private BufferedImage img;
    private Pixel[][] pixImg;
    private Pixel[][] originalPixImg;
    private Pixel[][] tempImg;
    private int imgWidth;
    private int imgHeight;
    protected int tempRed, tempGreen, tempBlue;
    private String fileName;

    Photo(String fileName) throws IOException {
        this.fileName = fileName;
        file  = new File("PhotoEvolution/src/resources/" + fileName);
        System.out.println(file.getAbsolutePath());
        BufferedImage originalImg = ImageIO.read(file);
        img = originalImg;
        imgWidth = img.getWidth();
        imgHeight = img.getHeight();
        pixImg = new Pixel[imgHeight][imgWidth];
        originalPixImg = new Pixel[imgHeight][imgWidth];
        tempImg = new Pixel[imgHeight][imgWidth];

        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                pixImg[y][x] = new Pixel(new Color(img.getRGB(x, y)));
                originalPixImg[y][x] = new Pixel(new Color(img.getRGB(x, y)));
                tempImg[y][x] = new Pixel(new Color(img.getRGB(x, y)));
            }
        }
    }

    boolean save() {
        int cnt = 0;
        boolean nameAvailable = false;
        List<String> nameList = Editor.getResults();
        String saveName;
        if (Editor.getName().contains(".jpg")) {
            saveName = Editor.getName().split(".jpg")[0];
        } else {
            saveName = Editor.getName().split(".png")[0];
        }
        while (!nameAvailable) {
            if (nameList.contains(saveName)) {
                saveName = saveName + Integer.toString(cnt);
            } else {
                nameAvailable = true;
            }
            cnt++;
        }
        try {
            ImageIO.write(convertToImage(pixImg),
                    "png", new File("PhotoEvolution/src/resources/" + saveName
                    + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void edit() {
        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                tempRed = 0;
                tempGreen = 0;
                tempBlue = 0;
                int cnt = 0;
                avgColor(y, x);
                cnt++;

                if (y > 0) {
                    avgColor(y - 1, x);
                    cnt++;
                    if (x > 0) {
                        avgColor(y - 1, x - 1);
                        cnt++;
                    }
                    if (x < imgWidth - 1) {
                        avgColor(y - 1, x + 1);
                        cnt++;
                    }
                }
                if (y < imgHeight - 1) {
                    avgColor(y + 1, x);
                    cnt++;

                    if (x > 0) {
                        avgColor(y + 1, x - 1);
                        cnt++;
                    }
                    if (x < imgWidth - 1) {
                        avgColor(y + 1, x + 1);
                        cnt++;
                    }
                }

                if (x > 0) {
                    avgColor(y, x - 1);
                    cnt++;
                }
                if (x < imgWidth - 1) {
                    avgColor(y, x + 1);
                    cnt++;
                }
                pixImg[y][x] = new Pixel(new Color(tempRed / cnt,
                        tempGreen / cnt, tempBlue / cnt));
            }
        }
        originalPixImg = pixImg;
    }

    public void edit(int bound) {
        for (Pixel[] pixy : pixImg) {
            for (Pixel pix : pixy) {
                pix.randomizer(bound);
            }
        }
    }

    private void avgColor(int y, int x) {
        tempRed += originalPixImg[y][x].getRed();
        tempGreen += originalPixImg[y][x].getGreen();
        tempBlue += originalPixImg[y][x].getBlue();
    }


    private BufferedImage convertToImage(Pixel[][] start) {
        BufferedImage newImg = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < imgHeight; y++) {
            for (int x = 0; x < imgWidth; x++) {
                newImg.setRGB(x, y, start[y][x].getColor().getRGB());
            }
        }
        return newImg;
    }

}
