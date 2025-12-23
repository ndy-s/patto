package io.github.ndys.patto.patterns.proxy.example1_image_loader;

public class ImageProxy implements Image {

    private RealImage realImage;
    private final String fileName;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }

    
}

