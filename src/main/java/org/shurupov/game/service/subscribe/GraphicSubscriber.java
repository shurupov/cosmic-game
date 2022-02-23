package org.shurupov.game.service.subscribe;

import lombok.RequiredArgsConstructor;
import org.shurupov.game.service.process.GraphicSystemService;

@RequiredArgsConstructor
public class GraphicSubscriber implements Subscriber {

    private final GraphicSystemService graphicSystemService;

    @Override
    public void onInit() {
        graphicSystemService.init();
    }

    @Override
    public void onBeforeUpdate() {
        graphicSystemService.beforeUpdate();
    }

    @Override
    public void onAfterUpdate() {
        graphicSystemService.afterUpdate();
    }
}
