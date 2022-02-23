package org.shurupov.game.service.process;

import lombok.RequiredArgsConstructor;
import org.shurupov.game.domain.Point;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

@RequiredArgsConstructor
public class DrawService {

    private final float widthHeightRatio;

    public void setPenColor(float red, float green, float blue) {
        glColor3f(red, green, blue);
    }

    public void setPenColor(float red, float green, float blue, float alpha) {
        glColor4f(red, green, blue, alpha);
    }

    public void drawPoint(Point point) {
        glBegin(GL_POINTS);
        glVertex2f(point.getX(), point.getY());
        glEnd();
    }

    public void drawPolygon(List<Point> points) {
        glBegin(GL_POLYGON);
        for (Point point : points) {
            glVertex2f(point.getX(), point.getY() / widthHeightRatio);
        }
        glEnd();
    }
}
