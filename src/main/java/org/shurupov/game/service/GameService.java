package org.shurupov.game.service;

import org.shurupov.game.service.process.DrawService;
import org.shurupov.game.service.process.GraphicSystemService;
import org.shurupov.game.service.process.SkyService;
import org.shurupov.game.service.process.SpaceShipService;
import org.shurupov.game.service.subscribe.GameEventPublisher;
import org.shurupov.game.service.subscribe.GraphicSubscriber;
import org.shurupov.game.service.subscribe.SkySubscriber;
import org.shurupov.game.service.subscribe.SpaceshipSubscriber;

import static org.shurupov.game.service.process.GraphicSystemService.WHK;

public class GameService {

    public void run() {

        GameEventPublisher gameEventPublisher = new GameEventPublisher();

        GraphicSystemService graphicSystemService = new GraphicSystemService(gameEventPublisher::keyPressed);
        DrawService drawService = new DrawService(WHK);

        GraphicSubscriber graphicSubscriber = new GraphicSubscriber(graphicSystemService);
        SkySubscriber skySubscriber = new SkySubscriber(new SkyService(drawService));
        SpaceshipSubscriber spaceshipSubscriber = new SpaceshipSubscriber(new SpaceShipService(drawService));


        gameEventPublisher.addSubscriber(graphicSubscriber);
        gameEventPublisher.addSubscriber(skySubscriber);
        gameEventPublisher.addSubscriber(spaceshipSubscriber);

        try {

            gameEventPublisher.init();

            while (graphicSystemService.isConditionToContinue()) {
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
