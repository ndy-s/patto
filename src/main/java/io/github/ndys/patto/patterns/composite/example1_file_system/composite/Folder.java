package io.github.ndys.patto.patterns.composite.example1_file_system.composite;

import java.util.ArrayList;
import java.util.List;

import io.github.ndys.patto.patterns.composite.example1_file_system.component.FileSystemItem;

public class Folder implements FileSystemItem {

    private final String name;
    private final List<FileSystemItem> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    @Override
    public void print(String indent) {
        System.out.println(indent + "Folder " + name);
        for (FileSystemItem item : children) {
            item.print(indent + " ");
        }
    }

    @Override
    public int getSize() {
        int total = 0;
        for (FileSystemItem item : children) {
            total += item.getSize();
        }
        return total;
    }

    
}

