package org.shurupov.game.service;

import org.shurupov.game.service.process.GraphicSystemService;
import org.shurupov.game.service.process.SkyService;
import org.shurupov.game.service.subscribe.GameEventPublisher;
import org.shurupov.game.service.subscribe.GraphicSubscriber;
import org.shurupov.game.service.subscribe.SkySubscriber;

public class GameService {

    public void run() {

        GraphicSystemService graphicSystemService = new GraphicSystemService();

        GraphicSubscriber graphicSubscriber = new GraphicSubscriber(graphicSystemService);
        SkySubscriber skySubscriber = new SkySubscriber(new SkyService());

        GameEventPublisher gameEventPublisher = new GameEventPublisher();

        gameEventPublisher.addSubscriber(graphicSubscriber);
        gameEventPublisher.addSubscriber(skySubscriber);

        try {

            gameEventPublisher.init();

            while (!graphicSystemService.isRequestedToClose()) {
                gameEventPublisher.beforeUpdate();
                gameEventPublisher.update();
                gameEventPublisher.render();
                gameEventPublisher.afterUpdate();
            }

            graphicSystemService.gracefulShutdown();

        } finally {
            graphicSystemService.forceShutdown();
        }

    }
}
