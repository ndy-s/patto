package io.github.ndys.patto.patterns.composite.example1_file_system.leaf;

import io.github.ndys.patto.patterns.composite.example1_file_system.component.FileSystemItem;

public class FileItem implements FileSystemItem {

    private final String name;
    private final int size;

    public FileItem(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "File " + name + " (" + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }

    
}

