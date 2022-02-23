package org.shurupov.game.service.process;

import lombok.RequiredArgsConstructor;
import org.shurupov.game.domain.Point;
import org.shurupov.game.domain.Sky;
import org.shurupov.game.domain.StarLayer;

import java.util.Random;

@RequiredArgsConstructor
public class SkyService {

    private final Random random = new Random();

    private final DrawService drawService;

    public StarLayer createStarLayer(float lightness, float velocity, int count) {
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = new Point(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
        }
        return new StarLayer(lightness, velocity, points);
    }

    public Sky createSky(int layers, int layerSize,
                         float maxLightness, float minLightness,
                         float maxVelocity, float minVelocity,
                         float widthHeightRatio) {
        StarLayer[] starLayers = new StarLayer[layers];
        for (int i = 0; i < layers; i++) {
            float lightness = minLightness + (maxLightness - minLightness) / layers * i;
            float velocity = (minVelocity + (maxVelocity - minVelocity) / layers * i) / widthHeightRatio;
            starLayers[i] = createStarLayer(lightness, velocity, layerSize);
        }
        return new Sky(starLayers);
    }

    public void moveSky(Sky sky) {
        StarLayer[] starLayers = sky.getLayers();
        int starLayersCount = starLayers.length;
        for (int i = 0; i < starLayersCount; i++) {
            StarLayer layer = starLayers[i];
            Point[] points = layer.getPoints();
            int pointsCount = points.length;
            for (int j = 0; j < pointsCount; j++) {
                Point point = points[j];
                point.setX(point.getX() - layer.getVelocity());
                if (point.getX() < -1) {
                    point.setX( 1 + layer.getVelocity() * random.nextFloat() );
                    point.setY( random.nextFloat() * 2 - 1 );
                }
            }
        }
    }

    public void drawSky(Sky sky) {
        StarLayer[] starLayers = sky.getLayers();
        int starLayersCount = starLayers.length;
        for (int i = 0; i < starLayersCount; i++) {
            StarLayer layer = starLayers[i];
            drawService.setPenColor(layer.getLightness(), layer.getLightness(), layer.getLightness());

            for (int j = 0; j < layer.getPoints().length; j++) {
                drawService.drawPoint(layer.getPoints()[j]);
            }
        }
    }
}
