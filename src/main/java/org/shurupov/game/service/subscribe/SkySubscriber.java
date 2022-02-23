package org.shurupov.game.service.subscribe;

import lombok.RequiredArgsConstructor;
import org.shurupov.game.domain.Sky;
import org.shurupov.game.service.process.SkyService;

import static org.shurupov.game.service.process.GraphicSystemService.WHK;

@RequiredArgsConstructor
public class SkySubscriber implements Subscriber {

    private Sky sky;
    private final SkyService skyService;

    @Override
    public void onInit() {
        //TODO Move this to properties file
        sky = skyService.createSky(10, 150,
            0.9f, 0.2f,
            0.008f, 0.0001f,
            WHK);
    }

    @Override
    public void onUpdate() {
        skyService.moveSky(sky);
    }

    @Override
    public void onRender() {
        skyService.drawSky(sky);
    }
}
