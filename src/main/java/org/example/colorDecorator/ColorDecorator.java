package org.example.colorDecorator;

public class ColorDecorator implements ITextDecorator {
    private String color;

    public ColorDecorator(String color) {
        this.color = color;
    }

    @Override
    public String decorate(String text) {
        return color + text + "\u001b[0m";
    }
}
