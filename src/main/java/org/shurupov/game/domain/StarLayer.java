package org.shurupov.game.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Data
@RequiredArgsConstructor
public class StarLayer {

    private static Random random = new Random();

    private final float lightness;
    private final float velocity;
    private final Point[] points;
}
