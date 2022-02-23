package org.shurupov.game.service.process;

import lombok.RequiredArgsConstructor;
import org.shurupov.game.domain.Point;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.shurupov.game.service.process.GraphicSystemService.WHK;

@RequiredArgsConstructor
public class SpaceShipService {

    private final DrawService drawService;

    private float startX = -0.9f;
    private float startY = 0.1f / WHK;


    public void drawShip() {
        drawService.setPenColor(0.6f, 0.6f, 0.6f);

        List<Point> points = new ArrayList<>();
        points.add(new Point(startX, startY));
        points.add(new Point(startX + 0.2f, startY));
        points.add(new Point(startX + 0.3f, startY - 0.05f));
        points.add(new Point(startX + 0.3f, startY - 0.2f));
        points.add(new Point(startX, startY - 0.2f));

        drawService.drawPolygon(points);
    }

    public void update() {

    }

    public void keyPressed(int key) {
        if (key == GLFW_KEY_UP) {
            startY += 0.01f * WHK;
        }
        if (key == GLFW_KEY_DOWN) {
            startY -= 0.01f * WHK;
        }
        if (key == GLFW_KEY_RIGHT) {
            startX += 0.01f;
        }
        if (key == GLFW_KEY_LEFT) {
            startX -= 0.01f;
        }
    }
}
