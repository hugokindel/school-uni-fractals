package com.ustudents.fgen.generators;

import com.ustudents.fgen.handlers.calculation.CalculationHandler;
import com.ustudents.fgen.handlers.image.ImageHandler;
import com.ustudents.fgen.handlers.image.SimpleImageHandler;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ListImageGenerator extends ListGenerator {
    BufferedImage[] bufferedImages;
    List<ImageHandler> imageHandlers = new ArrayList<>();

    @Override
    public void addCalculationHandler(CalculationHandler calculationHandler) {
        calculationHandlers.add(calculationHandler);
        imageHandlers.add(new SimpleImageHandler());
    }

    public void addCalculationHandler(CalculationHandler calculationHandler, ImageHandler imageHandler) {
        calculationHandlers.add(calculationHandler);
        imageHandlers.add(imageHandler);
    }

    @Override
    public void generate(int width, int height, double offsetX, double offsetY) {
        bufferedImages = new BufferedImage[calculationHandlers.size()];

        for (int i = 0; i < calculationHandlers.size(); i++) {
            SingleImageGenerator generator = new SingleImageGenerator(calculationHandlers.get(i), imageHandlers.get(i));
            generator.generate(width, height, offsetX, offsetY);
            bufferedImages[i] = generator.bufferedImage;
        }
    }
}