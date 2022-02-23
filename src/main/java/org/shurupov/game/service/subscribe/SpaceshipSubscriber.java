package org.shurupov.game.service.subscribe;

import lombok.RequiredArgsConstructor;
import org.shurupov.game.service.process.SpaceShipService;

@RequiredArgsConstructor
public class SpaceshipSubscriber implements Subscriber {

    private final SpaceShipService spaceShipService;

    @Override
    public void onRender() {
        spaceShipService.drawShip();
    }

    @Override
    public void onUpdate() {
        spaceShipService.update();
    }

    @Override
    public void onKeyPressed(int key) {
        spaceShipService.keyPressed(key);
    }
}
